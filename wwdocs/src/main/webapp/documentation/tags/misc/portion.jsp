<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="portion" />

  <tiles:put name="about" type="string">
    <p>
      Takes a collection of items, and turns it into a two dimensional structure.
      i.e. it creates a collection of portions, each portion holding a subset of
      the original collection.
    </p>

    <p>
      The most common use, is to create a grid of items, using a html table.
      In this case, each portion will be a <code>tr</code>, and each item will
      be a <code>td</code>.
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
        <td>item</td>
        <td>yes</td>
        <td>
          The items in the collection.
        </td>
      </tr>

      <tr>
        <td>var</td>
        <td>yes</td>
        <td>
          The name of the variable to hold the collection of portions.
        </td>
      </tr>

      <tr>
        <td>portions</td>
        <td>no</td>
        <td>
          The number of portions the collection is divided into.
          The number of items in each portion will be calculated, based on the number
          of items.
          <br/>
          Either <code>portions</code> must be set, or <code>portionSize</code>, but not both.
        </td>
      </tr>

      <tr>
        <td>portionSize</td>
        <td>no</td>
        <td>
          The size of each portion.
          The number of portions will be calculated, based on the number of items.
          <br/>
          Either <code>portions</code> must be set, or <code>portionSize</code>, but not both.
        </td>
      </tr>

      <tr>
        <td>transpose</td>
        <td>no</td>
        <td>
          Change the way that the items are layed out, down then across, instead of
          across and then down. The default is true.
        </td>
      </tr>

      <tr>
        <td>pad</td>
        <td>no</td>
        <td>
          Determines if addition padding is added, when the number of items
          does not fit completely into a rectangular grid. Defaults to false.
        </td>
      </tr>

      <tr>
        <td>padding</td>
        <td>no</td>
        <td>
          Determines the contents of the padding. The default is null. Setting
          to anything other than null will cause the padding to be used, regardless
          of the value of the pad attribute.
        </td>
      </tr>

    </table>

  </tiles:put>


  <tiles:put name="examples" type="string">

    <%
      java.util.List numbers = new java.util.LinkedList();
      numbers.add( "One" );
      numbers.add( "Two" );
      numbers.add( "Three" );
      numbers.add( "Four" );
      numbers.add( "Five" );
      numbers.add( "Six" );
      numbers.add( "Seven" );
      request.setAttribute( "numbers", numbers );

      java.util.List empty = new java.util.LinkedList();
      request.setAttribute( "emptyList", empty );
    %>

    <table class="examples">

      <tr>
        <td colspan="2">
          <h3>Example 1</h3>
          <p>
            Two portions (portions="2"). The number of columns is the
            number of items in the collection / 2 (rounded up).
          </p>
        </td>
      </tr>

      <tr>
        <td>
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
        </td>
        <td>

<pre class="code"><ww:noEval>
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
</ww:noEval></pre>
        </td>
      </tr>


      <tr>
        <td colspan="2">
          <h3>Example 2</h3>
          <p>
            Portion Size (portionSize="2"). There will always be two columns, and the
            number of rows is the number of items in the collection / 2 (rounded up).
          </p>
        </td>
      </tr>

      <tr>
        <td>
          <table border="1">
          <ww:portion var="portions" items="${numbers}" portionSize="2">
            <c:forEach var="portion" items="${portions}">
            <tr>
              <c:forEach var="number" items="${portion}">
                <td><c:out value="${number}" /></td>
              </c:forEach>
            </tr>
            </c:forEach>
          </ww:portion>
          </table>
        </td>
        <td>

<pre class="code"><ww:noEval>
<table border="1">
<ww:portion var="portions" items="${numbers}" portionSize="2">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td><c:out value="${number}" /></td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>
</ww:noEval></pre>
        </td>
      </tr>


      <tr>
        <td colspan="2">
          <h3>Example 3</h3>
          <p>
            As above, but the rows and columns are transposed. (transpose="true")
          </p>
        </td>
      </tr>

      <tr>
        <td>
          <table border="1">
          <ww:portion var="portions" items="${numbers}" portionSize="2" transpose="true">
            <c:forEach var="portion" items="${portions}">
            <tr>
              <c:forEach var="number" items="${portion}">
                <td><c:out value="${number}" /></td>
              </c:forEach>
            </tr>
            </c:forEach>
          </ww:portion>
          </table>
        </td>
        <td>

<pre class="code"><ww:noEval>
<table border="1">
<ww:portion var="portions" items="${numbers}" portionSize="2" transpose="true">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td><c:out value="${number}" /></td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>
</ww:noEval></pre>
        </td>
      </tr>


      <tr>
        <td colspan="2">
          <h3>Example 4</h3>
          <p>
            As example 2, with padding="true". Note that the bottom right cell now exists.
            Previously, there were only 7 td's. There is a problem doing this though,
            the body of the inner forEach loop will be evaluated, and the loop variable
            will be null for the missing cells, so you might have to protect yourself from
            null pointer exceptions (using a simple if statement).
          </p>
        </td>
      </tr>

      <tr>
        <td>
          <table border="1">
          <ww:portion var="portions" items="${numbers}" portionSize="2" pad="true">
            <c:forEach var="portion" items="${portions}">
            <tr>
              <c:forEach var="number" items="${portion}">
                <td>
                  <c:if test="${number != null}"><c:out value="${number}" /></c:if>
                </td>
              </c:forEach>
            </tr>
            </c:forEach>
          </ww:portion>
          </table>
        </td>
        <td>

<pre class="code"><ww:noEval>
<table border="1">
<ww:portion var="portions" items="${numbers}" portionSize="2" pad="true">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td>
        <c:if test="${number != null}"><c:out value="${number}" /></c:if>
      </td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>
</ww:noEval></pre>
        </td>
      </tr>


      <tr>
        <td colspan="2">
          <h3>Example 5</h3>
          <p>
            Similar to example 4, but this time, we can get around null pointer exceptions
            by specifying the value to add as padding. This can be any java object, I've
            used the String "x" for simplicity.
        </td>
      </tr>

      <tr>
        <td>
          <table border="1">
          <ww:portion var="portions" items="${numbers}" portionSize="2" padding="${'x'}">
            <c:forEach var="portion" items="${portions}">
            <tr>
              <c:forEach var="number" items="${portion}">
                <td>
                  <c:if test="${number != null}"><c:out value="${number}" /></c:if>
                </td>
              </c:forEach>
            </tr>
            </c:forEach>
          </ww:portion>
          </table>
        </td>
        <td>

<pre class="code"><ww:noEval>
<table border="1">
<ww:portion var="portions" items="${numbers}" portionSize="2" padding="${'x'}">
  <c:forEach var="portion" items="${portions}">
  <tr>
    <c:forEach var="number" items="${portion}">
      <td>
        <c:if test="${number != null}"><c:out value="${number}" /></c:if>
      </td>
    </c:forEach>
  </tr>
  </c:forEach>
</ww:portion>
</table>
</ww:noEval></pre>
        </td>
      </tr>


    </table>

  </tiles:put>

</tiles:insert>

