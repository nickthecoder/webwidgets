<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<h3>Example 1</h3>
<p>
  A simple example, without a css style (so no pretty colours etc).
</p>
<ww:box>
  <ww:boxTitle clickable="true" title="Hello World"/>
  <ww:boxContent>
    This is the content of the box.
  </ww:boxContent>
</ww:box>

<pre class="code"><ww:noEval>
<ww:box>
  <ww:boxTitle clickable="true" title="Hello World"/>
  <ww:boxContent>
    This is the content of the box.
  </ww:boxContent>
</ww:box>
</ww:noEval></pre>

<h3>Example 2</h3>
<p>
  Using the purpleBox style, and also using the body of the boxTitle
</p>
<ww:box styleClass="purpleBox">
  <ww:boxTitle clickable="true">Hello World</ww:boxTitle>
  <ww:boxContent>
    This is the content of the box.
  </ww:boxContent>
</ww:box>

<pre class="code"><ww:noEval>
<ww:box styleClass="purpleBox">
  <ww:boxTitle clickable="true">Hello<br/>World</ww:boxTitle>
  <ww:boxContent>
    This is the content of the box.
  </ww:boxContent>
</ww:box>
</ww:noEval></pre>

<h3>Example 3</h3>
<p>
  A much more complicated example, with minimize and close icons, as well as two inner boxes.
</p>
<ww:box styleClass="purpleBox">
  <ww:boxTitle title="Hello World" minMax="true" close="true"/>

  <ww:boxContent>
    This is the main content of the box.
  </ww:boxContent>

  <ww:innerBox>
    <ww:boxTitle title="Sub Title 1" minMax="true" close="true"/>

    <ww:boxContent>
      This is sub content 1
    </ww:boxContent>

  </ww:innerBox>

  <ww:innerBox>
    <ww:boxTitle title="Sub Title 2" minMax="true" close="true"/>
    
    <ww:boxContent>
      This is sub content TWO - Cool
    </ww:boxContent>

  </ww:innerBox>

</ww:box>
      
<pre class="code"><ww:noEval>
<ww:box styleClass="purpleBox">
  <ww:boxTitle title="Hello World" minMax="true" close="true"/>

  <ww:boxContent>
    This is the main content of the box.
  </ww:boxContent>

  <ww:innerBox>
    <ww:boxTitle title="Sub Title 1" minMax="true" close="true"/>

    <ww:boxContent>
      This is sub content 1
    </ww:boxContent>

  </ww:innerBox>

  <ww:innerBox>
    <ww:boxTitle title="Sub Title 2" minMax="true" close="true"/>
    
    <ww:boxContent>
      This is sub content TWO - Cool
    </ww:boxContent>

  </ww:innerBox>

</ww:box>
</ww:noEval></pre>

<h3>Example 4</h3>
<p>
  Add custom icons to the box's title.
</p>

<ww:box styleClass="purpleBox">
  <ww:boxTitle title="Hi">
    <ww:boxIcon><a href="javascript:alert('Hello');">!</a></ww:boxIcon>
  </ww:boxTitle>
  <ww:boxContent>
    Click the exclamation mark in the title above!
  </ww:boxContent>
</ww:box>

<pre><ww:noEval>
<ww:box styleClass="purpleBox">
  <ww:boxTitle title="Hi">
    <ww:boxIcon><a href="javascript:alert('Hello');">!</a></ww:boxIcon>
  </ww:boxTitle>
  <ww:boxContent>
    Click the exclamation mark in the title above!
  </ww:boxContent>
</ww:box>
</ww:noEval></pre>
