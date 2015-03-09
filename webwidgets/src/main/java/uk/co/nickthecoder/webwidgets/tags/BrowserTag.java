/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2006-08-29

----------------------------------------------------------------------

 History
 2006-08-29 : nick : Initial Development

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
import java.util.regex.*;

import javax.servlet.jsp.PageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.log4j.Logger;

import uk.co.nickthecoder.webwidgets.util.TagUtil;


/**

*/

public class BrowserTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  private static final String[] _mobilePatternStrings = { "ipod", "iphone", "android" };

  private static List _mobilePatterns; /* of Pattern objects */

  protected static Logger _logger = Logger.getLogger( BrowserTag.class );

  private static final String[] _versionPrefixes = { "msie", "firefox/" };
  
  // -------------------- [[Attributes]] --------------------

  private String _supportsPng;

  private String _isIe;

  private String _isMobile;

  private String _version;
  
  // -------------------- [[Static Methods]] --------------------

  public static float checkVersion( PageContext pageContext )
  {
    HttpServletRequest request = ((HttpServletRequest) pageContext.getRequest());
    String userAgent = request.getHeader("User-Agent").toLowerCase();

    // System.out.println("UserAgent " + userAgent );

    for (int i = 0; i < _versionPrefixes.length; i ++ ) {
      
      //System.out.println("Checking " + _versionPrefixes[i] );
      
      int index = userAgent.indexOf( _versionPrefixes[i] );
      if ( index >= 0 ) {
        
        //System.out.println("Found prefix " + _versionPrefixes[i] );

        int start = index + _versionPrefixes[i].length();
        int j;
        for ( j = start; j < userAgent.length(); j ++ ) {
          char c = userAgent.charAt( j );
          if ( Character.isWhitespace( c ) || Character.isDigit( c ) || c == '.' ) {
          } else {
            break;
          }
          
          //System.out.println("start,j " + start + "," + j  );
          
          String ver = userAgent.substring( start, j ).trim();
          //System.out.println("ver" + ver );
          try {
            float result = Float.parseFloat( ver );
            return result;
          } catch (Exception e) {
          }
        }
      }
    }

    return 0.0f;
  }
  
  public static boolean checkIsIe( PageContext pageContext )
  {
    HttpServletRequest request = ((HttpServletRequest) pageContext.getRequest());
    String userAgent = request.getHeader("User-Agent").toLowerCase();

    if (userAgent.indexOf("msie") != -1) {
      return true;
    }

    return false;
  }

  public static boolean checkSupportsPng( PageContext pageContext )
  {
    HttpServletRequest request = ((HttpServletRequest) pageContext.getRequest());
    String userAgent = request.getHeader("User-Agent").toLowerCase();

    if (userAgent.indexOf("msie") != -1) {
      return false;
    }

    return true;
  }

  public static boolean checkMobile( PageContext pageContext )
  {
    HttpServletRequest request = ((HttpServletRequest) pageContext.getRequest());
    String userAgent = request.getHeader("User-Agent").toLowerCase();

    for ( Iterator i = getMobilePatterns().iterator(); i.hasNext(); ) {
      Pattern pattern = (Pattern) (i.next());
      Matcher matcher = pattern.matcher( userAgent );
      if ( matcher.find() ) {
        return true;
      }
    }

    return false;
  }

  public static List getMobilePatterns()
  {
    if ( _mobilePatterns == null ) {
      _mobilePatterns = new ArrayList( _mobilePatternStrings.length );
      for ( int i = 0; i < _mobilePatternStrings.length; i ++ ) {
        _mobilePatterns.add( Pattern.compile( _mobilePatternStrings[ i ] ) );
      }
    }

    return _mobilePatterns;
  }

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public BrowserTag()
  {
    super();
    initialise();
  }

  private void initialise()
  {
    _supportsPng = null;
    _isIe = null;
    _version = null;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------


  public String getIsIe()
  {
    return _isIe;
  }

  public void setIsIe( String value )
  {
    _isIe = value;
  }

  public String getVersion()
  {
    return _version;
  }

  public void setVersion( String value )
  {
    _version = value;
  }


  public String getSupportsPng()
  {
    return _supportsPng;
  }

  public void setSupportsPng( String value )
  {
    _supportsPng = value;
  }


  public String getIsMobile()
  {
    return _isMobile;
  }

  public void setIsMobile( String value )
  {
    _isMobile = value;
  }


  public int doEndTag()
    throws JspException
  {
 
    if ( _supportsPng != null ) {
      pageContext.getRequest().setAttribute( _supportsPng, new Boolean( checkSupportsPng( pageContext ) ) );
    }

    if ( _isIe != null ) {
      pageContext.getRequest().setAttribute( _isIe, new Boolean( checkIsIe( pageContext ) ) );
    }

    if ( _isMobile != null ) {
      pageContext.getRequest().setAttribute( _isMobile, checkMobile( pageContext ) );
    }

    if ( _version != null ) {
      pageContext.getRequest().setAttribute( _version, checkVersion( pageContext ) );
    }

    return EVAL_PAGE;
  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class BrowserTag ----------

