<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="pager" />
  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/linkInfo.jsp">linkInfo</ww:link>
  </tiles:put>

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/pagerLinks.jsp">pagerLinks</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">

      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>

      <tr>
        <td>pages</td>
        <td>yes</td>
        <td>
          The total number of pages.
          Default = 1.
        </td>
      </tr>

      <tr>
        <td>page</td>
        <td>no</td>
        <td>
          The current page number.
          Default = 1.
        </td>
      </tr>

      <tr>
        <td>nextPages</td>
        <td>no</td>
        <td>
          The maximum number of links to pages after this one.
          Default = 9.
        </td>
      </tr>

      <tr>
        <td>previousPages</td>
        <td>no</td>
        <td>
          The maximum number of links to pages before this one.
          Default = 10.
        </td>
      </tr>

      <tr>
        <td>pageParameterName</td>
        <td>no</td>
        <td>
          The name of the parameter, holding a page number, to send back to
          the web server when one of the pager's links is clicked.
          Defaults to 'page'.
        </td>
      </tr>

      <tr>
        <td>pageAttributeName</td>
        <td>no</td>
        <td>
          The name of the attribute which holds the page number when
          rendering the pagerLinks tags.
          Defaults to 'page'.
        </td>
      </tr>

    </table>

  </tiles:put>

</tiles:insert>

