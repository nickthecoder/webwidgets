<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Sort" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

<%

  java.util.LinkedList files = new java.util.LinkedList();
  request.setAttribute( "files", files );
  files.add( new java.io.File( "/usr/xyz" ) );
  files.add( new java.io.File( "/tmp/foo" ) );
  files.add( new java.io.File( "/tmp/b" ) );
  files.add( new java.io.File( "/usr/abc" ) );
  files.add( new java.io.File( "/usr/bar" ) );
  files.add( new java.io.File( "/tmp/a" ) );


%>

<h1>Test Sort Tag</h1>


<h2>Sort by natural ordering</h2>

  <ww:sort items="${files}" var="sorted"/>
  <ul>
  <c:forEach items="${sorted}" var="item">
    <li><c:out value="${item}"/></li>
  </c:forEach>
  </ul>

<h3>Reversed</h3>

  <ww:sort items="${files}" var="sorted" reverse="true"/>
  <ul>
  <c:forEach items="${sorted}" var="item">
    <li><c:out value="${item}"/></li>
  </c:forEach>
  </ul>

<h2>Sort by natural ordering of field "name"</h2>

  <ww:sort items="${files}" var="sorted" field="name"/>
  <table>
  <tr>
    <th>getName()</th>
    <th>File</th>
  </tr>
  <c:forEach items="${sorted}" var="item">
    <tr>
      <td><c:out value="${item.name}"/></td>
      <td><c:out value="${item}"/></td>
    </tr>
  </c:forEach>
  </table>

<h3>Reversed</h3>

  <ww:sort items="${files}" var="sorted" field="name" reverse="true"/>
  <table>
  <tr>
    <th>getName()</th>
    <th>File</th>
  </tr>
  <c:forEach items="${sorted}" var="item">
    <tr>
      <td><c:out value="${item.name}"/></td>
      <td><c:out value="${item}"/></td>
    </tr>
  </c:forEach>
  </table>


  </tiles:put>
</tiles:insert>

