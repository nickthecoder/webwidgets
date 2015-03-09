/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2005-04-18

----------------------------------------------------------------------

 History
 2005-04-18 : nick : Initial Development

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
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
  Lets the user navigate from one page to another, often used for search results.
  This tag can work in two ways. Either you give it the set of items, and the number
  of items per page, or you give it the total number of pages.
*/

public class PagerTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  public static final String DEFAULT_PAGE_PARAMETER_NAME = "page";

  public static final String DEFAULT_PAGE_ATTRIBUTE_NAME = "page";

  // -------------------- [[Attributes]] --------------------

  /**
    The maximum number of page links to display after the current page.
  */
  private int _nextPages;

  /**
    The maximum number of page links to display before the current page.
  */
  private int _previousPages;

  /**
    The current page number. Page numbers start at 1.
  */
  private int _page;

  /**
    The numbers of pages of results
  */
  private int _pages;


  /**
    Used by the child tags, as a counter while iterating over the
    previous and next pages.
  */
  private int _pageNumber;

  /**
    The name of the parameter passed as part of the url whenever
    a page link is clicked.
  */
  private String _pageParameterName;

  /**
    The name of the attribute, which holds the page number while iterating
    over each page links.
  */
  private String _pageAttributeName;

  /**
    The collection or iterator of items which make up all of the pages.
  */
  private Object _items;

  /**
    The number of items per page.
  */
  private int _itemsPerPage;

  /**
    The name of the variable to assign the subset of items which are for the
    current page.
  */
  private String _subsetVar;

  /**
    The name of the variable to assign 'this' to
  */
  private String _pagerVar;

  /**
    The item in the collection that sholuld be displayed. If set, then
    the page number will be calculated, so that this item is being displayed.
    Can only be used in conjunction with _items of type List.
  */
  private Object _currentItem;


  /**
    A list version of the items attribute
  */
  private List _list;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public PagerTag()
  {
    super();
    initialise();
  }

  private void initialise()
  {
    // System.out.println( "PageTag init" );
    _nextPages = 9;
    _previousPages = 10;
    _page = 0;
    _pages = 1;
    _pageNumber = 0;
    _pageParameterName = DEFAULT_PAGE_PARAMETER_NAME;
    _pageAttributeName = DEFAULT_PAGE_ATTRIBUTE_NAME;

    _items =  null;
    _itemsPerPage = 0;
    _subsetVar = null;
    _pagerVar = null;
    _currentItem = null;
    _list = null;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------

  /**
    Get method for attribute {@link #_nextPages}.
    The maximum number of page links to display after the current page.
  */
  public int getNextPages()
  {
    return _nextPages;
  }

  /**
    Set method for attribute {@link #_nextPages}.
    The maximum number of page links to display after the current page.
  */
  public void setNextPages( int value )
  {
    _nextPages = value;
  }

  /**
    Get method for attribute {@link #_previousPages}.
    The maximum number of page links to display before the current page.
  */
  public int getPreviousPages()
  {
    return _previousPages;
  }

  /**
    Set method for attribute {@link #_previousPages}.
    The maximum number of page links to display before the current page.
  */
  public void setPreviousPages( int value )
  {
    _previousPages = value;
  }

  /**
    Get method for attribute {@link #_page}.
    The current page number. Page numbers start at 1.
  */
  public int getPage()
    throws JspException
  {
    if ( _page == 0 ) {

      if ( getPageParameterName() != null ) {

        String pageString = pageContext.getRequest().getParameter( getPageParameterName() );
        if ( pageString != null ) {
          try {
            // System.out.println( "Setting page from parameter to : " + pageString );
            return Integer.parseInt( pageString );
          } catch (Exception e) {
            // Do nothing
          }
        }
      }

      if ( ( getCurrentItem() != null ) && ( getList() != null ) ) {

        int index = getList().indexOf( getCurrentItem() );
        if ( index >= 0 ) {
          return 1 + index / getItemsPerPage();
        }

      }

      return 1;

    } else {
      return _page;
    }

  }

  /**
    Set method for attribute {@link #_page}.
    The current page number. Page numbers start at 1.
  */
  public void setPage( int value )
  {
    _page = value;
  }

  /**
    Get method for attribute {@link #_pages}.
    The numbers of pages of results
  */
  public int getPages()
  {
    return _pages;
  }

  /**
    Set method for attribute {@link #_pages}.
    The numbers of pages of results
  */
  public void setPages( int value )
  {
    _pages = value;
  }


  /**
    Used by the PageTag to set the number of the page for the link to the
    page currently being generated. This is NOT the current page being displayed.
  */
  public void setPageNumber( int pageNumber )
  {
    _pageNumber = pageNumber;
    pageContext.setAttribute( getPageAttributeName(), new Integer( pageNumber ) );
  }
  public int getPageNumber()
  {
    return _pageNumber;
  }



  public void setPageParameterName( String name )
  {
    _pageParameterName = name;
  }
  public String getPageParameterName()
  {
    return _pageParameterName;
  }



  public void setPageAttributeName( String name )
  {
    _pageAttributeName = name;
  }
  public String getPageAttributeName()
  {
    return _pageAttributeName;
  }


  public void setItems( Object value )
  {
    _items = value;
  }
  public Object getItems()
  {
    return _items;
  }


  public void setItemsPerPage( int value )
  {
    _itemsPerPage = value;
  }
  public int getItemsPerPage()
  {
    return _itemsPerPage;
  }


  public void setCurrentItem( Object value )
  {
    _currentItem = value;
  }
  public Object getCurrentItem()
  {
    return _currentItem;
  }


  public void setSubsetVar( String value )
  {
    _subsetVar = value;
  }
  public String getSubsetVar()
  {
    return _subsetVar;
  }

  public void setPagerVar( String value )
  {
    _pagerVar = value;
  }
  public String getPagerVar()
  {
    return _pagerVar;
  }


  private int getFromIndex()
    throws JspException
  {
    return (getPage()-1) * getItemsPerPage();
  }


  private int getToIndex()
    throws JspException
  {
    return getPage() * getItemsPerPage();
  }



  private void setSize( int size )
  {
    setPages( (size - 1) / getItemsPerPage() + 1 );
  }


  public List getList()
    throws JspException
  {
    if ( _list == null ) {

      Collection collection = TagUtil.collection( getItems(), "items" );
      if ( collection instanceof List ) {
        _list = (List) collection;
      } else {
        _list = new ArrayList( collection );
      }

    }
    return _list;

  }

  public boolean isSinglePage()
  {
    try {
      return getList().size() <= getItemsPerPage();
    } catch ( Exception e ) {
      return false;
    }
  }

  private void processItems()
    throws JspException
  {
    // System.out.println( "PagerTag:processItems" );

    setSize( getList().size() );

    if ( getSubsetVar() != null ) {

      // System.out.println( "creating subset" );

      List subset = null;
      int fromIndex = getFromIndex();
      int toIndex = getToIndex();

      // System.out.println( "Subsetting from : "+ fromIndex + " to " + toIndex );

      if ( toIndex > getList().size() ) {
        toIndex = getList().size();
        // System.out.println( "Corrected : from : "+ fromIndex + " to " + toIndex );
      }

      subset = getList().subList( fromIndex, toIndex );

      if ( getSubsetVar() != null ) {
        pageContext.getRequest().setAttribute( getSubsetVar(), subset );
      }

    }

  }


  public int doStartTag()
    throws JspException
  {
    // System.out.println( "PageTagdoStartTag" );

    // System.out.println( "getItems : " + getItems() );
    // System.out.println( "getItemsPerPage : " + getItemsPerPage() );

    if ( ( getItems() != null ) && ( getItemsPerPage() != 0 ) ) {
      processItems();
    }

    if ( getPagerVar() != null ) {
      pageContext.getRequest().setAttribute( getPagerVar(), this );
    }

    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag()
    throws JspException
  {
    _list = null;
    return super.doEndTag();
  }


  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class PagerTag ----------

