<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Alpha Tests" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

    <p>
      Use the panel on the left to select an alpha test.
    </p>

  </tiles:put>

  <ww:breadcrumb label="Alpha Tests"/>

</tiles:insert>

