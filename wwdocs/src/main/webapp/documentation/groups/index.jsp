<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/style/plain.jsp">

  <tiles:put name="title" type="string" value="Web Widgets Tag Groups" />

  <tiles:put name="navigation" value="/documentation/groups/navigation.jsp" />

  <tiles:put name="content" type="string" >

    Choose from the following categories :

    <h2><a href="boxes.jsp">Boxes</a></h2>
    <p>
      Render boxes, with title bars and contexts.
    </p>

    <h2><a href="links.jsp">Links</a></h2>
    <p>
      Create &lt;a&gt; tags - sounds easy, but these tags really do make things easier.
    </p>

    <h2><a href="maps.jsp">Maps</a></h2>
    <p>
      Create links to maps without all of the hassle. All you need to know is
      the longitude and latitude.
    </p>

    <h2><a href="pager.jsp">Pager</a></h2>
    <p>
      Create Gooooooogle style pagers, useful for splitting large lists into a series
      of pages.
    </p>

    <h2><a href="tabs.jsp">Tabs</a></h2>
    <p>
      Tabbed navigation (as shown at the top of every webwidgets page).
    </p>

    <hr/>

    <h2><a href="misc.jsp">Misc</a></h2>
    <p>
      All of the other tags within webwigets. They don't get a section each, as
      each tag in the "Misc" section is a standalone tag.
    </p>

    <!--
    <h2><a href=".jsp"></a></h2>
    <p>
    </p>
    -->

  </tiles:put>
</tiles:insert>
