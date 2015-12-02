<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Link" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

  <ul>
    <li><ww:link title="Local Index" href="index.jsp">Local Index</ww:link></li>
    <li><ww:link title="" href="index.jsp" useContextPath="true">Local Index (again)</ww:link></li>
    <li><ww:link href="index.jsp" useContextPath="false">Local Index (again)</ww:link></li>
    <li><ww:link href="/index.jsp">Web App Index</ww:link></li>
    <li><ww:link href="/index.jsp" useContextPath="true">Web App Indedx (again)</ww:link></li>
    <li><ww:link href="/index.jsp" useContextPath="false">Web Server Index</ww:link></li>
  </ul>

  <ww:linkInfo href="index.jsp">
    <ww:linkParameter name="album" value="Science & Nature"/>
    <ww:link>Science &amp; Nature</ww:link>
  </ww:linkInfo>

  <h3>Using samePageLinkInfo</h3>
  <ww:samePageLinkInfo>
    <ww:linkParameter name="album" value="Science & Nature"/>
    <ww:link>Science &amp; Nature</ww:link>
  </ww:samePageLinkInfo>
  <ww:samePageLinkInfo>
    <ww:linkParameter name="album" value="SomeThingElse"/>
    <ww:link>SomeThingElse</ww:link>
  </ww:samePageLinkInfo>

  <p>
    More - need to test linkInfo, and linkParameter here too.
  </p>

  </tiles:put>
</tiles:insert>

