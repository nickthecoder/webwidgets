/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2005-04-18

----------------------------------------------------------------------

 History
 2005-04-18 : nick : Initial Development

----------------------------------------------------------------------

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

----------------------------------------------------------------------
*/

package uk.co.nickthecoder.webwidgets.tags;


import java.util.*;
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
  Base class for all child tags of the PagerTag
*/

public class PagerChildTag
  extends BodyTagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public PagerChildTag()
  {
    super();
  }

  // -------------------- [[Methods]] --------------------

  public PagerTag getPagerTag()
    throws JspException
  {
    PagerTag pagerTag = (PagerTag) TagSupport.findAncestorWithClass( this, PagerTag.class );
    if ( pagerTag == null ) {
      throw new JspException( "Ancestor PagerTag not found" );
    }

    return pagerTag;
  }

  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class PagerChildTag ----------

