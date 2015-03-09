/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2005-04-21

----------------------------------------------------------------------

 History
 2005-04-21 : nick : Initial Development

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
  Includes javascript code only once per page
*/

public class StyleSheetTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  /**
  */
  private String _href;

  /**
  */
  private boolean _useContextPath;

  private boolean _encodeSessionId;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public StyleSheetTag()
  {
    super();

    initialise();
  }

  public void release()
  {
    super.release();
    initialise();
  }

  private void initialise()
  {
    _href = null;
    _useContextPath = true;
    _encodeSessionId = false;
  }

  // -------------------- [[Methods]] --------------------

  /**
    Get method for attribute {@link #_useContextPath}.
    Should request.getContextPath be added to the beginning of the src url.
  */
  public boolean getUseContextPath()
  {
    return _useContextPath;
  }

  /**
    Set method for attribute {@link #_useContextPath}.
    Should request.getContextPath be added to the beginning of the src url.
  */
  public void setUseContextPath( boolean value )
  {
    _useContextPath = value;
  }

  public boolean getEncodeSessionId()
  {
    return _encodeSessionId;
  }

  /**
    Set method for attribute {@link #_encodeSessionId}.
    Should request.getContextPath be added to the beginning of the src url.
  */
  public void setEncodeSessionId( boolean value )
  {
    _encodeSessionId = value;
  }

 /**
    Get method for attribute {@link #_href}.
    The url of the script
  */
  public String getHref()
  {
    return _href;
  }

  /**
    Set method for attribute {@link #_href}.
    The url of the script
  */
  public void setHref( String value )
  {
    _href = value;
  }


  public int doEndTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();
      String url = TagUtil.resolveUrl( pageContext, getHref(), getUseContextPath(), getEncodeSessionId() );

      out.print( "<link rel=\"stylesheet\" type=\"text/css\"" );
      TagUtil.printAttribute( out, "href", url );
      out.print( "/>" );

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class StyleSheetTag ----------

