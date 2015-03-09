<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="multipart" />

  <tiles:put name="about" type="string">
    <p>
      Adds support for multipart forms (file uploads).
    </p>
  </tiles:put>

  <tiles:put name="requires" type="string">
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">

      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>

      <tr>
        <td>paramVar</td>
        <td>no</td>
        <td>
          The name of the request attribute will will hold a Map of name value pair which
          can be used to replace the request.getParameter() calls. (request.getParameter won't
          work for multipart forms).
        </td>
      </tr>

      <tr>
        <td>paramListVar</td>
        <td>no</td>
        <td>
        If parameters have multiple values, then paramVar is not sufficient (as it only has
        a single value for each parameter). This is the name of the request attribute that wil
        hold a Map of name value pairs, where each value is a List of Strings.
        </td>
      </tr>

      <tr>
        <td>filesVar</td>
        <td>no</td>
        <td>
          The name of the request attribute to hold the Map of name value pairs, where the values
          are of type webwidgets.util.MultipartHelper.FileInfo. The most useful method of the FileInfo
          class is "getFile", which returns the File object for the temporary file that holds the named
          uploaded file.
        </td>
      </tr>

      <tr>
        <td>outputDirectory</td>
        <td>no</td>
        <td>
          The directory which holds the files once they are uploaded. Defaults to "/tmp".
        </td>
      </tr>

    </table>

  </tiles:put>


</tiles:insert>

