<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Box" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

  <ww:script src="/ww_resources/ww_minimizable.js"/>

  <h3>Created using the tags</h3>
  <ww:box className="purpleBox" width="50%">

    <ww:boxTitle title="Hello World">
      <ww:boxIcon>
        <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/><ww:standardButton type="close"/>
      </ww:boxIcon>
    </ww:boxTitle>

    <ww:boxContent>
      This is the main content of the box.
      <br/>
      You probably would never mix like this.
    </ww:boxContent>

    <ww:innerBox>
      <ww:boxTitle title="Sub Title 1">
        <ww:boxIcon>
          <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/><ww:standardButton type="close"/>
        </ww:boxIcon>
      </ww:boxTitle>

      <ww:boxContent>
        This is sub content 1
      </ww:boxContent>

    </ww:innerBox>

    <ww:innerBox>
      <ww:boxTitle title="Sub Title 2">
        <ww:boxIcon>
          <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/><ww:standardButton type="close"/>
        </ww:boxIcon>
      </ww:boxTitle>

      <ww:boxContent>
        This is sub content TWO - Cool
      </ww:boxContent>

    </ww:innerBox>

  </ww:box>

  <br/>






  <h3>Testing</h3>


  <h3>A Purple box using the tags</h3>

  <h3>Created using the tags</h3>
  <ww:box className="purpleBox" width="400">

    <ww:boxTitle title="Hello World">
      <ww:boxIcon>
        <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/><ww:standardButton type="close"/>
      </ww:boxIcon>
    </ww:boxTitle>

    <ww:boxContent>
      This is the main content of the box.
      <br/>
      You probably would never mix like this.
    </ww:boxContent>

    <ww:innerBox>
      <ww:boxTitle title="Sub Title 1">
        <ww:boxIcon>
          <ww:standardButton type="minimize"/><ww:standardButton type="maximize"/><ww:standardButton type="close"/>
        </ww:boxIcon>
      </ww:boxTitle>

      <ww:boxContent>
        This is sub content 1
      </ww:boxContent>

    </ww:innerBox>

  </ww:box>


  </tiles:put>
</tiles:insert>
