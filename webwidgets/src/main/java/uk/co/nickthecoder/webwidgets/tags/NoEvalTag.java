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
  Prevent jsp evaluating the body - useful for quoting jsp code.
*/
public class NoEvalTag
  extends BodyTagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private boolean _escape;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public NoEvalTag()
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
    _escape = true;
  }

  // -------------------- [[Methods]] --------------------

  public void setEscape( boolean value )
  {
    _escape = value;
  }

  public boolean getEscape()
  {
    return _escape;
  }


  public int doEndTag()
   throws JspException
  {
    try {

      JspWriter out = pageContext.getOut();

      String body = bodyContent.getString();


      if ( getEscape() ) {
        out.print( TagUtil.safeText( body ) );
      } else {
        out.println( body );
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
// ---------- End Of Class NoEvalTag ----------
