/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspFactory;
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
     * The collection or iterator of items to Sort.
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
    private Comparator<?> _comparator;

    /**
     * When comparing objects, if field is set, then instead of thing the object itself, then the object's
     * field is compared. For example, if we are sorting File objects, and field == "name", then
     * the method "getName" will be called, and that is used to compare the. This is done using
     * a generated el expression : ${OBJECT.FIELD}, where OBJECT is an item within the _items collection, and
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

    public void setComparator( Comparator<?> value )
    {
        _comparator = value;
    }

    public Comparator<?> getComparator()
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

    @SuppressWarnings("unchecked")
    public int doEndTag() throws JspException
    {

        Object[] array = null;

        if (getItems() instanceof Map) {
            array = TagUtil.array(((Map<?, ?>) getItems()).keySet(), "items");
        } else {
            array = TagUtil.array(getItems(), "items");
        }

        Comparator<Object> comparator = (Comparator<Object>) getOverallComparator();
        Arrays.sort(array, comparator);

        // Place the Sorted collection into the request scope
        pageContext.getRequest().setAttribute(getVar(), array);

        return EVAL_PAGE;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Comparator<?> getOverallComparator()
    {
        Comparator<?> comparator;

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

    private class NaturalComparator<T extends Comparable<T>> implements Comparator<T>
    {
        public int compare( T a, T b )
        {
            if (a == null) {
                if (b == null) {
                    return 0;
                } else {
                    return 1; // Is this right?
                }
            } else {
                return a.compareTo(b);
            }

        }
    }

    private class ReverseComparator<T> implements Comparator<T>
    {
        private Comparator<T> _comparator;

        public ReverseComparator( Comparator<T> comparator )
        {
            _comparator = comparator;
        }

        public int compare( T a, T b )
        {
            return -_comparator.compare(a, b);
        }
    }

    private class FieldComparator<T> implements Comparator<T>
    {
        private String _expressionA = "${__sortItemA." + getField() + "}";
        private String _expressionB = "${__sortItemB." + getField() + "}";

        private Comparator<T> _comparator;

        private FieldComparator( Comparator<T> comparator )
        {
            _comparator = comparator;
        }

        @SuppressWarnings("unchecked")
        @Override
        public int compare( T a, T b )
        {
            pageContext.setAttribute("__sortItemA", a);
            pageContext.setAttribute("__sortItemB", b);
            try {

                ExpressionFactory ef = JspFactory.getDefaultFactory().getJspApplicationContext(pageContext.getServletContext())
                    .getExpressionFactory();
                ELContext elContext = pageContext.getELContext();
                ValueExpression exprA = ef.createValueExpression(pageContext.getELContext(), _expressionA, Object.class);
                ValueExpression exprB = ef.createValueExpression(pageContext.getELContext(), _expressionB, Object.class);
                T objectA = (T) exprA.getValue(elContext);
                T objectB = (T) exprB.getValue(elContext);

                return _comparator.compare(objectA, objectB);

            } catch (ELException e) {

                System.err.println("Error evaluating sort compare");
                e.printStackTrace();
                return 0;

            } finally {
                pageContext.removeAttribute("__sortItemA");
                pageContext.removeAttribute("__sortItemB");
            }

        }

    }

}
