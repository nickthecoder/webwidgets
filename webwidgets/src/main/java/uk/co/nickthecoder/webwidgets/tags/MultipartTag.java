/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.tags;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.webwidgets.util.MultipartHelper;

/**
 * Lets the user navigate from one page to another, often used for search results.
 * This tag can work in two ways. Either you give it the set of items, and the number
 * of items per page, or you give it the total number of pages.
 */

public class MultipartTag extends TagSupport
{

    private static final long serialVersionUID = -9027810356868120241L;

    /**
     * Deletes all uploaded files once the body of the tag has been completed.
     */
    private boolean _autoDelete;

    /**
     * The name of the directory where files are uploaded to
     */
    private String _outputDirectory;

    /**
     * The name of the attribute to contain the map of name, MultipartTag.FileInfo pairs
     */
    private String _filesVar;

    /**
     * The name of the attribute to hold the map of name, value list
     */
    private String _paramListVar;

    /**
     * The name of the request attribute to hold the Map of parameter name value pairs
     */
    private String _paramVar;

    public MultipartTag()
    {
        super();
        initialise();
    }

    private void initialise()
    {
        _filesVar = null;
        _paramVar = null;
        _paramListVar = null;
        _autoDelete = true;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    /**
     * Get method for attribute {@link #_autoDelete}.
     * Deletes all uploaded files once the body of the tag has been completed.
     */
    public boolean getAutoDelete()
    {
        return _autoDelete;
    }

    /**
     * Set method for attribute {@link #_autoDelete}.
     * Deletes all uploaded files once the body of the tag has been completed.
     */
    public void setAutoDelete( boolean value )
    {
        _autoDelete = value;
    }

    /**
     * Get method for attribute {@link #_outputDirectory}.
     * The name of the directory where files are uploaded to
     */
    public String getOutputDirectory()
    {
        return _outputDirectory;
    }

    /**
     * Set method for attribute {@link #_outputDirectory}.
     * The name of the directory where files are uploaded to
     */
    public void setOutputDirectory( String value )
    {
        _outputDirectory = value;
    }

    /**
     * Get method for attribute {@link #_filesVar}.
     * The name of the attribute to contain the map of name, MultipartTag.FileInfo pairs
     */
    public String getFilesVar()
    {
        return _filesVar;
    }

    /**
     * Set method for attribute {@link #_filesVar}.
     * The name of the attribute to contain the map of name, MultipartTag.FileInfo pairs
     */
    public void setFilesVar( String value )
    {
        _filesVar = value;
    }

    /**
     * Get method for attribute {@link #_paramListVar}.
     * The name of the attribute to hold the map of name, value list
     */
    public String getParamListVar()
    {
        return _paramListVar;
    }

    /**
     * Set method for attribute {@link #_paramListVar}.
     * The name of the attribute to hold the map of name, value list
     */
    public void setParamListVar( String value )
    {
        _paramListVar = value;
    }

    /**
     * Get method for attribute {@link #_paramVar}.
     * The name of the request attribute to hold the Map of parameter name value pairs
     */
    public String getParamVar()
    {
        return _paramVar;
    }

    /**
     * Set method for attribute {@link #_paramVar}.
     * The name of the request attribute to hold the Map of parameter name value pairs
     */
    public void setParamVar( String value )
    {
        _paramVar = value;
    }

    public int doStartTag() throws JspException
    {
        try {

            MultipartHelper multipart = new MultipartHelper();

            // multipart.setDebug( new PrintWriter( System.out ) );

            if (getOutputDirectory() != null) {
                multipart.setOutputDirectory(new File(getOutputDirectory()));
            }

            multipart.go(pageContext.getRequest());

            if (getParamVar() != null) {
                // System.out.println( "setting param " + getParamVar() + " to : " + multipart.getSingleParameterMap() );
                pageContext.getRequest().setAttribute(getParamVar(), multipart.getSingleParameterMap());
            }

            if (getParamListVar() != null) {
                // System.out.println( "setting param list " + getParamListVar() + " to : " + multipart.getParameterMap() );
                pageContext.getRequest().setAttribute(getParamListVar(), multipart.getParameterMap());
            }

            if (getFilesVar() != null) {
                // System.out.println( "setting files " + getFilesVar() + " to : " + multipart.getFiles() );
                pageContext.getRequest().setAttribute(getFilesVar(), multipart.getFiles());
            } else {
                // We won't be able to delete the files in the end tag, and the body
                // won't be able to access the files, so lets delete them now.
                if (getAutoDelete()) {
                    deleteFiles(multipart.getFiles());
                }
            }

        } catch (IOException e) {
            throw new JspException(e);
        }

        return EVAL_BODY_INCLUDE;

    }

    public int doEndTag() throws JspException
    {
        if (getAutoDelete()) {
            if (getFilesVar() != null) {
                Map files = (Map) pageContext.getRequest().getAttribute(getFilesVar());
                if (files != null) {
                    deleteFiles(files);
                }
            }
        }

        return EVAL_PAGE;
    }

    protected void deleteFiles( Map files )
    {
        for (Iterator i = files.values().iterator(); i.hasNext();) {
            MultipartHelper.FileInfo fileInfo = (MultipartHelper.FileInfo) i.next();

            if (fileInfo.getFile() != null) {
                fileInfo.getFile().delete();
            }
        }
    }

}
