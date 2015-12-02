<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/groups/groupTemplate.jsp" flush="true">

  <tiles:put name="overview" value="/documentation/tags/pager/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/pager/examples.jsp" />

  <tiles:put name="name" type="string" value="Pager" />

  <tiles:put name="tags" type="string" >
    <a href="#pager">pager</a>,
    <a href="#pagerLinks">pagerLinks</a>
  </tiles:put>


  <tiles:put name="details" type="string" >

    <tiles:insert page="/documentation/tags/pager/pager.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/pager/pagerLinks.jsp" flush="false"/>

  </tiles:put>

  <ww:breadcrumb label="Pager"/>

</tiles:insert>
