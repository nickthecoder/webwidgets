// Finds an object by name. First looks at the documents attributes,
// then at document.all, and then iterates over all form objects.
function ww_findObj( n, d )
{
  var i,x;
  if ( d==null) {
    d=document;
  }

  // First try the document's attributes
  if ( !(x = d[n] ) && d.getElementById ) {
    // Then try the 'all' array - if its defined.
    x=d.getElementById( n );
  } else if ( !(x = d[n] ) && d.all ) {
    // Then try the 'all' array - if its defined.
    x=d.all[n];
  }
  // If still not found, look through ever form object.
  for ( i=0; !x && i < d.forms.length; i++ ) {
    x=d.forms[i][n];
  }
  return x;
}

// The following viewport functions were described here :
// http://www.quirksmode.org/viewport/compatibility.html
// Not copied verbatum, so any errors are my own.
function ww_innerWidth()
{
  if (self.innerWidth) {
    return self.innerWidth;
  } else if (document.documentElement && document.documentElement.clientWidth) {
    return document.documentElement.clientWidth;
  } else if (document.body) {
    return document.body.clientWidth;
  } else {
    return "ww_innerWidth Failed";
  }
}

function ww_innerHeight()
{
  if (self.innerHeight) {
    return self.innerHeight;
  } else if (document.documentElement && document.documentElement.clientHeight) {
    return document.documentElement.clientHeight;
  } else if (document.body) {
    return document.body.clientHeight;
  } else {
    return "ww_innerHeight Failed";
  }
}

function ww_scrollX()
{
  if (self.pageXOffset) {
    return self.pageXOffset;
  } else if (document.documentElement && document.documentElement.scrollLeft) {
    return document.documentElement.scrollLeft;
  } else if (document.body) {
    return document.body.scrollLeft;
  } else {
    return "ww_scrollX failed";
  }
}

function ww_scrollY()
{
  if (self.pageYOffset) {
    return self.pageYOffset;
  } else if (document.documentElement && document.documentElement.scrollTop) {
    return document.documentElement.scrollTop;
  } else if (document.body) {
    return document.body.scrollTop;
  } else {
    return "ww_scrollY failed";
  }
}
// End of viewport functions.


function ww_getEventElement( event )
{
  var ele;
  if (event.srcElement) {
    return event.srcElement;
  } else {
    return event.target;
  }
}


// Creates a pop-up window - usually called from an a tags on click.
function ww_popup( url, windowName, features, width, height, left, top )
{
  if ( windowName == null) windowName = "popup" + new Date().getMilliseconds();
  if ( features == null ) features = "resizable=yes,menubar=yes,toolbar=yes,scrollbars=yes";

  if ( height != null ) {
    features = features + ",height=" + height;
    if ( top == null ) {
      features = features + ",top=" + ((screen.height - height) / 2);
    } else {
      features = features + ",top=" + top;
    }
  }

  if ( width !=null ) {
    features = features + ",width=" + width;
    if ( left == null ) {
      features = features + ",left=" + ((screen.width - width) / 2);
    } else {
      features = features + ",left=" + left;
    }
  }

  var popupWindow = window.open( url, windowName, features );
  popupWindow.focus();
  return false;
}

// {{{  begin IMAGE ROLLOVER functions
function ww_changeImage( img, imageUrl )
{
  if ( ! img.oldSrc ) {
    img.ww_oldSrc = img.src;
  }
  img.src = imageUrl;
}
function ww_restoreImage( img )
{
  if ( img.ww_oldSrc ) {
    img.src = img.ww_oldSrc;
  }
}
ww_imageCache = new Array();
function ww_cacheImage( imageUrl )
{
  var image = new Image();
  image.src = imageUrl;
  ww_imageCache[ ww_imageCache.length ] = image;
}

// }}} end IMAGE ROLLOVER functions

// {{{
// Allows each part of a web page to call ww_onLoadAdd( myFunction )
// and then the page template must have <body onload="ww_onLoad()"> to call each of the added functions.
function ww_onLoad()
{
  if ( ww_onLoad.handlers != null ) {
    var i;
    for ( i=0; i < ww_onLoad.handlers.length; i ++ ) {
      // alert( ww_onLoad.handlers[ i ] );
      ww_onLoad.handlers[ i ]();
    }
  }



  // alert( "oln" + ww_onloadNotifier );
  if (typeof ww_onloadNotifier != "undefined") {
    ww_onloadNotifier.fire()
  }
}
ww_onLoad.handlers = new Array();
function ww_onLoadAdd( handler )
{
  if ( ww_onLoad.handlers == null ) {
    ww_onLoad.handlers = new Array();
  }
  ww_onLoad.handlers[ ww_onLoad.handlers.length ] = handler;
}
// }}}

function ww_dump (obj, depth, ind) {
  if (depth==null) depth = 1;
  if (!ind) ind = '';
  if (depth < 1) return "...";

  var r = '{\n';
  
  for (var n in obj) {
    var value = obj[n];
    r += ind + '    [' + n + '] : ';
    if ( typeof( value ) == "object" ) {
      r += ww_dump( value, depth -1, ind + "    " ) + '\n';
    } else {
      r += value + '\n';
    }
  }

  r += ind + '}';
  
  return r;
}

function ww_followLink( linkId ) {
  var link = ww_findObj( linkId );
  if ( link != null ) {
    if ( (link.onclick == null) || link.onclick() ) {
      document.location = link;
    }
  }
}

function ww_pressButton( buttonName ) {
  var button = ww_findObj( buttonName );
  if ( button != null ) {
    button.click();
  }
}

function ww_findFirst( div, nodeName ) {
  if ( typeof( div ) == "string" ) {
    div = ww_findObj( div );
  }
  if ( div == null ) {
    return null;
  }
  
  for ( var i = 0; i < div.childNodes.length; i ++ ) {
    var child = div.childNodes[i];
    if ( child.nodeName == nodeName ) {
      return child;
    }
    var recurse = ww_findFirst( child, nodeName );
    if ( recurse != null ) {
      return recurse;
    }
  }
  
  return null;
}

function ww_focusFirstLink( div ) {
  var link = ww_findFirst( div, "A" );
  if ( link != null ) {
    link.focus();
    return true;
  }
  return false;
}

