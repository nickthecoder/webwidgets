<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<ww:tabs className="ww_tabs">

  <ww:tab local="true" pattern="/index.jsp"><ww:link href="/">Home</ww:link></ww:tab>
  <ww:tab local="true" pattern="/installing.jsp"><ww:link href="/installing.jsp">Installing</ww:link></ww:tab>
  <ww:tab local="true" pattern="/documentation/groups/.*"><ww:link href="/documentation/groups">Tag Groups</ww:link></ww:tab>
  <ww:tab local="true" pattern="/documentation/apis/.*"><ww:link href="/documentation/apis">Tag APIs</ww:link></ww:tab>
  <ww:tab local="true" pattern="/test/.*"><ww:link href="/test/">Alpha&nbsp;Tests</ww:link></ww:tab>

</ww:tabs>

