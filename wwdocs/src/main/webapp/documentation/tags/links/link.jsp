<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="link" />

  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/linkInfo.jsp">link</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      The same as a regular 'a' tag, with the added convenience of automaticaly
      adding the web application's context path to the front of absolute urls.
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
        <td>href</td>
        <td>no</td>
        <td>
          Similar to html 'a' tag's href, except that urls begining with a '/'
          may have the web application's context path added to the front
          (dependaing on the value of useContextPath).
          <br/>
          The href is not required, because if you omit it, the link tag will
          look for parent tags which define the href.
          <ww:link href="/documentation/apis/linkInfo.jsp">linkInfo</ww:link>
          is one tag which will define the href.
        </td>
      </tr>

      <tr>
        <td>useContextPath</td>
        <td>no</td>
        <td>
          If the href begins with a '/', then this determins if the web application's
          context path is added to the front. Default is true.
        </td>
      </tr>

      <tr>
        <td>styleClass</td>
        <td>no</td>
        <td>
          The css class name.
        </td>
      </tr>

      <tr>
        <td>title</td>
        <td>no</td>
        <td>
          The title attribute for the a tag.
        </td>

      </tr>
    </table>

  </tiles:put>

</tiles:insert>

