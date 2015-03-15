/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class MapLinkTag extends TagSupport

implements HasLinkHref
{
    private static final long serialVersionUID = 2833759902511085996L;

    private String _provider;

    private boolean _render;

    public MapLinkTag()
    {
        super();
        initialise();
    }

    private void initialise()
    {
        _provider = null;
        _render = true;
    }

    public String getProvider()
    {
        return _provider;
    }

    public void setProvider( String provider )
    {
        _provider = provider;
    }

    public void setRender( boolean render )
    {
        _render = render;
    }

    public boolean getRender()
    {
        return _render;
    }

    public String getLinkHref() throws JspException
    {
        return getMapDetails().getUrl(getProvider());
    }

    private MapDetailsTag getMapDetails() throws JspException
    {
        Object ancestor = findAncestorWithClass(this, MapDetailsTag.class);
        if (ancestor == null) {
            throw new JspException("mapLink tags must be contained inside mapDetails tags");
        }

        return (MapDetailsTag) ancestor;
    }

    public int doStartTag() throws JspException
    {
        try {

            if (getRender()) {

                JspWriter out = pageContext.getOut();

                out.print("<a href=\"" + getLinkHref() + "\">");

            }

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException
    {
        try {

            if (getRender()) {
                JspWriter out = pageContext.getOut();

                out.println("</a>");
            }

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

        return EVAL_PAGE;
    }

    public void release()
    {
        super.release();
        initialise();
    }
}
