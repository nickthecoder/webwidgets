// Looks for the nearest parent with a class name of 'ww_minimized' or
// 'ww_maximized', and changes its class to 'closed'.
// With the correct style sheet, this will hide the whole section
// (not only the minimizable content).
// This will mean that you can't "unclose" the object.
function ww_doClose(event) {
    var ele = ww_getMinimizer(event);
    if (ele) {
        ele.className = "ww_closed";
    }
    return false; // Stops browser from processing the <a> tag and further.
}

// Looks for the nearest parent with a class name of 'ww_minimized' or
// 'ww_maximized', and changes its class to toggles its class between
// 'ww_minimized' and 'ww_maximized'
function ww_doToggleMinimize(event) {
    var ele = ww_getMinimizer(event);
    if (ele) {
        if (ele.className == "ww_minimized") {
            ele.className = "ww_maximized";
        } else {
            ele.className = "ww_minimized";
        }
    }
    return false;
}

// Looks for the nearest parent with a class name of 'ww_minimized' or
// 'ww_maximized', and changes its class to 'ww_minimized'.
// With the correct style sheet, this will hide any inner sections with a
// class name of 'ww_minimizable'.
function ww_doMinimize(event) {
    var ele = ww_getMinimizer(event);
    if (ele) {
        ele.className = "ww_minimized";
    }
    return false;
}
// Looks for the nearest parent with a class name of 'ww_minimized' or
// 'ww_maximized', and changes its class to 'ww_minimized'.
// This undoes the behaviour of doMinimize.
function ww_doMaximize(event) {
    var ele = ww_getMinimizer(event);
    if (ele) {
        ele.className = "ww_maximized";
    }
    return false;
}

// PRIVATE
// Looks for the nearest parent with a class name of 'ww_minimized' or
// 'ww_maximized'. Returns null if one is not found.
function ww_getMinimizer(event) {
    var ele;
    if (event.srcElement) {
        ele = event.srcElement;
    } else {
        ele = event.target;
    }

    if (ele) {
        do {
            ele = ele.parentNode;
        } while (ele && (ele.className != 'ww_minimized') && (ele.className != 'ww_maximized'));
    }

    return ele;
}
