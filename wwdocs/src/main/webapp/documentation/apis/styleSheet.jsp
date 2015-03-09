<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/apis/apisTemplate.jsp">
  <tiles:put name="name" type="string" value="styleSheet"/>
  <tiles:put name="api" value="/documentation/tags/misc/styleSheet.jsp" />

  <ww:breadcrumb label="styleSheet"/>

</tiles:insert>
