/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator implements Iterator<Object>
{

    private Object[] _array;

    private int _index;

    public ArrayIterator( Object[] array )
    {
        _array = array;
        _index = 0;
    }

    public boolean hasNext()
    {
        return _index < _array.length;
    }

    public Object next()
    {
        if (_index >= _array.length) {
            throw new NoSuchElementException("Beyond the bounds of the array length : " + _array.length);
        }

        return _array[_index++];
    }

    public void remove()
    {
        throw new UnsupportedOperationException("ArrayIterator does not support remove");
    }

}
