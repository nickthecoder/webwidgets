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
  Displays the set of breadcrumb objects that are held in a session object,
  put there by the BreadcrumbTag.
*/

public class BreadcrumbsTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  private static int _numberVisible = 10;

  private static final String SESSION_ATTRIBUTE = "BreadcrumbsTag";

  // -------------------- [[Attributes]] --------------------

  private boolean _backwards;

  private String _separator;

  // -------------------- [[Static Methods]] --------------------

  public static int getNumberVisible()
  {
    return _numberVisible;
  }

  public static void setNumberVisible( int value )
  {
    _numberVisible = value;
  }

  public static List getBreadcrumbs( PageContext pageContext )
  {
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    HttpSession session = request.getSession();

    List list = (List) session.getAttribute( SESSION_ATTRIBUTE );
    if ( list == null ) {
      list = new LinkedList();
      session.setAttribute( SESSION_ATTRIBUTE, list );
    }

    return list;
  }

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public BreadcrumbsTag()
  {
    _backwards = false;
    _separator = "";
  }


  public void release()
  {
  }

  // -------------------- [[Methods]] --------------------

  public boolean getBackwards()
  {
    return _backwards;
  }

  public void setBackwards( boolean value )
  {
    _backwards = value;
  }

  public void setSeparator( String value )
  {
    _separator = value;
  }

  public String getSeparator()
  {
    return _separator;
  }



  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();


      List list = getBreadcrumbs( pageContext );

      boolean first = true;
      
      for ( Iterator i = ReverseListIterator.getListIterator( list, getBackwards() ); i.hasNext(); ) {
        Crumb crumb = (Crumb) i.next();

        out.print( "<a class=\"ww_crumb" );
        if (first) {
          out.println( " ww_firstCrumb" );
          first = false;
        } else {
          if ( i.hasNext() ) {
            out.println( " ww_lastCrumb" );
          }
        }
        out.print( "\"" );
        TagUtil.printSafeAttribute( out, "href", crumb.getUrl() );
        out.print( ">" );

        TagUtil.printSafeText( out, crumb.getLabel() );

        out.println( "</a>" );

        if ( i.hasNext() ) {
          TagUtil.printSafeText( out, _separator );
        }

      }

      return EVAL_PAGE;

    } catch ( Exception e ) {
      // @MORE@
      System.err.println( e );
      e.printStackTrace();
      throw new JspException( e );
    }
  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class BreadcrumbsTag ----------


