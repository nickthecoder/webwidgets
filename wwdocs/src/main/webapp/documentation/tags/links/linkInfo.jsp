<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="linkInfo" />

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/link.jsp">link</ww:link>,
    <ww:link href="/documentation/apis/popup.jsp">popup</ww:link>,
    <ww:link href="/documentation/apis/pager.jsp">pager</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      Defines the href for a link, but does not render it. The rendering
      will be done by a child link tag renderer, such as link or popup.
    </p>

    <p>
      See <a href="pager.jsp">pager</a> for another way that linkInfo is
      used.
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
        <td>yes</td>
        <td>
          Similar to html 'a' tag's href, except that urls begining with a '/'
          may have the web application's context path added to the front
          (dependaing on the value of useContextPath).
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
        <td>parameters</td>
        <td>no</td>
        <td>
          A HashMap. Each entry in the hashmap defines a parameter to be sent
          back to the web server when the link is pressed. The key of each entry
          is the name of the parameter.
        </td>

      </tr>
    </table>

  </tiles:put>

</tiles:insert>

