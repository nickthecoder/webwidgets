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
public class MapLinkTag
  extends TagSupport

  implements HasLinkHref
{
  // -------------------- [[Static Attributes]] --------------------


  // -------------------- [[Attributes]] --------------------

  private String _provider;

  private boolean _render;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public MapLinkTag()
  {
    super();
    initialise();
  }

  private void initialise()
  {
    _provider = null;
    _render = true;
  }

  // -------------------- [[Methods]] --------------------


  public String getProvider()
  {
    return _provider;
  }

  public void setProvider( String provider )
  {
    _provider = provider;
  }

  public void setRender( boolean render )
  {
    _render = render;
  }
  public boolean getRender()
  {
    return _render;
  }

  public String getLinkHref()
    throws JspException
  {
    return getMapDetails().getUrl( getProvider() );
  }

  private MapDetailsTag getMapDetails()
    throws JspException
  {
    Object ancestor = findAncestorWithClass( this, MapDetailsTag.class );
    if ( ancestor == null ) {
      throw new JspException( "mapLink tags must be contained inside mapDetails tags" );
    }

    return (MapDetailsTag) ancestor;
  }


  public int doStartTag()
    throws JspException
  {
    try {

      if ( getRender() ) {

        JspWriter out = pageContext.getOut();

        out.print( "<a href=\"" + getLinkHref() + "\">" );

      }

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

    return EVAL_BODY_INCLUDE;
  }


  public int doEndTag()
    throws JspException
  {
    try {

      if ( getRender() ) {
        JspWriter out = pageContext.getOut();

        out.println( "</a>" );
      }

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }

    return EVAL_PAGE;
  }
  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Test / Debug]] --------------------


}
// ---------- End Of Class MapLinkTag ----------

