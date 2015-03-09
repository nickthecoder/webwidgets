<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/groups/groupTemplate.jsp" flush="true">

  <tiles:put name="overview" value="/documentation/tags/tabs/overview.jsp" />
  <tiles:put name="examples" value="/documentation/tags/tabs/examples.jsp" />

  <tiles:put name="name" type="string" value="Tabs" />

  <tiles:put name="tags" type="string" >
    <a href="#tabs">tabs</a>,
    <a href="#tab">tab</a>
  </tiles:put>


  <tiles:put name="details" type="string" >

    <tiles:insert page="/documentation/tags/tabs/tabs.jsp" flush="false"/>

    <tiles:insert page="/documentation/tags/tabs/tab.jsp" flush="false"/>

  </tiles:put>

  <ww:breadcrumb label="Tabs"/>

</tiles:insert>
