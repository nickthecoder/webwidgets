<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<html lang="en-GB">

<head>
  <title><tiles:insert attribute="title" /></title>
  <ww:styleSheet href="/ww_resources/ww.css" />
  <ww:styleSheet href="/style/style.css" />
  <ww:styleSheet href="/style/example.css" />
  <ww:icon href="/style/icon.png"/>

  <ww:script src="/ww_resources/ww_eventNotifier.js" />
  <ww:script src="/ww_resources/ww_minimizable.js" />
  <ww:script src="/ww_resources/ww_misc.js" />
</head>

<body onload="ww_onLoad()">

  <div class="heading">
    <h1><tiles:insert attribute="title" /></h1>
    <%@include file="../tabs.jsp" %>
  </div>

  <table class="ww_breadcrumbs">
    <tr>
      <td><ww:breadcrumbs backwards="true" separator="<"/></td>
      <th>&lt;&lt;&nbsp;History</th>
    </tr>
  </table>

  <div class="ww_tabsContent">

    <table id="pageLayout">
      <tr>
        <td id="navigationCell">
          <tiles:insert attribute="navigation" ignore="true"/>
        </td>

        <td id="contentCell">

          <div class="content">
            <tiles:insert attribute="content" ignore="true"/>
          </div>

          <tiles:insert attribute="fullWidth" ignore="true"/>

        </td>
      </tr>
    </table>

    <hr/>

    <div class="footer">
      &copy; <a href="http://nickthecoder.co.uk">nickthecoder.co.uk</a>
    </div>

  </div>

</body>

</html>

