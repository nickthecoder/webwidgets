<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="boxTitle" />
  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/box.jsp">box</ww:link>
  </tiles:put>

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/boxIcon.jsp">boxIcon</ww:link>
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">
      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>
      <tr>
        <td>title</td>
        <td>no</td>
        <td>
          The text to appear in the title bar.
          If you omit this attribute, then you can place the title bar text
          in the body of the boxTitle tag instead. Using the boxTitle body
          gives the flexibility to use any html, but using the title attribute
          may be more convenient.
        </td>
      </tr>
      <tr>
        <td>clickable</td>
        <td>no</td>
        <td>
          If true, then the title can be used to minimize/maximize the box contents.
        </td>
      </tr>
    </table>

  </tiles:put>
</tiles:insert>

