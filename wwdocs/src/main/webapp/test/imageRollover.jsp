<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Image Rollover" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >


  <ww:script src="/ww_resources/ww_misc.js"/>

  <h1>Test Body Image Rollover</h1>

  <h2>Using the rollover Tag</h2>

  <ww:imageRollover alt="exmaple" src="../images/rolloverOff.png" rollover="../images/rolloverOn.png" />

  <h2>Plain HTML - Used during development</h2>

  <img onmouseover="ww_changeImage( this, '../images/rolloverOn.png' );" onmouseout="ww_restoreImage( this );" src="../images/rolloverOff.png"/>

  <script language="javascript">
    ww_onloadNotifier.add( function() { ww_cacheImage( '../images/rolloverOn.png' ); } );
  </script>

</tiles:put>
</tiles:insert>
