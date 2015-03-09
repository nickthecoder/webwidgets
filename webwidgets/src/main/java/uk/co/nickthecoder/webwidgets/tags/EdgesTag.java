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

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
  Creates a box, which has inner divs with prefefined css classes, designed so that
  a pretty box can be made using background images within each div.
*/
public class EdgesTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private String _className;

  private String _width;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public EdgesTag()
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
    _width = null;
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

  public String getWidth()
  {
    return _width;
  }

  public void setWidth( String width )
  {
    _width = width;
  }


  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.print( "<div" );
      if ( _className != null ) {
        TagUtil.printSafeAttribute( out, "class", _className );
      }
      
      if ( getWidth() != null ) {
        TagUtil.printAttribute( out, "style", "width: " + getWidth() + ";" );
      }
      out.println( ">" );
      out.println( "<div class=\"ww_edges\">");
      
      out.println( "<div class=\"ww_top\"><div class=\"ww_left\"><div class=\"ww_right\"><div class=\"ww_center\"></div></div></div></div>" );
      out.print( "<div class=\"ww_middle\"><div class=\"ww_left\"><div class=\"ww_right\"><div class=\"ww_center\">" );
      out.print( "<div class=\"ww_enforceMargins\"></div>" );
      out.println( "<div class=\"ww_content\">" );
    
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

      out.print( "</div>" );
      out.print( "<div class=\"ww_enforceMargins\"></div>" );
      out.println( "</div></div></div></div>" );
      out.println( "<div class=\"ww_bottom\"><div class=\"ww_left\"><div class=\"ww_right\"><div class=\"ww_center\"></div></div></div></div>" );
      out.println( "</div></div>" );

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class EdgesTag ----------

