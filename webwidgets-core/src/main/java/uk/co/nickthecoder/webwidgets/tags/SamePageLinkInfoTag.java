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

public class SamePageLinkInfoTag extends TagSupport implements HasLinkInfo
{

    private static final long serialVersionUID = -8617581890910819399L;

    private LinkInfo _linkInfo;

    public SamePageLinkInfoTag()
    {
        super();

        initialise();
    }

    private void initialise()
    {
        _linkInfo = new LinkInfo();
    }

    public void release()
    {
        super.release();
        initialise();
    }

    /**
     * Get method for attribute {@link #_href}.
     */
    public String getPageUrl()
    {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        // System.out.println( "URI : " + request.getRequestURI() );
        // System.out.println( "URL : " + request.getRequestURL() );

        String result = request.getRequestURL().toString();
        int firstQ = result.indexOf("?");
        if (firstQ > 0) {
            return result.substring(0, firstQ - 1);
        } else {
            return result;
        }
    }

    public LinkInfo getLinkInfo()
    {
        // System.out.println( "SamePage:getLinkInfo:" + _linkInfo );
        return _linkInfo;
    }

    public String getLinkHref() throws JspException
    {
        return TagUtil.resolveUrl(pageContext, getLinkInfo().getLinkHref(), false);
    }

    public int doStartTag() throws JspException
    {
        _linkInfo.setUrl(getPageUrl());
        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException
    {
        initialise();

        return super.doEndTag();
    }

}
