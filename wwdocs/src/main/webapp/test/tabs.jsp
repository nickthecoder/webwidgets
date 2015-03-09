<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Tabs" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

  <h2>Tag generated HTML</h2>

  <ww:tabs>

    <ww:tab><a href="..">Home</a></ww:tab>
    <ww:tab pattern=".*tabs.jsp"><a href="tabs.jsp">Tabs</a></ww:tab>
    <ww:tab pattern=".*Tree.jsp"><a href="tree.jsp">Tree</a></ww:tab>
    <ww:tab><a href="index.jsp">A Very Long Label that is never on</a></ww:tab>
    <ww:tab><a href="index.jsp">No&nbsp;Breaking&nbsp;Spaces</a></ww:tab>

  </ww:tabs>
  <div class="ww_tabsContent" >
    This is where the main body of the page would go.
  </div>

  <h3>Using attribute test instead of pattern</h3>
  <ww:tabs>

    <ww:tab test="false"><a href="..">Home</a></ww:tab>
    <ww:tab test="true"><a href="tabs.jsp">Tabs</a></ww:tab>
    <ww:tab test="false"><a href="tree.jsp">Tree</a></ww:tab>
    <ww:tab test="false"><a href="index.jsp">A Very Long Label that is never on</a></ww:tab>
    <ww:tab test="false"><a href="index.jsp">No&nbsp;Breaking&nbsp;Spaces</a></ww:tab>

  </ww:tabs>
  <div class="ww_tabsContent" >
    This is where the main body of the page would go.
  </div>

  <h2>Hand crafted HTML (used during development)</h2>


  <div class="ww_tabs">
    <table class="ww_layout">
    <tr>
      <td><div class="ww_off"><a href="..">Home</a></div></td>
      <td><div class="ww_on"><a href="tabs.jsp">Tabs</a></div></td>
      <td><div class="ww_off"><a href="tree.jsp">Tree</a></div></td>
      <td><div class="ww_off"><a href="index.jsp">A Very Long Label that is never on</a></div></td>
      <td><div class="ww_off"><a href="index.jsp">No&nbsp;Breaking&nbsp;Spaces</a></div></td>
    </tr>
    </table>
  </div>
  <div class="ww_tabsContent" >
    This is where the main body of the page would go.
  </div>


  </tiles:put>
</tiles:insert>

