<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="boxIcon" />
  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/boxTitle.jsp">boxTitle</ww:link>
  </tiles:put>

  <tiles:put name="about" type="string">
    <p>
      In the body of the boxIcon tag, place any controls for the box.
      Usual icons include a minimize/maximize button, and a close button,
      but you can include any arbitary html.
    </p>
  </tiles:put>

</tiles:insert>

