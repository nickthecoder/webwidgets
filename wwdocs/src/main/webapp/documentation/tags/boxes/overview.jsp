<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<p>
  Create pretty boxes which can (optionally) be minimized/maximized.
  A box is defined using the
  <ww:link href="/documentation/apis/box.jsp">box</ww:link> tag.
  The contents of the box is defined using the
  <ww:link href="/documentation/apis/boxContent.jsp">boxContent</ww:link> inner tag.
  A box can optionally have a title bar - using the
  <ww:link href="/documentation/apis/boxTitle.jsp">boxTitle</ww:link> tag.
</p>
<p>
  Create more complicated boxes, by having multiple inner boxes, each with its own title
  (see <ww:link href="/documentation/apis/innerBox.jsp">innerBox</ww:link> tag).
</p>
<p>
  You must include <ww:link href="/ww_resources/ww.css">ww_resources/ww.css</ww:link>,
  or your own version of this style sheet. Also, you need to define your own style of box -
  see the purpleBox styles in <ww:link href="/style/example.css">example.css</ww:link>.
</p>

