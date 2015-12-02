<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="linkParameter" />

  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/linkInfo.jsp">linkInfo</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      Adds a parameter to a parent linkInfo. This is equivalent to making the
      linkInfo href more complex, by appending '?' + name + '=' + value,
      but if you append these manually, it gets messy, and you may forget to
      encode one of the parameter values correctly.
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

      <tr>
        <td>name</td>
        <td>yes</td>
        <td>
          The name of the parameter.
        </td>
      </tr>

      <tr>
        <td>value</td>
        <td>no</td>
        <td>
          The value of the parameter.
        </td>
      </tr>

    </table>

  </tiles:put>

</tiles:insert>

