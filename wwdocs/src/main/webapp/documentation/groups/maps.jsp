<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/groups/groupTemplate.jsp" flush="true">

  <tiles:put name="overview" value="/documentation/tags/maps/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/maps/examples.jsp" />

  <tiles:put name="name" type="string" value="Maps" />

  <tiles:put name="tags" type="string" >
    <a href="#map">map</a>,
    <a href="#mapDetails">mapDetails</a>,
    <a href="#mapLink">mapLink</a>
  </tiles:put>


  <tiles:put name="details" type="string" >

    <tiles:insert page="/documentation/tags/maps/map.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/maps/mapDetails.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/maps/mapLink.jsp" flush="false"/>

  </tiles:put>

  <ww:breadcrumb label="Maps"/>

</tiles:insert>
