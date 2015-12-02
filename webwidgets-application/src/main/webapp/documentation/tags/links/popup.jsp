<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="popup" />

  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/linkInfo.jsp"></ww:link>
  </tiles:put>


  <tiles:put name="requires" type="string">
    <ww:link href="/ww_resources/ww_misc.js">ww_resources/ww_misc.js</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      Does the same as the <a href="#link">link</a>, except that
      it will use javascript to popup the page in a new window.
      If the client has javascript disabled, or they use the middle button
      (on Mozilla), the 'a' tag's onclick won't run correctly, but the
      'a' tag's href will still take the client to the correct page.
    </p>
    <p>
      I don't like pop-up windows, so I don't want to encourage you to create
      lots of pop-up windows, but very occasionaly, they are worth while, so
      here's a simple tag to make life easier.
    </p>
    <p>
      Note, currently there is no way to define the window attributes (such
      as turning scroll bars off). The default behaviour is the most
      pleasant for the user (i.e. just like a normal browser window, with
      scrollbars, menu, status bars etc). Many people are using good browsers
      (such as Firefox), which allow pop-ups to appear as tabs, and then those
      window features become meaningless anyway :-)
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
          Similar to html 'a' tag's href, except that urls begining with a '/'
          may have the web application's context path added to the front
          (dependaing on the value of useContextPath).
        </td>
      </tr>

      <tr>
        <td>useContextPath</td>
        <td>no</td>
        <td>
          If the href begins with a '/', then this determins if the web application's
          context path is added to the front. Default is true.
        </td>
      </tr>

      <tr>
        <td>styleClass</td>
        <td>no</td>
        <td>
          The css class name.
        </td>
      </tr>

      <tr>
        <td>windowName</td>
        <td>no</td>
        <td>
          The name of the window to pop-up in. Giving a window name, ensures that
          only one window will pop-up, no matter how many times the user clicks the
          link. The second pop-up link will reuse the first pop-up links window -
          its size and position are ignored.
        </td>
      </tr>

      <tr>
        <td>width</td>
        <td>no</td>
        <td>
          The width of the new window.
        </td>
      </tr>

      <tr>
        <td>height</td>
        <td>no</td>
        <td>
          The height of the new window.
        </td>
      </tr>

      <tr>
        <td>left</td>
        <td>no</td>
        <td>
          The horizontal position of the window.
          If ommitted, the window is centered on the screen.
        </td>
      </tr>

      <tr>
        <td>top</td>
        <td>no</td>
        <td>
          The vertical position of the window.
          If ommitted, the window is centered on the screen.
        </td>
      </tr>

    </table>

  </tiles:put>

</tiles:insert>

