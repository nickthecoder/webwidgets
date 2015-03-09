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
  Creates a html a tag
*/

public class LinkTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  /**
    The css style for the a tag
  */
  private String _styleClass;

  /**
    The href for the a tag.
  */
  private String _href;

  /**
    The id for the a tag.
  */
  private String _id;
  /**
    The title for the a tag.
  */
  private String _title;


 /**
    Should request.getContextPath() be added to the front of urls
    beginning with "/"?
  */
  private boolean _useContextPath;

  /**
    Add request.getContextPath() to the front to form the a tag's href.
  */
  private String _page;


  private String _onclick;

  // -------------------- [[Static Methods]] --------------------

  public static LinkInfo getHrefTag( TagSupport innerTag )
    throws JspException
  {
    LinkInfo ancestor = (LinkInfo) TagSupport.findAncestorWithClass( innerTag, LinkInfo.class );
    if ( ancestor == null ) {
      throw new JspException( "Href ancestor tag not found" );
    }

    return (LinkInfo) ancestor;
  }

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public LinkTag()
  {
    super();
    initialise();

  }

  private void initialise()
  {
    _href = null;
    _page = null;
    _styleClass = null;
    _useContextPath = true;
    _title = null;
    _onclick = null;
    _id = null;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------

  public String getId()
  {
    return _id;
  }
  
  public void setId( String id )
  {
    _id = id;
  }
  
  /**
    Get method for attribute {@link #_useContextPath}.
    Should request.getContextPath be added to the front of urls beginning with "/"?
  */
  public boolean getUseContextPath()
  {
    return _useContextPath;
  }

  /**
    Set method for attribute {@link #_useContextPath}.
    Should request.getContextPath be added to the front of urls beginning with "/"?
  */
  public void setUseContextPath( boolean value )
  {
    _useContextPath = value;
  }

  /**
    Get method for attribute {@link #_styleClass}.
    The css style for the a tag
  */
  public String getStyleClass()
  {
    return _styleClass;
  }

  /**
    Set method for attribute {@link #_styleClass}.
    The css style for the a tag
  */
  public void setStyleClass( String value )
  {
    _styleClass = value;
  }

  public String getHref()
  {
    return _href;
  }

  public void setHref( String href )
  {
    _href = href;
  }

  public String getRealHref()
    throws JspException
  {
    if ( _href == null ) {
      return LinkInfoTag.getAncestorLinkHref( this );
    } else {
      return TagUtil.resolveUrl( pageContext, getHref(), getUseContextPath() );
    }
  }

  public void setTitle( String value )
  {
    _title = value;
  }

  public String getTitle()
  {
    return _title;
  }


  public void setOnclick( String value )
  {
    _onclick = value;
  }

  public String getOnclick()
  {
    return _onclick;
  }



  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.print( "<a href=\"" + getRealHref() + "\"" );
      if ( getOnclick() != null ) {
        out.print( " onclick=\"" + getOnclick() + "\"" );
      }
      if ( getId() != null ) {
        out.print( " id=\"" + getId() + "\"" );
      }
      if ( getStyleClass() != null ) {
        out.print( " class=\"" + getStyleClass() + "\"" );
      }
      if ( getTitle() != null ) {
        out.print( " title=\"" + getTitle() + "\"" );
      }
      out.print( ">" );


    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

    return EVAL_BODY_INCLUDE;
  }


  public int doEndTag()
    throws JspException
  {
    try {
      JspWriter out = pageContext.getOut();

      out.print( "</a>" );

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

    return EVAL_PAGE;
  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class ATag ----------

