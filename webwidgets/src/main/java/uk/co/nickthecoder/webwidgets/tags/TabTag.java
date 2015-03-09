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
import javax.servlet.http.*;
import javax.servlet.jsp.tagext.*;

import uk.co.nickthecoder.webwidgets.util.*;

/**
  @MORE@ Add javadoc comments here
*/
public class TabTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private String _pattern;

  private boolean _local;

  private Boolean _test;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public TabTag()
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
    _pattern = null;
    _test = null;
    _local = false;
  }

  // -------------------- [[Methods]] --------------------


  public boolean getLocal()
  {
    return _local;
  }

  public void setLocal( boolean value )
  {
    _local = value;
  }


  public String getPattern()
  {
    return _pattern;
  }

  public void setPattern( String value )
  {
    _pattern = value;
  }

  public void setTest( Boolean value )
  {
    _test = value;
  }

  public Boolean getTest()
  {
    return _test;
  }


  protected boolean isOn()
  {
    if ( _pattern == null ) {

      if ( getTest() == null ) {
        return false;
      } else {
        return getTest().booleanValue();
      }

    }

    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    String url = TagUtil.getUrl( request );

    String toCompare;
    if ( getLocal() ) {
      if ( request.getContextPath().equals( "" ) ) {
        toCompare = url.replaceAll( ".*?/", "/" );
      } else {
        toCompare = url.replaceAll( ".*?" + request.getContextPath(), "" );
      }
    } else {
      toCompare = url;
    }

    //System.out.println( "comparing " + toCompare + " with " + getPattern() );
    return toCompare.matches( getPattern() );
  }


  public int doStartTag()
    throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      if ( isOn() ) {
        out.println( "<td><div class=\"ww_on\">" );
      } else {
        out.println( "<td><div class=\"ww_off\">" );
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

      out.println( "</div></td>" );

      return EVAL_PAGE;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class Tab2Tag ----------
