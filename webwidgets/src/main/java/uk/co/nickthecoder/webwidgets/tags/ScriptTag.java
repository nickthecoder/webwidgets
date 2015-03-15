/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Includes javascript code only once per page
 */
public class ScriptTag extends TagSupport
{

    private static final long serialVersionUID = -6858324623865429007L;

    public static final String INCLUDED_SET_NAME = "webwidgets.tags.ScriptTag";

    /**
     * Should request.getContextPath be added to the beginning of the src url.
     */
    private boolean _useContextPath;

    /**
     * The url of the script
     */
    private String _src;

    /**
     * If you want the tag to include the "language" attribute, then use this attribute,
     * however, the "language" attribute is NOT valid for strict xhtml, so the "type"
     * attribute is preferred. The default language is null, which means the attribute
     * will not be rendered.
     */
    private String _language;

    /**
     * The type of file, defaults to "text/javascript"
     */
    private String _type;

    public ScriptTag()
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
        _language = null;
        _useContextPath = true;
        _type = "text/javascript";
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

    /**
     * Get method for attribute {@link #_src}.
     * The url of the script
     */
    public String getSrc()
    {
        return _src;
    }

    /**
     * Set method for attribute {@link #_src}.
     * The url of the script
     */
    public void setSrc( String value )
    {
        _src = value;
    }

    /**
     * Get method for attribute {@link #_language}.
     * The type of script, defaults to "javascript"
     */
    public String getLanguage()
    {
        return _language;
    }

    /**
     * Set method for attribute {@link #_language}.
     * The type of script, defaults to "javascript"
     */
    public void setLanguage( String value )
    {
        _language = value;
    }

    protected boolean includedOnPage( String url )
    {
        Set includedSet = (Set) pageContext.getAttribute(INCLUDED_SET_NAME);
        if (includedSet == null) {
            includedSet = new TreeSet();
            pageContext.setAttribute(INCLUDED_SET_NAME, includedSet);
        }

        if (includedSet.contains(url)) {
            return true;
        } else {
            includedSet.add(url);
            return false;
        }

    }

    public int doEndTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();
            String url = TagUtil.resolveUrl(pageContext, getSrc(), getUseContextPath(), false);

            if (!includedOnPage(url)) {

                out.print("<script");
                if (getLanguage() != null) {
                    TagUtil.printAttribute(out, "language", getLanguage());
                }
                TagUtil.printAttribute(out, "type", getType());
                TagUtil.printAttribute(out, "src", url);
                out.print("></script>");
            }

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
