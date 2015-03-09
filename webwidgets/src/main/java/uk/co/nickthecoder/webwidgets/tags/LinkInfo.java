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

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
*/

public class LinkInfo
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  /**
    The parameters set within the tag itself
  */
  private Map _parameters;

  /**
    The paramters set within the tag itself, plus any extra parameters added using the
    addParameter method.
    These need to be held seperately, because the servlet container is free to REUSE an
    instance of LinkInfo, and so if we don't have these two maps, then if LinkParameter
    tag is used, then one instance of this tag could interfere with another.
  */
  private Map _allParameters;

  /**
    The url without the parameters added
  */
  private String _url;


  /**
    the part of the url after the # symbol
  */
  private String _hash;


  private String _contextPath;


  // -------------------- [[Static Methods]] --------------------

  static String completeUrl( String contextPath, String url, Map parameters )
  {
    if ( url.startsWith( "/" ) && (contextPath != null) ) {
      url = contextPath + url;
    }

    String parameterString = buildParameterString( parameters );

    if ( parameterString.length() == 0 ) {
      return url;
    } else {

      if ( url.indexOf( "?" ) >= 0 ) {
        return url + "&amp;" + parameterString;
      } else {
        return url+ "?" + parameterString;
      }

    }

  }


  static String completeUrl( String contextPath, String url, String name, String value )
  {
    if ( url.startsWith( "/" ) && (contextPath != null) ) {
      url = contextPath + url;
    }

    String parameterString = name + "=" +  TagUtil.encodeUrl( value );

    if ( url.indexOf( "?" ) >= 0 ) {
      return url + "&amp;" + parameterString;
    } else {
      return url+ "?" + parameterString;
    }
  }

  static String buildParameterString( Map parameters )
  {
    if (parameters.isEmpty()) {
      return "";
    }

    StringBuffer result = new StringBuffer();

    for (Iterator i = parameters.keySet().iterator(); i.hasNext(); ) {
      Object key = i.next();
      Object value = parameters.get( key );

      result.append( "&amp;" ).append( key ).append( "=" ).append( TagUtil.encodeUrl( value.toString() ) );
    }

    return result.substring( 5 ); // remove the first &amp;
  }

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public LinkInfo()
  {
    _parameters = new HashMap();
    _allParameters = _parameters;
    _url="";
    _hash = null;
  }

  public LinkInfo( String url )
  {
    _parameters = new HashMap();
    _allParameters = _parameters;
    _url = url;
  }

  // -------------------- [[Methods]] --------------------

  /**
    Get method for attribute {@link #_parameters}.
    The set of parameters to add to the url
  */
  public Map getParameters()
  {
    return _allParameters;
  }

  /**
    Set method for attribute {@link #_parameters}.
    The set of parameters to add to the url
  */
  public void setParameters( Map map )
  {
    _parameters.clear();
    _parameters.putAll( map );
    _allParameters = _parameters;
  }

  /**
    Get method for attribute {@link #_url}.
    The url without the parameters added
  */
  public String getUrl()
  {
    return _url;
  }

  /**
    Set method for attribute {@link #_url}.
    The url without the parameters added
  */
  public void setUrl( String value )
  {
    _url = value;
  }

  public void addParameter( String name, String value )
  {
    _allParameters.put( name, value );
  }

  public void addParameter( String name, int value )
  {
    _allParameters.put( name, new Integer( value ) );
  }

  public void addParameter( String name, Object value )
  {
    if ( value == null ) {
      addParameter( name, "" );
    } else {
      addParameter( name, value.toString() );
    }
  }

  public void addParameters( Map map )
  {
    _parameters.putAll( map );
  }


  public void setHash( String value )
  {
    _hash = value;
  }

  public String getHash()
  {
    return _hash;
  }

  public void setContextPath( String value )
  {
    _contextPath = value;
  }

  public String getContextPath()
  {
    return _contextPath;
  }


  public String getLinkHref()
  {
    if ( _hash == null ) {
      return completeUrl( _contextPath, _url, _allParameters );
    } else {
      return completeUrl( _contextPath, _url, _allParameters ) + "#" + _hash;
    }

  }

  public void begin()
  {
    _allParameters = new HashMap( _parameters );
  }

  public void end()
  {
    _allParameters = _parameters;
  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class LinkInfo ----------

