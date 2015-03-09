// ----------------------------------------------------------------------
//
// Author        : Nick Robinson (nick)
// Creation Date : 2006-02-22
//
// ----------------------------------------------------------------------
// History
// 2003-03-18 : nick : Initial Development
//
// ----------------------------------------------------------------------

package uk.co.nickthecoder.webwidgets.util;

import java.util.*;

/**
  Allows traversing through a List in reverse order, but still use the next() method
  This allows the client code to be oblivious to the direction of travel.
*/
public class ReverseListIterator
  implements ListIterator

{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private ListIterator _listIterator;

  // -------------------- [[Static Methods]] --------------------

  public static ListIterator getListIterator( List list, boolean backwards )
  {
    if ( backwards ) {
      return new ReverseListIterator( list );
    } else {
      return list.listIterator();
    }
  }

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public ReverseListIterator( ListIterator listIterator )
  {
    _listIterator = listIterator;
  }

  public ReverseListIterator( List list )
  {
    _listIterator = list.listIterator( list.size() );
  }

  // -------------------- [[Methods]] --------------------

  public boolean hasNext()
  {
    return _listIterator.hasPrevious();
  }

  public boolean hasPrevious()
  {
    return _listIterator.hasNext();
  }

  public Object next()
  {
    return _listIterator.previous();
  }

  public Object previous()
  {
    return _listIterator.next();
  }

  public void remove()
  {
    _listIterator.remove();
  }

  public void add( Object o )
  {
    _listIterator.add( o );
  }

  public void set( Object o )
  {
    _listIterator.set( o );
  }

  public int nextIndex()
  {
    return _listIterator.previousIndex();
  }

  public int previousIndex()
  {
    return _listIterator.nextIndex();
  }

  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class ReverseIterator ----------

