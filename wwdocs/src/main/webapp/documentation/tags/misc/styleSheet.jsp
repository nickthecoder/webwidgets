<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="styleSheet" />

  <tiles:put name="about" type="string">
    <p>
      Creates a &lt;link rel="stylesheet" type="text/css"... tag.
    </p>
    <p>
      Can automatically add the web app's context path to the front
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
          The location of the style sheet
        </td>
      </tr>

      <tr>
        <td>useContextPath</td>
        <td>no</td>
        <td>
          Default = true. If the href begins with "/" and useContextPath is set,
          then the web app's context path is added to the front of the href.
        </td>
      </tr>

      <tr>
        <td>encodeSessionId</td>
        <td>no</td>
        <td>
          If set, and the client doesn't support cookies (or this is the very first hit to this webapp),
          then the sessionid is added to the url. If your style sheet is dynaic based on a session object,
          and your client has disabled cookies, you will need this set to true.
        </td>
      </tr>

    </table>

  </tiles:put>



</tiles:insert>

