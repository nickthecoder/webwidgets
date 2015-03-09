// ----------------------------------------------------------------------
//
// Author        : Nick Robinson (nick)
// Creation Date : 2003-03-18
//
// ----------------------------------------------------------------------
// History
// 2003-03-18 : nick : Initial Development
//
// ----------------------------------------------------------------------

package uk.co.nickthecoder.webwidgets.tags;


import java.util.*;
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;


/**
  @MORE@ Add javadoc comments here
*/
public class BoxIconTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public BoxIconTag()
  {
    super();
  }

  // -------------------- [[Methods]] --------------------

  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      // This may look weird, but I'm closing the parent boxTitleTag's td.
      // Also, I'm not closing my own td - boxTitleTag will close mine, so
      // everything evens out.
      out.println( "</td><td class=\"ww_boxIcon\">" );
      return EVAL_BODY_INCLUDE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

  }

  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class BoxIconTag ----------
