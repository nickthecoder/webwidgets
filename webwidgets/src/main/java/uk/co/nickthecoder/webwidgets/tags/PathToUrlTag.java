/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Converts a path to a URL, appending the webapp context path.
 * Option to make it a complete url (including the scheme, server name
 * and port).
 * Option to replace with a different url if the file isn't found.
 */

public class PathToUrlTag extends TagSupport
{

    private static final long serialVersionUID = 1667313350539369707L;
    private String _path;

    private String _notFoundPath;

    private boolean _full;

    /**
     * Changes symbol characters into the form %nn.
     */
    public static String encodeForURL( String path )
    {
        StringBuffer result = new StringBuffer();
        int from = 0;

        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);

            if ((c != '/') && (c != '.') && !Character.isLetterOrDigit(c)) {
                String hex = Integer.toHexString(c);
                result.append(path.substring(from, i));
                result.append("%").append(hex);
                from = i + 1;
            }
        }

        result.append(path.substring(from));

        return result.toString();
    }

    public PathToUrlTag()
    {
        super();
        initialise();
    }

    /**
     * Called by the constructor, and by release to set each of the tags
     * properties to their default values.
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

    /**
     * Get method for attribute {@link #_path}.
     */
    public String getPath()
    {
        return _path;
    }

    /**
     * Set method for attribute {@link #_path}.
     */
    public void setPath( String path )
    {
        _path = path;
    }

    /**
     * Get method for attribute {@link #_notFoundPath}.
     */
    public String getNotFoundPath()
    {
        return _notFoundPath;
    }

    /**
     * Set method for attribute {@link #_notFoundPath}.
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

        if (getFull()) {
            url.append(request.getScheme());
            url.append("://");
            url.append(request.getServerName());
            url.append(":");
            url.append(request.getServerPort());
        }

        String path;
        if (getNotFoundPath() != null) {
            String rootPath = context.getRealPath("/");
            File file = new File(rootPath + File.separator + getPath());
            if (file.exists()) {
                path = getPath();
            } else {
                path = getNotFoundPath();
            }
        } else {
            path = getPath();
        }

        url.append(request.getContextPath());
        url.append(encodeForURL(path));

        return url.toString().replaceAll(" ", "%20");

    }

    public int doStartTag() throws JspException
    {
        try {
            JspWriter writer = pageContext.getOut();

            String url = getUrl(pageContext);

            writer.print(url);

        } catch (Exception e) {
        }

        return EVAL_PAGE;
    }

    public int doEndTag()
    {
        return EVAL_PAGE;
    }

}
