/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */
package uk.co.nickthecoder.webwidgets.tags;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.servlet.ServletContext;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.FilteredIterator;
import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Filters a collection of objects based upon an EL expression.
 * Note. Functions are not supported as part of the test expression.
 * If you try to use a function, it throws the following exception :
 * 
 * javax.el.ELException: Expression uses functions, but no FunctionMapper was provided
 * 
 * I have no idea why, or how to fix it, despite spending quite some time on it.
 * I suspect it is a bug within the verion of JSP I'm using, but cannot prove it.
 */

public class FilterTag extends TagSupport
{

    private static final long serialVersionUID = -4090957767104464663L;

    public static final String DEFAULT_ITEM_NAME = "item";

    private Object _items;

    /**
     * The variable to hold the iterator of filtered items.
     */
    private String _var;

    /**
     * The el expression which should evaluate to true or false.
     */
    private String _test;
    
    private String _itemName;

    private String _resultType;

    public FilterTag()
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
        _itemName = DEFAULT_ITEM_NAME;
        _var = null;
        _test = null;
        _resultType = "iterator";
    }

    public void release()
    {
        super.release();
        initialise();
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

    public void setResultType( String value )
    {
        _resultType = value;
    }

    public String getResultType()
    {
        return _resultType;
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

    public void setTest( String value )
    {
        _test = value;
    }

    public String getTest()
    {
        return _test;
    }

    public void setItemName( String value )
    {
        _itemName = value;
    }

    public String getItemName()
    {
        return _itemName;
    }

    public int doEndTag() throws JspException
    {
        Iterator<Object> unfiltered = TagUtil.iterator(getItems(), "items");
        Iterator<Object> filtered = new FilterTagIterator(unfiltered);

        // Either place a LIST into the request scope, or an ITERATOR, depending on getResultType.
        if ("list".equals(getResultType())) {
            List<Object> list = new LinkedList<Object>();
            while (filtered.hasNext()) {
                Object item = filtered.next();
                list.add(item);
            }
            pageContext.getRequest().setAttribute(getVar(), list);

        } else {
            pageContext.getRequest().setAttribute(getVar(), filtered);
        }

        // Evaluate the body of this tag
        return EVAL_PAGE;

    }

    private class FilterTagIterator extends FilteredIterator<Object>
    {
        private ValueExpression _valueExpression;

        private FilterTagIterator( Iterator<Object> source )
        {
            super(source);

            try {
                ServletContext servletContext = pageContext.getServletContext();
                JspApplicationContext jspAppContext = JspFactory.getDefaultFactory().getJspApplicationContext(servletContext);
                ELContext elContext = pageContext.getELContext();
                _valueExpression = jspAppContext.getExpressionFactory().createValueExpression(elContext, "${" + _test + "}",
                    Boolean.class);
                
                // Why is elContext.getFunctionMapper() == null? It means that functions cannot be used within the expression.
                // Maybe its a bug in JSP???
                // System.out.println( elContext.getFunctionMapper() );
                // Is there a fix to set the function mapper to the one used within the pageContext?

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected boolean include( Object item )
        {
            try {
                if ( _valueExpression != null ) {
                    pageContext.setAttribute(_itemName, item);
        
                    ELContext elContext = pageContext.getELContext();
                    Boolean result = (Boolean) _valueExpression.getValue(elContext);
                    return result.booleanValue();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

    }

}
