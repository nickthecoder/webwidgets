<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/style/plain.jsp">

  <tiles:put name="title" type="string" value="Installing" />

  <tiles:put name="content" type="string" >

    <ww:script src="/ww_resources/ww_minimizable.js" />

    <p>
      To use webwidgets in your own web application, copy the following file.
      Those in <span class="optional">grey are optional</span>, the others are
      manadatory.
    </p>

    <div class="ww_tree">
      <ww:treeNode>
        <ww:treeNodeLabel>webapp</ww:treeNodeLabel>
        <ww:treeNodeContent last="true">

          <ww:treeNode>
            <ww:treeNodeLabel>WEB_INF</ww:treeNodeLabel>
            <ww:treeNodeContent>

              <ww:treeNode>
                <ww:treeNodeLabel>lib</ww:treeNodeLabel>
                <ww:treeNodeContent>

                  <ww:treeNode minimized="true">
                    <ww:treeNodeLabel>webwidgets.jar</ww:treeNodeLabel>
                    <ww:treeNodeContent last="true">
                      Contains the java code for all of the webwidget tags.
                    </ww:treeNodeContent>
                  </ww:treeNode>

                </ww:treeNodeContent>
              </ww:treeNode>

            </ww:treeNodeContent>
          </ww:treeNode>

          <ww:treeNode>
            <ww:treeNodeLabel>ww_resources</ww:treeNodeLabel>
            <ww:treeNodeContent>

              <!-- webwidgets-min.js -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>webwidgets-min.js</ww:treeNodeLabel>
                <ww:treeNodeContent >
                  A compressed version of the javascript code. You can use webwidgets.js if you do not want the uncompressed version.
                </ww:treeNodeContent>
              </ww:treeNode>

            </ww:treeNodeContent>
          </ww:treeNode>

          <!-- style -->
          <ww:treeNode>
            <ww:treeNodeLabel><span class="optional">style</span></ww:treeNodeLabel>
            <ww:treeNodeContent last="true">


              <!-- example.css -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>example.css</ww:treeNodeLabel>
                <ww:treeNodeContent>
                    CSS rules used by webwidgets. You may want to use this file as is, or include the styles it contains within your own style sheet.
                </ww:treeNodeContent>
              </ww:treeNode>
              
              <!-- ww_expand.png -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_expand.png</ww:treeNodeLabel>
                <ww:treeNodeContent>
                  Used by the <a href="tree.jps">tree</a> tag.
                </ww:treeNodeContent>
              </ww:treeNode>

              <!-- ww_contract.png -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_contract.png</ww:treeNodeLabel>
                <ww:treeNodeContent>
                  Used by the <a href="tree.jps">tree</a> tag.
                </ww:treeNodeContent>
              </ww:treeNode>

              <!-- ww_disabled.png -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_disabled.png</ww:treeNodeLabel>
                <ww:treeNodeContent>
                  Used by the <a href="tree.jps">tree</a> tag.
                </ww:treeNodeContent>
              </ww:treeNode>

              <!-- ww_close.png -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_close.png</ww:treeNodeLabel>
                <ww:treeNodeContent>
                  Used by the <a href="box.jsp">box</a> tag.
                </ww:treeNodeContent>
              </ww:treeNode>

              <!-- ww_maximize.png -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_maximize.png</ww:treeNodeLabel>
                <ww:treeNodeContent>
                  Used by the <a href="box.jsp">box</a> tag.
                </ww:treeNodeContent>
              </ww:treeNode>

              <!-- ww_minimize.png -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_minimize.png</ww:treeNodeLabel>
                <ww:treeNodeContent last="true">
                  Used by the <a href="box.jsp">box</a> tag.
                </ww:treeNodeContent>
              </ww:treeNode>

              <!-- verticalDots.png -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_verticalDots.png</ww:treeNodeLabel>
                <ww:treeNodeContent>
                  Used by the <a href="tree.jps">tree</a> tag.
                </ww:treeNodeContent>
              </ww:treeNode>

            </ww:treeNodeContent>
          </ww:treeNode>


        </ww:treeNodeContent>
      </ww:treeNode>
    </div>


  </tiles:put>

  <ww:breadcrumb label="Installing"/>

</tiles:insert>
