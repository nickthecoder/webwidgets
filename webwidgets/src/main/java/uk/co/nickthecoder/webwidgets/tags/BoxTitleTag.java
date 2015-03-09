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
public class BoxTitleTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private String _title;

  private boolean _clickable;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public BoxTitleTag()
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
    _title = "";
    _clickable = false;
  }

  // -------------------- [[Methods]] --------------------

  public String getTitle()
  {
    return _title;
  }

  public void setTitle( String title )
  {
    _title = title;
  }

  public boolean getClickable()
  {
    return _clickable;
  }

  public void setClickable( boolean value )
  {
    _clickable = value;
  }

  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.println( "<div class=\"ww_boxHeading\">" );
      out.println( "<table class=\"ww_layout\" width=\"100%\"><tr>" );
      out.print( "<td class=\"ww_boxTitle\">" );

      if ( getClickable() ) {
        out.print( "<a href=\"#\" onclick=\"javascript: return ww_doToggleMinimize( event )\">" );
      }
      out.print( TagUtil.safeText( getTitle() ) );

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

      if ( getClickable() ) {
        out.print( "</a>" );
      }

      out.println( "</td>" );

      out.println( "</tr></table>" ); // End <table class="ww_layout">
      out.println( "</div>" ); // End <div class="ww_boxHeading">

      // This is closed in BoxTag (or InnerBoxTag) doEndTag
      // That menas that all BoxTags MUST have one and only one BoxTitle Tag.
      // That is VERY bad design :-(
      out.println( "<div class=\"ww_minimizable\">" );

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class TitleBarTag ----------
