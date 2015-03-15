/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

// {{{ imports
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

// }}}

/**
 * Adds a breadcrumb to the List held in the session.
 */

public class BreadcrumbTag extends TagSupport
{

    private static final long serialVersionUID = -4619767805136570662L;

    private String _label;

    private String _url;

    public BreadcrumbTag()
    {
        _label = null;
        _url = null;
    }

    public void release()
    {
    }

    public String getLabel()
    {
        return _label;
    }

    public void setLabel( String value )
    {
        _label = value;
    }

    public String getUrl()
    {
        return _url;
    }

    public void setUrl( String value )
    {
        _url = value;
    }

    public int doStartTag() throws JspException
    {
        try {

            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

            List list = BreadcrumbsTag.getBreadcrumbs(pageContext);

            String url = getUrl();
            if (url == null) {
                url = TagUtil.getUrl(request);
            }
            Crumb crumb = new Crumb(getLabel(), url);
            addCrumb(list, crumb);

            return EVAL_PAGE;

        } catch (Exception e) {
            // @MORE@
            System.err.println(e);
            e.printStackTrace();
            throw new JspException(e);
        }
    }

    protected void addCrumb( List list, Crumb crumb )
    {

        for (Iterator i = list.iterator(); i.hasNext();) {
            Crumb existing = (Crumb) i.next();
            if (existing.getUrl().equals(crumb.getUrl())) {
                i.remove();
            }
        }

        list.add(crumb);

        while (list.size() > BreadcrumbsTag.getNumberVisible()) {
            list.remove(0);
        }

    }

}
