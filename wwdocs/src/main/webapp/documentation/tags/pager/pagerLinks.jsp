<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="pagerLinks" />

  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/pager.jsp">pager</ww:link>
  </tiles:put>

  <tiles:put name="children" type="string">
    <ww:link href="/documentation/apis/link.jsp">link</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      Renders one set of pager links. There are 5 different kinds of pagerLinks.
      Here's how they are conventionally rendered from left to right :
    </p>
    <ul>
      <li><b>previous</b> - The page immediately beofre the current page</li>
      <li><b>before</b> - The set of pages before this one.
        For example, if the current page is 5, then this will be pages 1..4
      </li>
      <li><b>current</b> - The current page. Often this isn't rendered as a link.</li>
      <li><b>after</b> - The set of pages after this one.
        For example, if the current page is 5, then this will be pages 6..15
        (assuming there are 15 or more pages available).
      </li>
      <li><b>next</b> - The page immediately after the current page.</li>
    </ul>
    <p>
      The body of the pageLinks tag is iterated over the appropriate number
      of times. The attribure named 'page' is set the relevant page number.
      The name of this attribute can be changed from the default ('page')
      using the pageAttributeName of the pager tag.
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
        <td>type</td>
        <td>yes</td>
        <td>
          See the description above
          for the possible values and their meanings.
        </td>

      </tr>
    </table>

  </tiles:put>

</tiles:insert>

