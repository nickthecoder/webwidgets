<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/apis/apisTemplate.jsp">
  <tiles:put name="name" type="string" value="link"/>
  <tiles:put name="api" value="/documentation/tags/links/link.jsp" />

  <tiles:put name="overview" value="/documentation/tags/links/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/links/examples.jsp" />

  <ww:breadcrumb label="link"/>
</tiles:insert>
