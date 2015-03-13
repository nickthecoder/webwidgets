<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

    <table class="examples">

      <tr>
        <td colspan="2">
          <a name="example1"></a>
          <h3>Example 1</h3>
          <p>
            A link to this page, using an absolute url.
          </p>
        </td>
      </tr>

      <tr>
        <td>
          <ww:link href="/documentation/link.jsp">link</ww:link>
         </td>
        <td>

<pre class="code"><ww:noEval>
<ww:link href="/documentation/link.jsp">link</ww:link>
</ww:noEval></pre>
        </td>
      </tr>

      <tr>
        <td colspan="2">
          <a name="example2"></a>
          <h3>Example 2</h3>
          <p>
            A popup link to this page, using an absolute url.
          </p>
        </td>
      </tr>

      <tr>
        <td>
          <ww:popup href="/documentation/link.jsp">popup</ww:popup>
         </td>
        <td>

<pre class="code"><ww:noEval>
<ww:popup href="/documentation/link.jsp">popup</ww:popup>
</ww:noEval></pre>
        </td>
      </tr>

      <tr>
        <td colspan="2">
          <a name="example3"></a>
          <h3>Example 3</h3>
          <p>
            Using linkInfo and linkParameter to define a link.
          </p>
        </td>
      </tr>

      <tr>
        <td>
          <ww:linkInfo href="link.jsp">
            <ww:linkParameter name="age" value="32"/>
            <ww:linkParameter name="sex" value="male"/>
            <ww:link>Link</ww:link>
          </ww:linkInfo>
        </td>

        <td>
<pre class="code"><ww:noEval>
<ww:linkInfo href="link.jsp">
  <ww:linkParameter name="age" value="32"/>
  <ww:linkParameter name="sex" value="male"/>
  <ww:link>Link</ww:link>
</ww:linkInfo>
</ww:noEval></pre>
        </td>
      </tr>


    </table>

