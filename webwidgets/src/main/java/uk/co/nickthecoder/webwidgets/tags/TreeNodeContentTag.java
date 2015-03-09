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
public class TreeNodeContentTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private boolean _last;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public TreeNodeContentTag()
  {
    super();
  }

  // -------------------- [[Methods]] --------------------

  public boolean getLast()
  {
    return _last;
  }

  public void setLast( boolean last )
  {
    _last = last;
  }

  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.println( "<div class=\"ww_minimizable\">" );
      out.println( "<div class=\"" + ( getLast() ? "ww_treeNodeLastContent" : "ww_treeNodeContent" ) + "\">" );

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

      out.println( "</div>" );
      out.println( "</div>" );

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class TreeNodeContentTag ----------

