<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/apis/apisTemplate.jsp">
  <tiles:put name="name" type="string" value="tab"/>
  <tiles:put name="api" value="/documentation/tags/tabs/tab.jsp" />

  <tiles:put name="overview" value="/documentation/tags/tabs/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/tabs/examples.jsp" />

  <ww:breadcrumb label="tab"/>
</tiles:insert>
