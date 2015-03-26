<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="contextPath" />

  <tiles:put name="about" type="string">
    <p>
      Outputs the web app's context path.
    </p>
  </tiles:put>


  <tiles:put name="examples" type="string">

<ww:contextPath/>

<pre class="code"><ww:noEval>
<ww:contextPath/>
</ww:noEval></pre>

  </tiles:put>

</tiles:insert>

