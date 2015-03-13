<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<a name="<tiles:insert attribute="tag"/>"></a>
<ww:box className="mainbox">
  <ww:boxTitle>Tag : <tiles:insert attribute="tag"/></ww:boxTitle>
  <ww:boxContent>

    <tiles:useAttribute id="tag" name="tag" ignore="true"/>
    <tiles:useAttribute id="requires" name="requires" ignore="true"/>
    <tiles:useAttribute id="parent" name="parent" ignore="true"/>
    <tiles:useAttribute id="children" name="children" ignore="true"/>
    <tiles:useAttribute id="about" name="about" ignore="true"/>
    <tiles:useAttribute id="attributes" name="attributes" ignore="true"/>
    <tiles:useAttribute id="examples" name="examples" ignore="true"/>

    <c:if test="${about != null}">
      <h3>About</h3>
      <tiles:insert attribute="about" ignore="true" flush="false" />
    </c:if>

    <c:if test="${requires != null}">
      <h3>Requires</h3>
      <tiles:insert attribute="requires" ignore="true" flush="false" />
    </c:if>

    <c:if test="${parent != null}">
      <h3>Parent Tags</h3>
      <tiles:insert attribute="parent"/>
    </c:if>

    <c:if test="${children != null}">
      <h3>Child Tags</h3>
      <tiles:insert attribute="children"/>
    </c:if>

    <h3>Attributes</h3>
    <c:if test="${attributes != null}">
      <tiles:insert attribute="attributes" flush="false" ignore="true"/>
    </c:if>
    <c:if test="${attributes == null}">
      <p>
        none
      </p>
    </c:if>

  </ww:boxContent>

</ww:box>


<c:if test="${examples != null}">
  <h3>Examples</h3>
  <tiles:insert attribute="examples"/>
</c:if>

