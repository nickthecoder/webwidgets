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
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
  Swaps an image when the mouse rolls over it.
*/

public class ImageRolloverTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------


  // -------------------- [[Attributes]] --------------------

  /**
    If true, then request.getContextPath() is appended to the front of the src urls when they begin with '/'
  */
  private boolean _useContextPath;

  /**
    The url for the normal image
  */
  private String _src;

  /**
    The url for the rollover image
  */
  private String _rollover;

  /**
    The css style for the img tag
  */
  private String _styleClass;

  /**
    The alt attribute for the img tag - wc3 say you must include an alt :-)
  */
  private String _alt;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public ImageRolloverTag()
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
    _src = null;
    _rollover = null;

    _alt = null;
    _useContextPath = true;
    _styleClass = null;
  }

  // -------------------- [[Methods]] --------------------

  public String getAlt()
  {
    return _alt;
  }

  public void setAlt( String value )
  {
    _alt = value;
  }

  /**
    Get method for attribute {@link #_useContextPath}.
    If true, then request.getContextPath() is appended to the front of the src urls when they begin with '/'
  */
  public boolean getUseContextPath()
  {
    return _useContextPath;
  }

  /**
    Set method for attribute {@link #_useContextPath}.
    If true, then request.getContextPath() is appended to the front of the src urls when they begin with '/'
  */
  public void setUseContextPath( boolean value )
  {
    _useContextPath = value;
  }

  /**
    Get method for attribute {@link #_offSrc}.
    The url for the OFF image
  */
  public String getRollover()
  {
    return _rollover;
  }

  /**
    Set method for attribute {@link #_rollover}.
    The url for the OFF image
  */
  public void setRollover( String value )
  {
    _rollover = value;
  }

  /**
    Get method for attribute {@link #_src}.
    The url for the ON image
  */
  public String getSrc()
  {
    return _src;
  }

  /**
    Set method for attribute {@link #_onSrc}.
    The url for the ON image
  */
  public void setSrc( String value )
  {
    _src = value;
  }

  /**
    Get method for attribute {@link #_styleClass}.
    The css style for the img tag
  */
  public String getStyleClass()
  {
    return _styleClass;
  }

  /**
    Set method for attribute {@link #_styleClass}.
    The css style for the img tag
  */
  public void setStyleClass( String value )
  {
    _styleClass = value;
  }

  public String getUrl()
  {
    return TagUtil.resolveUrl( pageContext, getSrc(), getUseContextPath() );
  }

  public String getRolloverUrl()
  {
    return TagUtil.resolveUrl( pageContext, getRollover(), getUseContextPath() );
  }

  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.print( "<img" );
      if ( getAlt() != null ) {
        out.print( " alt=\"" + getAlt() + "\"" );
      }
      if ( getStyleClass() != null ) {
        out.print( " class=\"" + getStyleClass() + "\"" );
      }
      out.print( " onmouseover=\"ww_changeImage( this, '" + getRolloverUrl() + "' );\"" );
      out.print( " onmouseout=\"ww_restoreImage( this );\" " );
      out.print( " src=\"" + getUrl() + "\"" );
      out.print( "/>" );

      out.print( "<script type=\"text/javascript\" language=\"javascript\">ww_onloadNotifier.add( function() { ww_cacheImage('" );
      out.print( getRolloverUrl() );
      out.print( "'); } );</script>" );

      return EVAL_BODY_INCLUDE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

  }


  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class ImageRolloverTag ----------

