/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Renders the value of the referrer http header, however, if referrer is given as
 * a url parameter (or post parameter), then that value is used.
 * 
 * NOTE. The http spec got the spelling wrong, and I was unsure whether to continue
 * this bad spelling. I chose to spell referrer correctly, and I apologise for half
 * of you that are totally confused. The only place I spell referrer incorrectly, is
 * when I read the http headers (i.e. where I NEED to spell it wrong).
 */

public class ReferrerTag extends TagSupport
{
    private static final long serialVersionUID = 4451503771388464929L;

    public ReferrerTag()
    {
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

            String referrer;
            if (request.getParameter("referrer") != null) {
                referrer = request.getParameter("referrer"); // Note the spelling is correct.
            } else {
                referrer = request.getHeader("referer"); // Note the spelling is wrong in HTTP spec!
            }

            if (referrer != null) {
                out.print(referrer);
            }

            return SKIP_BODY;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

    }
    
}

