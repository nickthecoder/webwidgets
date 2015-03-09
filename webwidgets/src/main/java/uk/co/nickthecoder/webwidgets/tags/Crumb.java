/* {{{ GPL

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

}}} */

package uk.co.nickthecoder.webwidgets.tags;


// {{{ imports
import java.io.*;
// }}}

/**
  Used by the BreadcrumbTag, a crumb is a single, labelled web page which has been recently visited.
*/

public class Crumb
  implements Serializable
{

  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private String _label;

  private String _url;

  // -------------------- [[Static Methods]] --------------------


  // -------------------- [[Constructors]] --------------------

  /**
  */
  public Crumb( String label, String url )
  {
    _label = label;
    _url = url;
  }

  // -------------------- [[Methods]] --------------------

  public String getLabel()
  {
    return _label;
  }

  public void setLabel( String value )
  {
    _label = value;
  }

  public String getUrl()
  {
    return _url;
  }

  public void setUrl( String value )
  {
    _url = value;
  }


  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class BreadcrumbTag ----------


