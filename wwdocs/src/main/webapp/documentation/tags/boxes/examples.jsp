<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

  <table class="examples">
  
      <!-- Edges Example -->
      <tr>
        <td colspan="2">
          Using the <i>edges</i> tags to create a rounded box.
        </td>
      </tr>

      <tr>
        <td>
          <ww:edges className="rounded" width="100px">
            This is the content of the box.
          </ww:edges>
        </td>
        <td>

<pre class="code"><ww:noEval>
<ww:edges className="rounded" width="100px">
  This is the content of the box.
</ww:edges>
</ww:noEval></pre>
        </td>
      </tr>
  
      <!-- Simple example -->
      <tr>
        <td colspan="2">
          A simple example, without a css style (so no pretty colours etc).
        </td>
      </tr>

      <tr>
        <td>
          <ww:box width="100%">
            <ww:boxTitle clickable="true" title="Hello World"/>
            <ww:boxContent>
              This is the content of the box.
            </ww:boxContent>
          </ww:box>
        </td>
        <td>

<pre class="code"><ww:noEval>
<ww:box width="100%">
  <ww:boxTitle clickable="true" title="Hello World"/>
  <ww:boxContent>
    This is the content of the box.
  </ww:boxContent>
</ww:box>
</ww:noEval></pre>
        </td>
      </tr>

      <!-- purple example -->
      <tr>
        <td colspan="2">
          Using the purpleBox style, and also using the body of the boxTitle.
        </td>
      </tr>
      <tr>
        <td>
          <ww:box className="purpleBox" width="100%">
            <ww:boxTitle clickable="true">Hello World</ww:boxTitle>
            <ww:boxContent>
              This is the content of the box.
            </ww:boxContent>
          </ww:box>
        </td>
        <td>
<pre class="code"><ww:noEval>
<ww:box className="purpleBox" width="50%">
  <ww:boxTitle clickable="true">Hello<br/>World</ww:boxTitle>
  <ww:boxContent>
    This is the content of the box.
  </ww:boxContent>
</ww:box>
</ww:noEval></pre>
        </td>
      </tr>

      <tr>
        <td colspan="2">
          A much more complicated example, with minimize and close icons, as well as two inner boxes.
        </td>
      </tr>
      <tr>
        <td>

          <ww:box className="purpleBox" width="100%">
            <ww:boxTitle title="Hello World">
              <ww:boxIcon>
                <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/><ww:standardButton type="close"/>
              </ww:boxIcon>
            </ww:boxTitle>

            <ww:boxContent>
              This is the main content of the box.
            </ww:boxContent>

            <ww:innerBox>
              <ww:boxTitle title="Sub Title 1">
                <ww:boxIcon>
                  <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/>
                </ww:boxIcon>
              </ww:boxTitle>

              <ww:boxContent>
                This is sub content 1
              </ww:boxContent>

            </ww:innerBox>

            <ww:innerBox>
              <ww:boxTitle title="Sub Title 2">
                <ww:boxIcon>
                  <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/>
                </ww:boxIcon>
              </ww:boxTitle>

              <ww:boxContent>
                This is sub content TWO - Cool
              </ww:boxContent>

            </ww:innerBox>

          </ww:box>
        </td>

        <td>

<pre class="code"><ww:noEval>
<ww:box className="purpleBox" width="100%">
  <ww:boxTitle title="Hello World">
    <ww:boxIcon>
      <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/><ww:standardButton type="close"/>
    </ww:boxIcon>
  </ww:boxTitle>

  <ww:boxContent>
    This is the main content of the box.
  </ww:boxContent>

  <ww:innerBox>
    <ww:boxTitle title="Sub Title 1">
      <ww:boxIcon>
        <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/>
      </ww:boxIcon>
    </ww:boxTitle>

    <ww:boxContent>
      This is sub content 1
    </ww:boxContent>

  </ww:innerBox>

  <ww:innerBox>
    <ww:boxTitle title="Sub Title 2">
      <ww:boxIcon>
        <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/>
      </ww:boxIcon>
    </ww:boxTitle>

    <ww:boxContent>
      This is sub content TWO - Cool
    </ww:boxContent>

  </ww:innerBox>

</ww:box>
</ww:noEval></pre>

        </td>

      </tr>

    </table>

