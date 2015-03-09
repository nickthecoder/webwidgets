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
import javax.servlet.jsp.PageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;


/**
  @MORE@ Add javadoc comments here
*/
public class ContextPathTag
  extends TagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public ContextPathTag()
  {
    super();
  }

  // -------------------- [[Methods]] --------------------

  public int doEndTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.print( ((HttpServletRequest) pageContext.getRequest()).getContextPath() );

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class ContextPathTag ----------

