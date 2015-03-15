/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Base class for all child tags of the PagerTag
 */

public class PagerChildTag extends BodyTagSupport
{

    private static final long serialVersionUID = 6122414126645900562L;

    public PagerChildTag()
    {
        super();
    }

    public PagerTag getPagerTag() throws JspException
    {
        PagerTag pagerTag = (PagerTag) TagSupport.findAncestorWithClass(this, PagerTag.class);
        if (pagerTag == null) {
            throw new JspException("Ancestor PagerTag not found");
        }

        return pagerTag;
    }

}
