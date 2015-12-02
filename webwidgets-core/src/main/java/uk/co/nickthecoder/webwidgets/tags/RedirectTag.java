/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Redirects to a page, usually used after a POST.
 */

public class RedirectTag extends TagSupport
{
    private static final long serialVersionUID = -2317383051363690412L;

    private String _url;

    private boolean _useContextPath;

    private String _parameterName;

    private String _parameterValue;

    public RedirectTag()
    {
        super();
        initialise();
    }

    private void initialise()
    {
        _url = null;
        _parameterName = null;
        _parameterValue = null;
        _useContextPath = true;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    public void setUrl( String value )
    {
        _url = value;
    }

    public String getUrl()
    {
        return _url;
    }

    public boolean getUseContextPath()
    {
        return _useContextPath;
    }

    public void setUseContextPath( boolean value )
    {
        _useContextPath = value;
    }

    public void setParameterName( String value )
    {
        _parameterName = value;
    }

    public String getParameterName()
    {
        return _parameterName;
    }

    public void setParameterValue( String value )
    {
        _parameterValue = value;
    }

    public String getParameterValue()
    {
        return _parameterValue;
    }

    public int doEndTag() throws JspException
    {
        try {
            // System.out.println( "Redirecting to : " + getLocation() );
            ((HttpServletResponse) pageContext.getResponse()).sendRedirect(getLocation());
            // System.out.println( "ok" );
        } catch (Exception e) {
            System.err.println("Failed to redirect to : " + getLocation());
            throw new JspException(e);
        }

        // return SKIP_PAGE;
        return SKIP_BODY;
    }

    protected String getLocation()
    {
        String url = TagUtil.resolveUrl(pageContext, getUrl(), getUseContextPath());

        if (getParameterName() != null) {
            url = LinkInfo.completeUrl(null, url, getParameterName(), getParameterValue());
        }

        return url;
    }

}
