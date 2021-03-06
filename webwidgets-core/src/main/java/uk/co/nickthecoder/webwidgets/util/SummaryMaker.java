/*
This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package uk.co.nickthecoder.webwidgets.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.co.nickthecoder.webwidgets.filter.Filter;

/**
 * Creates a short summary of some text, with certain words highlighted. This is
 * used to display a summary for search results.
 *
 * Use getSections() to iterate over each SummarySection, and call each
 * SummarySection's getText method to get the text. Highlight in bold where
 * summarySections.isMatched == true.
 * 
 * The final output will be something like this :
 * 
 * ... there's some words before <b>keyword</b> then some more words ... and yet
 * more words <b>keyword2</b> then some more words ...
 * 
 * In that example, wordsBeforeMatch and wordsAfterMatch are both 4.
 */
public class SummaryMaker
{

    protected static Logger _logger = LogManager.getLogger(SummaryMaker.class);

    /**
     * Stop after n words have been matched.
     */
    public int maxMatchedSections = 5;

    /**
     * The number of words to output before a matched word. If there are more
     * than <code>wordsBeforeMatch + wordsAfterMath</code> between matched
     * words, then an ellipsis replaces the words that are excluded.
     */
    public int wordsBeforeMatch = 10;

    /**
     * The number of words to output after a matched word
     */
    public int wordsAfterMatch = 10;

    /**
     * The string used in place of missed words. The default is an ellipsis ….
     */
    public String separator = "…";

    /**
     * The full text that needs to be summarised
     */
    private String _content;

    /**
     * The keywords that need to be highlighted. A collection of String objects
     */
    private Filter<String> _keywordFilter;

    private List<SummarySection> _sections;

    public SummaryMaker(String content, Filter<String> keywordFilter)
    {
        _content = content;
        _keywordFilter = keywordFilter;
    }

    public String getContent()
    {
        return _content;
    }

    public Iterator<SummarySection> getSections()
    {
        if (_sections == null) {
            createSummarySections();
        }
        return _sections.iterator();
    }

    private void createSummarySections()
    {
        _sections = new ArrayList<SummarySection>();
        List<String> unMatchedWords = new ArrayList<String>();

        String[] words = _content.split("\\s");
        int matchCount = 0;

        // Build up a list of SummarySection objects. These are either a single
        // matched word,
        // or a set of 1 or more unmatched words.
        for (String word : words) {
            if (_keywordFilter.accept(word)) {
                matchCount++;
                if (unMatchedWords.size() > 0) {
                    _sections.add(new UnmatchedSummarySection(unMatchedWords));
                    unMatchedWords = new ArrayList<String>();
                }
                if (matchCount >= maxMatchedSections) {
                    break;
                }
                _sections.add(new MatchedSummarySection(word));
            } else {
                unMatchedWords.add(word);
            }
        }
        if (unMatchedWords.size() > 0) {
            _sections.add(new UnmatchedSummarySection(unMatchedWords));
        }

        if (_sections.size() > 0) {
            SummarySection firstSection = _sections.get(0);
            SummarySection lastSection = _sections.get(_sections.size() - 1);
            for (SummarySection section : _sections) {
                section.compress(section == firstSection, section == lastSection);
            }
        }
    }

    public interface SummarySection
    {
        public boolean isMatched();

        public String getText();

        public void compress(boolean isFirst, boolean isLast);
    }

    public class MatchedSummarySection implements SummarySection
    {
        private String _text;

        public MatchedSummarySection(String word)
        {
            this._text = word;
        }

        public boolean isMatched()
        {
            return true;
        }

        public String getText()
        {
            return this._text;
        }

        public void compress(boolean isFirst, boolean isLast)
        {
            // Do nothing
        }
    }

    public class UnmatchedSummarySection implements SummarySection
    {
        private List<String> _words;

        private String _text;

        public UnmatchedSummarySection(List<String> words)
        {
            _words = words;
        }

        public boolean isMatched()
        {
            return false;
        }

        public String getText()
        {
            if (_text == null) {
                compress(false, false);
            }
            return _text;
        }

        /**
         * Build up the text summary. In general the output will be the first n
         * words, followed by a separator, followed by the last m words.
         * 
         * n = wordsAfterMatch, m = wordsBeforeMatch
         * 
         * If word count < n + m, then all the words are added, and there is no
         * separator.
         * 
         * The first summary section ignores the first n words. The last summary
         * section ignores the last m words.
         */
        public void compress(boolean isFirst, boolean isLast)
        {
            StringBuffer buffer = new StringBuffer();

            int firstEnd = 0;
            int secondStart = 0;
            boolean addSep = false;

            int max = isFirst ? wordsBeforeMatch : (isLast ? wordsAfterMatch : wordsBeforeMatch + wordsAfterMatch + 2);
            if (_words.size() > max) {

                addSep = true;

                if (isFirst && isLast) {
                    // No keywords, so output more words than usual at the
                    // beginning, and
                    // non after the "...".
                    firstEnd = wordsAfterMatch + wordsBeforeMatch;
                    secondStart = _words.size();
                    // Pretend there is a keyword BEFORE us, so that the first
                    // part IS used.
                    isFirst = false;
                } else {
                    firstEnd = wordsAfterMatch;
                    secondStart = _words.size() - wordsBeforeMatch;
                }
            }

            // Check bounds. I think the only time this is needed is when
            // isFirst == isLast == true, and
            // the number wordCount > wordsAfterMatch and wordCount <
            // wordsAfterMatch + wordsBeforeMatch.
            if ((firstEnd > _words.size()) || (secondStart < 0)) {
                firstEnd = 0;
                secondStart = 0;
                addSep = false;
            }

            // Useful for debugging.
            // buffer.append( " ((0.." + firstEnd +") & (" + secondStart + ".."
            // + _words.size() + ")) ");

            if (!isFirst) {
                for (int i = 0; i < firstEnd; i++) {
                    buffer.append(_words.get(i));
                    buffer.append(" ");
                }
            }

            if (addSep) {
                buffer.append(separator);
            }

            if ((!addSep) || (!isLast)) {
                for (int i = secondStart; i < _words.size(); i++) {
                    buffer.append(" ");
                    buffer.append(_words.get(i));
                }
            }
            _text = buffer.toString();
        }
    }

}
