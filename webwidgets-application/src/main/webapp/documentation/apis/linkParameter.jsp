<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/apis/apisTemplate.jsp">
  <tiles:put name="name" type="string" value="linkParameter"/>
  <tiles:put name="api" value="/documentation/tags/links/linkParameter.jsp" />

  <tiles:put name="overview" value="/documentation/tags/links/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/links/examples.jsp" />

  <ww:breadcrumb label="linkParameter"/>
</tiles:insert>
