<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="tab" />

  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/tabs.jsp">tabs</ww:link>
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">

      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>
      <tr>

      <tr>
        <td>pattern</td>
        <td>no</td>
        <td>
          A regular expression, the current page's url is matched using this pattern.
          Note, the protocol and server name are never used as part of the match, and the context path is optionally
          matched based on the useContextPath property.
          If it matches then the tab is turned "on".
        </td>
      </tr>

      <tr>
        <td>useContextPath</td>
        <td>no</td>
        <td>
          If true, then the context path is included when matching with the pattern property above.
          The default is true.
        </td>
      </tr>

      <tr>
        <td>test</td>
        <td>no</td>
        <td>
          An el expression to determine if the tab is turned "on". Use either test or
          pattern, but not both.
        </td>
      </tr>

      </table>

  </tiles:put>

</tiles:insert>

