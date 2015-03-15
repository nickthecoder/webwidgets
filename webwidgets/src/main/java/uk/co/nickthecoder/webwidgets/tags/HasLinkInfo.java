/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import javax.servlet.jsp.JspException;

public interface HasLinkInfo extends HasLinkHref
{

    public LinkInfo getLinkInfo() throws JspException;

}
