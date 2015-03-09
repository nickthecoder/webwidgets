<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="boxContent" />
  <tiles:put name="parent" type="string">
    <ww:link href="/documentation/apis/box.jsp">box</ww:link>
  </tiles:put>

</tiles:insert>

