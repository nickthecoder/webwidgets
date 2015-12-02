<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/groups/groupTemplate.jsp" flush="true">

  <tiles:put name="overview" value="/documentation/tags/links/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/links/examples.jsp" />

  <tiles:put name="name" type="string" value="Links" />

  <tiles:put name="tags" type="string" >
    <a href="#link">link</a>,
    <a href="#linkInfo">linkInfo</a>,
    <a href="#linkParameter">linkParameter</a>,
    <a href="#popup">popup</a>,
  </tiles:put>


  <tiles:put name="details" type="string" >

    <tiles:insert page="/documentation/tags/links/link.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/links/linkInfo.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/links/linkParameter.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/links/popup.jsp" flush="false"/>

  </tiles:put>

  <ww:breadcrumb label="Links"/>

</tiles:insert>
