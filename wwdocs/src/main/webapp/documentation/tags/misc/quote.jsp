<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="quote" />

  <tiles:put name="about" type="string">
    <p>
      Places quote marks around a piece of text. This sounds pretty easy,
      but the quote marks can be images, and I need to ensure that line
      breaks never break up the quotation images, and the letter that
      they touch. To do this, I place one span tag around the open quote and
      the first word, and another around the last word and the close quote.
      These spans use a style, with the quote image as a background image.
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
        <td>type</td>
        <td>no</td>
        <td>
          The type of quote, One of : <br/>
            normal, fancy
        </td>
      </tr>

      <tr>
        <td>styleClass</td>
        <td>no</td>
        <td>
          The css class name. Defaults to 'quote'.
        </td>
      </tr>

    </table>

  </tiles:put>


  <tiles:put name="examples" type="string">

    <table class="examples">

      <tr>
        <td colspan="2">
          <a name="example1"></a>
          <h3>Example 1</h3>
          <p>
            A normal quote.
          </p>
        </td>
      </tr>

      <tr>
        <td align="center">
          <ww:quote>The quick brow fox jumped over the lazy dog</ww:quote>
        </td>

        <td>
<pre class="code"><ww:noEval>
<ww:quote>The quick brow fox jumped over the lazy dog</ww:quote>
</ww:noEval></pre>
        </td>
      </tr>

      <tr>
        <td colspan="2">
          <a name="example2"></a>
          <h3>Example 2</h3>
          A fancy quote.
        </td>
      </tr>

      <tr>
        <td align="center">
          <ww:quote type="fancy">The quick brow fox jumped over the lazy dog</ww:quote>
        </td>

        <td>
<pre class="code"><ww:noEval>
<ww:quote type="fancy">The quick brow fox jumped over the lazy dog</ww:quote>
</ww:noEval></pre>
        </td>
      </tr>


    </table>

  </tiles:put>

</tiles:insert>

