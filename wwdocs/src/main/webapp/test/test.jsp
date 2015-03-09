<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Test" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >


    Struts Tiles Attribute : Title = <c:out value="${tiles['title']}"/>

  </tiles:put>
</tiles:insert>

