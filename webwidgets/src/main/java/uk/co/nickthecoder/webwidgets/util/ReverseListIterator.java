/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.util;

import java.util.*;

/**
 * Allows traversing through a List in reverse order, but still use the next() method
 * This allows the client code to be oblivious to the direction of travel.
 */
public class ReverseListIterator implements ListIterator

{

    private ListIterator _listIterator;

    public static ListIterator getListIterator( List list, boolean backwards )
    {
        if (backwards) {
            return new ReverseListIterator(list);
        } else {
            return list.listIterator();
        }
    }

    public ReverseListIterator( ListIterator listIterator )
    {
        _listIterator = listIterator;
    }

    public ReverseListIterator( List list )
    {
        _listIterator = list.listIterator(list.size());
    }

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
        _listIterator.add(o);
    }

    public void set( Object o )
    {
        _listIterator.set(o);
    }

    public int nextIndex()
    {
        return _listIterator.previousIndex();
    }

    public int previousIndex()
    {
        return _listIterator.nextIndex();
    }

}
