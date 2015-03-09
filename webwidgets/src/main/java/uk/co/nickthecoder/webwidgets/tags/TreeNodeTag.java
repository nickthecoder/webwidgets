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
public class TreeNodeTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private boolean _expandable;

  private boolean _minimized;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public TreeNodeTag()
  {
    super();

    initialise();
  }

  private void initialise()
  {
    _expandable = true;
    _minimized = false;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------


  public boolean getExpandable()
  {
    return _expandable;
  }

  public void setExpandable( boolean expandable )
  {
    _expandable = expandable;
  }

  public boolean getMinimized()
  {
    return _minimized;
  }

  public void setMinimized( boolean value )
  {
    _minimized = value;
  }


  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.println( "<div class=\"" + (getMinimized() ? "ww_minimized" : "ww_maximized") + "\">" );
      out.println( "<div class=\"ww_treeNode\">" );
      out.println( "<div class=\"ww_treeNodeButton\">" );
      if ( getExpandable() ) {
        out.println( StandardButtonTag.makeButton( pageContext, StandardButtonTag.CONTRACT_BUTTON ) );
        out.println( StandardButtonTag.makeButton( pageContext, StandardButtonTag.EXPAND_BUTTON ) );
      } else {
        out.println( StandardButtonTag.makeButton( pageContext, StandardButtonTag.DISABLED_BUTTON ) );
      }
      out.println( "</div>" );

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

      out.println( "</div>" ); // End <div class="ww_treeNode">
      out.println( "</div>" ); // End <div class="ww_maximized">

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class TreeNodeTag ----------

