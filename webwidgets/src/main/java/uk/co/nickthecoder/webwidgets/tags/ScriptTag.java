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

public class ScriptTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  public static final String INCLUDED_SET_NAME = "webwidgets.tags.ScriptTag";

  // -------------------- [[Attributes]] --------------------

  /**
    Should request.getContextPath be added to the beginning of the src url.
  */
  private boolean _useContextPath;

  /**
    The url of the script
  */
  private String _src;

  /**
    If you want the tag to include the "language" attribute, then use this attribute,
    however, the "language" attribute is NOT valid for strict xhtml, so the "type"
    attribute is preferred. The default language is null, which means the attribute
    will not be rendered.
  */
  private String _language;

  /**
    The type of file, defaults to "text/javascript"
  */
  private String _type;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public ScriptTag()
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
    _language = null;
    _useContextPath = true;
    _type="text/javascript";
  }

  // -------------------- [[Methods]] --------------------

  public String getType()
  {
    return _type;
  }

  public void setType( String value )
  {
    _type = value;
  }


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

  /**
    Get method for attribute {@link #_src}.
    The url of the script
  */
  public String getSrc()
  {
    return _src;
  }

  /**
    Set method for attribute {@link #_src}.
    The url of the script
  */
  public void setSrc( String value )
  {
    _src = value;
  }

  /**
    Get method for attribute {@link #_language}.
    The type of script, defaults to "javascript"
  */
  public String getLanguage()
  {
    return _language;
  }

  /**
    Set method for attribute {@link #_language}.
    The type of script, defaults to "javascript"
  */
  public void setLanguage( String value )
  {
    _language = value;
  }

  protected boolean includedOnPage( String url )
  {
    Set includedSet = (Set) pageContext.getAttribute( INCLUDED_SET_NAME );
    if ( includedSet == null ) {
      includedSet = new TreeSet();
      pageContext.setAttribute( INCLUDED_SET_NAME, includedSet );
    }

    if ( includedSet.contains( url ) ) {
      return true;
    } else {
      includedSet.add( url );
      return false;
    }

  }

  public int doEndTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();
      String url = TagUtil.resolveUrl( pageContext, getSrc(), getUseContextPath(), false );

      if ( ! includedOnPage( url ) ) {

        out.print( "<script" );
        if ( getLanguage() != null ) {
          TagUtil.printAttribute( out, "language", getLanguage() );
        }
        TagUtil.printAttribute( out, "type", getType() );
        TagUtil.printAttribute( out, "src", url );
        out.print( "></script>" );
      }

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class ScriptTag ----------

