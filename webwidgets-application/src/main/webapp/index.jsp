<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>


<tiles:insert template="style/plain.jsp" >

	<tiles:put name="title" type="string">About Webwidgets</tiles:put>

	<tiles:put name="content" type="string">

    <p>
      I started creating this set of custom tags to help build my intranet and internet sites
      more quickly. The aim is to build simple to use tags, which are very generic, i.e.
      useable in many web applications.
    </p>

    <p>
      Using these tags should help :
    </p>
    <ul>
      <li>reduce development time</li>
      <li>increase quality</li>
      <li>maintain consistancy across your web site</li>
    </ul>
	</tiles:put>
</tiles:insert>

<ww:breadcrumb label="Home"/>

