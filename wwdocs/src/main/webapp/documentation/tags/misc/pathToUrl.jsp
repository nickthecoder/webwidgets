<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="pathToUrl" />

  <tiles:put name="about" type="string">
    <p>
      This tag looks badly written - use at your peril!
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
        <td>path</td>
        <td>yes</td>
        <td>
          The path to the file.
        </td>
      </tr>

      <tr>
        <td>notFoundPath</td>
        <td>no</td>
        <td>
          If the file above was not found, then use this instead.
        </td>
      </tr>

      <tr>
        <td>full</td>
        <td>no</td>
        <td>
          If true, then the whole url is returned, rather than a server relative url.
        </td>
      </tr>

    </table>

  </tiles:put>


</tiles:insert>

