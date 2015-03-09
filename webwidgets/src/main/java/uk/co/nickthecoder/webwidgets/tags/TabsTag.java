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
public class TabsTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  public static final String DEFAULT_CLASS_NAME = "ww_tabs";

  // -------------------- [[Attributes]] --------------------

  private String _className;


  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public TabsTag()
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
    _className = null;
  }

  // -------------------- [[Methods]] --------------------

  public String getClassName()
  {
    return _className;
  }

  public void setClassName( String className )
  {
    _className = className;
  }

  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.println( "<!-- BEGIN TABS -->" );

      out.println( "<div class=\"" +
        ( (getClassName() == null) ? DEFAULT_CLASS_NAME : getClassName() ) +
        "\"><table class=\"ww_layout\"><tr>" );

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

      out.println( "</tr></table></div>" );
      out.println( "<!-- END TABS -->" );

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class TabsTag ----------
