<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/style/plain.jsp">

  <tiles:put name="title" type="string" >
    Web Widgets : Misc
  </tiles:put>

  <tiles:put name="navigation" value="/documentation/groups/navigation.jsp" />

  <tiles:put name="content" type="string">



  </tiles:put>

  <ww:breadcrumb label="Misc"/>

</tiles:insert>
