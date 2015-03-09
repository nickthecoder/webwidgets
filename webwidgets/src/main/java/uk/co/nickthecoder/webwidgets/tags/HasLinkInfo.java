/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2005-04-19

----------------------------------------------------------------------

 History
 2005-04-19 : nick : Initial Development

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

import javax.servlet.jsp.*;

import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
  @MORE@ Add javadoc comments here
*/

public interface HasLinkInfo
  extends HasLinkHref
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Methods]] --------------------

  public LinkInfo getLinkInfo()
    throws JspException;

  // -------------------- [[Test / Debug]] --------------------


}
// ---------- End Of Class HasLinkInfo ----------

