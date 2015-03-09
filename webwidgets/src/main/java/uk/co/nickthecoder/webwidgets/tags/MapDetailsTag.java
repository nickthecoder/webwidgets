// ----------------------------------------------------------------------
//
// Author        : Nick Robinson (nick)
// Creation Date : 2003-03-18
//
// ----------------------------------------------------------------------
// History
// 2003-03-18 : nick : Initial Development
//
// ----------------------------------------------------------------------

package uk.co.nickthecoder.webwidgets.tags;


import java.util.*;
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
  @MORE@ Add javadoc comments here
*/
public class MapDetailsTag
  extends TagSupport
{
  // -------------------- [[Static Attributes]] --------------------

  public static final String STREETMAP_PROVIDER = "streetmap.co.uk";

  public static final String MULTIMAP_PROVIDER = "multimap.com";

  public static final String PRINT_MULTIMAP_PROVIDER = "print-multimap.com";

  public static final String GOOGLE_PROVIDER = "google.com";

  // -------------------- [[Attributes]] --------------------

  private double _longitude;

  private double _latitude;

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
    @MORE@ Add Constructor javadocs comments here
  */
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

  // -------------------- [[Methods]] --------------------


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


  public String getUrl( String provider )
    throws JspException
  {

    if ( STREETMAP_PROVIDER.equals( provider ) ) {
      return "http://www.streetmap.co.uk/newsearch.srf?mapp=newmap&amp;searchp=newsearch&amp;name=" +
        getLatitude() + "+" + getLongitude() +
        "&amp;type=LatLong";

    } else if ( MULTIMAP_PROVIDER.equals( provider ) ) {
      return "http://www.multimap.com/map/browse.cgi?lat=" +
        getLatitude() +"&amp;lon=" + getLongitude() +
        "&amp;scale=10000&amp;icon=x";

    } else if ( PRINT_MULTIMAP_PROVIDER.equals( provider ) ) {
      return "http://www.multimap.com/map/browse.cgi?client=print&amp;lat=" +
        getLatitude() +"&amp;lon=" + getLongitude() +
        "&amp;scale=10000&amp;icon=x";

    } else if ( GOOGLE_PROVIDER.equals( provider ) ) {
      return "http://maps.google.com/maps?ll=" + getLatitude() + "," + getLongitude() +
        "&amp;spn=0.008779,0.019692&amp;hl=en";

    } else {
      throw new JspException( "Unknown map provider : " + provider );
    }

  }


  public int doStartTag()
    throws JspException
  {
    return EVAL_BODY_INCLUDE;
  }


  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Test / Debug]] --------------------


}
// ---------- End Of Class MapTag ----------


