/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.Serializable;

/**
 * Used by the BreadcrumbTag, a crumb is a single, labelled web page which has been recently visited.
 */
public class Crumb implements Serializable
{

    private static final long serialVersionUID = 2395426911918904944L;

    private String _label;

    private String _url;

    public Crumb( String label, String url )
    {
        _label = label;
        _url = url;
    }

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

}
