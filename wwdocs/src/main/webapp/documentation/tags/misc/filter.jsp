<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="filter" />

  <tiles:put name="about" type="string">
    <p>
      Takes a collection, or an iterator of objects, and applies a test to them.
      Those that pass the test are added to the filtered collection.
    </p>
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">

      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>

      <tr>
        <td>items</td>
        <td>yes</td>
        <td>
          The input. Can be a Collection, an Iterator, or an array.
        </td>
      </tr>

      <tr>
        <td>var</td>
        <td>yes</td>
        <td>
          The name of the request attribute to hold the filtered list.
        </td>
      </tr>

      <tr>
        <td>test</td>
        <td>yes</td>
        <td>
          The boolean expression used to filter the input. Althought this is treated just like
          a normal EL expression, it is not, and therefore must not be with $ { ... }.
          If it were, then it would get evalutated only once, and it need to be evalutated
          for each item in the input collection.
        </td>
      </tr>

      <tr>
        <td>itemName</td>
        <td>no</td>
        <td>
          The name of the variable which hold each item in the input while it is being filtered.
          Note, this is NOT a request attribute, it is just a name that the "test" expression
          can use to refer to.
        </td>
      </tr>

    </table>

  </tiles:put>


  <tiles:put name="examples" type="string">

    <table class="examples">

      <tr>
        <td colspan="2">

        </td>
      </tr>

      <tr>
        <td>
<%
  java.util.LinkedList names = new java.util.LinkedList();
  request.setAttribute( "names", names );
  names.add( "Nick" );
  names.add( "Nalin" );
  names.add( "Steve" );
  names.add( "Lilian" );
  names.add( "Peter" );
%>

<ww:filter items="${names}" var="filteredNames" test="foo != 'Lilian'" itemName="foo"/>
<ul>
<c:forEach items="${filteredNames}" var="name">
  <li><c:out value="${name}"/></li>
</c:forEach>
</ul>

        </td>
        <td>

<pre class="code"><ww:noEval>
<%
  java.util.LinkedList names = new java.util.LinkedList();
  request.setAttribute( "names", names );
  names.add( "Nick" );
  names.add( "Nalin" );
  names.add( "Steve" );
  names.add( "Lilian" );
  names.add( "Peter" );
%>

<ww:filter items="${names}" var="filteredNames" test="foo != 'Lilian'" itemName="foo"/>
<ul>
<c:forEach items="${filteredNames}" var="name">
  <li><c:out value="${name}"/></li>
</c:forEach>
</ul>
</ww:noEval></pre>

        </td>
      </tr>


    </table>


  </tiles:put>

</tiles:insert>

