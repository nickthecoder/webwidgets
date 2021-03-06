/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Converts a collection of objects into a 2D structure, portioning them into
 * groups. You can either specify the number of portions you want, or specify
 * the maximum size of each portion.
 */

public class PortionTag extends TagSupport
{

    private static final long serialVersionUID = 3798319514975256014L;

    public static final String DEFAULT_VAR_NAME = "portions";

    public static final String PAD_NOT_EMPTY = "notEmpty";
    public static final String PAD_NORMAL = "normal";
    public static final String PAD_ALWAYS = "always";
    public static final String PAD_NEVER = "never";

    /**
     * Use the alternate way of ordering the items to the portions.
     */
    private boolean _transpose;

    /**
     * The number of portions
     */
    private int _portions;

    /**
     * The size of each portion
     */
    private int _portionSize;

    /**
     * The collection or iterator, whose items are to be grouped into individual
     * portions.
     */
    private Object _items;

    /**
     * The same data as _items, but as type Collection
     */
    private Collection<?> _collection;

    /**
     * The name of the variable, which will be an iterator of portions.
     * Each portion is itself an iterator. i.e. _var is an iterator of iterators
     * or items from the original collection.
     */
    private String _var;

    /**
     * Determines if addition padding is added, when the number of items
     * does not fit completely into a rectangular grid. Defaults to false.
     * Note. both pad and padding are needed, so that null can be specified
     * as padding.
     */
    private String _pad;

    /**
     * Determines the contents of the padding. The default is null. Setting
     * to anything other than null will cause the padding to be used, regardless
     * of the value of the pad attribute.
     */
    private Object _padding;

    public PortionTag()
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
        _transpose = false;
        _items = null;
        _collection = null;
        _portions = 0;
        _portionSize = 0;
        _var = DEFAULT_VAR_NAME;
        _pad = PAD_NEVER;
        _padding = null;

    }

    /**
     * Get method for attribute {@link #_padding}.
     */
    public Object getPadding()
    {
        return _padding;
    }

    /**
     * Set method for attribute {@link #_padding}.
     */
    public void setPadding( Object value )
    {
        _padding = value;
    }

    /**
     * Get method for attribute {@link #_pad}.
     * Determines the contents of the padding. The default is never.
     */
    public String getPad()
    {
        return _pad;
    }

    /**
     * Set method for attribute {@link #_pad}.
     * Determines the contents of the padding. The default is null. Setting
     */
    public void setPad( String value )
    {
        _pad = value;
    }

    /**
     * Get method for attribute {@link #_transpose}.
     */
    public boolean getTranspose()
    {
        return _transpose;
    }

    /**
     * Set method for attribute {@link #_transpose}.
     */
    public void setTranspose( boolean value )
    {
        _transpose = value;
    }

    /**
     * Get method for attribute {@link #_portions}.
     * The number of portions in the grid.
     */
    public int getPortions()
    {
        return _portions;
    }

    /**
     * Set method for attribute {@link #_portions}.
     * The number of portions in the grid.
     */
    public void setPortions( int value )
    {
        _portions = value;
    }

    /**
     * Get method for attribute {@link #_portions}.
     * The number of portions in the grid.
     */
    public int getPortionSize()
    {
        return _portionSize;
    }

    /**
     * Set method for attribute {@link #_portions}.
     * The number of portions in the grid.
     */
    public void setPortionSize( int value )
    {
        _portionSize = value;
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
     * The Iterator or Collection, which is to be converted into a 2D structure.
     * Note, it is quicker if you pass a Collection, rather than an Iterator.
     */
    public void setItems( Object value ) throws JspException
    {
        _collection = TagUtil.collection(value, "items");
        _items = value;
    }

    private Iterator<?> getIterator()
    {
        return _collection.iterator();
    }

    private int getItemCount()
    {
        return _collection.size();
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
     *       <ww:portion var="rows" items="${aIteratorName}" portionSize="3">
     *         <c:forEach var="row" items="${rows}">
     *           <c:forEach var="cell" items="row">
     *             <c:out value="${cell}"
     *           </c:forEach>
     *         </c:forEach>
     *       </ww:portion>
     * </pre>
     */
    public int doStartTag() throws JspException
    {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        int portions;
        int portionSize;

        if (getPortions() <= 0) {
            if (getPortionSize() <= 0) {
                throw new JspException("Must specify portions or portionSize");
            }

            portionSize = getPortionSize();
            portions = (getItemCount() - 1) / portionSize + 1;

        } else {

            portions = getPortions();
            portionSize = (getItemCount() - 1) / portions + 1;
        }

        List<List<Object>> portionArray;

        if (getItemCount() == 0) {
            if ((PAD_NEVER.equals(getPad())) || (PAD_NOT_EMPTY.equals(getPad()))) {

                return SKIP_BODY;

            } else if (PAD_NORMAL.equals(getPad())) {

                // No items, but as this is probably going to be used within a html table,
                // the html spec doesn't allow a table without any rows, so return a single
                // piece of padding.
                portionArray = singleRow(1);

            } else {
                // Always pad, so return a complete row of padding.
                portionArray = singleRow(portionSize);
            }
        } else {

            portionArray = arrange(portionSize, portions);

        }

        request.setAttribute(getVar(), portionArray);

        // Evaluate the body of this tag
        return EVAL_BODY_INCLUDE;

    }

    protected List<List<Object>> singleRow( int portionSize )
    {
        List<List<Object>> portionArray = new ArrayList<List<Object>>(1);
        List<Object> row = new ArrayList<Object>(portionSize);

        for (int i = 0; i < portionSize; i++) {
            row.add(_padding);
        }
        portionArray.add(row);

        return portionArray;
    }

    protected List<List<Object>> arrange( int portionSize, int portions )
    {
        List<List<Object>> portionArray = new ArrayList<List<Object>>(portions);
        for (int i = 0; i < portions; i++) {
            portionArray.add(i, new LinkedList<Object>());
        }

        if (_transpose) {
            Iterator<?> i = getIterator();
            for (int x = 0; x < portionSize; x++) {
                for (int y = 0; y < portions; y++) {
                    if (i.hasNext()) {
                        portionArray.get(y).add(i.next());
                    } else {
                        if ((!PAD_NEVER.equals(getPad())) || (getPadding() != null)) {
                            if ((!PAD_NORMAL.equals(getPad())) || (portions > 1)) {
                                portionArray.get(y).add(getPadding());
                            }
                        }
                    }
                }
            }
        } else {
            Iterator<?> i = getIterator();
            for (int y = 0; y < portions; y++) {
                for (int x = 0; x < portionSize; x++) {
                    if (i.hasNext()) {
                        portionArray.get(y).add(i.next());
                    } else {
                        if ((!PAD_NEVER.equals(getPad())) || (getPadding() != null)) {
                            if ((!PAD_NORMAL.equals(getPad())) || (portions > 1)) {
                                portionArray.get(y).add(getPadding());
                            }
                        }
                    }
                }
            }
        }

        return portionArray;
    }

    public int doEndTag()
    {
        _items = null;
        _collection = null;
        return EVAL_PAGE;
    }

    public void release()
    {
        super.release();
        initialise();
    }

}
