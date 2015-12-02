/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */
package uk.co.nickthecoder.webwidgets.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Places a piece of text in speech marks.
 * This tag was written before the ":before" and ":after" css selectors were invented/widely used.
 * Now, there is not much point to this tag, as all it does is wrap the body in a span.
 * Typical css :
 * <pre> 
 * .ww_quote:before { content: "“"; }
 * .ww_quote:after { content: "”"; }
 *
 * .ww_quote2:before { content: url('beginQuote.png'); white-space: nowrap; }
 * .ww_quote2:after { content: url('endQuote.png'); white-space: nowrap; }
 * </pre>
 */
public class QuoteTag extends BodyTagSupport
{

    private static final long serialVersionUID = 3985480119192658218L;

    public static final String DEFAULT_STYLE_CLASS = "ww_quote";

    /**
     * The css class
     */
    private String _styleClass;

    public QuoteTag()
    {
        super();

        initialise();
    }

    private void initialise()
    {
        _styleClass = DEFAULT_STYLE_CLASS;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    /**
     * Get method for attribute {@link #_styleClass}.
     * The css class
     */
    public String getStyleClass()
    {
        return _styleClass;
    }

    /**
     * Set method for attribute {@link #_styleClass}.
     * The css class
     */
    public void setStyleClass( String value )
    {
        _styleClass = value;
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.print("<span class=\"" + getStyleClass() + "\">");

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException
    {
        try {
            JspWriter out = pageContext.getOut();
            out.print("</span>");

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

        return EVAL_PAGE;
    }

}
