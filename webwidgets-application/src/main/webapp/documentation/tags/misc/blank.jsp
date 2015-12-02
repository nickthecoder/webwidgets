<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="?" />

  <tiles:put name="about" type="string">
    <p>

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
        <td></td>
        <td>yes</td>
        <td>
        </td>
      </tr>

    </table>

  </tiles:put>


  <tiles:put name="examples" type="string">

    <table class="examples">

      <tr>
        <td colspan="2">

        </td>
      </tr>

      <tr>
        <td>

        </td>
        <td>

<pre class="code"><ww:noEval>


</ww:noEval></pre>

        </td>
      </tr>


    </table>


  </tiles:put>

</tiles:insert>

