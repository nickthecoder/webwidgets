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

package uk.co.nickthecoder.webwidgets.tags;


import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
  @MORE@ Add javadoc comments here
*/

public class LinkInfoTag
  extends TagSupport
  implements HasLinkInfo
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  /**
  */
  private String _href;

  /**
    Should request.getContextPath be added to the front of urls beginning with "/"?
  */
  private boolean _useContextPath;

  private LinkInfo _linkInfo;

  // -------------------- [[Static Methods]] --------------------

  public static LinkInfo getAncestorLinkInfo( Tag childTag )
    throws JspException
  {
    HasLinkInfo ancestor =
      (HasLinkInfo) findAncestorWithClass( childTag, HasLinkInfo.class );

    if ( ancestor == null ) {
      throw new JspException( "Ancestor Tag implementing HasLinkInfo not found" );
    }

    return ancestor.getLinkInfo();
  }

  public static String getAncestorLinkHref( Tag childTag )
    throws JspException
  {
    HasLinkHref ancestor =
      (HasLinkHref) findAncestorWithClass( childTag, HasLinkHref.class );

    if ( ancestor == null ) {
      throw new JspException( "Ancestor Tag implementing HasLinkHref not found" );
    }

    return ancestor.getLinkHref();
  }

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public LinkInfoTag()
  {
    super();

    initialise();
  }

  private void initialise()
  {
    _href = null;
    _linkInfo = new LinkInfo();
    _useContextPath = true;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------

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
    Get method for attribute {@link #_href}.
  */
  public String getHref()
  {
    return _href;
  }

  /**
    Set method for attribute {@link #_href}.
  */
  public void setHref( String value )
  {
    _href = value;
    _linkInfo.setUrl( _href );
  }

  public String getHash()
  {
    return _linkInfo.getHash();
  }

  public void setHash( String value )
  {
    _linkInfo.setHash( value );
  }


  public void setParameters( Map parameters )
  {
    _linkInfo.setParameters( parameters );
  }

  public LinkInfo getLinkInfo()
  {
    return _linkInfo;
  }

  public String getLinkHref()
    throws JspException
  {
    return getLinkInfo().getLinkHref();
  }

  public int doStartTag()
    throws JspException
  {
    LinkInfo linkInfo = getLinkInfo();
    if ( getUseContextPath() ) {
      linkInfo.setContextPath( ((HttpServletRequest) pageContext.getRequest()).getContextPath() );
    }

    linkInfo.begin();
    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag()
    throws JspException
  {
    if ( getUseContextPath() ) {
      getLinkInfo().setContextPath( ((HttpServletRequest) pageContext.getRequest()).getContextPath() );
    }

    getLinkInfo().end();
    return EVAL_PAGE;
  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class LinkInfoTag ----------

