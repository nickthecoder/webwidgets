/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

public class LinkInfo
{

    /**
     * The parameters set within the tag itself
     */
    private Map<String,String> _parameters;

    /**
     * The paramters set within the tag itself, plus any extra parameters added using the
     * addParameter method.
     * These need to be held separately, because the servlet container is free to REUSE an
     * instance of LinkInfo, and so if we don't have these two maps, then if LinkParameter
     * tag is used, then one instance of this tag could interfere with another.
     */
    private Map<String,String> _allParameters;

    /**
     * The url without the parameters added
     */
    private String _url;

    /**
     * the part of the url after the # symbol
     */
    private String _hash;

    private String _contextPath;

    static String completeUrl( String contextPath, String url, Map<String,String> parameters )
    {
        if (url.startsWith("/") && (contextPath != null)) {
            url = contextPath + url;
        }

        String parameterString = buildParameterString(parameters);

        if (parameterString.length() == 0) {
            return url;
        } else {

            if (url.indexOf("?") >= 0) {
                return url + "&amp;" + parameterString;
            } else {
                return url + "?" + parameterString;
            }

        }

    }

    static String completeUrl( String contextPath, String url, String name, String value )
    {
        if (url.startsWith("/") && (contextPath != null)) {
            url = contextPath + url;
        }

        String parameterString = name + "=" + TagUtil.encodeUrl(value);

        if (url.indexOf("?") >= 0) {
            return url + "&amp;" + parameterString;
        } else {
            return url + "?" + parameterString;
        }
    }

    static String buildParameterString( Map<String,String> parameters )
    {
        if (parameters.isEmpty()) {
            return "";
        }

        StringBuffer result = new StringBuffer();

        for (Iterator<String> i = parameters.keySet().iterator(); i.hasNext();) {
            String key = i.next();
            String value = parameters.get(key);

            result.append("&amp;").append(key).append("=").append(TagUtil.encodeUrl(value));
        }

        return result.substring(5); // remove the first &amp;
    }

    public LinkInfo()
    {
        _parameters = new HashMap<String,String>();
        _allParameters = _parameters;
        _url = "";
        _hash = null;
    }

    public LinkInfo( String url )
    {
        _parameters = new HashMap<String,String>();
        _allParameters = _parameters;
        _url = url;
    }

    /**
     * Get method for attribute {@link #_parameters}.
     * The set of parameters to add to the url
     */
    public Map<String,String> getParameters()
    {
        return _allParameters;
    }

    /**
     * Set method for attribute {@link #_parameters}.
     * The set of parameters to add to the url
     */
    public void setParameters( Map<String,String> map )
    {
        _parameters.clear();
        _parameters.putAll(map);
        _allParameters = _parameters;
    }

    /**
     * Get method for attribute {@link #_url}.
     * The url without the parameters added
     */
    public String getUrl()
    {
        return _url;
    }

    /**
     * Set method for attribute {@link #_url}.
     * The url without the parameters added
     */
    public void setUrl( String value )
    {
        _url = value;
    }

    public void addParameter( String name, String value )
    {
        _allParameters.put(name, value);
    }

    public void addParameter( String name, int value )
    {
        _allParameters.put(name, Integer.toString(value));
    }

    public void addParameter( String name, Object value )
    {
        if (value == null) {
            addParameter(name, "");
        } else {
            addParameter(name, value.toString());
        }
    }

    public void addParameters( Map<String,String> map )
    {
        _parameters.putAll(map);
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
        if (_hash == null) {
            return completeUrl(_contextPath, _url, _allParameters);
        } else {
            return completeUrl(_contextPath, _url, _allParameters) + "#" + _hash;
        }

    }

    public void begin()
    {
        _allParameters = new HashMap<String,String>(_parameters);
    }

    public void end()
    {
        _allParameters = _parameters;
    }

}
