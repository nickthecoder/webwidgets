/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.ReverseListIterator;
import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Displays the set of breadcrumb objects that are held in a session object,
 * put there by the BreadcrumbTag.
 */

public class BreadcrumbsTag extends TagSupport
{

    // -------------------- [[Static Attributes]] --------------------

    /**
     * 
     */
    private static final long serialVersionUID = -444770582412639701L;

    private static int _numberVisible = 10;

    private static final String SESSION_ATTRIBUTE = "BreadcrumbsTag";

    // -------------------- [[Attributes]] --------------------

    private boolean _backwards;

    private String _separator;

    // -------------------- [[Static Methods]] --------------------

    public static int getNumberVisible()
    {
        return _numberVisible;
    }

    public static void setNumberVisible( int value )
    {
        _numberVisible = value;
    }

    public static List getBreadcrumbs( PageContext pageContext )
    {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = request.getSession();

        List list = (List) session.getAttribute(SESSION_ATTRIBUTE);
        if (list == null) {
            list = new LinkedList();
            session.setAttribute(SESSION_ATTRIBUTE, list);
        }

        return list;
    }

    // -------------------- [[Constructors]] --------------------

    /**
  */
    public BreadcrumbsTag()
    {
        _backwards = false;
        _separator = "";
    }

    public void release()
    {
    }

    // -------------------- [[Methods]] --------------------

    public boolean getBackwards()
    {
        return _backwards;
    }

    public void setBackwards( boolean value )
    {
        _backwards = value;
    }

    public void setSeparator( String value )
    {
        _separator = value;
    }

    public String getSeparator()
    {
        return _separator;
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            List list = getBreadcrumbs(pageContext);

            boolean first = true;

            for (Iterator i = ReverseListIterator.getListIterator(list, getBackwards()); i.hasNext();) {
                Crumb crumb = (Crumb) i.next();

                out.print("<a class=\"ww_crumb");
                if (first) {
                    out.println(" ww_firstCrumb");
                    first = false;
                } else {
                    if (i.hasNext()) {
                        out.println(" ww_lastCrumb");
                    }
                }
                out.print("\"");
                TagUtil.printSafeAttribute(out, "href", crumb.getUrl());
                out.print(">");

                TagUtil.printSafeText(out, crumb.getLabel());

                out.println("</a>");

                if (i.hasNext()) {
                    TagUtil.printSafeText(out, _separator);
                }

            }

            return EVAL_PAGE;

        } catch (Exception e) {
            // @MORE@
            System.err.println(e);
            e.printStackTrace();
            throw new JspException(e);
        }
    }

}

