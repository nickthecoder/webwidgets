<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/style/plain.jsp">

  <tiles:put name="title" type="string" >
    Web Widgets : <tiles:insert attribute="name" flush="false"/>
  </tiles:put>

  <tiles:put name="navigation" value="/documentation/groups/navigation.jsp" />

  <tiles:put name="content" type="string">

    <tiles:useAttribute id="overview" name="overview" ignore="true"/>
    <tiles:useAttribute id="examples" name="examples" ignore="true"/>

    <h1><tiles:insert attribute="name" flush="false"/></h1>
    <tiles:insert attribute="tags" ignore="true" flush="false"/>

    <c:if test="${overview != null}">
      <h2>Overview</h2>
      <tiles:insert attribute="overview" flush="false"/>
    </c:if>

    <h2>Details</h2>
    <tiles:insert attribute="details" flush="false"/>

    <c:if test="${examples != null}">
      <h2>Examples</h2>
      <tiles:insert attribute="examples" flush="false"/>
    </c:if>

  </tiles:put>

</tiles:insert>


