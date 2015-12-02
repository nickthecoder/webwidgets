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
*/

public class IconTag extends TagSupport
{
    private static final long serialVersionUID = 1710263566554913859L;

    /**
     * Should request.getContextPath be added to the beginning of the src url.
     */
    private boolean _useContextPath;

    /**
     * The url of the icon
     */
    private String _href;

    private boolean _encodeSessionId;

    /**
     * The type of file, defaults to "text/javascript"
     */
    private String _type;

    public IconTag()
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
        _type = null; // "image/png";
    }

    // -------------------- [[Methods]] --------------------

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

    public String getType()
    {
        return _type;
    }

    public void setType( String value )
    {
        _type = value;
    }

    /**
     * Get method for attribute {@link #_href}.
     * The url of the icon
     */
    public String getHref()
    {
        return _href;
    }

    /**
     * Set method for attribute {@link #_href}.
     * The url of the icon
     */
    public void setHref( String value )
    {
        _href = value;
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

    public int doEndTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();
            String url = TagUtil.resolveUrl(pageContext, getHref(), getUseContextPath(), getEncodeSessionId());

            out.print("<link rel=\"icon\"");
            if (getType() != null) {
                TagUtil.printAttribute(out, "type", getType());
            }
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
