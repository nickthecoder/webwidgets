/*
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

*/

package uk.co.nickthecoder.webwidgets.util;

import java.io.*;
import javax.servlet.http.*;

/**
  Buffers the ouput from a servlet/jsp page, first used by wiki VisualPlugins that
  want to use jsp pages to render their view. The results of the jsp page can be
  read as a string buffer using the getBuffer() method.

  Note. This class may be bug ridden. I have no idea how many of the HttpServletRepsonse
  methods I should be intercepting. YOU HAVE BEEN WARNED!
*/

public class BufferedHttpServletResponse
  extends HttpServletResponseWrapper
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Methods]] --------------------

  private StringWriter _writer;

  private PrintWriter _printWriter;

  public BufferedHttpServletResponse( HttpServletResponse response )
  {
    super( response );
    _writer = new StringWriter();
    _printWriter = new PrintWriter( _writer );
  }

  public PrintWriter getWriter()
    throws IOException
  {
    return _printWriter;
  }

  public StringBuffer getBuffer()
  {
    return _writer.getBuffer();
  }

}
// ---------- End Of Interface BufferedHttpServletResponse ----------

