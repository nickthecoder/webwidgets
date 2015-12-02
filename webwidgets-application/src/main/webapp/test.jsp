<%@ page contentType="text/html; charset=iso-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://nickthecoder.co.uk/webwidgets" prefix="ww" %>

<tiles:insert page="/style/plain.jsp" flush="true">

  <tiles:put name="title" type="string" value="Test" />

  <tiles:put name="content" type="string" >

    <ww:script src="/ww_resources/ww_minimizable.js" />
    <ww:script src="/ww_resources/ww_misc.js" />
    <ww:script src="/ww_resources/acunote-shortcuts.js" />

    <script>
      ww_onLoadAdd( function() { shortcutListener.init() } );
      shortcutListener.root = {
        'h': function() { alert('Help!'); },
        'f': {
            'o': {
                'o': function() { alert('"foo" has been typed!'); }
            }
        },
        'b': {
            'a': {
                'R': function() { alert('"baR" has been typed!'); },
                'z': function() { alert('"baz" has been typed!'); }
            }
        }
    }

    shortcutListener.add( ["n", "r"], function() { alert( "nr" ) } );
    shortcutListener.add( ["n", "2"], function() { alert( "n2" ) } );
    shortcutListener.add( ["f", "o", "p"], function() { alert( "fop" ) } );
    shortcutListener.add( ["f", "2"], function() { alert( "f2" ) } );
    shortcutListener.add( ["p", "o", "p"], function() { alert( "pop" ) } );

   
    alert( ww_dump( shortcutListener.root, 2 ) );
    //shortcutListener.add( ["f"], function() { alert( "clashes" ) } );

    // shortcutListener.add( ["h", "i"], function() { alert( "clashes with help" ) } );
    </script>
    
    <p>                                   
      I use this page for putting small bits of code for alpha testing.
    </p>

    <ww:link href="#" id="hello">Hello</ww:link>
    
    <div style="margin: 0px; padding: 0px;">
      This is just before.
    </div>

    <!-- {{{ manual with style sheet -->
    <div class="rounded">
      <div class="ww_edges">
      
      <div class="ww_top">
        <div class="ww_left">
          <div class="ww_right">
            <div class="ww_center">
            </div>
          </div>
        </div>
      </div>

      <div class="ww_middle">
        
        <div class="ww_left">
          <div class="ww_right">
            <div class="ww_center">
            
              <div class="ww_content">
                <div class="ww_enforceMargins"></div>
              
                <div style="margin: 4em 0;">
                  Hello world
                  <br/>
                  Nick
                </div>
              </div>

              <div class="ww_enforceMargins"></div>
              
            </div>
          </div>
        </div>
        
      </div>
      
      <div class="ww_bottom">
        <div class="ww_left">
          <div class="ww_right">
            <div class="ww_center">
            </div>
          </div>
        </div>
      </div>
           
    </div>
    </div>

    <!-- }}} -->
    
    <h2>This is in between.</h2>
    Hello
    <h2>This is in between.</h2>
    
    <!-- {{{ manual 300px wide -->
    <div style="width: 300px; margin: 70px 0;">
    
    <div class="edgesx" style="position: relative;">

      <div class="topx" style="position: absolute; top: -30px; width: 100%;">
        <div class="leftx" style="background: red; height: 40px;">
          <div class="rightx" style="background: pink; height: 40px;">
            <div class="centerx" style="background: blue; height: 40px; margin: 0 40px;">
            </div>
          </div>
        </div>
      </div>

      <div class="middlex" style="margin: 30px 0;">
        
        <div class="leftx" style="background: yellow;">
          <div class="rightx" style="background: green;">
            <div class="centerx" style="background: white; position: relative; z-index: 10; margin: 0 30px;">
            
              <div style="height: 1px;"></div>
              <div class="contentx" style="margin: 0px 0;">
                <div style="margin: 4em 0;">
                  Hello world
                  <br/>
                  Nick
                </div>
              </div>
              <div style="height: 1px;"></div>
              
            </div>
          </div>
        </div>
        
      </div>
      
      <div class="bottomx" style="position: absolute; width: 100%; margin-top: -40px;">
        <div class="leftx" style="background: red; height: 40px;">
          <div class="rightx" style="background: pink; height: 40px;">
            <div class="centerx" style="background: blue; height: 40px; margin: 0 40px;">
            </div>
          </div>
        </div>
      </div>
           
    </div>
    </div>

    <!-- }}} -->
    
    <div style="margin: 0px; padding: 0px;">
      This is just after.
    </div>
    
    <h2>Using edges tag</h2>
    
    <ww:edges className="rounded">
    </ww:edges>
    
    <ww:edges className="rounded">
      Hello world
    </ww:edges>
    
    <ww:edges className="rounded">
      Hello world
    </ww:edges>
    
    <ww:edges className="rounded">
      <div style="margin: 1em;">
        Hello world - 1 em margin.
      </div>
    </ww:edges>
        
    <ww:edges className="rounded">
      <div style="margin: 4em;">
        Hello world - 4 em margin.
      </div>
    </ww:edges>
        
    <ww:edges className="rounded">
      Hello world jhdksa hdjksa hdj ksahdjksa hdjksa hdkjsa dhjksa hdjksa dhsjak dhsajk dhsak
       hdsla hdjklsa jdklsa jdklsa jdkl sjadkl sjdkl sajdklsa jdklsa
        djskal djksal djksla jdklsa jdksal jksla
         djskal djsakl djsakl 
    </ww:edges>

  
  </tiles:put>
</tiles:insert>


