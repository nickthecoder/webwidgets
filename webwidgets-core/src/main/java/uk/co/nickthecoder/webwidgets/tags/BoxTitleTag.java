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
import uk.co.nickthecoder.webwidgets.tags.StandardButtonTag;

public class BoxTitleTag extends BodyTagSupport
{
    /**
     * 
     */
    private static final long serialVersionUID = -5998999585644062475L;

    private String _title;

    private boolean _clickable;

    private boolean _minMax;

    private boolean _close;

    public BoxTitleTag()
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
        _title = "";
        _clickable = false;
        _minMax = false;
        _close = false;
    }

    public String getTitle()
    {
        return _title;
    }

    public void setTitle( String title )
    {
        _title = title;
    }

    public boolean getClickable()
    {
        return _clickable;
    }

    public void setClickable( boolean value )
    {
        _clickable = value;
    }

    public boolean getMinMax()
    {
        return _minMax;
    }

    public void setMinMax( boolean value )
    {
        _minMax = value;
    }

    public boolean getClose()
    {
        return _close;
    }

    public void setClose( boolean value )
    {
        _close = value;
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            String onclick = getClickable() ? " onclick=\"ww_doToggleMinimize( event )\"" : "";
            String cssClass = getClickable() ? "ww_boxTitle ww_clickable" : "ww_boxTitle";
            out.println("<div class=\"" + cssClass + "\"" + onclick + ">");

            out.print(TagUtil.safeText(getTitle()));

            if (_close) {
                out.print("<div class=\"ww_boxIcon\">");
                out.println(StandardButtonTag.makeButton(pageContext, StandardButtonTag.CLOSE_BUTTON));
                out.println( "</div>" );
            }

            if (_minMax) {
                out.print("<div class=\"ww_boxIcon\">");
                out.println(StandardButtonTag.makeButton(pageContext, StandardButtonTag.MINIMIZE_BUTTON));
                out.println( "</div>" );
                out.print("<div class=\"ww_boxIcon\">");
                out.println(StandardButtonTag.makeButton(pageContext, StandardButtonTag.MAXIMIZE_BUTTON));
                out.println( "</div>" );
            }

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

            // This is closed in BoxTag (or InnerBoxTag) doEndTag
            // That menas that all BoxTags MUST have one and only one BoxTitle Tag.
            // That is VERY bad design :-(
            out.println("<div class=\"ww_minimizable\">");

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
