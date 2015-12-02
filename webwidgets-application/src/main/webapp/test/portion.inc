<h3>Two Portions</h3>
<table border="1">
<ww:portion var="portions" items="${numbers}" portions="2" transpose="${transpose}">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td><c:out value="${number}" /></td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>

<h3>Three Portions</h3>
<table border="1">
<ww:portion var="portions" items="${numbers}" portions="3" transpose="${transpose}">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td><c:out value="${number}" /></td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>

<h3>Portions Size Two</h3>
<table border="1">
<ww:portion var="portions" items="${numbers}" portionSize="2" transpose="${transpose}">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td><c:out value="${number}" /></td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>

<h3>Portions Size Five</h3>
<table border="1">
<ww:portion var="portions" items="${numbers}" portionSize="5" transpose="${transpose}">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td><c:out value="${number}" /></td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>

<h3>Portions Size Five Padded</h3>
<table border="1">
<ww:portion var="portions" items="${numbers}" portionSize="5" transpose="${transpose}" pad="normal">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td><c:out value="${number}" /></td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>

<h3>Portions Size Five With Padding</h3>
<table border="1">
<ww:portion var="portions" items="${numbers}" portionSize="5" transpose="${transpose}" padding="xxx">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td><c:out value="${number}" /></td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>

