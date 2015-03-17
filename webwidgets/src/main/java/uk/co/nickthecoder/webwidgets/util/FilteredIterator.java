/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.util;

import java.util.Iterator;

public abstract class FilteredIterator<T> implements Iterator<T>
{

    private Iterator<T> _source;

    private T _currentItem;

    public FilteredIterator( Iterator<T> source )
    {
        _source = source;
    }

    public boolean hasNext()
    {
        if (_currentItem == null) {
            lookAhead();
        }
        return _currentItem != null;
    }

    public T next()
    {
        T result = _currentItem;
        lookAhead();

        return result;
    }

    public void remove()
    {
        throw new UnsupportedOperationException("remove not supported for FilteredIterator");
    }

    private void lookAhead()
    {

        while (_source.hasNext()) {

            _currentItem = _source.next();

            if (include(_currentItem)) {
                // break out of the function - we are done.
                return;
            }
        }

        // Got the the end of the source iterator.
        _currentItem = null;
    }

    protected abstract boolean include( Object item );

}
