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
 * Includes javascript code only once per page
 */

public class StyleSheetTag extends TagSupport
{

    private static final long serialVersionUID = -8667989568856174832L;

    private String _href;

    private boolean _useContextPath;

    private boolean _encodeSessionId;

    public StyleSheetTag()
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
        _href = null;
        _useContextPath = true;
        _encodeSessionId = false;
    }

    /**
     * Get method for attribute {@link #_useContextPath}.
     * Should request.getContextPath be added to the beginning of the src url.
     */
    public boolean getUseContextPath()
    {
        return _useContextPath;
    }

    /**
     * Set method for attribute {@link #_useContextPath}.
     * Should request.getContextPath be added to the beginning of the src url.
     */
    public void setUseContextPath( boolean value )
    {
        _useContextPath = value;
    }

    public boolean getEncodeSessionId()
    {
        return _encodeSessionId;
    }

    /**
     * Set method for attribute {@link #_encodeSessionId}.
     * Should request.getContextPath be added to the beginning of the src url.
     */
    public void setEncodeSessionId( boolean value )
    {
        _encodeSessionId = value;
    }

    /**
     * Get method for attribute {@link #_href}.
     * The url of the script
     */
    public String getHref()
    {
        return _href;
    }

    /**
     * Set method for attribute {@link #_href}.
     * The url of the script
     */
    public void setHref( String value )
    {
        _href = value;
    }

    public int doEndTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();
            String url = TagUtil.resolveUrl(pageContext, getHref(), getUseContextPath(), getEncodeSessionId());

            out.print("<link rel=\"stylesheet\" type=\"text/css\"");
            TagUtil.printAttribute(out, "href", url);
            out.print("/>");

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
