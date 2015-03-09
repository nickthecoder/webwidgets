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
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
*/

public class IconTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  /**
    Should request.getContextPath be added to the beginning of the src url.
  */
  private boolean _useContextPath;

  /**
    The url of the icon
  */
  private String _href;

  private boolean _encodeSessionId;

  /**
    The type of file, defaults to "text/javascript"
  */
  private String _type;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public IconTag()
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
    _type=null; //"image/png";
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

  public String getType()
  {
    return _type;
  }

  public void setType( String value )
  {
    _type = value;
  }

  /**
    Get method for attribute {@link #_href}.
    The url of the icon
  */
  public String getHref()
  {
    return _href;
  }

  /**
    Set method for attribute {@link #_href}.
    The url of the icon
  */
  public void setHref( String value )
  {
    _href = value;
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


  public int doEndTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();
      String url = TagUtil.resolveUrl( pageContext, getHref(), getUseContextPath(), getEncodeSessionId() );

      out.print( "<link rel=\"icon\"" );
      if ( getType() != null ) {
        TagUtil.printAttribute( out, "type", getType() );
      }
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

// ---------- End Of Class IconTag ----------

