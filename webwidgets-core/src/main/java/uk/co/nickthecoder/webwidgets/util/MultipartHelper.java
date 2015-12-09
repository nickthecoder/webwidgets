/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultipartHelper
{
    protected static Logger _logger = LogManager.getLogger(MultipartHelper.class);

    public static final String DEFAULT_CHARACTER_ENCODING = "ISO-8859-1";

    public static final int DEFAULT_MAXIMUM_CONTENT_LENGTH = 3 * (1024 * 1024); // 3MB!

    /**
     * Defines the number of bytes to read per readLine call. 128K
     */
    public static final int READline_BLOCK = 1024 * 128;

    /**
     * The maximum content length, used to prevent a DOS attack filling up your
     * hard drive, and consuming all your bandwidth.
     */
    private int _maximumContentLength; // bytes

    /**
     * The directory where the uploaded files are put
     */
    private File _outputDirectory;

    /**
     * A map of all of the files uploaded keyed on parameter name(String) value
     * is FileInfo.
     */
    private Map<String, MultipartHelper.FileInfo> _files;

    /**
     * This is a replacement for the normal servletrequests parameters. key is
     * the parameter name, value is a List of the values
     */
    private Map<String, List<String>> _parameterMap;

    /**
     * The character encoding of the received data.
     */
    private String _characterEncoding;

    /**
     * Store a read from the input stream here. Global so we do not keep
     * creating new arrays each read.
     */
    private byte[] _blockOfBytes = null;

    private String _boundary;
    private long _contentLength;
    private long _totalRead;

    public MultipartHelper()
    {
        _characterEncoding = DEFAULT_CHARACTER_ENCODING;
        _parameterMap = new HashMap<String, List<String>>();
        _files = new HashMap<String, MultipartHelper.FileInfo>();
        _maximumContentLength = DEFAULT_MAXIMUM_CONTENT_LENGTH;
    }

    /**
     * Get method for attribute {@link #_maximumContentLength}. The maximum
     * content length, used to prevent a DOS attack filling up your hard drive,
     * and consuming all your bandwidth.
     */
    public int getMaximumContentLength()
    {
        return _maximumContentLength;
    }

    /**
     * Set method for attribute {@link #_maximumContentLength}. The maximum
     * content length, used to prevent a DOS attack filling up your hard drive,
     * and consuming all your bandwidth.
     */
    public void setMaximumContentLength(int value)
    {
        _maximumContentLength = value;
    }

    /**
     * Get method for attribute {@link #_outputDirectory}. The direectory where
     * the uploaded files are put
     */
    public File getOutputDirectory()
    {
        return _outputDirectory;
    }

    /**
     * Set method for attribute {@link #_outputDirectory}. The directory where
     * the uploaded files are put
     */
    public void setOutputDirectory(File value) throws IOException
    {
        if (!value.exists()) {
            throw new IOException("Directory [" + value + "] not found");
        } else if (!value.canWrite()) {
            throw new IOException("Directory [" + value + "] is readonly");
        }
        _outputDirectory = value;
    }

    /**
     * Get method for attribute {@link #_files}. A map of all of the files
     * uploaded.
     */
    public Map<String, MultipartHelper.FileInfo> getFiles()
    {
        return _files;
    }

    /**
     * Set method for attribute {@link #_files}. A map of all of the files
     * uploaded.
     */
    public void setFiles(Map<String, MultipartHelper.FileInfo> value)
    {
        _files = value;
    }

    /**
     * Get method for attribute {@link #_parameterMap}. This is a replacement
     * for the normal servletrequests parameters.
     */
    public Map<String, List<String>> getParameterMap()
    {
        return _parameterMap;
    }

    /**
     * Set method for attribute {@link #_parameterMap}. This is a replacement
     * for the normal servletrequests parameters.
     */
    public void setParameterMap(Map<String, List<String>> value)
    {
        _parameterMap = value;
    }

    /**
     * Get method for attribute {@link #_characterEncoding}.
     */
    public String getCharacterEncoding()
    {
        return _characterEncoding;
    }

    /**
     * Set method for attribute {@link #_characterEncoding}.
     */
    public void setCharacterEncoding(String value)
    {
        _characterEncoding = value;
    }

    public void go(ServletRequest request) throws IOException
    {
        go(request.getInputStream(), request.getContentType(), request.getContentLength());
    }

    public void go(InputStream in, String contentTypeText, int contentLength) throws IOException
    {
        _logger.trace("contentTypeText : " + contentTypeText);
        if ((contentTypeText != null) && (contentTypeText.startsWith("multipart/form-data"))
                        && (contentTypeText.indexOf("boundary=") != -1)) {

            _boundary = contentTypeText.substring(contentTypeText.indexOf("boundary=") + "boundary=".length()).trim();
            _logger.trace("Boundary = " + _boundary);
        } else {
            _logger.trace("ContentType = " + contentTypeText);
            throw new IllegalArgumentException("Invalid Content Type.");
        }

        _contentLength = contentLength;
        if (contentLength > _maximumContentLength) {
            throw new IOException("Content Length Error (" + contentLength + " > " + _maximumContentLength + ")");
        }
        _logger.trace("ContentLength = " + contentLength);
        _logger.trace("MaxReadBytes = " + _maximumContentLength);

        _blockOfBytes = new byte[READline_BLOCK];
        _totalRead = 0;

        parse(new BufferedInputStream(in));

        // No need for these once parse is complete.
        _blockOfBytes = null;
        _boundary = null;
    }

    /**
     * This is the main parse method.
     */
    private void parse(InputStream in) throws IOException
    {
        String contentType = null;
        String name = null;
        String filename = null;
        String rawFilename = null;
        String line = null;
        int read = -1;

        // First run through, check that the first line is a boundary, otherwise
        // throw a exception as format incorrect.
        read = readLine(in, _blockOfBytes);
        line = read > 0 ? new String(_blockOfBytes, 0, read, _characterEncoding) : null;

        // Must be boundary at top of loop, otherwise we have finished.
        if ((line == null) || (line.indexOf(_boundary) == -1)) {
            _logger.trace("No boundary encountered");
            throw new IOException("Invalid Form Data, no boundary encountered.");
        }

        // At the top of loop, we assume that the Content-Disposition line is
        // next,
        // otherwise we are at the end.
        while (true) {

            // Get Content-Disposition line.
            read = readLine(in, _blockOfBytes);
            if (read <= 0) {
                break; // Nothing to do.
            } else {

                line = new String(_blockOfBytes, 0, read, _characterEncoding);
                _logger.trace("line: " + line);

                // Mac IE4 adds extra line after last boundary - 1.21
                if ((line == null) || (line.length() == 0) || (line.trim().length() == 0)) {
                    break;
                }

                // TODO: Improve performance by getting both the name and
                // filename from line in one go...
                name = trimQuotes(getValue("name", line));
                _logger.trace("Name: " + name);
                // If this is not null???, it indicates that we are processing a
                // filename.
                // Now if not null, strip it of any directory information.
                filename = trimQuotes(getValue("filename", line));
                _logger.trace("Filename: " + filename);

                // No filename specified at all - parameter
                if (filename == null) {

                    // Skip blank line.
                    readLine(in, _blockOfBytes);

                    String param = readParameter(in);
                    _logger.trace("Parameter Value: " + param);
                    addParameter(name, param);

                } else { // (strFilename!=null)

                    // Fix: did not check whether filename was empty string
                    // indicating a FILE was
                    // not passed.
                    if (filename.length() == 0) {
                        _logger.trace("filename length == 0");

                        // FIX 1.14: IE problem with empty filename.
                        read = readLine(in, _blockOfBytes);
                        line = read > 0 ? new String(_blockOfBytes, 0, read, _characterEncoding) : null;

                        // FIX 1.14 IE Problem still: Check for content-type and
                        // extra line even
                        // though no file specified.
                        if ((line != null) && (line.toLowerCase().startsWith("content-type:"))) {
                            readLine(in, _blockOfBytes);
                        }

                        // Skip blank line.
                        readLine(in, _blockOfBytes);

                        // Fix: FILE INPUT TYPE, but no file passed as input...
                        // addFileParameter( name, null );

                        readLine(in, _blockOfBytes);

                    } else { // File uploaded, or at least a filename was
                             // specified, it could still be spurious.
                        _logger.trace("uploading file");

                        // Need to get the content type.
                        read = readLine(in, _blockOfBytes);
                        line = read > 0 ? new String(_blockOfBytes, 0, read, _characterEncoding) : null;

                        contentType = "application/octet-stream";

                        // Fix 1.11: If not null AND line.length() is long
                        // enough.
                        // Modified in 1.19, as we should be checking if it is
                        // actually a Content-Type.
                        if ((line != null) && (line.toLowerCase().startsWith("content-type:"))) {

                            contentType = line.substring("content-type:".length()).trim(); // Changed
                                                                                           // 1.13

                            // Skip blank line, but only if a Content-Type was
                            // specified.
                            readLine(in, _blockOfBytes);
                        }

                        long filesize = -1;

                        // Will remain null if files are loaded into memory.
                        File outFile = null;

                        // Get the BASENAME version of strFilename.
                        rawFilename = filename;
                        filename = getBasename(filename);

                        // If nowhere to write file, then we pass a null file to
                        // readAndWriteFile, in which case the uploaded file
                        // contents
                        // will silently be processed and discarded.
                        if (_outputDirectory != null) {
                            outFile = TempFile.createTempFile(getClass().getName(), null, _outputDirectory);
                        }

                        filesize = readAndWriteFile(in, outFile);
                        _logger.trace("filesize " + filesize);

                        // Fix 1.18 for multiple FILE parameter values.
                        if (filesize > 0) {
                            addFileParameter(new FileInfo(name, filename, contentType, filesize, outFile, rawFilename));
                        } else { // Zero length file.
                            addFileParameter(new FileInfo(name, filename, null, 0, null, rawFilename));
                        }
                    }
                }
            }
        } // while
    }

    /**
     * Read parameters, assume already passed Content-Disposition and blank
     * line.
     * 
     * @return the value read in.
     */
    private String readParameter(InputStream in) throws IOException
    {
        StringBuffer buffer = new StringBuffer();
        int read = -1;

        String line = null;
        while (true) {

            read = readLine(in, _blockOfBytes);
            if (read < 0) {
                throw new IOException("Stream ended prematurely.");
            }

            // Change v1.18: Only instantiate string once for performance
            // reasons.
            line = new String(_blockOfBytes, 0, read, _characterEncoding);

            if (read < _blockOfBytes.length && line.indexOf(_boundary) != -1) {
                break; // Boundary found, we need to finish up.
            } else {
                buffer.append(line);
            }
        }

        if (buffer.length() > 0) {
            buffer.setLength(getLengthMinusEnding(buffer));
        }
        _logger.trace("readParameter(" + buffer.toString() + ")");
        String result = buffer.toString();

        // NICK. I don't know what this means, but it works for requests from
        // Apache HTTPClient.
        if (result.startsWith("Content-Transfer-Encoding: 8bit")) {
            return result.substring(35);
        }

        return result;
    }

    /**
     * Read from in, write to out, minus last two line ending bytes.
     */
    private long readAndWrite(InputStream in, OutputStream out) throws IOException
    {
        long fileSize = 0;
        int read = -1;

        // This variable will be assigned the bytes actually read.
        byte[] secondLineOfBytes = new byte[_blockOfBytes.length];

        // So we do not have to keep creating the second array.
        int sizeOfSecondArray = 0;

        while (true) {

            read = readLine(in, _blockOfBytes);
            if (read < 0) {
                throw new IOException("Stream ended prematurely.");
            }

            // Found boundary.
            if (read < _blockOfBytes.length
                            && new String(_blockOfBytes, 0, read, _characterEncoding).indexOf(_boundary) != -1) {

                // Write the line, minus any line ending bytes.
                // The secondLineOfBytes will NEVER BE NON-NULL if out==null, so
                // there is no need to included this in the test
                if (sizeOfSecondArray != 0) {

                    // Only used once, so declare here.
                    int actualLength = getLengthMinusEnding(secondLineOfBytes, sizeOfSecondArray);
                    if (actualLength > 0 && out != null) {

                        out.write(secondLineOfBytes, 0, actualLength);
                        // Update file size.
                        fileSize += actualLength;
                    }
                }
                break;

            } else {

                // Write out previous line.
                // The sizeOfSecondArray will NEVER BE ZERO if out==null, so
                // there is no need to included this in the test
                if (sizeOfSecondArray != 0) {

                    out.write(secondLineOfBytes, 0, sizeOfSecondArray);
                    // Update file size.
                    fileSize += sizeOfSecondArray;
                }

                // out will always be null, so there is no need to reset
                // sizeOfSecondArray to zero each time.
                if (out != null) {

                    // Copy the read bytes into the array.
                    System.arraycopy(_blockOfBytes, 0, secondLineOfBytes, 0, read);
                    // That is how many bytes to read from the secondLineOfBytes
                    sizeOfSecondArray = read;
                }
            }
        }

        // Return the number of bytes written to outstream.
        return fileSize;
    }

    /**
     * Read a Multipart section that is a file type. Assumes that the
     * Content-Disposition/Content-Type and blank line have already been
     * processed. So we read until we hit a boundary, then close file and
     * return.
     * 
     * @exception IOException
     *                if an error occurs writing the file.
     * @return the number of bytes read.
     */
    private long readAndWriteFile(InputStream in, File outFile) throws IOException
    {
        BufferedOutputStream out = null;

        try {

            // Because the outFile should be a temporary file provided by the
            // TempFile
            // class, it should already exist and should be writable.
            if (outFile != null && outFile.exists() && outFile.canWrite()) {
                out = new BufferedOutputStream(new FileOutputStream(outFile));
            }

            long count = readAndWrite(in, out);

            // Count would NOT be larger than zero if 'out' was null.
            if (count == 0) {

                // Delete file as empty.
                if (outFile != null) {
                    outFile.delete();
                }
            }

            return count;

        } finally {

            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * Returns the length of the line minus line ending.
     * 
     * @param endOfArray
     *            This is because in many cases the byteLine will have garbage
     *            data at the end, so we act as though the actual end of the
     *            array is this parameter. If you want to process the complete
     *            byteLine, specify byteLine.length as the endOfArray parameter.
     */
    private static final int getLengthMinusEnding(byte byteLine[], int endOfArray)
    {
        if (byteLine == null) {
            return 0;
        }

        if (endOfArray >= 2 && byteLine[endOfArray - 2] == '\r' && byteLine[endOfArray - 1] == '\n') {

            return endOfArray - 2;

        } else if (endOfArray >= 1 && byteLine[endOfArray - 1] == '\n' || byteLine[endOfArray - 1] == '\r') {

            return endOfArray - 1;

        } else {
            return endOfArray;
        }
    }

    private static final int getLengthMinusEnding(StringBuffer buf)
    {
        if (buf.length() >= 2 && buf.charAt(buf.length() - 2) == '\r' && buf.charAt(buf.length() - 1) == '\n') {

            return buf.length() - 2;

        } else if (buf.length() >= 1 && buf.charAt(buf.length() - 1) == '\n' || buf.charAt(buf.length() - 1) == '\r') {

            return buf.length() - 1;

        } else {
            return buf.length();
        }
    }

    /**
     * Reads at most READ_BLOCK blocks of data, or a single line whichever is
     * smaller. Returns -1, if nothing to read, or we have reached the specified
     * content-length.
     * 
     * Assumes that bytToBeRead.length indicates the block size to read.
     * 
     * @return -1 if stream has ended, before a newline encountered (should
     *         never happen) OR we have read past the Content-Length specified.
     *         (Should also not happen). Otherwise return the number of
     *         characters read. You can test whether the number returned is less
     *         than bytesToBeRead.length, which indicates that we have read the
     *         last line of a file or parameter or a border line, or some other
     *         formatting stuff.
     */
    private int readLine(InputStream in, byte[] bytesToBeRead) throws IOException
    {
        // Ensure that there is still stuff to read...
        if (_totalRead >= _contentLength) {
            return -1;
        }

        // Get the length of what we are wanting to read.
        int length = bytesToBeRead.length;

        // End of content, but some servers (apparently) may not realise this
        // and end the InputStream, so
        // we cover ourselves this way.
        if (length > (_contentLength - _totalRead)) {
            length = (int) (_contentLength - _totalRead); // So we only read the
                                                          // data that is left.
        }

        int result = readLine(in, bytesToBeRead, 0, length);
        // Only if we get actually read something, otherwise something weird has
        // happened, such as the end of stream.
        if (result > 0) {
            _totalRead += result;
        }

        return result;
    }

    /**
     * This needs to support the possibility of a / or a \ separator.
     * 
     * Returns strFilename after removing all characters before the last
     * occurence of / or \.
     */
    private static final String getBasename(String strFilename)
    {
        if (strFilename == null) {
            return strFilename;
        }

        int intIndex = strFilename.lastIndexOf("/");
        if (intIndex == -1 || strFilename.lastIndexOf("\\") > intIndex)
            intIndex = strFilename.lastIndexOf("\\");

        if (intIndex != -1) {
            return strFilename.substring(intIndex + 1);
        } else {
            return strFilename;
        }
    }

    /**
     * trimQuotes trims any quotes from the start and end of a string and
     * returns the trimmed string...
     */
    private static final String trimQuotes(String strItem)
    {
        // Saves having to go any further....
        if (strItem == null || strItem.indexOf("\"") == -1) {
            return strItem;
        }

        // Get rid of any whitespace..
        strItem = strItem.trim();

        if (strItem.charAt(0) == '\"') {
            strItem = strItem.substring(1);
        }

        if (strItem.charAt(strItem.length() - 1) == '\"') {
            strItem = strItem.substring(0, strItem.length() - 1);
        }

        return strItem;
    }

    /**
     * Format of string name=value; name=value;
     * 
     * If not found, will return null.
     */
    private static final String getValue(String strName, String strToDecode)
    {
        strName = strName + "=";

        int startIndexOf = 0;

        while (startIndexOf < strToDecode.length()) {

            int indexOf = strToDecode.indexOf(strName, startIndexOf);
            // Ensure either first name, or a space or ; precedes it.
            if (indexOf != -1) {

                if (indexOf == 0 || Character.isWhitespace(strToDecode.charAt(indexOf - 1))
                                || strToDecode.charAt(indexOf - 1) == ';') {

                    int endIndexOf = strToDecode.indexOf(";", indexOf + strName.length());

                    if (endIndexOf == -1) { // May return an empty string...

                        return strToDecode.substring(indexOf + strName.length());

                    } else {
                        return strToDecode.substring(indexOf + strName.length(), endIndexOf);
                    }

                } else {
                    startIndexOf = indexOf + strName.length();
                }

            } else {
                return null;
            }
        }

        return null;
    }

    /**
     * <I>Tomcat's ServletInputStream.readLine(byte[],int,int) Slightly Modified
     * to utilise in.read()</I> <BR>
     * Reads the input stream, one line at a time. Starting at an offset, reads
     * bytes into an array, until it reads a certain number of bytes or reaches
     * a newline character, which it reads into the array as well.
     * 
     * <p>
     * This method <u><b>does not</b></u> returns -1 if it reaches the end of
     * the input stream before reading the maximum number of bytes, it returns
     * -1, if no bytes read.
     * 
     * @param b
     *            an array of bytes into which data is read
     * 
     * @param off
     *            an integer specifying the character at which this method
     *            begins reading
     * 
     * @param len
     *            an integer specifying the maximum number of bytes to read
     * 
     * @return an integer specifying the actual number of bytes read, or -1 if
     *         the end of the stream is reached
     * 
     * @exception IOException
     *                if an input or output exception has occurred
     * 
     * 
     *                Note: We have a problem with Tomcat reporting an erroneous
     *                number of bytes, so we need to check this. This is the
     *                method where we get an infinite loop, but only with binary
     *                files.
     */
    private int readLine(InputStream in, byte[] b, int off, int len) throws IOException
    {
        if (len <= 0) {
            return 0;
        }

        int count = 0, c;

        while ((c = in.read()) != -1) {
            b[off++] = (byte) c;
            count++;
            if (c == '\n' || count == len) {
                break;
            }
        }

        return count > 0 ? count : -1;
    }

    protected void addParameter(String name, String value)
    {
        List<String> values = _parameterMap.get(name);

        if (values == null) {
            values = new ArrayList<String>();
            _parameterMap.put(name, values);
        }
        values.add(value);
    }

    protected void addFileParameter(FileInfo fileInfo)
    {
        if (fileInfo != null) {

            // If we allow one fileinfo to replace another, then it is easy for
            // files to
            // go undeleted in the output directory.
            // The options are (1) create a hashmap of list of fileinfo, (2)
            // delete the file
            // it is overriding, (3) bodge it by giving all unique names.
            // I've chosen option (2).
            if (getFile(fileInfo.name) != null) {
                getFile(fileInfo.name).file.delete();
            }

            _files.put(fileInfo.name, fileInfo);
        }
    }

    public FileInfo getFile(String name)
    {
        return (FileInfo) _files.get(name);
    }

    public Iterator<String> getParameterNames()
    {
        return _parameterMap.keySet().iterator();
    }

    public List<String> getParameterValues(String name)
    {
        return _parameterMap.get(name);
    }

    public String getParameter(String name)
    {
        List<String> list = getParameterValues(name);
        if ((list == null) || (list.size() == 0)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * Return a Map keyed on the parameter names, and the values are the first
     * parameter value in the list of parameters of that name. You will most
     * likely want to use this if one or more parameters are expected to only
     * have one value.
     */
    public Map<String, String> getSingleParameterMap()
    {
        Map<String, String> result = new HashMap<String, String>();
        for (Iterator<Entry<String, List<String>>> i = _parameterMap.entrySet().iterator(); i.hasNext();) {
            Map.Entry<String, List<String>> entry = i.next();
            List<String> values = entry.getValue();
            result.put(entry.getKey(), values.get(0));
        }

        return result;
    }

    public class FileInfo
    {
        public String name; // the name of the parameter
        public String filename; // the name of the file on the client machine
        public String contentType; // as given by the browser
        long fileSize;
        File file; // the uploaded verison of the file.
        String rawFilename; // the filename as given by the browser

        public FileInfo(String name, String filename, String contentType, long fileSize, File file, String rawFilename)
        {
            this.name = name;
            this.filename = filename;
            this.contentType = contentType;
            this.fileSize = fileSize;
            this.file = file;
            this.rawFilename = rawFilename;
        }

        public String getName()
        {
            return name;
        }

        public String getFilename()
        {
            return filename;
        }

        public String getContentType()
        {
            return contentType;
        }

        public long getFileSize()
        {
            return fileSize;
        }

        public File getFile()
        {
            return file;
        }

        public String getRawFilename()
        {
            return rawFilename;
        }
    }

}
