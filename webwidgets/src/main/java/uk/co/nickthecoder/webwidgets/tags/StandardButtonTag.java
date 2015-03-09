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
public class StandardButtonTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  public static final String MINIMIZE_BUTTON = "minimize";
  public static final String MAXIMIZE_BUTTON = "maximize";
  public static final String CLOSE_BUTTON = "close";
  public static final String EXPAND_BUTTON = "expand";
  public static final String CONTRACT_BUTTON = "contract";
  public static final String DISABLED_BUTTON = "disabled";

  // -------------------- [[Attributes]] --------------------

  private String _buttonType;

  private boolean _displayImage;

  // -------------------- [[Static Methods]] --------------------


  private static String getImagePath( PageContext pageContext )
  {
    return ((HttpServletRequest) pageContext.getRequest()) .getContextPath() + "/ww_resources/";
  }

  static String makeButton( PageContext pageContext, String buttonType )
  {
    return makeLink( pageContext, buttonType ) + makeImage( pageContext, buttonType ) + "</a>";
  }

  static String makeLink( PageContext pageContext, String buttonType )
  {
    if ( MINIMIZE_BUTTON.equals( buttonType ) ) {
      return "<a class=\"ww_minButton\" href=\"#\" onclick=\"javascript: return ww_doMinimize( event )\">";
    } else if ( MAXIMIZE_BUTTON.equals( buttonType ) ) {
      return "<a class=\"ww_maxButton\" href=\"#\" onclick=\"javascript: return ww_doMaximize( event )\">";
    } else if ( CLOSE_BUTTON.equals( buttonType ) ) {
      return "<a href=\"#\" onclick=\"javascript: return ww_doClose( event )\">";
    } else if ( EXPAND_BUTTON.equals( buttonType ) ) {
      return "<a class=\"ww_maxButton\" href=\"#\" onclick=\"javascript: return ww_doMaximize( event )\">";
    } else if ( CONTRACT_BUTTON.equals( buttonType ) ) {
      return "<a class=\"ww_minButton\" href=\"#\" onclick=\"javascript: return ww_doMinimize( event )\">";
    } else if ( DISABLED_BUTTON.equals( buttonType ) ) {
      return "";
    } else {
      return "Unknown Button Type : " + buttonType;
    }
  }
  static String makeImage( PageContext pageContext, String buttonType )
  {
    if ( MINIMIZE_BUTTON.equals( buttonType ) ) {
      return "<img src=\"" + getImagePath( pageContext ) + "ww_minimize.png\" alt=\"Min\" title=\"Minimize\" />";
    } else if ( MAXIMIZE_BUTTON.equals( buttonType ) ) {
      return "<img src=\"" + getImagePath( pageContext ) + "ww_maximize.png\" alt=\"Max\" title=\"Maximize\" />";
    } else if ( CLOSE_BUTTON.equals( buttonType ) ) {
      return "<img src=\"" + getImagePath( pageContext ) + "ww_close.png\" alt=\"X\" title=\"Close\" />";
    } else if ( EXPAND_BUTTON.equals( buttonType ) ) {
      return "<img src=\"" + getImagePath( pageContext ) + "ww_expand.png\" alt=\"+\" title=\"Expand\" />";
    } else if ( CONTRACT_BUTTON.equals( buttonType ) ) {
      return "<img src=\"" + getImagePath( pageContext ) + "ww_contract.png\" alt=\"-\" title=\"Contract\" />";
    } else if ( DISABLED_BUTTON.equals( buttonType ) ) {
      return "<img src=\"" + getImagePath( pageContext ) + "ww_disabled.png\" />";
    } else {
      return "";
    }
  }

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public StandardButtonTag()
  {
    super();
    _displayImage = true;
  }

  // -------------------- [[Methods]] --------------------

  public String getType()
  {
    return _buttonType;
  }

  public void setType( String buttonType )
  {
    _buttonType = buttonType;
  }

  public void setImage( boolean value )
  {
    _displayImage = value;
  }

  public boolean getImage()
  {
    return _displayImage;
  }


  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      out.print( makeLink( pageContext, _buttonType ) );

      if ( _displayImage ) {
        out.print( makeImage( pageContext, _buttonType ) );
      }

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

      if ( ! DISABLED_BUTTON.equals( _buttonType ) ) {
        out.print( "</a>" );
      }

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

  }

  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class StandardButtonTag ----------

