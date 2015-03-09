<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Popup" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

  <ww:script src="/ww_resources/ww_misc.js"/>

  <p>
    Same tests as for the <a href="link.jsp">link</a> tag.
  </p>
  <ul>
    <li><ww:popup href="index.jsp">Local Index</ww:popup></li>
    <li><ww:popup href="index.jsp" useContextPath="true">Local Index (again)</ww:popup></li>
    <li><ww:popup href="index.jsp" useContextPath="false">Local Index (again)</ww:popup></li>
    <li><ww:popup href="/index.jsp">Web App Index</ww:popup></li>
    <li><ww:popup href="/index.jsp" useContextPath="true">Web App Indedx (again)</ww:popup></li>
    <li><ww:popup href="/index.jsp" useContextPath="false">Web Server Index</ww:popup></li>
  </ul>

  <p>
    Each link should popup in a new window
  </p>

  <ul>

    <li><ww:popup href="index.jsp">Index</ww:popup> - Plain</li>
    <li><ww:popup href="index.jsp" width="300">Index</ww:popup> - Width 300</li>
    <li><ww:popup href="index.jsp" height="300">Index</ww:popup> - Height 300</li>
    <li><ww:popup href="index.jsp" width="300" height="200">Index</ww:popup> - Width 300 Height 200</li>

  </ul>

  <p>
    Each link uses the name FRED, so only one new window should open up
  </p>

  <ul>

    <li><ww:popup windowName="fred" href="index.jsp">Index</ww:popup> - Plain</li>
    <li><ww:popup windowName="fred" href="index.jsp" width="300">Index</ww:popup> - Width 300</li>
    <li><ww:popup windowName="fred" href="index.jsp" height="300">Index</ww:popup> - Height 300</li>
    <li><ww:popup windowName="fred" href="index.jsp" width="300" height="200">Index</ww:popup> - Width 300 Height 200</li>

  </ul>

  <p>
    Each link uses the name BOB, so only one new window should open up
  </p>

  <ul>

    <li><ww:popup windowName="bob" href="index.jsp">Index</ww:popup> - Plain</li>
    <li><ww:popup windowName="bob" href="index.jsp" width="300">Index</ww:popup> - Width 300</li>
    <li><ww:popup windowName="bob" href="index.jsp" height="300">Index</ww:popup> - Height 300</li>
    <li><ww:popup windowName="bob" href="index.jsp" width="300" height="200">Index</ww:popup> - Width 300 Height 200</li>

  </ul>



  </tiles:put>
</tiles:insert>

