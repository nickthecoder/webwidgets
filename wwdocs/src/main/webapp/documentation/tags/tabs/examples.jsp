<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

    <table class="examples">

      <tr>
        <td colspan="2">
          <a name="example1"></a>
          <h3>Example 1</h3>
          <p>
            Some tabs, using url pattern matching to determin which tab to turn on.
          </p>
        </td>
      </tr>

      <tr>
        <td>
          <ww:tabs>

            <ww:tab><ww:link href="/">Home</ww:link></ww:tab>
            <ww:tab local="true" pattern="/documentation/apis/.*"><ww:link href="/documentation/apis/tab.jsp">Apis</ww:link></ww:tab>
            <ww:tab local="true" pattern="/documentation/groups/.*"><ww:link href="/documentation/groups/tabs.jsp">Documentation</ww:link></ww:tab>
            <ww:tab><a href="/">Root</a></ww:tab>

          </ww:tabs>
          <div class="ww_tabsContent">
            Hello world
          </div>

        </td>

        <td>
<pre class="code"><ww:noEval>
<ww:tabs>

  <ww:tab><ww:link href="/">Home</ww:link></ww:tab>
  <ww:tab local="true" pattern="/documentation/apis/.*"><ww:link href="/documentation/apis/tab.jsp">Apis</ww:link></ww:tab>
  <ww:tab local="true" pattern="/documentation/groups/.*"><ww:link href="/documentation/groups/tabs.jsp">Documentation</ww:link></ww:tab>
  <ww:tab><a href="/">Root</a></ww:tab>

</ww:tabs>
<div class="ww_tabsContent">
  Hello world
</div>
</ww:noEval></pre>
        </td>
      </tr>


      <tr>
        <td colspan="2">
          <a name="example1"></a>
          <h3>Example 2</h3>
          <p>
            Some tabs, using EL expressions to determine which tab is on.
          </p>
        </td>
      </tr>

      <tr>
        <td>
          <ww:tabs>

            <ww:tab test="false"><a href="#">Off</a></ww:tab>
            <ww:tab test="true"><a href="#">On</a></ww:tab>
            <ww:tab test="false"><a href="#">Also Off</a></ww:tab>
            <ww:tab test="false"><a href="#">One more Off</a></ww:tab>

          </ww:tabs>
          <div class="ww_tabsContent">
            Hello world
          </div>

        </td>

        <td>
<pre class="code"><ww:noEval>
<ww:tabs>

  <ww:tab test="false"><a href="#">Off</a></ww:tab>
  <ww:tab test="true"><a href="#">On</a></ww:tab>
  <ww:tab test="false"><a href="#">Also Off</a></ww:tab>
  <ww:tab test="false"><a href="#">One more Off</a></ww:tab>

</ww:tabs>
<div class="ww_tabsContent">
  Hello world
</div>
</ww:noEval></pre>
        </td>
      </tr>


</table>

