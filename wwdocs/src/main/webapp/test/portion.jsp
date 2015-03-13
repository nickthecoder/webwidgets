<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Portion" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

<%

  java.util.List numbers = new java.util.LinkedList();
  numbers.add( "One" );
  numbers.add( "Two" );
  numbers.add( "Three" );
  numbers.add( "Four" );
  numbers.add( "Five" );
  numbers.add( "Six" );
  numbers.add( "Seven" );
  numbers.add( "Eight" );
  request.setAttribute( "numbers", numbers );

  java.util.List empty = new java.util.LinkedList();
  request.setAttribute( "emptyList", empty );
%>

  <table border="1">

    <tr>
      <td>Normal Sequencing</td>
      <td>Transposed</td>
    </tr>


    <tr>
      <td>
        <% request.setAttribute( "numbers", numbers ); %>
        <% request.setAttribute( "transpose", new Boolean(false) ); %>
        <%@include file="portion.inc"%>

      </td>
      <td>
        <% request.setAttribute( "numbers", numbers ); %>
        <% request.setAttribute( "transpose", new Boolean(true) ); %>
        <%@include file="portion.inc"%>
      </td>
    </tr>
  </table>

  <% request.setAttribute( "numbers", numbers.iterator() ); %>
  <h3>Using an Iterator</h3>
  <table border="1">
  <ww:portion var="portions" items="${numbers}" portions="2">
    <c:forEach var="portion" items="${portions}">
    <tr>
      <c:forEach var="number" items="${portion}">
        <td><c:out value="${number}" /></td>
      </c:forEach>
    </tr>
    </c:forEach>
  </ww:portion>
  </table>

  <h3>Empty List, two portions</h3>
  This should not display any rows.
  <table border="1">
  <ww:portion var="portions" items="${emptyList}" portions="2">
    <c:forEach var="portion" items="${portions}">
    <tr>
      <td>This is a row</td>
      <c:forEach var="number" items="${portion}">
        <td><c:out value="${number}" /></td>
      </c:forEach>
    </tr>
    </c:forEach>
  </ww:portion>
  </table>

  <h3>Empty List, two portions, with pad="normal"</h3>
  This should display two rows, despite there being no data.
  <table border="1">
  <ww:portion var="portions" items="${emptyList}" portions="2" pad="normal">
    <c:forEach var="portion" items="${portions}">
    <tr>
      <td>This is a row</td>
      <c:forEach var="number" items="${portion}">
        <td><c:out value="${number}" /></td>
      </c:forEach>
    </tr>
    </c:forEach>
  </ww:portion>
  </table>



  </tiles:put>
</tiles:insert>

