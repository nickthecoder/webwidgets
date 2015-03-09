/*
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

*/

package uk.co.nickthecoder.webwidgets.tags;


import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
*/

public class LinkParameterTag
  extends TagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  /**
  */
  private String _name;

  private String _value;

  // -------------------- [[Static Methods]] --------------------

   // -------------------- [[Constructors]] --------------------

  /**
  */
  public LinkParameterTag()
  {
    super();

    initialise();
  }

  private void initialise()
  {
    _name = null;
    _value = null;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------


  public void setName( String name )
  {
    _name = name;
  }

  public String getName()
  {
    return _name;
  }

  public void setValue( String value )
  {
    _value = value;
  }

  public String getValue()
  {
    return _value;
  }


  public int doEndTag()
    throws JspException
  {
    LinkInfo linkInfo = LinkInfoTag.getAncestorLinkInfo( this );
    // System.out.println( "Adding parameter " + getName() + "=" + getValue() + " to " + linkInfo );
    linkInfo.addParameter( getName(), getValue() );

    return EVAL_BODY_INCLUDE;
  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class LinkParameterTag ----------

