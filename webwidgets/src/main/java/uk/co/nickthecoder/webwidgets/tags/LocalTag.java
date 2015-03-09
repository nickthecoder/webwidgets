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

import uk.co.nickthecoder.webwidgets.util.*;
/**
  Sets a variable with a boolean - true if the request is from a local ipv4 address.
*/
public class LocalTag
  extends TagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private String _var = "isLocal";

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public LocalTag()
  {
    super();
  }

  // -------------------- [[Methods]] --------------------

  public void setVar( String var )
  {
    _var = var;
  }

  public String getVar()
  {
    return _var;
  }

  public int doEndTag()
    throws JspException
  {

    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    boolean isLocal = TagUtil.isLocal( request );
  
    request.setAttribute( getVar(), isLocal ? Boolean.TRUE : Boolean.FALSE );
    return EVAL_PAGE;

  }


  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class ----------

