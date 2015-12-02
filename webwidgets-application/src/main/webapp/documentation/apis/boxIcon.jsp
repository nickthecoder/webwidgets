<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/apis/apisTemplate.jsp">
  <tiles:put name="name" type="string" value="boxIcon"/>
  <tiles:put name="api" value="/documentation/tags/boxes/boxIcon.jsp" />

  <tiles:put name="overview" value="/documentation/tags/boxes/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/boxes/examples.jsp" />

  <ww:breadcrumb label="boxIcon"/>

</tiles:insert>
