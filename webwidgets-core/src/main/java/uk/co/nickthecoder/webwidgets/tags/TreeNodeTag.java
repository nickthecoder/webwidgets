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

public class TreeNodeTag extends BodyTagSupport
{

    private static final long serialVersionUID = -2770358280688465773L;

    private boolean _expandable;

    private boolean _minimized;

    public TreeNodeTag()
    {
        super();

        initialise();
    }

    private void initialise()
    {
        _expandable = true;
        _minimized = false;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    public boolean getExpandable()
    {
        return _expandable;
    }

    public void setExpandable( boolean expandable )
    {
        _expandable = expandable;
    }

    public boolean getMinimized()
    {
        return _minimized;
    }

    public void setMinimized( boolean value )
    {
        _minimized = value;
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.println("<div class=\"" + (getMinimized() ? "ww_minimized" : "ww_maximized") + "\">");
            out.println("<div class=\"ww_treeNode\">");
            out.println("<div class=\"ww_treeNodeButton\">");
            if (getExpandable()) {
                out.println(StandardButtonTag.makeButton(pageContext, StandardButtonTag.CONTRACT_BUTTON));
                out.println(StandardButtonTag.makeButton(pageContext, StandardButtonTag.EXPAND_BUTTON));
            } else {
                out.println(StandardButtonTag.makeButton(pageContext, StandardButtonTag.DISABLED_BUTTON));
            }
            out.println("</div>");

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

            out.println("</div>"); // End <div class="ww_treeNode">
            out.println("</div>"); // End <div class="ww_maximized">

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
