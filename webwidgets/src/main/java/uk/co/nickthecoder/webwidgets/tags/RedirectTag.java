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
import javax.servlet.jsp.PageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
  Redirects to a page, usually used after a POST.
*/

public class RedirectTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private String _url;

  private boolean _useContextPath;

  private String _parameterName;

  private String _parameterValue;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public RedirectTag()
  {
    super();
    initialise();
  }


  private void initialise()
  {
    _url = null;
    _parameterName = null;
    _parameterValue = null;
    _useContextPath = true;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------

  public void setUrl( String value )
  {
    _url = value;
  }

  public String getUrl()
  {
    return _url;
  }


  public boolean getUseContextPath()
  {
    return _useContextPath;
  }

  public void setUseContextPath( boolean value )
  {
    _useContextPath = value;
  }


  public void setParameterName( String value )
  {
    _parameterName = value;
  }

  public String getParameterName()
  {
    return _parameterName;
  }

  public void setParameterValue( String value )
  {
    _parameterValue = value;
  }

  public String getParameterValue()
  {
    return _parameterValue;
  }



  public int doEndTag()
    throws JspException
  {
    try {
      // System.out.println( "Redirecting to : " + getLocation() );
      ((HttpServletResponse) pageContext.getResponse()).sendRedirect( getLocation() );
      // System.out.println( "ok" );
    } catch ( Exception e ) {
      System.err.println( "Failed to redirect to : " + getLocation() );
      throw new JspException( e );
    }

    //return SKIP_PAGE;
    return SKIP_BODY;
  }

  protected String getLocation()
  {
    String url = TagUtil.resolveUrl( pageContext, getUrl(), getUseContextPath() );

    if ( getParameterName() != null ) {
      url = LinkInfo.completeUrl( null, url, getParameterName(), getParameterValue() );
    }

    return url;
  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class RedirectTag ----------

