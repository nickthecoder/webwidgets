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
  @MORE@ Add javadoc comments here
*/
public class BoxTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private String _className;

  private String _width;

  private boolean _minimized;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public BoxTag()
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

      out.println( "<!-- BEGIN BOX -->" );

      if( getMinimized() ) {
        out.println( "<div class=\"ww_minimized\">" );
      } else {
        out.println( "<div class=\"ww_maximized\">" );
      }

      out.print( "<div" );
      if ( _className != null ) {
        TagUtil.printSafeAttribute( out, "class", _className );
      }
      if ( getWidth() != null ) {
        TagUtil.printAttribute( out, "style", "width: " + getWidth() + ";" );
      }
      out.println( ">" );

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
      out.println( "</div> <!-- box tag end minimizable -->" ); // End <div class="ww_minimizable">


      out.println( "</div>" ); // End <div class="_className">
      out.println( "</div>" ); // End <div class=""ww_maximized">
      out.println( "<!-- END BOX -->" );

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class BoxTag ----------
