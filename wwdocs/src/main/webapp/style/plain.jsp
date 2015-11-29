<!DOCTYPE html>
<html lang="en-GB">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<head>
  <title><tiles:insert attribute="title" /></title>
  <ww:styleSheet href="/ww_resources/ww.css" />
  <ww:styleSheet href="/style/style.css" />
  <ww:styleSheet href="/style/example.css" />
  <ww:icon href="/style/icon.png"/>

  <ww:script src="/ww_resources/webwidgets-min.js" />
  <link href='http://fonts.googleapis.com/css?family=Arvo' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="whole">
    <div id="header">
      <div id="logo">
        <h1>Webwidgets</h1>
      </div>
      <ww:tabs id="tabs">
        <ww:tab useContextPath="false" pattern="/index.jsp|/|"><ww:link href="/">About</ww:link></ww:tab>
        <ww:tab useContextPath="false" pattern="/installing.jsp"><ww:link href="/installing.jsp">Installing</ww:link></ww:tab>
        <ww:tab useContextPath="false" pattern="/documentation/groups/.*"><ww:link href="/documentation/groups">Tag Groups</ww:link></ww:tab>
        <ww:tab useContextPath="false" pattern="/documentation/apis/.*"><ww:link href="/documentation/apis">Tag APIs</ww:link></ww:tab>
        <ww:tab useContextPath="false" pattern="/test/.*"><ww:link href="/test/">Alpha&nbsp;Tests</ww:link></ww:tab>	
      </ww:tabs>

    </div>

    <div id="belowTabs">
    </div>
    
    <div id="main">
      <div id="columns">

        <div id="content">
          <h1><tiles:insert attribute="title" /></h1>
          <tiles:insert attribute="content" ignore="true"/>
        </div>

        <div id="navigation">
          <tiles:insert attribute="navigation" ignore="true"/>
        </div>
        
      </div>

      <div id="belowColumns">
      </div>
    </div>

    <div id="footer">        
      &copy; <a href="http://nickthecoder.co.uk">nickthecoder.co.uk</a>
    </div>
        
  </div>

</body>
</html>

