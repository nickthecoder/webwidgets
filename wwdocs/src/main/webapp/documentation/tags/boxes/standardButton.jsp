<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="standardButton" />

  <tiles:put name="about" type="string">
    <p>
      Used with the boxIcon tag, and aslo by the tree tags.
    </p>
    <p>
      This tag isn't well designed, but its aim was to help create standard buttons used by other web widgets.
      For example, a box may have a minimize button to hode its contents.
    </p>
    <p>
      Below are the examples of how to use boxes, as boxes are the most common use for the standardButtons.
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
          One of the followin :<br/>
          minimize, maximize, close, expand, contract, disabled
        </td>
      </tr>

      <tr>
        <td>image</td>
        <td>no</td>
        <td>
          If true, then the button is completely rendered. If false, then only the html 'a' tag is
          rendered, and it is up to you to create the bodu of the 'a' tag.
        </td>
      </tr>

    </table>

  </tiles:put>

</tiles:insert>

