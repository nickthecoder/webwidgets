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
public class BoxContentTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public BoxContentTag()
  {
    super();
  }

  // -------------------- [[Methods]] --------------------

  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.print( "<div class=\"ww_boxContent\">" );

      return EVAL_BODY_INCLUDE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

  }


  public int doEndTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.println( "</div>" ); // End <div class="ww_boxContent">

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class BoxContentTag ----------
