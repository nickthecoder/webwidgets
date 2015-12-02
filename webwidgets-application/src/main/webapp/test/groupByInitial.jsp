<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Group By Initial" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

    <%
      java.util.ArrayList list = new java.util.ArrayList();
      list.add( "Nick" );
      list.add( "Nalin" );
      list.add( "Fred" );
      list.add( "Jane" );

      request.setAttribute( "names", list );
    %>

    <ul>
      <c:forEach var="name" items="${names}">
        <li><c:out value="${name}"/></li>
      </c:forEach>
    </ul>

    <ww:groupByInitial var="letters" items="${names}">

      <c:forEach var="letter" items="${letters}">
        <ul>
          <li>
            <c:out value="${letter}"/> :
            <c:forEach var="name" items="${letter.items}">
              <c:out value="${name}"/>
            </c:forEach>
          </li>
        </ul>
      </c:forEach>

    </ww:groupByInitial>

  </tiles:put>
</tiles:insert>

