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
public class BoxIconTag extends BodyTagSupport
{

    /**
     * 
     */
    private static final long serialVersionUID = 5071116883681850528L;

    /**
     */
    public BoxIconTag()
    {
        super();
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            // This may look weird, but I'm closing the parent boxTitleTag's td.
            // Also, I'm not closing my own td - boxTitleTag will close mine, so
            // everything evens out.
            out.println("</td><td class=\"ww_boxIcon\">");
            return EVAL_BODY_INCLUDE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

    }

}
