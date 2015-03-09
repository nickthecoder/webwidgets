<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insert page="/documentation/tags/tagTemplate.jsp">

  <tiles:put name="tag" type="string" value="noEval" />

  <tiles:put name="about" type="string">
    <p>
      Takes the body of the noEval tag, and renders it verbatum, i.e. it does not
      let the jsp parser at it. It also replaces ampersands and angled brackets.
      This is very useful when you want to quote a piece of jsp code.
    </p>
    <p>
      I created this tag specifically for these documentation pages, but if it
      is useful elsewhere, all the better :-)
    </p>
  </tiles:put>

  <tiles:put name="attributes" type="string">

    <table class="ww_table">

      <tr>
        <th>Name</th>
        <th>Required</th>
        <th>Description</th>
      </tr>

      <tr>
        <td>escape</td>
        <td>no</td>
        <td>
          Determines if ampersands and angled brackets are escaped. The default is true.
        </td>
      </tr>

    </table>

  </tiles:put>


  <tiles:put name="examples" type="string">

    <table class="examples">

      <tr>
        <td colspan="2">

        </td>
      </tr>

      <tr>
        <td>
<ww:noEval>
  This is inside the noEval tag, so the following jsp looking
  tag won't be evaluated :
  <ww:aFakeTag blah="hello"/>
</ww:noEval>
         </td>
        <td>

<pre class="code"><ww:noEval>
<ww:noEval>
  This is inside the noEval tag, so the following jsp looking
  tag won't be evaluated :
  <ww:aFakeTag blah="hello"/>
</ww:noEval>&lt;ww:noEval&gt;</pre>

        </td>
      </tr>


    </table>

  </tiles:put>

</tiles:insert>

