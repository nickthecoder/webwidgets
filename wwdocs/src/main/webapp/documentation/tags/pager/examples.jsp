<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

    <p>
      I've not done anything fancy in these examples - a simple improvement
      would be to add arrow images to the "previous" and "next" links.
    </p>

<h3>Example 1</h3>
<p>
  Current page = 1, with 6 pages in total. As there aren't any pages
  before the current one, the pageLinks of type previous and before
  produce no output (their bodies are iterated zero times).
</p>
<ww:linkInfo href="pager.jsp">
  <ww:pager pages="6" page="1">
    <ww:pagerLinks type="previous"> <ww:link>Previous</ww:link></ww:pagerLinks>
    <ww:pagerLinks type="before"> (<ww:link>${page}</ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="current"> (${page}) </ww:pagerLinks>
    <ww:pagerLinks type="after"> (<ww:link>${page}</ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="next"> <ww:link>Next</ww:link></ww:pagerLinks>
  </ww:pager>
</ww:linkInfo>

<pre class="code"><ww:noEval>
<ww:linkInfo href="pager.jsp">
  <ww:pager pages="6" page="1">
    <ww:pagerLinks type="previous"> <ww:link>Previous</ww:link></ww:pagerLinks>
    <ww:pagerLinks type="before"> (<ww:link>${page}</ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="current"> (${page}) </ww:pagerLinks>
    <ww:pagerLinks type="after"> (<ww:link>${page}</ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="next"> <ww:link>Next</ww:link></ww:pagerLinks>
  </ww:pager>
</ww:linkInfo>
</ww:noEval></pre>

<h3>Example 2</h3>
<p>
  Page 15 of 100. Note, I've also added a hashmap named searchCriteria to the
  linkInfo tag. When you click a link, you will see how the content of the
  hash map are sent as parameters to the web server (as well as the page number
  that you chose).
 </p>
<%
  java.util.HashMap searchCriteria = new java.util.HashMap();
  searchCriteria.put( "search", "monty python" );
  searchCriteria.put( "category", "tv" );
  request.setAttribute( "searchCriteria", searchCriteria );
%>

<ww:linkInfo href="pager.jsp" parameters="${searchCriteria}">
  <ww:pager pages="100" page="15">
    <ww:pagerLinks type="previous"> <ww:link>Previous</ww:link></ww:pagerLinks>
    <ww:pagerLinks type="before"> (<ww:link>${page}</ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="current"> (${page}) </ww:pagerLinks>
    <ww:pagerLinks type="after"> (<ww:link>${page}</ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="next"> <ww:link>Next</ww:link></ww:pagerLinks>
  </ww:pager>
</ww:linkInfo>

<pre class="code"><ww:noEval>
<ww:linkInfo href="pager.jsp" parameters="${searchCriteria}">
  <ww:pager pages="100" page="15">
    <ww:pagerLinks type="previous"> <ww:link>Previous</ww:link></ww:pagerLinks>
    <ww:pagerLinks type="before"> (<ww:link>${page}</ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="current"> (${page}) </ww:pagerLinks>
    <ww:pagerLinks type="after"> (<ww:link>${page}</ww:link>) </ww:pagerLinks>
    <ww:pagerLinks type="next"> <ww:link>Next</ww:link></ww:pagerLinks>
  </ww:pager>
</ww:linkInfo>
</ww:noEval></pre>

<h3>Example 3</h3>
<p>
  Here's an example to show that the look and feel is up to you.
</p>
  <table class="pager"><tr>
  <ww:linkInfo href="pager.jsp" parameters="${searchCriteria}">
    <ww:pager pages="100" page="15" previousPages="5" nextPages="4">
      <ww:pagerLinks type="previous"><th><ww:link>&lt;</ww:link></th></ww:pagerLinks>
      <ww:pagerLinks type="before"><td><ww:link>${page}</ww:link></td></ww:pagerLinks>
      <ww:pagerLinks type="current"><td>${page}</td></ww:pagerLinks>
      <ww:pagerLinks type="after"><td><ww:link>${page}</ww:link></td></ww:pagerLinks>
      <ww:pagerLinks type="next"><th><ww:link>&gt;</ww:link></th></ww:pagerLinks>
    </ww:pager>
  </ww:linkInfo>
  </tr></table>

<pre class="code"><ww:noEval>
<table class="pager"><tr>
<ww:linkInfo href="pager.jsp" parameters="${searchCriteria}">
  <ww:pager pages="100" page="15" previousPages="5" nextPages="4">
    <ww:pagerLinks type="previous"><th><ww:link>&lt;</ww:link></th></ww:pagerLinks>
    <ww:pagerLinks type="before"><td><ww:link>${page}</ww:link></td></ww:pagerLinks>
    <ww:pagerLinks type="current"><td>${page}</td></ww:pagerLinks>
    <ww:pagerLinks type="after"><td><ww:link>${page}</ww:link></td></ww:pagerLinks>
    <ww:pagerLinks type="next"><th><ww:link>&gt;</ww:link></th></ww:pagerLinks>
  </ww:pager>
</ww:linkInfo>
</tr></table>
</ww:noEval></pre>
