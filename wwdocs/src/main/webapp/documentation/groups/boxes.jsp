<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/groups/groupTemplate.jsp" flush="true">

  <tiles:put name="overview" value="/documentation/tags/boxes/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/boxes/examples.jsp" />

  <tiles:put name="name" type="string" value="Boxes" />

  <tiles:put name="tags" type="string" >
    <a href="#box">box</a>,
    <a href="#boxTitle">boxTitle</a>,
    <a href="#boxIcon">boxIcon</a>,
    <a href="#boxContent">boxContent</a>,
    <a href="#innerBox">innerBox</a>,
    <a href="#standardButton">standardButton</a>,
    <a href="#edges">edges</a>
  </tiles:put>


  <tiles:put name="details" type="string" >

    <tiles:insert page="/documentation/tags/boxes/box.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/boxes/boxContent.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/boxes/boxTitle.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/boxes/boxIcon.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/boxes/innerBox.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/boxes/standardButton.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/boxes/edges.jsp" flush="false"/>
    
  </tiles:put>

  <ww:breadcrumb label="Boxes"/>

</tiles:insert>

