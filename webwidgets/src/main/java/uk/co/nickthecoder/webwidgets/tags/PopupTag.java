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
public class PopupTag
  extends LinkTag
{
  // -------------------- [[Static Attributes]] --------------------

  public static final String DEFAULT_FEATURES = "resizable=yes,menubar=yes,toolbar=yes,scrollbars=yes";

  private static final int NOT_SPECIFIED = -1;

  // -------------------- [[Attributes]] --------------------

  private int _width;

  private int _height;

  private String _windowName;

  private int _left;

  private int _top;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public PopupTag()
  {
    super();
    initialise();
  }

  private void initialise()
  {
    _width = NOT_SPECIFIED;
    _height = NOT_SPECIFIED;
    _left = NOT_SPECIFIED;
    _top = NOT_SPECIFIED;
    _windowName = null;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------


  public int getWidth()
  {
    return _width;
  }
  public void setWidth( int width )
  {
    _width = width;
  }

  public int getHeight()
  {
    return _height;
  }
  public void setHeight( int height )
  {
    _height = height;
  }

  public int getLeft()
  {
    return _left;
  }
  public void setLeft( int left )
  {
    _left = left;
  }

  public int getTop()
  {
    return _top;
  }
  public void setTop( int top )
  {
    _top = top;
  }


  public void setWindowName( String windowName )
  {
    _windowName = windowName;
  }
  public String getWindowName()
  {
    return _windowName;
  }

  public String getFeatures()
  {
    return DEFAULT_FEATURES;
  }



  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      String href = getRealHref();

      out.print( "<a href=\"" + href + "\"" );
      if ( getStyleClass() != null ) {
        out.print( " class=\"" + getStyleClass() + "\"" );
      }

      out.print( " onclick=\"javascript: return ww_popup( '" + href + "'," );

      if ( getWindowName() == null ) {
        out.print( "null" );
      } else {
        out.print( "'" + getWindowName() + "'" );
      }

      out.print( ",'" + getFeatures() + "'" );

      out.print( "," );
      if ( getWidth() > 0 ) {
        out.print( getWidth() );
      } else {
        out.print( "null" );
      }

      out.print( "," );
      if ( getHeight() > 0 ) {
        out.print( getHeight() );
      } else {
        out.print( "null" );
      }

      out.print( "," );
      if ( getLeft() == NOT_SPECIFIED ) {
        out.print( "null" );
      } else {
        out.print( getLeft() );
      }

      out.print( "," );
      if ( getTop() == NOT_SPECIFIED ) {
        out.print( "null" );
      } else {
        out.print( getTop() );
      }

      out.print( ");\">" );

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

    return EVAL_BODY_INCLUDE;
  }



  // -------------------- [[Test / Debug]] --------------------


}
// ---------- End Of Class PopupTag ----------


