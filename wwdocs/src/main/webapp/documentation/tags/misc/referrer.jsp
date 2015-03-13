<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="referrer" />

  <tiles:put name="about" type="string">
    <p>
      Outputs the referrer request parameter, unless that is null, in which
      case it outputs the http referer header.
    </p>
    <p>
      This is useful when a page wants to do some processing, and then return
      from where it came. If you have a form, add a hidden field with the name
      "referrer", and the value of <ww:noEval><ww:referrer/></ww:noEval>.
    </p>
  </tiles:put>


</tiles:insert>

