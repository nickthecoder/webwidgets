/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

public class MapTag extends MapDetailsTag

implements HasLinkHref

{
    private static final long serialVersionUID = 8499828199001112044L;

    private String _provider;

    private boolean _render;

    public MapTag()
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
        return super.getUrl(getProvider());
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
