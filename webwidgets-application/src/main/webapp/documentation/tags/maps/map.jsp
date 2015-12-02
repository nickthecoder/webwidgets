<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="map" />

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/link.jsp">link</ww:link>,
    <ww:link href="/documentation/apis/popup.jsp">popup</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      The most straight forward way to create a link to a map.
    </p>
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">

      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>

      <tr>
        <td>provider</td>
        <td>yes</td>
        <td>
          The provider of the maps. Values :
          <ul>
            <li>google.com : <a href="http://maps.google.com">Google Maps</a></li>
            <li>streetmap.co.uk : <a href="http://streetmap.co.uk">Streemap.co.uk</a></li>
            <li>multimap.com : <a href="http://multimap.com">Multi Map</a></li>
            <li>print-multimap.com : As above, but goes directly to the printer friendly page</li>
          </ul>
        </td>
      </tr>

      <tr>
        <td>longitude</td>
        <td>yes</td>
        <td>
          The longitude (a floating point number).
        </td>
      </tr>

      <tr>
        <td>latitude</td>
        <td>yes</td>
        <td>
          The latitude (a floating point number).
        </td>
      </tr>

      <tr>
        <td>render</td>
        <td>no</td>
        <td>
          Default is true.
          If render == false, the link will not be rendered. At first, it appears
          silly to define a link, and then not render it. The reason is to
          allow a child link to render the link instead. The child tag can be
          <a href="link.jsp#link">link</a> or <a href="link.jsp#popup">popup</a>
          (or you could even write your own jsp custom tag). This allows the
          presentation of the link to be seperated from the
          definition of the link.
        </td>
      </tr>

    </table>

  </tiles:put>

</tiles:insert>

