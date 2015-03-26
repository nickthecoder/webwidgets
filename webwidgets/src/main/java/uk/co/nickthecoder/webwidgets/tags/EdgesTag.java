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

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Creates a box, which has inner divs with prefefined css classes, designed so that
 * a pretty box can be made using background images within each div.
 */
public class EdgesTag extends BodyTagSupport
{
    private static final long serialVersionUID = 7859666195439464756L;

    private String _styleClass;

    private String _width;

    public EdgesTag()
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
        _styleClass = null;
        _width = null;
    }

    public String getStyleClass()
    {
        return _styleClass;
    }

    public void setStyleClass( String value )
    {
        _styleClass = value;
    }

    public String getWidth()
    {
        return _width;
    }

    public void setWidth( String width )
    {
        _width = width;
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.print("<div");
            if (_styleClass != null) {
                TagUtil.printSafeAttribute(out, "class", _styleClass);
            }

            if (getWidth() != null) {
                TagUtil.printAttribute(out, "style", "width: " + getWidth() + ";");
            }
            out.println(">");
            out.println("<div class=\"ww_edges\">");

            out.println("<div class=\"ww_top\"><div class=\"ww_left\"><div class=\"ww_right\"><div class=\"ww_center\"></div></div></div></div>");
            out.print("<div class=\"ww_middle\"><div class=\"ww_left\"><div class=\"ww_right\"><div class=\"ww_center\">");
            out.print("<div class=\"ww_enforceMargins\"></div>");
            out.println("<div class=\"ww_content\">");

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

            out.print("</div>");
            out.print("<div class=\"ww_enforceMargins\"></div>");
            out.println("</div></div></div></div>");
            out.println("<div class=\"ww_bottom\"><div class=\"ww_left\"><div class=\"ww_right\"><div class=\"ww_center\"></div></div></div></div>");
            out.println("</div></div>");

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
