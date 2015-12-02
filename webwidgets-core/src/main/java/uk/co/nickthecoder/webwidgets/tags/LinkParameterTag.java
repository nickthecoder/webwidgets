/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
*/

public class LinkParameterTag extends TagSupport
{
    private static final long serialVersionUID = 663029017486676502L;

    private String _name;

    private String _value;

    public LinkParameterTag()
    {
        super();

        initialise();
    }

    private void initialise()
    {
        _name = null;
        _value = null;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    public void setName( String name )
    {
        _name = name;
    }

    public String getName()
    {
        return _name;
    }

    public void setValue( String value )
    {
        _value = value;
    }

    public String getValue()
    {
        return _value;
    }

    public int doEndTag() throws JspException
    {
        LinkInfo linkInfo = LinkInfoTag.getAncestorLinkInfo(this);
        // System.out.println( "Adding parameter " + getName() + "=" + getValue() + " to " + linkInfo );
        linkInfo.addParameter(getName(), getValue());

        return EVAL_BODY_INCLUDE;
    }

}
