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

public class TreeNodeContentTag extends BodyTagSupport
{

    private static final long serialVersionUID = -888850057007105253L;

    private boolean _last;

    public TreeNodeContentTag()
    {
        super();
    }

    public boolean getLast()
    {
        return _last;
    }

    public void setLast( boolean last )
    {
        _last = last;
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.println("<div class=\"ww_minimizable\">");
            out.println("<div class=\"" + (getLast() ? "ww_treeNodeLastContent" : "ww_treeNodeContent") + "\">");

            return EVAL_BODY_INCLUDE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

    }

    public int doEndTag() throws JspException
    {
        try {
            JspWriter out = pageContext.getOut();

            out.println("</div>");
            out.println("</div>");

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
