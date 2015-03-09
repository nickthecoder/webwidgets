<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="mapDetails" />

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/mapLink.jsp">mapLink</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      Define the <b>location</b> of the map in this tag, and
      then specify the provider of the map in one or more
      <a href="#mapLink">mapLink</a> tags.
    </p>
    <p>
      The only reason for using mapDetails instead of <a href="#map">map</a>,
      is if you want to create many links to a single location, but with
      more than one map provider. In this case, mapDetails lets you specify
      the map location only once.
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
        <td>longitude</td>
        <td>yes</td>
        <td>
          The same as the <a href="#map">map</a> tag.
          The longitude (a floating point number).
        </td>
      </tr>

      <tr>
        <td>latitude</td>
        <td>yes</td>
        <td>
          The same as the <a href="#map">map</a> tag.
          The latitude (a floating point number).
        </td>
      </tr>

    </table>

  </tiles:put>

</tiles:insert>

