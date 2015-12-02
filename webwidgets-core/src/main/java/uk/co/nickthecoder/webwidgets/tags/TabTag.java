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
import javax.servlet.jsp.tagext.BodyTagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * @MORE@ Add javadoc comments here
 */
public class TabTag extends BodyTagSupport
{

    private static final long serialVersionUID = 6658948899986334923L;

    private String _id;

    private String _styleClass;

    private String _pattern;

    private boolean _useContextPath;

    private Boolean _test;

    public TabTag()
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
        _pattern = null;
        _test = null;
        _useContextPath = true;
        _id = null;
        _styleClass = null;
    }

    public String getId()
    {
        return _id;
    }

    public void setId( String id )
    {
        _id = id;
    }

    public String getStyleClass()
    {
        return _styleClass;
    }

    public void setStyleClass( String value )
    {
        _styleClass = value;
    }

    public boolean getUseContextPath()
    {
        return _useContextPath;
    }

    public void setUseContextPath( boolean value )
    {
        _useContextPath = value;
    }

    public String getPattern()
    {
        return _pattern;
    }

    public void setPattern( String value )
    {
        _pattern = value;
    }

    public void setTest( Boolean value )
    {
        _test = value;
    }

    public Boolean getTest()
    {
        return _test;
    }

    protected boolean isOn()
    {
        if (_pattern == null) {

            if (getTest() == null) {
                return false;
            } else {
                return getTest().booleanValue();
            }

        }

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String url = TagUtil.getUrl(request);
        
        String toCompare = url.replaceAll( ".*?//",  "" ); // Strip the protocol
        toCompare = toCompare.replaceAll("^.*?/",  "/"); // Strip the server name (and port if it exists)

        if (! getUseContextPath()) {
            // Remove the context path
            if (request.getContextPath().equals("")) {
                // Do nothing for the root context.
            } else {
                toCompare = toCompare.replaceAll( "^" + request.getContextPath(), "");
            }
        }

        // System.out.println( "comparing " + toCompare + " (" + url + ") with " + getPattern() );
        return toCompare.matches(getPattern());
    }

    public int doStartTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.print("<div class=\"ww_tab");
            if (isOn()) {
                out.print(" ww_tabSelected");
            }
            if (getStyleClass() != null) {
                out.print(" ");
                TagUtil.printSafeText(out, getStyleClass());
            }
            out.println("\">");

            return EVAL_BODY_INCLUDE;

        } catch (IOException e) {
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }

    }

    public int doEndTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            out.println("</div>");

            return EVAL_PAGE;

        } catch (IOException e) {
            // @MORE@
            e.printStackTrace();
            throw new JspException("Unexpected IO Exception.");
        }
    }

}
