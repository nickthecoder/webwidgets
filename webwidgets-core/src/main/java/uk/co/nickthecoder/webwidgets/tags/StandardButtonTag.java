/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class StandardButtonTag extends BodyTagSupport
{

    private static final long serialVersionUID = 5852092210634501848L;

    public static final String MINIMIZE_BUTTON = "minimize";

    public static final String MAXIMIZE_BUTTON = "maximize";

    public static final String CLOSE_BUTTON = "close";

    public static final String EXPAND_BUTTON = "expand";

    public static final String CONTRACT_BUTTON = "contract";

    public static final String DISABLED_BUTTON = "disabled";

    private String _buttonType;

    static String makeButton( PageContext pageContext, String buttonType )
    {
        if (buttonType.equals(DISABLED_BUTTON)) {
            return "";
        } else {
            return makeLink(pageContext, buttonType) + "</a>";
        }
    }

    static String makeLink( PageContext pageContext, String buttonType )
    {
        if (MINIMIZE_BUTTON.equals(buttonType)) {
            return "<a class=\"ww_button ww_minButton\" href=\"#\" onclick=\"javascript: return ww_doMinimize( event )\">";
        } else if (MAXIMIZE_BUTTON.equals(buttonType)) {
            return "<a class=\"ww_button ww_maxButton\" href=\"#\" onclick=\"javascript: return ww_doMaximize( event )\">";
        } else if (CLOSE_BUTTON.equals(buttonType)) {
            return "<a class=\"ww_button ww_closeButton\" href=\"#\" onclick=\"javascript: return ww_doClose( event )\">";
        } else if (EXPAND_BUTTON.equals(buttonType)) {
            return "<a class=\"ww_button ww_expandButton\" href=\"#\" onclick=\"javascript: return ww_doMaximize( event )\">";
        } else if (CONTRACT_BUTTON.equals(buttonType)) {
            return "<a class=\"ww_button ww_contractButton\" href=\"#\" onclick=\"javascript: return ww_doMinimize( event )\">";
        } else if (DISABLED_BUTTON.equals(buttonType)) {
            return "";
        } else {
            return "<a class=\"ww_button " + buttonType + "\" href=\"#\">";
        }
    }

    public String getType()
    {
        return _buttonType;
    }

    public void setType( String buttonType )
    {
        _buttonType = buttonType;
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.print(makeLink(pageContext, _buttonType));

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

            if (!DISABLED_BUTTON.equals(_buttonType)) {
                out.print("</a>");
            }

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

    }

}
