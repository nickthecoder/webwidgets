/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ContextPathTag extends TagSupport
{
    private static final long serialVersionUID = 232578931591510556L;

    public ContextPathTag()
    {
        super();
    }

    public int doEndTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.print(((HttpServletRequest) pageContext.getRequest()).getContextPath());

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

    }

}
