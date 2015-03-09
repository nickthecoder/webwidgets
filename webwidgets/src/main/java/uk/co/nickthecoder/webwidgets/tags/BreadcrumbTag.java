/* {{{ GPL

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

}}} */

package uk.co.nickthecoder.webwidgets.tags;


// {{{ imports
import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import uk.co.nickthecoder.webwidgets.util.*;
// }}}

/**
  Adds a breadcrumb to the List held in the session.
*/

public class BreadcrumbTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private String _label;

  private String _url;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public BreadcrumbTag()
  {
    _label = null;
    _url = null;
  }


  public void release()
  {
  }

  // -------------------- [[Methods]] --------------------

  public String getLabel()
  {
    return _label;
  }

  public void setLabel( String value )
  {
    _label = value;
  }

  public String getUrl()
  {
    return _url;
  }

  public void setUrl( String value )
  {
    _url = value;
  }


  public int doStartTag()
    throws JspException
  {
    try {

      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

      List list = BreadcrumbsTag.getBreadcrumbs( pageContext );

      String url = getUrl();
      if ( url == null ) {
        url = TagUtil.getUrl( request );
      }
      Crumb crumb = new Crumb( getLabel(), url );
      addCrumb( list, crumb );

      return EVAL_PAGE;

    } catch ( Exception e ) {
      // @MORE@
      System.err.println( e );
      e.printStackTrace();
      throw new JspException( e );
    }
  }


  protected void addCrumb( List list, Crumb crumb )
  {

    for ( Iterator i = list.iterator(); i.hasNext(); ) {
      Crumb existing = (Crumb) i.next();
      if ( existing.getUrl().equals( crumb.getUrl() ) ) {
        i.remove();
      }
    }

    list.add( crumb );

    while ( list.size() > BreadcrumbsTag.getNumberVisible() ) {
      list.remove( 0 );
    }

  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class BreadcrumbTag ----------


