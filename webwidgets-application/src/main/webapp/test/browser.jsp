<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Box" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

    Returns basic info about the browser.

    <ww:browser isIe="isIe" supportsPng="supportsPng" version="version"/>

    <ul>
      <li>Supports Png Images : ${supportsPng}</li>
      <li>Is Internet Explorer : ${isIe}</li>
      <li>Version : ${version}</li>
    </ul>

  </tiles:put>
</tiles:insert>
