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

public class InnerBoxTag extends BodyTagSupport
{
    private static final long serialVersionUID = -7751528261535892575L;

    public boolean _minimized;

    public InnerBoxTag()
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
        _minimized = false;
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

            out.println("<!--BEGIN InnerBox-->");

            if (getMinimized()) {
                out.println("<div class=\"ww_minimized\">");
            } else {
                out.println("<div class=\"ww_maximized\">");
            }

            out.println("<div class=\"ww_innerBox\">");
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

            // This closes the BoxTitleTags "minimizable" div.
            // See BoxTitleTag for details - this is bad design :-(
            out.println("</div> <!-- innerbox end minimizable -->"); // End <div class="ww_minimizable">

            out.println("</div>"); // End <div class="innerBox">
            out.println("</div>"); // End <div class="ww_maximized">
            out.println("<!--END InnerBox-->");

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
