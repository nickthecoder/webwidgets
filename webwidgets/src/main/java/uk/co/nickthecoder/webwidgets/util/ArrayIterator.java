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
  @MORE@ Add javadoc comments here
*/
public class ArrayIterator
  implements Iterator

{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private Object[] _array;

  private int _index;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public ArrayIterator( Object[] array )
  {
    _array = array;
    _index = 0;
  }

  // -------------------- [[Methods]] --------------------

  public boolean hasNext()
  {
    return _index < _array.length;
  }

  public Object next()
  {
    if ( _index >= _array.length ) {
      throw new NoSuchElementException( "Beyond the bounds of the array length : " + _array.length );
    }

    return _array[ _index ++ ];
  }

  public void remove()
  {
    throw new UnsupportedOperationException( "ArrayIterator does not support remove" );
  }

  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class ArrayIterator ----------

