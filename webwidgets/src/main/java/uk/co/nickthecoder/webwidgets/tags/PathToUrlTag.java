/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2004-03-19

----------------------------------------------------------------------

 History
 2004-03-19 : nick : Initial Development

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
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
  Coverts a path to a URL, appending the webapp context path.
  Option to make it a complete url (including the scheme, server name
  and port).
  Option to replace with a different url if the file isn't found.
*/

public class PathToUrlTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  /**
  */
  private String _path;

  private String _notFoundPath;

  private boolean _full;

  // -------------------- [[Static Methods]] --------------------


  /**
    Changes symbol characters into the form %nn.
  */
  public static String encodeForURL( String path )
  {
    StringBuffer result = new StringBuffer();
    int from = 0;

    for ( int i = 0; i < path.length(); i ++ ) {
      char c = path.charAt( i );

      if ( (c != '/') && (c != '.') && ! Character.isLetterOrDigit( c ) ) {
        String hex = Integer.toHexString( c );
        result.append( path.substring( from, i ) );
        result.append( "%" ).append( hex );
        from = i + 1;
      }
    }

    result.append( path.substring( from ) );

    return result.toString();
  }


  // -------------------- [[Constructors]] --------------------

  public PathToUrlTag()
  {
    super();
    initialise();
  }

  /**
    Called by the constructor, and by release to set each of the tags
    properties to their default values.
  */
  private void initialise()
  {
    _path = null;
    _notFoundPath = null;
    _full = false;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------

  /**
    Get method for attribute {@link #_path}.
  */
  public String getPath()
  {
    return _path;
  }

  /**
    Set method for attribute {@link #_path}.
  */
  public void setPath( String path )
  {
    _path = path;
  }

  /**
    Get method for attribute {@link #_notFoundPath}.
  */
  public String getNotFoundPath()
  {
    return _notFoundPath;
  }

  /**
    Set method for attribute {@link #_notFoundPath}.
  */
  public void setNotFoundPath( String notFoundPath )
  {
    _notFoundPath = notFoundPath;
  }

  public void setFull( boolean value )
  {
    _full = value;
  }
  public boolean getFull()
  {
    return _full;
  }

  public String getUrl( PageContext pageContext )
  {
    ServletContext context = pageContext.getServletContext();
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

    StringBuffer url = new StringBuffer();

    if ( getFull() ) {
      url.append( request.getScheme() );
      url.append( "://" );
      url.append( request.getServerName() );
      url.append( ":" );
      url.append( request.getServerPort() );
    }

    String path;
    if ( getNotFoundPath() != null ) {
      String rootPath = context.getRealPath( "/" );
      File file = new File( rootPath + File.separator + getPath() );
      if ( file.exists() ) {
        path = getPath();
      } else {
        path = getNotFoundPath();
      }
    } else {
      path = getPath();
    }

    url.append( request.getContextPath() );
    url.append( encodeForURL( path ) );

    return url.toString().replaceAll( " ", "%20" );

  }


  /**
  */
  public int doStartTag()
    throws JspException
  {
    try {
      JspWriter writer = pageContext.getOut();

      String url = getUrl( pageContext );

      writer.print( url );

    } catch ( Exception e ) {
    }

    return EVAL_PAGE;
  }

  public int doEndTag()
  {
    return EVAL_PAGE;
  }



  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class PathToUrlTag ----------

