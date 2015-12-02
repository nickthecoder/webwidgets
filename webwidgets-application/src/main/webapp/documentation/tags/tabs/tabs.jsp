<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="tabs" />

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/tab.jsp">tab</ww:link>
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">
      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>

      <tr>
        <td>styleClass</td>
        <td>no</td>
        <td>
          The css class name for this set of tabs.
        </td>
      </tr>

    </table>

  </tiles:put>

</tiles:insert>

