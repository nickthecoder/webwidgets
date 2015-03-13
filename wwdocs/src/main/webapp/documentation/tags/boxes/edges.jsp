<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="edges" />

  <tiles:put name="children" type="string">
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      Creates a box, which has inner divs with prefefined css classes, designed so that
      a pretty box can be made using background images within each div
    </p>
    <p>
      The css classes used are : <br/>
      ww_top, ww_bottom, ww_left, ww_right, ww_middelOuter, ww_middleInner, ww_middleCenter, ww_content.
      <br/>
      See the <i>.rounded</i> within <ww:link href="/style/example.css">example.css</ww:link>.
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
        <td>className</td>
        <td>yes</td>
        <td>
          The css class name. The appearance is defined within css style sheets.
        </td>
      </tr>
      <tr>
        <td>width</td>
        <td>no</td>
        <td>
          The width of the box. This is mapped to the css <i>width</i> attribute, so
          it can be a percentage, or a fixed length.
        </td>
      </tr>
    </table>

  </tiles:put>

</tiles:insert>

