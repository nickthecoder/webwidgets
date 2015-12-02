/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class BrowserTag extends TagSupport
{
    private static final long serialVersionUID = 1125159996123483821L;

    private static final String[] _mobilePatternStrings = { "ipod", "iphone", "android" };

    private static List<Pattern> _mobilePatterns;

    private static final String[] _versionPrefixes = { "msie", "firefox/" };

    private String _supportsPng;

    private String _isIe;

    private String _isMobile;

    private String _version;

    public static float checkVersion( PageContext pageContext )
    {
        HttpServletRequest request = ((HttpServletRequest) pageContext.getRequest());
        String userAgent = request.getHeader("User-Agent").toLowerCase();

        // System.out.println("UserAgent " + userAgent );

        for (int i = 0; i < _versionPrefixes.length; i++) {

            // System.out.println("Checking " + _versionPrefixes[i] );

            int index = userAgent.indexOf(_versionPrefixes[i]);
            if (index >= 0) {

                // System.out.println("Found prefix " + _versionPrefixes[i] );

                int start = index + _versionPrefixes[i].length();
                int j;
                for (j = start; j < userAgent.length(); j++) {
                    char c = userAgent.charAt(j);
                    if (Character.isWhitespace(c) || Character.isDigit(c) || c == '.') {
                    } else {
                        break;
                    }

                    // System.out.println("start,j " + start + "," + j );

                    String ver = userAgent.substring(start, j).trim();
                    // System.out.println("ver" + ver );
                    try {
                        float result = Float.parseFloat(ver);
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

        for (Iterator<Pattern> i = getMobilePatterns().iterator(); i.hasNext();) {
            Pattern pattern = (Pattern) (i.next());
            Matcher matcher = pattern.matcher(userAgent);
            if (matcher.find()) {
                return true;
            }
        }

        return false;
    }

    public static List<Pattern> getMobilePatterns()
    {
        if (_mobilePatterns == null) {
            _mobilePatterns = new ArrayList<Pattern>(_mobilePatternStrings.length);
            for (int i = 0; i < _mobilePatternStrings.length; i++) {
                _mobilePatterns.add(Pattern.compile(_mobilePatternStrings[i]));
            }
        }

        return _mobilePatterns;
    }

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

    public int doEndTag() throws JspException
    {

        if (_supportsPng != null) {
            pageContext.getRequest().setAttribute(_supportsPng, new Boolean(checkSupportsPng(pageContext)));
        }

        if (_isIe != null) {
            pageContext.getRequest().setAttribute(_isIe, new Boolean(checkIsIe(pageContext)));
        }

        if (_isMobile != null) {
            pageContext.getRequest().setAttribute(_isMobile, checkMobile(pageContext));
        }

        if (_version != null) {
            pageContext.getRequest().setAttribute(_version, checkVersion(pageContext));
        }

        return EVAL_PAGE;
    }

}
