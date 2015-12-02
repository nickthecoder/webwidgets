<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="icon" />

  <tiles:put name="about" type="string">
    <p>
      Creates a &lt;link rel="icon" ... tag.
    </p>
    <p>
      Can automatically add the web app's context path to the front of the href.
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
        <td>href</td>
        <td>yes</td>
        <td>
          The location of the icon
        </td>
      </tr>

      <tr>
        <td>useContextPath</td>
        <td>no</td>
        <td>
          Default = true. If the href begins with "/" and useContextPath is set,
          then the web app's context path is added to the front of the href.
        </td>
      </tr>

      <tr>
        <td>encodeSessionId</td>
        <td>no</td>
        <td>
          If set, and the client doesn't support cookies (or this is the very first hit to this webapp),
          then the sessionid is added to the url. If your style sheet is dynamic based on a session object,
          and your client has disabled cookies, you will need this set to true.
        </td>
      </tr>

      <tr>
        <td>type</td>
        <td>no</td>
        <td>
          If set, includes the mime type (such as image/png) of the image.
        </td>
      </tr>

    </table>

  </tiles:put>

  <tiles:put name="examples" type="string">

    <p>
      All these pages use the following <em>icon</em> tag in the <em>head</em> section :
    </p>
 
<pre><ww:noEval>
<ww:icon href="/style/icon.png"/>
</ww:noEval></pre>

    <p>
      The actual icon is <ww:link href="/style/icon.png">here</ww:link>. 
    </p>
    
  </tiles:put>


</tiles:insert>

