<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="innerBox" />
  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/box.jsp">box</ww:link>
  </tiles:put>

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/boxTitle.jsp">boxTitle</ww:link>,
    <ww:link href="/documentation/apis/boxContent.jsp">boxContent</ww:link>
  </tiles:put>


  <tiles:put name="about" type="string">
    <p>
      Allows a box to have multiple sub-sections (inner boxes).
      Each inner box can have its own boxTitle and boxContent.
      If you use the standard buttons "minimize" and "maximize" within the
      inner boxes's title, then each innerBox can be minimized seperately.
    </p>
    <p>
      Note, when using inner boxes, often, the main box does not have a boxContent
      of its own.
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
        <td>minimized</td>
        <td>no</td>
        <td>
          If true, then the contents are initialy hidden.
        </td>
      </tr>
    </table>

  </tiles:put>

</tiles:insert>

