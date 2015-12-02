/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Buffers the output from a servlet/jsp page, first used by wiki VisualPlugins that
 * want to use jsp pages to render their view. The results of the jsp page can be
 * read as a string buffer using the getBuffer() method.
 * 
 * Note. This class may be bug ridden. I have no idea how many of the HttpServletRepsonse
 * methods I should be intercepting. YOU HAVE BEEN WARNED!
 */
public class BufferedHttpServletResponse extends HttpServletResponseWrapper
{

    private StringWriter _writer;

    private PrintWriter _printWriter;

    public BufferedHttpServletResponse( HttpServletResponse response )
    {
        super(response);
        _writer = new StringWriter();
        _printWriter = new PrintWriter(_writer);
    }

    public PrintWriter getWriter() throws IOException
    {
        return _printWriter;
    }

    public StringBuffer getBuffer()
    {
        return _writer.getBuffer();
    }

}
