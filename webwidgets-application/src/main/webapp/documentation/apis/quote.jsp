<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/apis/apisTemplate.jsp">
  <tiles:put name="name" type="string" value="quote"/>
  <tiles:put name="api" value="/documentation/tags/misc/quote.jsp" />

  <ww:breadcrumb label="quote"/>
</tiles:insert>
