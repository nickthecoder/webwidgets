<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="contextPath" />

  <tiles:put name="about" type="string">
    <p>
      Outputs the web app's context path.
    </p>
  </tiles:put>


  <tiles:put name="examples" type="string">

    <table class="examples">

      <tr>
        <td colspan="2">
        </td>
      </tr>

      <tr>
        <td>
          <ww:contextPath/>
        </td>
        <td>

<pre class="code"><ww:noEval>
<ww:contextPath/>
</ww:noEval></pre>

        </td>
      </tr>


    </table>


  </tiles:put>

</tiles:insert>

