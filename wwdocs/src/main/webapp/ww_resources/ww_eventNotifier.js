
function ww_EventNotifier()
{
  this._listeners = new Array();
}

ww_EventNotifier.prototype.add = function( listener )
{
  this._listeners[ this._listeners.length ] = listener;
}

ww_EventNotifier.prototype.fire = function()
{
  // alert( "Firing" );
  var i;
  for ( i = 0; i < this._listeners.length; i ++ ) {
    // alert( this._listeners[i] );
    this._listeners[i](); // Call the method
  }
}

ww_EventNotifier.prototype.getHandler = function()
{
  // Takes advantage of javascipt CLOSURES. The variable theNotifier will
  // still be available to the returned function, even when this function
  // is not running. The functions scope is defined at the time the function
  // is CREATED, not when it ir run!!!
  var en = this;
  var result = function() { en.fire(); }

  // Add a reference to this, so that you can do this kind of thing :
  // window.onresize = myNotifier.getEvent();
  // window.onresize.notifier.addListener( myFunction );
  result.notifier = this;
  return result;
}

ww_onblurNotifier = new ww_EventNotifier();
ww_ondragdropNotifier = new ww_EventNotifier();
ww_onerrorNotifier = new ww_EventNotifier();
ww_onfocusNotifier = new ww_EventNotifier();
ww_onloadNotifier = new ww_EventNotifier();
ww_onmoveNotifier = new ww_EventNotifier();
ww_onresizeNotifier = new ww_EventNotifier();
ww_onunloadNotifier = new ww_EventNotifier();

if ( ! window.onblur ) window.onblur = ww_onblurNotifier.getHandler();
if ( ! window.onload ) window.onload = ww_onloadNotifier.getHandler();
if ( ! window.onerror ) window.onerror = ww_onerrorNotifier.getHandler();
if ( ! window.onfocus ) window.onfocus = ww_onfocusNotifier.getHandler();
if ( ! window.onload ) window.onload = ww_onloadNotifier.getHandler();
if ( ! window.onmove ) window.onmove = ww_onmoveNotifier.getHandler();
if ( ! window.resize ) window.onresize = ww_onresizeNotifier.getHandler();
if ( ! window.onunload ) window.onunload = ww_onunloadNotifier.getHandler();


