<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Script" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

<p>
  You should see one popup window saying hello.
  If you see two, then somethings gone wrong!
</p>

<ww:script src="hello.js"/>
<ww:script src="hello.js"/>
<ww:script src="hello.js"/>
<ww:script src="hello.js"/>



  </tiles:put>
</tiles:insert>


