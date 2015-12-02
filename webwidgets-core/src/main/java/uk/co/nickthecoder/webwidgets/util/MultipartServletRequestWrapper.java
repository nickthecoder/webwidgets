/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Buffers the output from a servlet/jsp page, first used by wiki VisualPlugins that
 * want to use jsp pages to render their view. The results of the jsp page can be
 * read as a string buffer using the getBuffer() method.
 * 
 * Note. This class may be bug ridden. I have no idea how many of the HttpServletRepsonse
 * methods I should be intercepting. YOU HAVE BEEN WARNED!
 */
public class MultipartServletRequestWrapper extends HttpServletRequestWrapper
{

    private MultipartHelper _multipartHelper;

    public MultipartServletRequestWrapper( HttpServletRequest request, MultipartHelper multipartHelper )
    {
        super(request);
        _multipartHelper = multipartHelper;
    }

    public String getParameter( String name )
    {
        String result = _multipartHelper.getParameter(name);
        if (result == null) {
            return super.getParameter(name);
        } else {
            return result;
        }
    }

}
