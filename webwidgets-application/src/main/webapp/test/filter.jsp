<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Filter" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

<%

  java.util.LinkedList names = new java.util.LinkedList();
  request.setAttribute( "names", names );
  names.add( new Integer( 3 ) );
  names.add( new Integer( 1 ) );
  names.add( new Integer( 2 ) );
  names.add( new Integer( 4 ) );
  names.add( new Integer( 6 ) );
  names.add( new Integer( 0 ) );
  names.add( new Integer( 5 ) );
  names.add( new Integer( 2 ) );

%>

<h1>Test Filter Tag</h1>


<h2>Filter</h2>

  <ww:filter items="${names}" var="filtered" itemName="number" test="number > 3"/>
  <ul>
  <c:forEach items="${filtered}" var="item">
    <li><c:out value="${item}"/></li>
  </c:forEach>
  </ul>


  </tiles:put>
</tiles:insert>

