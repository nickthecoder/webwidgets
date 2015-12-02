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

/**
 * Prevent jsp evaluating the body - useful for quoting jsp code.
 */
public class NoEvalTag extends BodyTagSupport
{
    private static final long serialVersionUID = 2345154346406347866L;

    private boolean _escape;

    public NoEvalTag()
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
        _escape = true;
    }

    public void setEscape( boolean value )
    {
        _escape = value;
    }

    public boolean getEscape()
    {
        return _escape;
    }

    public int doEndTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            String body = bodyContent.getString();

            if (getEscape()) {
                out.print(TagUtil.safeText(body));
            } else {
                out.println(body);
            }

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

    }

}
