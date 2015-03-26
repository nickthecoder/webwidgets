<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>
<h3>Example 1</h3>
<p>
  A link to this page, using an absolute url.
</p>
<ww:link href="/documentation/apis/link.jsp">link</ww:link>

<pre class="code"><ww:noEval>
<ww:link href="/documentation/apis/link.jsp">link</ww:link>
</ww:noEval></pre>

<h3>Example 2</h3>
<p>
  A popup link to this page, using an absolute url.
</p>
<ww:popup href="/documentation/apis/link.jsp">popup</ww:popup>

<pre class="code"><ww:noEval>
<ww:popup href="/documentation/apis/link.jsp">popup</ww:popup>
</ww:noEval></pre>

<h3>Example 3</h3>
<p>
  Using linkInfo and linkParameter to define a link.
</p>

<ww:linkInfo href="link.jsp">
  <ww:linkParameter name="age" value="32"/>
  <ww:linkParameter name="sex" value="male"/>
  <ww:link>Link</ww:link>
</ww:linkInfo>

<pre class="code"><ww:noEval>
<ww:linkInfo href="link.jsp">
  <ww:linkParameter name="age" value="32"/>
  <ww:linkParameter name="sex" value="male"/>
  <ww:link>Link</ww:link>
</ww:linkInfo>
</ww:noEval></pre>
