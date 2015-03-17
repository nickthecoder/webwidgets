/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspFactory;
import javax.el.ELException;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Allows a collection of objects to be grouped by their first letter.
 * Typically used to create a mini-contents list, allowing the user to click
 * on "D" to jump to the items beginning with "D".
 */

public class GroupByInitialTag extends TagSupport
{

    private static final long serialVersionUID = -7702614003539678256L;

    public static final Character EMPTY_CHARACTER = new Character(' ');

    private Object _items;

    /**
     * The name of the variable, which will define each group.
     */
    private String _var;

    private String _expression;

    private Object _currentItem;

    public GroupByInitialTag()
    {
        super();
        initialise();
    }

    /**
     * Called by the constructor, and by release to set each of the tags
     * properties to their default values.
     */
    private void initialise()
    {
        _items = null;
        _var = "group";
        _expression = null;
    }

    /**
     * Get method for attribute {@link #_items}.
     * The iterator, which is to be converted into a 2D structure
     */
    public Object getItems()
    {
        return _items;
    }

    /**
     * Set method for attribute {@link #_items}.
     * The iterator, which is to be converted into a 2D structure
     */
    public void setItems( Object value )
    {
        _items = value;
    }

    /**
     * Get method for attribute {@link #_var}.
     * The name of the variable, which will define each row.
     */
    public String getVar()
    {
        return _var;
    }

    /**
     * Set method for attribute {@link #_var}.
     * The name of the variable, which will define each row.
     */
    public void setVar( String value )
    {
        _var = value;
    }

    public void setExpression( String expression )
    {
        _expression = expression;
    }

    public String getExpression()
    {
        return _expression;
    }

    /**
     * Creates an iterator, where each item is itself an iterator.
     * Each item of the inner iterator is one of the objects from the
     * iterator passed into the tag using the <code>items</code> attribute.
     * The new iterator is placed into the request scope, with a name from
     * the <code>var</code> attribute.
     * 
     * Example:
     * 
     * <pre>
     *       <app:groupByFirstLetter var="letters" items="${aIteratorName}">
     *         <c:forEach var="letter" items="${letters.items}">
     *           <c:out value="${letter}"/>
     *           <c:forEach var="cell" items="letter">
     *             <c:out value="${cell}"
     *           </c:forEach>
     *         </c:forEach>
     *       </app:groupByLetter>
     * </pre>
     */
    public int doStartTag() throws JspException
    {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        TreeMap<Character, LetterEntry> letterMap = new TreeMap<Character, LetterEntry>();
        for (char letter1 = 'A'; letter1 <= 'Z'; letter1++) {
            LetterEntry letterEntry = new LetterEntry(letter1);
            letterMap.put(new Character(letter1), letterEntry);
        }

        for (Iterator<?> i = TagUtil.iterator(getItems(), "items"); i.hasNext();) {
            Object item = i.next();

            LetterEntry letterEntry;
            Character letter = getLetter(item);
            if (!letterMap.containsKey(letter)) {
                letterEntry = new LetterEntry(letter);
                letterMap.put(letter, letterEntry);
            } else {
                letterEntry = (LetterEntry) letterMap.get(letter);
            }
            letterEntry.add(item);
        }

        request.setAttribute(getVar(), letterMap.values());

        // Evaluate the body of this tag
        return EVAL_BODY_INCLUDE;

    }

    /**
     * Used by getLetter( Object item ), this always evaluates to the
     * currently processed item in the users iterator.
     */
    public Object resolveVariable( String name )
    {
        return _currentItem;
    }

    private Object resolveExpression( Object item ) throws JspException
    {
        if (getExpression() == null) {
            return item;
        }

        String expression = "${item." + getExpression() + "}";
        try {

            pageContext.setAttribute("__groupByItem", item);
            ExpressionFactory ef = JspFactory.getDefaultFactory().getJspApplicationContext(pageContext.getServletContext())
                .getExpressionFactory();
            ELContext elContext = pageContext.getELContext();
            ValueExpression expr = ef.createValueExpression(pageContext.getELContext(), _expression, Boolean.class);

            return expr.getValue(elContext);

        } catch (ELException e) {
            throw new JspException("Failed to evalate : " + expression);
        }
    }

    private Character getLetter( Object item ) throws JspException
    {
        Object result = resolveExpression(item);

        if (result == null) {
            return EMPTY_CHARACTER;
        } else {

            String string = result.toString();

            if ((string == null) || (string.equals(""))) {
                return EMPTY_CHARACTER;
            } else {
                return new Character(Character.toUpperCase(string.charAt(0)));
            }
        }

    }

    public int doEndTag()
    {
        return EVAL_PAGE;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    public class LetterEntry
    {
        private Character _letter;

        private List<Object> _items;

        public LetterEntry( Character letter )
        {
            _letter = letter;
            _items = new LinkedList<Object>();
        }

        public LetterEntry( char letter )
        {
            this(new Character(letter));
        }

        public Character getLetter()
        {
            return _letter;
        }

        public Collection<Object> getItems()
        {
            return _items;
        }

        public Iterator<Object> iterator()
        {
            return _items.iterator();
        }

        public boolean isEmpty()
        {
            return _items.isEmpty();
        }

        public int getCount()
        {
            return _items.size();
        }

        public void add( Object item )
        {
            _items.add(item);
        }

        public String toString()
        {
            return _letter.toString();
        }
    }

}
