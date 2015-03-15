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
 */
public class BoxContentTag extends BodyTagSupport
{

    private static final long serialVersionUID = -3266219608728031644L;

    public BoxContentTag()
    {
        super();
    }

    @Override
    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = this.pageContext.getOut();

            out.print("<div class=\"ww_boxContent\">");

            return EVAL_BODY_INCLUDE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

    }

    @Override
    public int doEndTag() throws JspException
    {
        try {

            JspWriter out = this.pageContext.getOut();

            out.println("</div>"); // End <div class="ww_boxContent">

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
