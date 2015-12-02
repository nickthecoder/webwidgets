<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Event Notifier" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >


<ww:script src="/ww_resources/ww_eventNotifier.js"/>

<h1>Test Body Events</h1>

<p>
  The html body has events. These can be tricky to use in a modular system,
  because the events are defined once (in the body tag). However, what if
  two separate modeuls within one page both want to do someth onLoad?
  Either the body tag is custom coded, so that it call both functions (which
  breaks modularity), or you do something clever!
</p>

<p>
  Instead of calling functions directly from onLoad, have onLoad iterate over
  all registered "listeners".
</p>

<script language="javascript">

function hello()
{
  alert( "Hello" );
}

function wow()
{
  alert( "wow" );
}

ww_onloadNotifier.add( hello );
ww_onresizeNotifier.add( wow );
ww_onloadNotifier.add( function() { alert( "Dynamic" ) } );

</script>

<p>
 on load, we should see 'hello' and then 'dynamic', and on resize, we should see 'wow'.
</p>

</tiles:put>
</tiles:insert>

