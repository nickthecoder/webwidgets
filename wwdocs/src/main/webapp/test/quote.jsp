<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Quote" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

Resize the window, and see how the quotes are affected.

<h2>Default (normal)</h2>
<div style="width:10%;">
<center>
  <ww:quote>Mary had a little lamb its fleece was white as snow.</ww:quote>
</center>
</div>

<h2>Normal</h2>
<div style="width:10%;">
<center>
  <ww:quote type="normal">Mary had a little lamb its fleece was white as snow.</ww:quote>
</center>
</div>

<h2>Fancy</h2>
<div style="width:10%;">
<center>
  <ww:quote type="fancy">Mary had a little lamb its fleece was white as snow</ww:quote>
</center>
</div>

<h2>Fancy - tricky</h2>
<div style="width:10%;">
<center>
  <ww:quote type="fancy"><b>Mary</b> had a little lamb its fleece was white as <b>snow</b></ww:quote>
</center>
</div>

<h2>Fancy - trickier</h2>
<div style="width:10%;">
<center>
  <ww:quote type="fancy"><b><i>Mary</i></b> had a little lamb its fleece was white as <b><u>snow</u></b></ww:quote>
</center>
</div>

<h1>Help During Development</h1>

<p>
  The following items are pure html - no JSP tags. I created the html first,
  and then wrote the jsp tags, and I've left this preparatory work, as it might
  be useful to someone later.
</p>

<h2>Span the first and last words</h2>
<p>Works fine - the quotes are never separated from the words</p>

<div class="ww_quote" style="width:10%;">
<center>
  <span class="beginQuote">Mary</span> had a little lamb its fleece was white
  as <span class="endQuote">snow.</span>
</center>
</div>

<h2>Using just the first letter</h2>
<p>No good, as the M and ary get separated.</p>

<div class="ww_quote" style="width:10%;">
<center>
  <span class="ww_beginQuote">M</span>ary had a little lamb its fleece was white
  as <span class="ww_endQuote">snow.</span>
</center>
</div>

<h2>If the first word contains a &lt;, then don't have anything in the span</h2>
<p>The quote does get separated, but what else can I do in a generic tag?</p>
<div class="ww_quote" style="width:10%;">
<center>
  <span class="ww_beginQuote"></span><a href="#">Mary</a> had a little lamb its fleece was white
  as <span class="ww_endQuote">snow.</span>
</center>
</div>

<h2>Likewise, if the last word contains a &gt;, then don't have anything in the span</h2>
<p>The quote does get separated, but what else can I do in a generic tag?</p>

<div class="ww_quote" style="width:10%;">
<center>
  <span class="ww_beginQuote">Mary</span> had a little lamb its fleece was white
  as <a href="snow">snow</a><span class="ww_endQuote"></span>
</center>
</div>


  </tiles:put>
</tiles:insert>

