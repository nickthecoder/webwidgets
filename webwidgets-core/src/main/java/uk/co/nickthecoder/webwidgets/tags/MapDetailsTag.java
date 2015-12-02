/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class MapDetailsTag extends TagSupport
{
    private static final long serialVersionUID = 6961280407290916600L;

    public static final String STREETMAP_PROVIDER = "streetmap.co.uk";

    public static final String MULTIMAP_PROVIDER = "multimap.com";

    public static final String PRINT_MULTIMAP_PROVIDER = "print-multimap.com";

    public static final String GOOGLE_PROVIDER = "google.com";

    private double _longitude;

    private double _latitude;

    public MapDetailsTag()
    {
        super();
        initialise();
    }

    private void initialise()
    {
        _longitude = 0.0;
        _latitude = 0.0;
    }

    public double getLongitude()
    {
        return _longitude;
    }

    public void setLongitude( double longitude )
    {
        _longitude = longitude;
    }

    public double getLatitude()
    {
        return _latitude;
    }

    public void setLatitude( double latitude )
    {
        _latitude = latitude;
    }

    public String getUrl( String provider ) throws JspException
    {

        if (STREETMAP_PROVIDER.equals(provider)) {
            return "http://www.streetmap.co.uk/newsearch.srf?mapp=newmap&amp;searchp=newsearch&amp;name=" + getLatitude() + "+" +
                getLongitude() + "&amp;type=LatLong";

        } else if (MULTIMAP_PROVIDER.equals(provider)) {
            return "http://www.multimap.com/map/browse.cgi?lat=" + getLatitude() + "&amp;lon=" + getLongitude() +
                "&amp;scale=10000&amp;icon=x";

        } else if (PRINT_MULTIMAP_PROVIDER.equals(provider)) {
            return "http://www.multimap.com/map/browse.cgi?client=print&amp;lat=" + getLatitude() + "&amp;lon=" + getLongitude() +
                "&amp;scale=10000&amp;icon=x";

        } else if (GOOGLE_PROVIDER.equals(provider)) {
            return "http://maps.google.com/maps?ll=" + getLatitude() + "," + getLongitude() + "&amp;spn=0.008779,0.019692&amp;hl=en";

        } else {
            throw new JspException("Unknown map provider : " + provider);
        }

    }

    public int doStartTag() throws JspException
    {
        return EVAL_BODY_INCLUDE;
    }

    public void release()
    {
        super.release();
        initialise();
    }

}
