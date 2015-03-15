/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class LinkInfoTag extends TagSupport implements HasLinkInfo
{

    private static final long serialVersionUID = -5034364749948924866L;

    private String _href;

    /**
     * Should request.getContextPath be added to the front of urls beginning with "/"?
     */
    private boolean _useContextPath;

    private LinkInfo _linkInfo;

    public static LinkInfo getAncestorLinkInfo( Tag childTag ) throws JspException
    {
        HasLinkInfo ancestor = (HasLinkInfo) findAncestorWithClass(childTag, HasLinkInfo.class);

        if (ancestor == null) {
            throw new JspException("Ancestor Tag implementing HasLinkInfo not found");
        }

        return ancestor.getLinkInfo();
    }

    public static String getAncestorLinkHref( Tag childTag ) throws JspException
    {
        HasLinkHref ancestor = (HasLinkHref) findAncestorWithClass(childTag, HasLinkHref.class);

        if (ancestor == null) {
            throw new JspException("Ancestor Tag implementing HasLinkHref not found");
        }

        return ancestor.getLinkHref();
    }

    public LinkInfoTag()
    {
        super();

        initialise();
    }

    private void initialise()
    {
        _href = null;
        _linkInfo = new LinkInfo();
        _useContextPath = true;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    /**
     * Get method for attribute {@link #_useContextPath}.
     * Should request.getContextPath be added to the front of urls beginning with "/"?
     */
    public boolean getUseContextPath()
    {
        return _useContextPath;
    }

    /**
     * Set method for attribute {@link #_useContextPath}.
     * Should request.getContextPath be added to the front of urls beginning with "/"?
     */
    public void setUseContextPath( boolean value )
    {
        _useContextPath = value;
    }

    /**
     * Get method for attribute {@link #_href}.
     */
    public String getHref()
    {
        return _href;
    }

    /**
     * Set method for attribute {@link #_href}.
     */
    public void setHref( String value )
    {
        _href = value;
        _linkInfo.setUrl(_href);
    }

    public String getHash()
    {
        return _linkInfo.getHash();
    }

    public void setHash( String value )
    {
        _linkInfo.setHash(value);
    }

    public void setParameters( Map parameters )
    {
        _linkInfo.setParameters(parameters);
    }

    public LinkInfo getLinkInfo()
    {
        return _linkInfo;
    }

    public String getLinkHref() throws JspException
    {
        return getLinkInfo().getLinkHref();
    }

    public int doStartTag() throws JspException
    {
        LinkInfo linkInfo = getLinkInfo();
        if (getUseContextPath()) {
            linkInfo.setContextPath(((HttpServletRequest) pageContext.getRequest()).getContextPath());
        }

        linkInfo.begin();
        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException
    {
        if (getUseContextPath()) {
            getLinkInfo().setContextPath(((HttpServletRequest) pageContext.getRequest()).getContextPath());
        }

        getLinkInfo().end();
        return EVAL_PAGE;
    }

}
