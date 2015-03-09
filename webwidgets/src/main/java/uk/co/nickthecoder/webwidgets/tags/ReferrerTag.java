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
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;



/**
  Renders the value of the referrer http header, however, if referrer is given as
  a url parameter (or post parameter), then that value is used.

  NOTE. The http spec got the spelling wrong, and I was unsure whether to continue
  this bad spelling. I chose to spell referrer correctly, and I appologise for half
  of you that are totally confused. The only place I spell referrer incorrectly, is
  when I read the http headers (i.e. where I NEED to spell it wrong).
*/

public class ReferrerTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public ReferrerTag()
  {
  }

  // -------------------- [[Methods]] --------------------


  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

      String referrer;
      if ( request.getParameter( "referrer" ) != null ) {
        referrer = request.getParameter( "referrer" );
      } else {
        referrer = request.getHeader( "referer" ); // Note the spelling is wrong in HTTP spec!
      }

      if ( referrer != null ) {
        out.print( referrer );
      }

      return SKIP_BODY;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class ReferrerTag ----------

