/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.VariableResolver;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Lets the user navigate from one page to another, often used for search results.
 * This tag can work in two ways. Either you give it the set of items, and the number
 * of items per page, or you give it the total number of pages.
 */

public class SortTag extends TagSupport
{

    private static final long serialVersionUID = 4730792300525642721L;

    /**
     * The collection of iterator of items to Sort.
     */
    private Object _items;

    /**
     * The name of the variable to store the Sorted collection.
     */
    private String _var;

    /**
     * The comparator used to Sort the items in the collection. If null,
     * then the natural Sorting of the objects is used.
     */
    private Comparator _comparator;

    /**
     * When comparing objects, if field is set, then instead of thing the object itself, then the object's
     * field is compared. For example, if we are sorting File objects, and field == "name", then
     * the method "getName" will be called, and that is used to compare the. This is done using
     * a generated el expression : ${OBJECT.FIELD}, where OBJECT is an item with the _items collection, and
     * FIELD is the value of _field.
     */
    private String _field;

    private boolean _reverse;

    public SortTag()
    {
        super();
        initialise();
    }

    private void initialise()
    {
        _items = null;
        _comparator = null;
        _var = null;
        _reverse = false;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    public void setVar( String value )
    {
        _var = value;
    }

    public String getVar()
    {
        return _var;
    }

    public void setItems( Object value )
    {
        _items = value;
    }

    public Object getItems()
    {
        return _items;
    }

    public void setComparator( Comparator value )
    {
        _comparator = value;
    }

    public Comparator getComparator()
    {
        return _comparator;
    }

    public boolean getReverse()
    {
        return _reverse;
    }

    public void setReverse( boolean value )
    {
        _reverse = value;
    }

    public void setField( String value )
    {
        _field = value;
    }

    public String getField()
    {
        return _field;
    }

    public int doEndTag() throws JspException
    {

        Object[] array = null;

        if (getItems() instanceof Map) {
            array = TagUtil.array(((Map) getItems()).keySet(), "items");
        } else {
            array = TagUtil.array(getItems(), "items");
        }

        Arrays.sort(array, getOverallComparator());

        // Place the Sorted collection into the request scope
        pageContext.getRequest().setAttribute(getVar(), array);

        return EVAL_PAGE;
    }

    private Comparator getOverallComparator()
    {
        Comparator comparator;

        if (getField() == null) {

            if (getComparator() == null) {
                comparator = new NaturalComparator();
            } else {
                comparator = getComparator();
            }
        } else {

            comparator = new FieldComparator((getComparator() == null) ? new NaturalComparator() : getComparator());

        }

        if (getReverse()) {
            return new ReverseComparator(comparator);
        } else {
            return comparator;
        }
    }

    private class NaturalComparator implements Comparator
    {
        public int compare( Object a, Object b )
        {
            if (a == null) {
                if (b == null) {
                    return 0;
                } else {
                    return 1; // Is this right?
                }
            } else {
                return ((Comparable) a).compareTo(b);
            }

        }
    }

    private class ReverseComparator implements Comparator
    {
        private Comparator _comparator;

        public ReverseComparator( Comparator comparator )
        {
            _comparator = comparator;
        }

        public int compare( Object a, Object b )
        {
            return -_comparator.compare(a, b);
        }
    }

    private class FieldComparator implements Comparator, VariableResolver
    {
        private String _expressionA = "${itemA." + getField() + "}";
        private String _expressionB = "${itemB." + getField() + "}";

        private Object _currentItemA;
        private Object _currentItemB;

        private FieldComparator( Comparator comparator )
        {
            _comparator = comparator;
        }

        public int compare( Object a, Object b )
        {
            _currentItemA = a;
            _currentItemB = b;

            try {
                Object objectA = pageContext.getExpressionEvaluator().evaluate(_expressionA, Object.class, this, null);
                Object objectB = pageContext.getExpressionEvaluator().evaluate(_expressionB, Object.class, this, null);

                return _comparator.compare(objectA, objectB);

            } catch (ELException e) {
                System.err.println("Error evaluating sort compare");
                e.printStackTrace();
                return 0;
            }

        }

        public Object resolveVariable( String name ) throws ELException
        {
            if (name.equals("itemA")) {
                return _currentItemA;
            } else if (name.equals("itemB")) {
                return _currentItemB;
            } else {
                return pageContext.getVariableResolver().resolveVariable(name);
            }
        }
    }

}
