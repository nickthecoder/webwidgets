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

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
  @MORE@ Add javadoc comments here
*/

public class SamePageLinkInfoTag
  extends TagSupport
  implements HasLinkInfo
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private LinkInfo _linkInfo;

  /**
    The href, which is the same as the current request href, but without the parameters
  */
  private String _href;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public SamePageLinkInfoTag()
  {
    super();

    initialise();
  }

  private void initialise()
  {
    _linkInfo = new LinkInfo();
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------

  /**
    Get method for attribute {@link #_href}.
  */
  public String getPageUrl()
  {
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    //System.out.println( "URI : " + request.getRequestURI() );
    //System.out.println( "URL : " + request.getRequestURL() );

    String result = request.getRequestURL().toString();
    int firstQ = result.indexOf( "?" );
    if ( firstQ > 0 ) {
      return result.substring( 0, firstQ - 1 );
    } else {
      return result;
    }
  }


  public LinkInfo getLinkInfo()
  {
    // System.out.println( "SamePage:getLinkInfo:" + _linkInfo );
    return _linkInfo;
  }

  public String getLinkHref()
    throws JspException
  {
    return TagUtil.resolveUrl( pageContext, getLinkInfo().getLinkHref(), false );
  }

  public int doStartTag()
    throws JspException
  {
    _linkInfo.setUrl( getPageUrl() );
    return EVAL_BODY_INCLUDE;
  }

  public int doEndTag()
    throws JspException
  {
    initialise();

    return super.doEndTag();
  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class SamePageLinkInfoTag ----------

