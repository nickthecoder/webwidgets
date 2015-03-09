/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2005-04-19

----------------------------------------------------------------------

 History
 2005-04-19 : nick : Initial Development

----------------------------------------------------------------------

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

----------------------------------------------------------------------
*/

package uk.co.nickthecoder.webwidgets.tags;


import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
/**
  Places a piece of text in speech marks.
*/

public class QuoteTag
  extends BodyTagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  public static final String TYPE_NORMAL = "normal";

  public static final String TYPE_FANCY = "fancy";

  public static final String DEFAULT_STYLE_CLASS = "ww_quote";

  // -------------------- [[Attributes]] --------------------

  /**
    The type of quotes used.
  */
  private String _type;

  /**
    The css class
  */
  private String _styleClass;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public QuoteTag()
  {
    super();

    initialise();
  }

  private void initialise()
  {
    _type = TYPE_NORMAL;
    _styleClass = DEFAULT_STYLE_CLASS;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------

  /**
    Get method for attribute {@link #_type}.
    The type of quotes used.
  */
  public String getType()
  {
    return _type;
  }

  /**
    Set method for attribute {@link #_type}.
    The type of quotes used.
  */
  public void setType( String value )
  {
    _type = value;
  }

  /**
    Get method for attribute {@link #_styleClass}.
    The css class
  */
  public String getStyleClass()
  {
    return _styleClass;
  }

  /**
    Set method for attribute {@link #_styleClass}.
    The css class
  */
  public void setStyleClass( String value )
  {
    _styleClass = value;
  }


  private String fancyQuote( String text )
  {
    text = text.trim();
    if (text.length() == 0) {
      // System.out.println( "Zero length" );
      return "";
    }

    // LOOK FOR THE FIRST WORD...

    int firstStart = 0;
    int firstEnd = 0;

    // If the line begins with a html tag, then look for its end tag.
    while ( (firstStart < text.length()) && (text.charAt( firstStart ) == '<' )) {

      // System.out.println( "Found < at " + firstStart );

      firstEnd = 0;
      for ( int j = firstStart + 1; j < text.length(); j ++ ) {
        if ( text.charAt( j ) == '>' ) {
          firstEnd = j;
          // System.out.println( "Found > at " + firstEnd );
          break;
        }
      }
      if ( firstEnd == 0 ) {
        // The matching ">" was not found, so abort.
        // System.out.println( "No matching >" );
        return simpleFancyQuote( text );
      }

      firstStart = firstEnd + 1;
    }

    // Look for the first whitespace or "<"
    for ( int i = firstStart + 1; i < text.length(); i ++ ) {
      if ( ( Character.isWhitespace( text.charAt( i ) ) ) || ( text.charAt( i ) == '<' ) ) {
        firstEnd = i;
        // System.out.println( "Found firstEnd at " + firstEnd );
        break;
      }
    }

    // LOOK FOR THE LAST WORD...

    int lastEnd = text.length() - 1;
    int lastStart = 0;

    // If the line ends with a html tag, then look for its beginning.
    while ( (lastEnd >= firstEnd) && (text.charAt( lastEnd ) == '>' )) {

      // System.out.println( "Found > at " + lastEnd );

      lastStart = 0;
      for ( int j = lastEnd - 1; j >= firstEnd; j -- ) {
        if ( text.charAt( j ) == '<' ) {
          lastStart = j;
          // System.out.println( "Found < at " + lastStart );
          break;
        }
      }
      if ( lastStart == 0 ) {
        // The matching "<" was not found, so abort.
        // System.out.println( "No matching <" );
        return simpleFancyQuote( text );
      }

      lastEnd = lastStart - 1;
    }

    // Look for the first whitespace or ">"
    for ( int i = lastEnd - 1; i > firstEnd; i -- ) {
      if ( ( Character.isWhitespace( text.charAt( i ) ) ) || ( text.charAt( i ) == '>' ) ) {
        lastStart = i + 1;
        // System.out.println( "Found lastStart at " + lastStart );
        break;
      }
    }


    // ADD THE TAGS IN THE CORRECT PLACE...

    if ( (firstEnd == 0) || (lastEnd == 0) ) {

      // System.out.println( "Couldn't do it.");
      return simpleFancyQuote( text );

    } else {

      // System.out.println( "firstStart = " + firstStart + " firstEnd = " + firstEnd );
      // System.out.println( "lastStart = " + lastStart + " lastEnd = " + lastEnd );

      return text.substring( 0, firstStart ) +
        "<span class=\"ww_beginQuote\">" +
        text.substring( firstStart, firstEnd ) +
        "</span>" +
        text.substring( firstEnd, lastStart ) +
        "<span class=\"ww_endQuote\">" +
        text.substring( lastStart, lastEnd + 1) +
        "</span>" +
        text.substring( lastEnd + 1);
    }
  }

  public String simpleFancyQuote( String text )
  {
    return "<span class=\"ww_beginQuote\"></span>" +
        text +
        "<span class=\"ww_endQuote\"></span>";
  }


  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.println( "<span class=\"" + getStyleClass() + "\">" );
      if ( getType().equals( TYPE_NORMAL ) ) {
        out.print( "&#8220;" );
        return EVAL_BODY_INCLUDE;
      } else {
        return EVAL_BODY_BUFFERED;
      }


    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }

  public int doAfterBody()
    throws JspException
  {
    try {

      if ( TYPE_FANCY.equals( getType() ) ) {

        String text = getBodyContent().getString();
        JspWriter out = getPreviousOut();

        // System.out.println( "To quote: " + text );
        // System.out.println( "Quoted: " + fancyQuote( text ) );
        out.println( fancyQuote( text ) );

      }

      return SKIP_BODY;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  public int doEndTag()
    throws JspException
  {
    try {
      JspWriter out = pageContext.getOut();

      if ( getType().equals( TYPE_NORMAL ) ) {
        out.print( "&#8221;" );
      }
      out.print( "</span>" );


    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

    return EVAL_PAGE;
  }


  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class QuoteTag ----------


