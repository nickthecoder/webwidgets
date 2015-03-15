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
 * Swaps an image when the mouse rolls over it.
 */

public class ImageRolloverTag extends TagSupport
{

    private static final long serialVersionUID = -6437313654711676598L;

    /**
     * If true, then request.getContextPath() is appended to the front of the src urls when they begin with '/'
     */
    private boolean _useContextPath;

    /**
     * The url for the normal image
     */
    private String _src;

    /**
     * The url for the rollover image
     */
    private String _rollover;

    /**
     * The css style for the img tag
     */
    private String _styleClass;

    /**
     * The alt attribute for the img tag - wc3 say you must include an alt :-)
     */
    private String _alt;

    public ImageRolloverTag()
    {
        super();

        initialise();
    }

    public void release()
    {
        super.release();
        initialise();
    }

    private void initialise()
    {
        _src = null;
        _rollover = null;

        _alt = null;
        _useContextPath = true;
        _styleClass = null;
    }

    public String getAlt()
    {
        return _alt;
    }

    public void setAlt( String value )
    {
        _alt = value;
    }

    /**
     * Get method for attribute {@link #_useContextPath}.
     * If true, then request.getContextPath() is appended to the front of the src urls when they begin with '/'
     */
    public boolean getUseContextPath()
    {
        return _useContextPath;
    }

    /**
     * Set method for attribute {@link #_useContextPath}.
     * If true, then request.getContextPath() is appended to the front of the src urls when they begin with '/'
     */
    public void setUseContextPath( boolean value )
    {
        _useContextPath = value;
    }

    /**
     * Get method for attribute {@link #_offSrc}.
     * The url for the OFF image
     */
    public String getRollover()
    {
        return _rollover;
    }

    /**
     * Set method for attribute {@link #_rollover}.
     * The url for the OFF image
     */
    public void setRollover( String value )
    {
        _rollover = value;
    }

    /**
     * Get method for attribute {@link #_src}.
     * The url for the ON image
     */
    public String getSrc()
    {
        return _src;
    }

    /**
     * Set method for attribute {@link #_onSrc}.
     * The url for the ON image
     */
    public void setSrc( String value )
    {
        _src = value;
    }

    /**
     * Get method for attribute {@link #_styleClass}.
     * The css style for the img tag
     */
    public String getStyleClass()
    {
        return _styleClass;
    }

    /**
     * Set method for attribute {@link #_styleClass}.
     * The css style for the img tag
     */
    public void setStyleClass( String value )
    {
        _styleClass = value;
    }

    public String getUrl()
    {
        return TagUtil.resolveUrl(pageContext, getSrc(), getUseContextPath());
    }

    public String getRolloverUrl()
    {
        return TagUtil.resolveUrl(pageContext, getRollover(), getUseContextPath());
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.print("<img");
            if (getAlt() != null) {
                out.print(" alt=\"" + getAlt() + "\"");
            }
            if (getStyleClass() != null) {
                out.print(" class=\"" + getStyleClass() + "\"");
            }
            out.print(" onmouseover=\"ww_changeImage( this, '" + getRolloverUrl() + "' );\"");
            out.print(" onmouseout=\"ww_restoreImage( this );\" ");
            out.print(" src=\"" + getUrl() + "\"");
            out.print("/>");

            out.print("<script type=\"text/javascript\" language=\"javascript\">ww_onloadNotifier.add( function() { ww_cacheImage('");
            out.print(getRolloverUrl());
            out.print("'); } );</script>");

            return EVAL_BODY_INCLUDE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

    }

}
