<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Pager" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

<%
  java.util.HashMap searchCriteria = new java.util.HashMap();
  searchCriteria.put( "search", "monty python" );
  searchCriteria.put( "category", "tv" );
  request.setAttribute( "searchCriteria", searchCriteria );

  java.util.LinkedList names = new java.util.LinkedList();
  request.setAttribute( "names", names );
  names.add( "Nick" );
  names.add( "Nalin" );
  names.add( "Steve" );
  names.add( "Lilian" );
  names.add( "Peter" );
  names.add( "Mike" );
  names.add( "Fiona" );
  names.add( "Matthew" );
  names.add( "Levi" );
  names.add( "Eric" );
  names.add( "Sonia" );
  names.add( "Richard" );
  names.add( "William" );
  names.add( "Fred" );
  names.add( "Jane" );
  names.add( "Joan" );
  names.add( "Joyce" );
  names.add( "Brian" );
  names.add( "Linda" );
  names.add( "Catherine" );
  names.add( "Julie" );
  names.add( "Eileen" );
  names.add( "John" );
  names.add( "George" );
  names.add( "Christine" );
  names.add( "Sarah" );
  names.add( "Billy" );
  names.add( "Bob" );
  names.add( "Kong" );
  names.add( "Sonic" );
  names.add( "Martin" );
  names.add( "Martine" );
  names.add( "James" );
  names.add( "Ringo" );
  names.add( "Paul" );
  names.add( "Frank" );
  names.add( "Jamie" );
  names.add( "Alan" );
  names.add( "Robin" );
  names.add( "James" );
  names.add( "David" );
  names.add( "Lee" );
%>

<h1>Test Pager Tag</h1>


<h2>Page 1 of 1 (should be nothing to see)</h2>

<ww:linkInfo href="pager.jsp">
<ww:pager pages="1" page="1">
  <ww:pagerLinks type="previous"> <ww:link>Previous (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
  <ww:pagerLinks type="before"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="current"> (<c:out value="${page}"/>) </ww:pagerLinks>
  <ww:pagerLinks type="after"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="next"> <ww:link>Next (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
</ww:pager>
</ww:linkInfo>

<h2>Page 1 of 28</h2>

<ww:linkInfo href="pager.jsp" parameters="${searchCriteria}">
<ww:pager pages="28" page="1">
  <ww:pagerLinks type="previous"> <ww:link>Previous (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
  <ww:pagerLinks type="before"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="current"> (<c:out value="${page}"/>) </ww:pagerLinks>
  <ww:pagerLinks type="after"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="next"> <ww:link>Next (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
</ww:pager>
</ww:linkInfo>

<h2>Page 4 of 28</h2>

<ww:linkInfo href="pager.jsp">
<ww:pager pages="28" page="4">
  <ww:pagerLinks type="previous"> <ww:link>Previous (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
  <ww:pagerLinks type="before"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="current"> (<c:out value="${page}"/>) </ww:pagerLinks>
  <ww:pagerLinks type="after"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="next"> <ww:link>Next (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
</ww:pager>
</ww:linkInfo>

<h2>Page 14 of 28</h2>

<ww:linkInfo href="pager.jsp">
<ww:pager pages="28" page="14">
  <ww:pagerLinks type="previous"> <ww:link>Previous (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
  <ww:pagerLinks type="before"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="current"> (<c:out value="${page}"/>) </ww:pagerLinks>
  <ww:pagerLinks type="after"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="next"> <ww:link>Next (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
</ww:pager>
</ww:linkInfo>

<h2>Page 24 of 28</h2>

<ww:linkInfo href="pager.jsp">
<ww:pager pages="28" page="24">
  <ww:pagerLinks type="previous"> <ww:link>Previous (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
  <ww:pagerLinks type="before"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="current"> (<c:out value="${page}"/>) </ww:pagerLinks>
  <ww:pagerLinks type="after"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="next"> <ww:link>Next (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
</ww:pager>
</ww:linkInfo>

<h2>Page 28 of 28</h2>

<ww:linkInfo href="pager.jsp">
<ww:pager pages="28" page="28">
  <ww:pagerLinks type="previous"> <ww:link>Previous (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
  <ww:pagerLinks type="before"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="current"> (<c:out value="${page}"/>) </ww:pagerLinks>
  <ww:pagerLinks type="after"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
  <ww:pagerLinks type="next"> <ww:link>Next (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
</ww:pager>
</ww:linkInfo>


<hr/>

<h2>Using items attribute</h2>
<ww:pager items="${names}" itemsPerPage="5" subsetVar="someNames" >

  <c:forEach items="${someNames}" var="name">
    <c:out value="${name}"/><br/>
  </c:forEach>

  <ww:linkInfo href="pager.jsp">
    <ww:pagerLinks type="previous"> <ww:link>Previous (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
    <ww:pagerLinks type="before"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="current"> (<c:out value="${page}"/>) </ww:pagerLinks>
    <ww:pagerLinks type="after"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="next"> <ww:link>Next (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
  </ww:linkInfo>

</ww:pager>

<h2>Using items, and currentItem = bob</h2>
<ww:pager items="${names}" itemsPerPage="5" subsetVar="someNames" currentItem="Bob">

  <c:forEach items="${someNames}" var="name" >
    <c:out value="${name}"/><br/>
  </c:forEach>

  <ww:linkInfo href="pager.jsp">
    <ww:pagerLinks type="previous"> <ww:link>Previous (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
    <ww:pagerLinks type="before"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="current"> (<c:out value="${page}"/>) </ww:pagerLinks>
    <ww:pagerLinks type="after"> (<ww:link><c:out value="${page}"/></ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="next"> <ww:link>Next (<c:out value="${page}"/>)</ww:link></ww:pagerLinks>
  </ww:linkInfo>

</ww:pager>

  </tiles:put>
</tiles:insert>

