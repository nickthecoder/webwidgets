<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="script" />

  <tiles:put name="about" type="string">
    <p>
      Adds javascript code to your web site. The script will only be added once,
      even if you have two identical script tags in one page. This is helpful when
      a web page is built using components, each component will know which
      javascript files it needs, but it wouldn't know if the file has already
      been included.
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
        <td>src</td>
        <td>yes</td>
        <td>
          The url of the script.
        </td>
      </tr>

      <tr>
        <td>language</td>
        <td>no</td>
        <td>
          The language of the script. Defaults to javascript.
        </td>
      </tr>

      <tr>
        <td>useContextPath</td>
        <td>no</td>
        <td>
          If set, then the webapp's context path is appended to the front of urls
          beginning with a slash.
        </td>
      </tr>

      <tr>
        <td>type</td>
        <td>no</td>
        <td>
          Defaults to "text/javascript".
        </td>
      </tr>

    </table>

  </tiles:put>


</tiles:insert>

