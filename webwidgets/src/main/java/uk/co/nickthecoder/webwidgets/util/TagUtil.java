// ----------------------------------------------------------------------
//
// Author        : Nick Robinson (nick)
// Creation Date : 2006-02-22
//
// ----------------------------------------------------------------------
// History
// 2003-03-18 : nick : Initial Development
//
// ----------------------------------------------------------------------

package uk.co.nickthecoder.webwidgets.util;

import java.util.*;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.jsp.*;
import javax.servlet.http.*;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

public class TagUtil
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  // -------------------- [[Static Methods]] --------------------


  // {{{ printAttribute
  public static void printAttribute( JspWriter out, String name, String value )
    throws IOException
  {
    out.print( " " );
    out.print( name );
    out.print( "=\"" );
    out.print( value );
    out.print( "\"" );
  }

  public static void printAttribute( JspWriter out, String name, int value )
    throws IOException
  {
    out.print( " " );
    out.print( name );
    out.print( "=\"" );
    out.print( value );
    out.print( "\"" );
  }
  // }}}
  
  // {{{ printSafeAttribute
  public static void printSafeAttribute( JspWriter out, String name, String value )
    throws IOException
  {
    printAttribute( out, name, safeText( value ) );
  }
  // }}}
    
  // {{{ printSafeText
  public static void printSafeText( JspWriter out, String value )
    throws IOException
  {
    if ( value != null ) {
      out.write( safeText( value ) );
    }
  }
  // }}}

  // {{{ safeText
  /**
    Converts strings so that they can be included in a html document
    and appear exactly as they the original would appear in a text
    document.
    <br><br>
    This involves changing the following character :
    <ul>
      <li>&lt; - &amp;lt;</li>
      <li>&gt; - &amp;gt;</li>
      <li>&amp; - &amp;amp;</li>
      <li>&quot; - &amp;quot;</li>
    </ul>
  */
  public static String safeText( String oldValue )
  {
    if (oldValue == null) {
      return "";
    }

    // Create a string buffer, with enough room for at least a few substitutions
    // before it will need to reallocate space.
    StringBuffer buffer = new StringBuffer (oldValue.length() + 40);

    int length = oldValue.length();

    for ( int i = 0; i < length; i++)
      {
        char c = oldValue.charAt(i);

        switch (c)
          {

          case '<' :
            buffer.append( "&lt;" );
            break;

          case '>' :
            buffer.append( "&gt;" );
            break;

          case '&' :
            buffer.append( "&amp;" );
            break;

          case '"' :
            buffer.append( "&quot;" );
            break;

          default:
            buffer.append( c );
            break;

          }
      }

    return buffer.toString();
  }
  // }}}


  // {{{ resolveUrl
  /**
    Does url rewriting and optionally appends the context path.
  */
  public static final String resolveUrl( PageContext pageContext, String url, boolean useContextPath )
  {
    return resolveUrl( pageContext, url, useContextPath, true );
  }

  public static final String resolveUrl( PageContext pageContext, String url, boolean useContextPath, boolean encodeSessionId )
  {
    if ( useContextPath && (url.startsWith( "/" ) ) ) {
      url = ( (HttpServletRequest) pageContext.getRequest()).getContextPath() + url;
    }
    if ( encodeSessionId ) {
      return ((HttpServletResponse)pageContext.getResponse()).encodeURL( url );
    } else {
      return url;
    }
  }
  // }}}

  // {{{ encodeUrl
  public static String encodeUrl( String value )
  {
    try {
      return URLEncoder.encode( value, "UTF-8" );
    } catch ( java.io.UnsupportedEncodingException e ) {
      System.err.println( "UTF-8 not supported? No way! : " + e );
      return value;
    }
  }
  // }}}
  
  // {{{ encodePath
  /**
    Encodes a string, so that it is suitable as the pathinfo portion of a url.
    This differs from encodeUrl in that 
    slashes remain as slashes, and not conerted to %2F.
  */
  public static String encodePath( String value )
  {
    String result = encodeUrl( value );
    // result = result.replaceAll( "\\+", "%20" );
    result = result.replaceAll( "%28", "(" );
    result = result.replaceAll( "%29", ")" );
    result = result.replaceAll( "%2F", "/" );

    return result;
  }
  // }}}

  // {{{ iterator 
  public static Iterator iterator( Object object )
  {
    if ( object instanceof Iterator ) {
      return (Iterator) object;

    } else if ( object instanceof Collection ) {
      return ((Collection) object).iterator();

    } else if ( object instanceof Object[] ) {
      return new ArrayIterator( (Object[]) object );
    }

    throw new ClassCastException( "Must be a collection, an iterator or an array" );

  }

  public static Iterator iterator( Object object, String attributeName )
    throws JspException
  {
    try {
      return iterator( object );
    } catch ( ClassCastException e ) {
      throw new JspException( "Attribute " + attributeName  + " : " + e.getMessage() );
    }

  }
  // }}}
  
  // {{{ collection
  public static Collection collection( Object object )
  {
    if ( object instanceof Collection ) {

      return (Collection) object;

    } else if ( object instanceof Iterator ) {

      List result = new LinkedList();
      for ( Iterator i = (Iterator) object; i.hasNext(); ) {
        result.add( i.next() );
      }

      return result;

    } else if ( object instanceof Object[] ) {

       return Arrays.asList( (Object[]) object );
    }

    throw new ClassCastException( "Must be a collection, an iterator or an array" );
  }


  public static Collection collection( Object object, String attributeName )
    throws JspException
  {
    try {
      return collection( object );
    } catch ( ClassCastException e ) {
      throw new JspException( "Attribute " + attributeName + " : " + e.getMessage() );
    }

  }
  // }}}

  // {{{ array
  public static Object[] array( Object object )
  {
    if ( object instanceof Object[] ) {

      return (Object[]) object;

    } else if ( object instanceof Collection ) {

      return ((Collection) object).toArray();

    } else if ( object instanceof Map ) {

      return ((Map) object).values().toArray();

    } else if ( object instanceof Iterator ) {
      List result = new LinkedList();
      for ( Iterator i = (Iterator) object; i.hasNext(); ) {
        result.add( i.next() );
      }

      return result.toArray();
    }

    throw new ClassCastException( "Must be a Collection, Map or an Iterator" );
  }


  public static Object[] array( Object object, String attributeName )
    throws JspException
  {
    try {
      return array( object );
    } catch ( ClassCastException e ) {
      throw new JspException( "Attribute " + attributeName + " : " + e.getMessage() );
    }

  }
  // }}}
  
  // {{{ getUrl
  public static String getUrl( HttpServletRequest request )
  {
    StringBuffer buffer = request.getRequestURL();
    if ( request.getPathInfo() != null ) {
      buffer.append( request.getPathInfo() );
    }

    if ( request.getQueryString() != null ) {
      buffer.append( "?" ).append( request.getQueryString() );
    }
    return buffer.toString();
  }
  // }}}

   // {{{ isLocal
  /**  
    Checks if the request came from an ipv4 local address as defined here :
    http://en.wikipedia.org/wiki/Private_network
    i.e. 	10.0.0.0 – 10.255.255.255 , 172.16.0.0 – 172.31.255.255 or 192.168.0.0 – 192.168.255.255 
  */
  public static boolean isLocal( HttpServletRequest request )
  {
    String addr = request.getRemoteAddr();
    if ( addr.startsWith( "192.168." ) ) {
      return true;
    } else if ( addr.startsWith( "10." ) ) {
      return true;
    } else if ( addr.startsWith( "172." ) ) {
      String next = addr.substring( 5 );
      int dot = next.indexOf( "." );
      int value = Integer.parseInt( next.substring( 0, dot ) );
      if ( (value >= 16) && (value < 32) ) {
        return true;
      }
    }
    return false;
  }

  // -------------------- [[Constructors]] --------------------

  public TagUtil()
  {
  }

  // -------------------- [[Methods]] --------------------



  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class TagUtil ----------

