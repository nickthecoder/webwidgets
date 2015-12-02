/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */
package uk.co.nickthecoder.webwidgets.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Borrowed pretty much all the the logic from java.io.File (Java 2)
 */
public class TempFile
{
    /* -- Temporary files -- */
    private static final Object tmpFileLock = new Object();
    private static int counter = -1; /* Protected by tmpFileLock */

    /**
     * @param prefix
     *        Must be at least 3 characters long
     * @param suffix
     *        The file extension (minus the extension)
     * @param directory
     *        Where do you want this temporary file saved.
     */
    public static File createTempFile( String prefix, String suffix, File directory ) throws IOException
    {
        if (prefix == null)
            throw new NullPointerException();
        if (prefix.length() < 3)
            throw new IllegalArgumentException("Prefix string too short");

        String extension = (suffix == null) ? "tmp" : suffix;
        synchronized (tmpFileLock) {
            File f;
            while (true) {
                f = generateFile(prefix, extension, directory);
                if (!f.exists()) {
                    // Create the file.
                    FileWriter writer = new FileWriter(f);
                    writer.close();
                    break;// break out of while loop!
                }
            }
            return f;
        }
    }

    /**
     * This method is used to generate the file name.
     */
    private static File generateFile( String prefix, String extension, File dir ) throws IOException
    {
        if (counter == -1)
            counter = new Random().nextInt() & 0xffff;
        counter++;

        return new File(dir, prefix + Integer.toString(counter) + "." + extension);
    }
}
