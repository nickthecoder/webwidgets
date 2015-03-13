<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="box" />

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/boxTitle.jsp">boxTitle</ww:link>,
    <ww:link href="/documentation/apis/boxContent.jsp">boxContent</ww:link>,
    <ww:link href="/documentation/apis/innerBox.jsp">innerBox</ww:link>
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">
      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>
      <tr>
        <td>className</td>
        <td>yes</td>
        <td>
          The css class name. The appearance is defined within css style sheets.
          See the <i>.purpleBox</i> within <ww:link href="/style/example.css">example.css</ww:link>
        </td>
      </tr>
      <tr>
        <td>width</td>
        <td>no</td>
        <td>
          The width of the box. This is mapped to the css <i>width</i> attribute, so
          it can be a percentage, or a fixed length.
        </td>
      </tr>
      <tr>
        <td>minimized</td>
        <td>no</td>
        <td>
          If true, the box content is initialy hidden.
        </td>
      </tr>
    </table>

  </tiles:put>

</tiles:insert>

