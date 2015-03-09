<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="sort" />

  <tiles:put name="about" type="string">
    <p>
      Takes a collection or iterator, and returns the collection ordered.
      The ordering is either the natural order of the elements, or specified
      by a Comparator.
    </p>
    <p>
      You can optionally choose to sort based on a single field rather of each item.
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
          A collection or iterator of items to be sorted.
        </td>
      </tr>
      <tr>
        <td>var</td>
        <td>yes</td>
        <td>
          The name of the variable to place into the request scope
          which will hold the sorted collection.
        </td>
      </tr>
      <tr>
        <td>comparator</td>
        <td>no</td>
        <td>
          If set, defines the ordering. Defaults to null.
        </td>
      </tr>
      <tr>
        <td>reverse</td>
        <td>no</td>
        <td>
          Reverses the order.
        </td>
      </tr>
      <tr>
        <td>field</td>
        <td>no</td>
        <td>
          The name of the field to sort. If this is null, then the objects in the <i>items</i>
          are compared.
          <br/>
          This is achieved by evaluating the following EL expression, each time objects need to
          be compared :
          <br/>
          comparator.compare( ${itemA.field}, ${itemB.field} )
          <br/>
          Note, <i>field</i> can be more complex than a single field, for example if field is
          "class.name", then the collection will be sorted based on the item's class names
          (as getClass().getName() is called when comparing items in the collection).
        </td>
      </tr>

    </table>

  </tiles:put>

  <tiles:put name="examples" type="string">

  <%
    java.util.LinkedList names = new java.util.LinkedList();
    request.setAttribute( "names", names );
    names.add( "Nick" );
    names.add( "Nalin" );
    names.add( "Steve" );
    names.add( "Lilian" );
    names.add( "Peter" );
  %>

  <table class="examples">

      <tr>
        <td colspan="2">
          Sort a set of names.
        </td>
      </tr>

      <tr>
        <td>
          <ww:sort items="${names}" var="sortedNames"/>
          <ul>
          <c:forEach items="${sortedNames}" var="name">
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

<ww:sort items="${names}" var="sortedNames"/>
<ul>
<c:forEach items="${sortedNames}" var="name">
  <li><c:out value="${name}"/></li>
</c:forEach>
</ul>
</ww:noEval></pre>
        </td>
      </tr>

    </table>



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
  <table class="examples">

      <tr>
        <td colspan="2">
          Sort a set of files, using just their filename, not their full paths.
        </td>
      </tr>

      <tr>
        <td>
 
  <ww:sort items="${files}" var="sorted" field="name"/>
  <ww:sort items="${files}" var="sorted" field="name"/>
  <ul>
  <c:forEach items="${sorted}" var="item">
    <li><c:out value="${item}"/></li>
  </c:forEach>
  </ul>

        </td>

        <td>
<pre class="code"><ww:noEval>
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
  <ww:sort items="${files}" var="sorted" field="name"/>
  <ul>
  <c:forEach items="${sorted}" var="item">
    <li><c:out value="${item}"/></li>
  </c:forEach>
  </ul>
</ww:noEval>
        </td>

      </tr>

  </tiles:put>

</tiles:insert>

