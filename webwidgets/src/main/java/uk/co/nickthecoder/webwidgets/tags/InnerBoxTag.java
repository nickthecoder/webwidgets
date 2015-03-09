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
public class InnerBoxTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  public boolean _minimized;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public InnerBoxTag()
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
    _minimized = false;
  }

  // -------------------- [[Methods]] --------------------

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

      out.println( "<!--BEGIN InnerBox-->" );

      if( getMinimized() ) {
        out.println( "<div class=\"ww_minimized\">" );
      } else {
        out.println( "<div class=\"ww_maximized\">" );
      }

      out.println( "<div class=\"ww_innerBox\">" );
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

      // This closes the BoxTitleTags "minimizable" div.
      // See BoxTitleTag for details - this is bad design :-(
      out.println( "</div> <!-- innerbox end minimizable -->" ); // End <div class="ww_minimizable">


      out.println( "</div>" ); // End <div class="innerBox">
      out.println( "</div>" ); // End <div class="ww_maximized">
      out.println( "<!--END InnerBox-->" );

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class InnerBoxTag ----------
