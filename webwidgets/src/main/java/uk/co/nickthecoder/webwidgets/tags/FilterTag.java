// ----------------------------------------------------------------------
//
// Author        : Nick Robinson (nick)
// Creation Date : 2003-03-18
//
// ----------------------------------------------------------------------
// History
// 2003-03-18 : nick : Initial Development
//
// ----------------------------------------------------------------------

package uk.co.nickthecoder.webwidgets.tags;


import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;


import javax.servlet.jsp.el.VariableResolver;
import javax.servlet.jsp.el.ELException;

import uk.co.nickthecoder.webwidgets.util.FilteredIterator;
import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
  Filters a collection of objects based upon an EL expression.
*/

public class FilterTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  public static final String DEFAULT_ITEM_NAME = "item";

  // -------------------- [[Attributes]] --------------------

  private Object _items;

  /**
    The variable to hold the iterator of filtered items.
  */
  private String _var;

  /**
    The el expression which should evaluate to true or false.
  */
  private String _test;

  private String _itemName;

  private String _resultType;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  public FilterTag()
  {
    super();
    initialise();
  }

  /**
    Called by the constructor, and by release to set each of the tags
    properties to their default values.
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

  // -------------------- [[Methods]] --------------------


  /**
    Get method for attribute {@link #_items}.
    The iterator, which is to be converted into a 2D structure
  */
  public Object getItems()
  {
    return _items;
  }

  /**
    Set method for attribute {@link #_items}.
    The iterator, which is to be converted into a 2D structure
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
    Get method for attribute {@link #_var}.
    The name of the variable, which will define each row.
  */
  public String getVar()
  {
    return _var;
  }

  /**
    Set method for attribute {@link #_var}.
    The name of the variable, which will define each row.
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



  public int doEndTag()
    throws JspException
  {
    Iterator unfiltered = TagUtil.iterator( getItems(), "items" );
    Iterator filtered = new FilterTagIterator( unfiltered, pageContext, getItemName(), getTest() );

    // Either place a LIST into the request scope, or an ITERATOR, depending on getResultType.
    if ( "list".equals( getResultType() ) ) {
      List list = new LinkedList();
      while ( filtered.hasNext() ) {
        Object item = filtered.next();
        list.add( item );
      }
      pageContext.getRequest().setAttribute( getVar(), list );

    } else {
      pageContext.getRequest().setAttribute( getVar(), filtered );
    }

    // Evaluate the body of this tag
    return EVAL_PAGE;

  }




  private class FilterTagIterator
    extends FilteredIterator
    implements Iterator, VariableResolver
  {
    private PageContext _pageContext;

    private String _itemName;

    private Object _currentItem;

    private String _expression;

    private FilterTagIterator( Iterator source, PageContext pageContext, String itemName, String expression )
    {
      super( source );

      _pageContext = pageContext;
      _itemName = itemName;
      _expression = "${" + expression + "}";
    }

    protected boolean include( Object item )
    {
      _currentItem = item;

      try {
        Boolean result = (Boolean) _pageContext.getExpressionEvaluator().evaluate( _expression, Boolean.class, this, null );
        return result.booleanValue();

      } catch ( ELException e ) {
        return false;
      }

    }

    public Object resolveVariable( String name )
      throws ELException
    {
      if ( name.equals( _itemName ) ) {
        return _currentItem;
      } else {
        return _pageContext.getVariableResolver().resolveVariable( name );
      }
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class FilterTag ----------
