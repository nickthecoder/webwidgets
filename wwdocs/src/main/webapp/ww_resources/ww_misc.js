// Finds an object by name. First looks at the documents attributes,
// then at document.all, and then iterates over all form objects.
function ww_findObj(n, d) {

    // If we already have an Element, rather than an ID, return it.
    if (n instanceof Element) {
        return n;
    }

    var i, x;
    if (d == null) {
        d = document;
    }

    // First try the document's attributes
    if (!(x = d[n]) && d.getElementById) {
        // Then try the 'all' array - if its defined.
        x = d.getElementById(n);
    } else if (!(x = d[n]) && d.all) {
        // Then try the 'all' array - if its defined.
        x = d.all[n];
    }
    // If still not found, look through ever form object.
    for (i = 0; !x && i < d.forms.length; i++) {
        x = d.forms[i][n];
    }
    return x;
}

// The following viewport functions were described here :
// http://www.quirksmode.org/viewport/compatibility.html
// Not copied verbatum, so any errors are my own.
function ww_innerWidth() {
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

function ww_innerHeight() {
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

function ww_scrollX() {
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

function ww_scrollY() {
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

function ww_getEventElement(event) {
    var ele;
    if (event.srcElement) {
        return event.srcElement;
    } else {
        return event.target;
    }
}

// begin IMAGE ROLLOVER functions
function ww_changeImage(img, imageUrl) {
    if (!img.oldSrc) {
        img.ww_oldSrc = img.src;
    }
    img.src = imageUrl;
}
function ww_restoreImage(img) {
    if (img.ww_oldSrc) {
        img.src = img.ww_oldSrc;
    }
}
ww_imageCache = new Array();
function ww_cacheImage(imageUrl) {
    var image = new Image();
    image.src = imageUrl;
    ww_imageCache[ww_imageCache.length] = image;
}

// end IMAGE ROLLOVER functions

function ww_dump(obj, depth, ind) {
    if (depth == null)
        depth = 1;
    if (!ind)
        ind = '';
    if (depth < 1)
        return "...";

    var r = '{\n';

    for ( var n in obj) {
        var value = obj[n];
        r += ind + '    [' + n + '] : ';
        if (typeof (value) == "object") {
            r += ww_dump(value, depth - 1, ind + "    ") + '\n';
        } else {
            r += value + '\n';
        }
    }

    r += ind + '}';

    return r;
}

function ww_followLink(link) {
    link = ww_findObj(link);
    if (link != null) {
        if ((link.onclick == null) || link.onclick()) {
            document.location = link;
        }
    }
}

function ww_pressButton(ele) {
    ele = ww_findObj(ele);
    if (ele != null) {
        ele.click();
    }
}

function ww_findFirst(ele, nodeName) {
    ele = ww_findObj(ele);
    if (ele == null) {
        return null;
    }

    for (var i = 0; i < ele.childNodes.length; i++) {
        var child = ele.childNodes[i];
        if (child.nodeName == nodeName) {
            return child;
        }
        var recurse = ww_findFirst(child, nodeName);
        if (recurse != null) {
            return recurse;
        }
    }

    return null;
}

function ww_focusFirstLink(ele) {
    var link = ww_findFirst(ele, "A");
    if (link != null) {
        link.focus();
        return true;
    }
    return false;
}
