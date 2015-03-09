<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="imageRollover" />

  <tiles:put name="about" type="string">
    <p>
      Creates an image, which changes to a different image when the mouse
      moves over it. The alternate image is loaded into the browser cache
      druing the page onLoad event.
    </p>
  </tiles:put>

  <tiles:put name="requires" type="string">
    The following javascript files : ww_misc.js and ww_eventNotifier.js
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">

      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>

      <tr>
        <td>src</td>
        <td>yes</td>
        <td>
          The url of the normal image (i.e. while the mouse is NOT over the image)
        </td>
      </tr>

      <tr>
        <td>rollover</td>
        <td>yes</td>
        <td>
          The url of the rollover image (i.e. while the mouse IS over the image)
        </td>
      </tr>

      <tr>
        <td>useContextPath</td>
        <td>no</td>
        <td>
          Determines if the web applications context path is added to the beginning
          of urls beginning with a slash. The default is true.
        </td>
      </tr>

      <tr>
        <td>alt</td>
        <td>yes</td>
        <td>
          The alt attributes for the img tag. The w3c say you really should have alts,
          so I have made it required. Change your copy of the tld if you don't like
          this behaviour - the java class will work fine without an alt specified.
        </td>
      </tr>

      <tr>
        <td>styleClass</td>
        <td>no</td>
        <td>
          The css class name for the img tag.
        </td>
      </tr>

    </table>

  </tiles:put>


  <tiles:put name="examples" type="string">

    <table class="examples">

      <tr>
        <td colspan="2">

        </td>
      </tr>

      <tr>
        <td>
          <ww:script src="/ww_resources/ww_eventNotifier.js"/>
          <ww:script src="/ww_resources/ww_misc.js"/>
          <ww:imageRollover alt="myrollover" src="/images/rolloverOff.png" rollover="/images/rolloverOn.png" />
        </td>
        <td>

<pre class="code"><ww:noEval>
<ww:script src="/ww_resources/ww_eventNotifier.js"/>
<ww:script src="/ww_resources/ww_misc.js"/>
<ww:imageRollover alt="myrollover" src="/images/rolloverOff.png" rollover="/images/rolloverOn.png" />
</ww:noEval></pre>

        </td>
      </tr>


    </table>


  </tiles:put>

</tiles:insert>

