/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Sets a variable with a boolean - true if the request is from a local ipv4 address.
 */
public class LocalTag extends TagSupport
{
    private static final long serialVersionUID = -2936791420071562384L;

    private String _var = "isLocal";

    /**
  */
    public LocalTag()
    {
        super();
    }

    public void setVar( String var )
    {
        _var = var;
    }

    public String getVar()
    {
        return _var;
    }

    public int doEndTag() throws JspException
    {

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        boolean isLocal = TagUtil.isLocal(request);

        request.setAttribute(getVar(), isLocal ? Boolean.TRUE : Boolean.FALSE);
        return EVAL_PAGE;

    }

}
