/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import javax.servlet.jsp.JspException;

/**
 * Iterator tag, which creates a HTML A tag for the set of pages before or after the current page.
 */

public class PagerLinksTag extends PagerChildTag

implements HasLinkHref
{

    private static final long serialVersionUID = -3817351325330163433L;

    public static final String TYPE_PREVIOUS = "previous";

    public static final String TYPE_BEFORE = "before";

    public static final String TYPE_CURRENT = "current";

    public static final String TYPE_AFTER = "after";

    public static final String TYPE_NEXT = "next";

    private String _type;

    public PagerLinksTag()
    {
        super();

        initialise();
    }

    private void initialise()
    {
        _type = null;
    }

    public String getType()
    {
        return _type;
    }

    public void setType( String type )
    {
        _type = type;
    }

    public String getLinkHref() throws JspException
    {
        // @MORE@ Add the parameters and the page number.
        // String url = LinkInfoTag.getAncestorLinkHref( this );
        LinkInfo linkInfo = LinkInfoTag.getAncestorLinkInfo(this);
        linkInfo.addParameter(getPagerTag().getPageParameterName(), getPagerTag().getPageNumber());

        return linkInfo.getLinkHref();

        // System.out.println( "parent url : " + url );
        /*
            return PlainLinkInfo.completeUrl(
              url,
              getPagerTag().getPageParameterName(),
              Integer.toString(getPagerTag().getPageNumber()) );
              */
    }

    public int doStartTag() throws JspException
    {
        PagerTag pagerTag = getPagerTag();

        if (TYPE_BEFORE.equals(getType())) {

            if (pagerTag.getPage() <= 1) {
                return SKIP_BODY;
            } else {
                if (pagerTag.getPage() > pagerTag.getPreviousPages()) {
                    pagerTag.setPageNumber(pagerTag.getPage() - pagerTag.getPreviousPages());
                } else {
                    pagerTag.setPageNumber(1);
                }
                return EVAL_BODY_INCLUDE;
            }

        } else if (TYPE_PREVIOUS.equals(getType())) {
            if (pagerTag.getPage() <= 1) {
                return SKIP_BODY;
            } else {
                pagerTag.setPageNumber(pagerTag.getPage() - 1);
                return EVAL_BODY_INCLUDE;
            }

        } else if (TYPE_CURRENT.equals(getType())) {

            if (pagerTag.getPages() > 1) {
                getPagerTag().setPageNumber(getPagerTag().getPage());
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }

        } else if ((TYPE_AFTER.equals(getType())) || (TYPE_NEXT.equals(getType()))) {

            if (pagerTag.getPage() >= pagerTag.getPages()) {
                return SKIP_BODY;
            } else {
                pagerTag.setPageNumber(pagerTag.getPage() + 1);
                return EVAL_BODY_INCLUDE;
            }

        } else {
            throw new JspException("Unknown type : " + getType());
        }

    }

    public int doAfterBody() throws JspException
    {
        PagerTag pagerTag = getPagerTag();

        pagerTag.setPageNumber(pagerTag.getPageNumber() + 1);

        if ((TYPE_BEFORE.equals(getType())) && (pagerTag.getPageNumber() < pagerTag.getPage())) {
            return EVAL_BODY_AGAIN;
        }

        if ((TYPE_AFTER.equals(getType())) && (pagerTag.getPageNumber() <= pagerTag.getPages()) &&
            (pagerTag.getPageNumber() <= pagerTag.getPage() + pagerTag.getNextPages())) {

            return EVAL_BODY_AGAIN;
        }

        return SKIP_BODY;
    }

}
