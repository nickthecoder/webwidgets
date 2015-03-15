/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Creates a html a tag
 */

public class LinkTag extends TagSupport
{

    private static final long serialVersionUID = -589364267447199963L;

    /**
     * The css style for the a tag
     */
    private String _styleClass;

    /**
     * The href for the a tag.
     */
    private String _href;

    /**
     * The id for the a tag.
     */
    private String _id;
    /**
     * The title for the a tag.
     */
    private String _title;

    /**
     * Should request.getContextPath() be added to the front of urls
     * beginning with "/"?
     */
    private boolean _useContextPath;

    /**
     * Add request.getContextPath() to the front to form the a tag's href.
     */
    private String _page;

    private String _onclick;

    public static LinkInfo getHrefTag( TagSupport innerTag ) throws JspException
    {
        LinkInfo ancestor = (LinkInfo) TagSupport.findAncestorWithClass(innerTag, LinkInfo.class);
        if (ancestor == null) {
            throw new JspException("Href ancestor tag not found");
        }

        return (LinkInfo) ancestor;
    }

    public LinkTag()
    {
        super();
        initialise();

    }

    private void initialise()
    {
        _href = null;
        _page = null;
        _styleClass = null;
        _useContextPath = true;
        _title = null;
        _onclick = null;
        _id = null;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    public String getId()
    {
        return _id;
    }

    public void setId( String id )
    {
        _id = id;
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
     * Get method for attribute {@link #_styleClass}.
     * The css style for the a tag
     */
    public String getStyleClass()
    {
        return _styleClass;
    }

    /**
     * Set method for attribute {@link #_styleClass}.
     * The css style for the a tag
     */
    public void setStyleClass( String value )
    {
        _styleClass = value;
    }

    public String getHref()
    {
        return _href;
    }

    public void setHref( String href )
    {
        _href = href;
    }

    public String getRealHref() throws JspException
    {
        if (_href == null) {
            return LinkInfoTag.getAncestorLinkHref(this);
        } else {
            return TagUtil.resolveUrl(pageContext, getHref(), getUseContextPath());
        }
    }

    public void setTitle( String value )
    {
        _title = value;
    }

    public String getTitle()
    {
        return _title;
    }

    public void setOnclick( String value )
    {
        _onclick = value;
    }

    public String getOnclick()
    {
        return _onclick;
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.print("<a href=\"" + getRealHref() + "\"");
            if (getOnclick() != null) {
                out.print(" onclick=\"" + getOnclick() + "\"");
            }
            if (getId() != null) {
                out.print(" id=\"" + getId() + "\"");
            }
            if (getStyleClass() != null) {
                out.print(" class=\"" + getStyleClass() + "\"");
            }
            if (getTitle() != null) {
                out.print(" title=\"" + getTitle() + "\"");
            }
            out.print(">");

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException
    {
        try {
            JspWriter out = pageContext.getOut();

            out.print("</a>");

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

        return EVAL_PAGE;
    }

}
