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
    <p>
      When copying the javascript, style sheets and images, you don't have to
      keep the same directory structure. The web widgets tags do not assume
      that javascript code is held in a directory named 'javascript' for exmaple.
      For all tags that require a particular javascript code, it is up to the
      web page developer to ensure that they include the appropriate .js file.
      The same is true for css files and images, with the following exception :
      The standardButton tag is currently hard-wired to expect its images in the
      'ww_resources' directory. I consider this a bug, but I haven't fixed it yet - sorry.
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

              <ww:treeNode>
                <ww:treeNodeLabel>tld</ww:treeNodeLabel>
                <ww:treeNodeContent last="true">

                  <ww:treeNode minimized="true">
                    <ww:treeNodeLabel>webwidgets.tld</ww:treeNodeLabel>
                    <ww:treeNodeContent last="true">
                      The tag description file for webwidgets.
                    </ww:treeNodeContent>
                  </ww:treeNode>

                </ww:treeNodeContent>
              </ww:treeNode>

            </ww:treeNodeContent>
          </ww:treeNode>

          <ww:treeNode>
            <ww:treeNodeLabel>ww_resources</ww:treeNodeLabel>
            <ww:treeNodeContent>

              <!-- ww_eventNotifier.js -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_eventNotifier.js</ww:treeNodeLabel>
                <ww:treeNodeContent >
                  A useful set of javascript functions for components that need to
                  alter document events such as onload.
                  <br/>
                  Very useful in conjection
                  with the <a href="rollover.jsp">rollover</a> tag.
                </ww:treeNodeContent>
              </ww:treeNode>

              <!-- ww_minimizable.js -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_minimizable.js</ww:treeNodeLabel>
                <ww:treeNodeContent>
                  Helps hide and show sections of html. Can be used on their own,
                  but are directly used by the
                  <a href="box.jsp">box</a> tags, and the
                  <a href="tree.jsp">tree</a> tags.
                </ww:treeNodeContent>
              </ww:treeNode>

              <!-- ww_misc.js -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_misc.js</ww:treeNodeLabel>
                <ww:treeNodeContent>
                  A mixed bag of javascript functions that had nowhere else to go.
                </ww:treeNodeContent>
              </ww:treeNode>

              <!-- ww.css -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww.css</ww:treeNodeLabel>
                <ww:treeNodeContent>
                  Styles directly used by webwidgets - i.e. these style names
                  will be used without you specifying them.
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

              <!-- verticalDots.png -->
              <ww:treeNode minimized="true">
                <ww:treeNodeLabel>ww_verticalDots.png</ww:treeNodeLabel>
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

            </ww:treeNodeContent>
          </ww:treeNode>

          <!-- style -->
          <ww:treeNode>
            <ww:treeNodeLabel><span class="optional">style</span></ww:treeNodeLabel>
            <ww:treeNodeContent last="true">

              <ww:treeNode minimized="true">
                <ww:treeNodeLabel><span class="optional">example.css</span></ww:treeNodeLabel>
                <ww:treeNodeContent last="true">
                  Contains some exmaple css that you might find useful.
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
