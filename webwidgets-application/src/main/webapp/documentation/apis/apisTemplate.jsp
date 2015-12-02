<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/style/plain.jsp">

  <tiles:put name="title" type="string" >
    API for Tag : <tiles:insert attribute="name" flush="false"/>
  </tiles:put>

  <tiles:put name="navigation" value="/documentation/apis/navigation.jsp" />

  <tiles:put name="content" type="string">

    <tiles:useAttribute id="name" name="name" ignore="true"/>
    <tiles:useAttribute id="overview" name="overview" ignore="true"/>
    <tiles:useAttribute id="examples" name="examples" ignore="true"/>

    <c:if test="${overview != null}">
      <h2>Overview</h2>
      <tiles:insert attribute="overview" flush="false"/>
    </c:if>

    <tiles:insert attribute="api" flush="false"/>

    <c:if test="${examples != null}">
      <h2>Examples</h2>
      <tiles:insert attribute="examples" flush="false"/>
    </c:if>

  </tiles:put>

</tiles:insert>


