<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Map" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

  <ww:script src="/ww_resources/ww_misc.js"/>

  <p>
    All of these links take you to a map of my house.
  </p>

  <h2>Using the map tag</h2>
  <p>
    Each link is definied separately using a map tag.
  </p>

  <ul>
    <li><ww:map provider="streetmap.co.uk" latitude="51.5758" longitude="0.2064">Streetmap</ww:map></li>
    <li><ww:map provider="multimap.com" latitude="51.5758" longitude="0.2064">Multimap</ww:map></li>
    <li><ww:map provider="print-multimap.com" latitude="51.5758" longitude="0.2064">Multimap (Print)</ww:map></li>
    <li><ww:map provider="google.com" latitude="51.5758" longitude="0.2064">Google Maps</ww:map></li>
  </ul>

  <h2>Using the mapDetails and mapLink tags</h2>

  <p>
    Each line uses a single mapDetails tag, and three mapLink tags. This is useful
    as it allows the web designer to define a single map location, and let the web
    user choose which type of map they would like to see.
  </p>

  <ul>
    <li>
      <ww:mapDetails latitude="51.5758" longitude="0.2064">
        [<ww:mapLink provider="streetmap.co.uk">streetmap</ww:mapLink>]
        [<ww:mapLink provider="multimap.com">multimap</ww:mapLink>]
        [<ww:mapLink provider="print-multimap.com">print</ww:mapLink>]
        [<ww:mapLink provider="google.com">Google Maps</ww:mapLink>]
      </ww:mapDetails>
    </li>
  </ul>

  <h2>Using the Link tag in conjection with the map tags</h2>
  <p>
    The map and mapLink tags have the render attribute set to false, which means
    that they do not output any html.
  </p>
  <p>
    Inside each map and mapLink tags, there is a link tag. The href attribute
    is not set, so it looks at the ancestor tags for the href instead.
  </p>
  <p>
    It is the link tag that generates the html A tags.
  </p>


  <ul>
    <li><ww:map render="false" provider="streetmap.co.uk" latitude="51.5758" longitude="0.2064"><ww:link>Streetmap</ww:link></ww:map></li>
    <li>
      <ww:mapDetails latitude="51.5758" longitude="0.2064">
        [<ww:mapLink render="false" provider="streetmap.co.uk"   ><ww:link>streetmap</ww:link></ww:mapLink>]
        [<ww:mapLink render="false" provider="multimap.com"      ><ww:link>multimap</ww:link></ww:mapLink>]
        [<ww:mapLink render="false" provider="print-multimap.com"><ww:link>print</ww:link></ww:mapLink>]
      </ww:mapDetails>
    </li>
  </ul>

  <h2>Using the Popup tag in conjection with the map tags</h2>

  <p>
    As above, but using the popup tag, rather than the link tag.
  </p>

  <ul>
    <li><ww:map render="false" provider="streetmap.co.uk" latitude="51.5758" longitude="0.2064"><ww:popup width="600" height="600">Streetmap</ww:popup></ww:map></li>
    <li>
      <ww:mapDetails latitude="51.5758" longitude="0.2064">
        [<ww:mapLink render="false" provider="streetmap.co.uk"   ><ww:popup width="600" height="600">streetmap</ww:popup></ww:mapLink>]
        [<ww:mapLink render="false" provider="multimap.com"      ><ww:popup width="600" height="600">multimap</ww:popup></ww:mapLink>]
        [<ww:mapLink render="false" provider="print-multimap.com"><ww:popup width="600" height="600">print</ww:popup></ww:mapLink>]
      </ww:mapDetails>
    </li>
  </ul>

  </tiles:put>
</tiles:insert>

