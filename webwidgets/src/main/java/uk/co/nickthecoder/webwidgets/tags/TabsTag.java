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

public class TabsTag extends BodyTagSupport
{
    private static final long serialVersionUID = 7466869496795223005L;

    public static final String DEFAULT_CLASS_NAME = "ww_tabs";

    private String _id;

    private String _className;

    public TabsTag()
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
        _id = null;
        _className = null;
    }

    public String getId()
    {
        return _id;
    }

    public void setId( String id )
    {
        _id = id;
    }

    public String getClassName()
    {
        return _className;
    }

    public void setClassName( String className )
    {
        _className = className;
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.print("<div");
            TagUtil.printSafeAttribute(out, "class", getClassName());
            TagUtil.printSafeAttribute(out, "id", getId());
            out.println(">");
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

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
