<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>


<tiles:insert page="/style/plain.jsp" flush="true">
  <tiles:put name="title" type="string" value="Tree" />
  <tiles:put name="navigation" value="/test/navigation.jsp" />
  <tiles:put name="content" type="string" >

  <ww:script src="/ww_resources/ww_minimizable.js"/>

  <div class="ww_tree">
    <ww:treeNode>
      <ww:treeNodeLabel>Very Top Most Node (with no description)</ww:treeNodeLabel>
      <ww:treeNodeContent last="true">

        <ww:treeNode>
          <ww:treeNodeLabel>This is the first node</ww:treeNodeLabel>
          <ww:treeNodeContent>
            This is the first nodes content. There could be loads of waffle at the top.

            <ww:treeNode>
              <ww:treeNodeLabel>A second level node</ww:treeNodeLabel>
              <ww:treeNodeContent>
                This is waffle about the second node.
              </ww:treeNodeContent>
            </ww:treeNode>

            <ww:treeNode>
              <ww:treeNodeLabel>Another second level node</ww:treeNodeLabel>
              <ww:treeNodeContent>
                This is more waffle
              </ww:treeNodeContent>
            </ww:treeNode>

            <ww:treeNode expandable="false">
              <ww:treeNodeLabel>A second level node with a description, but no child nodes</ww:treeNodeLabel>
              <ww:treeNodeContent last="true">
                This is the node content, but this node has no child nodes.
              </ww:treeNodeContent>
            </ww:treeNode>

          </ww:treeNodeContent>
        </ww:treeNode>

        <ww:treeNode expandable="false">
          <ww:treeNodeLabel>Red</ww:treeNodeLabel>
          <ww:treeNodeContent>
          </ww:treeNodeContent>
        </ww:treeNode>

        <ww:treeNode expandable="false">
          <ww:treeNodeLabel>Green</ww:treeNodeLabel>
          <ww:treeNodeContent>
          </ww:treeNodeContent>
        </ww:treeNode>

        <ww:treeNode expandable="false">
          <ww:treeNodeLabel>Blue</ww:treeNodeLabel>
          <ww:treeNodeContent last="true">
          </ww:treeNodeContent>
        </ww:treeNode>

      </ww:treeNodeContent>
    </ww:treeNode>

  </div>


  </tiles:put>
</tiles:insert>

