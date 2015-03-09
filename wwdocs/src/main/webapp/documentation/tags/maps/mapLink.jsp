<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="mapLink" />
  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/mapDetails.jsp">mapDetails</ww:link>
  </tiles:put>

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/link.jsp">link</ww:link>,
    <ww:link href="/documentation/apis/popup.jsp">popup</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      Render a link to a map. The map provider is specified in this tag,
      but the map location is specified in the parent
      <a href="#mapDetails">mapDetails</a> tag.
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
          The same as the <a href="#map">map</a> tag.
          The provider of the map. see the map tag above for details.
        </td>
      </tr>

      <tr>
        <td>render</td>
        <td>no</td>
        <td>
          Just as with the <a href="#map">map</a> tag, the rendering of
          the link can be left to a child tag, (such as the
          <a href="link.jsp#popup">popup</a> tag).
        </td>
      </tr>

    </table>

  </tiles:put>

</tiles:insert>

