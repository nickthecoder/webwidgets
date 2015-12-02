<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/apis/apisTemplate.jsp">
  <tiles:put name="name" type="string" value="mapLink"/>
  <tiles:put name="api" value="/documentation/tags/maps/mapLink.jsp" />

  <tiles:put name="overview" value="/documentation/tags/maps/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/maps/examples.jsp" />

  <ww:breadcrumb label="mapLink"/>
</tiles:insert>
