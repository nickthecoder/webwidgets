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

import java.util.Iterator;


/**
  @MORE@ Add javadoc comments here
*/
public abstract class FilteredIterator
{
  // -------------------- [[Static Attributes]] --------------------

  // -------------------- [[Attributes]] --------------------

  private Iterator _source;

  private Object _currentItem;


  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
  public FilteredIterator( Iterator source )
  {
    _source = source;
  }

  // -------------------- [[Methods]] --------------------

  public boolean hasNext()
  {
    if ( _currentItem == null ) {
      lookAhead();
    }
    return _currentItem != null;
  }

  public Object next()
  {
    Object result = _currentItem;
    lookAhead();

    return result;
  }

  public void remove()
  {
    throw new UnsupportedOperationException( "remove not supported for FilteredIterator" );
  }

  private void lookAhead()
  {

    while ( _source.hasNext() ) {

      _currentItem = _source.next();

      if ( include( _currentItem ) ) {
        // break out of the function - we are done.
        return;
      }
    }

    // Got the the end of the source iterator.
    _currentItem = null;
  }

  protected abstract boolean include( Object item );

  // -------------------- [[Test / Debug]] --------------------

}
// ---------- End Of Class FilteredIterator ----------

