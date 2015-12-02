<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="quote" />

  <tiles:put name="about" type="string">
    <p>
      Places quote marks around a piece of text.
    </p>
    <p>
      This is now trivial, using css's :before and :after selectors.
      However, this tag was written before they existed, and so had to do it the hard way,
      using background images on the first and last words.
    </p>
    <p>
      The current implementation just wraps the body in a span tag, leaving it up to the css to
      add the quotes.
    <p>
  
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">

      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>

      <tr>
        <td>styleClass</td>
        <td>no</td>
        <td>
          The css class for the span. Defaults to 'ww_quote'.
        </td>
      </tr>

    </table>

  </tiles:put>


  <tiles:put name="examples" type="string">

<h3>Example 1</h3>
<p>
  A normal quote.
</p>
<p>
  <ww:quote>The quick brown fox jumped over the lazy dog.</ww:quote>
</p>

<pre class="code"><ww:noEval>
<ww:quote>The quick brown fox jumped over the lazy dog.</ww:quote>
</ww:noEval></pre>

<h3>Example 2</h3>
<p>
  A fancy quote.
</p>

<ww:quote styleClass="ww_quote2">The quick brown fox jumped over the lazy dog</ww:quote>

<pre class="code"><ww:noEval>
<ww:quote styleClass="ww_quote2">The quick brown fox jumped over the lazy dog</ww:quote>
</ww:noEval></pre>

  </tiles:put>

</tiles:insert>

