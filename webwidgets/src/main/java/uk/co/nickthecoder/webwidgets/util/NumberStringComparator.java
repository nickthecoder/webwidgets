/*
 * Copyright (c) Nick Robinson All rights reserved. This program and the accompanying materials are
 * made available under the terms of the GNU Public License v3.0 which accompanies this distribution, and
 * is available at http://www.gnu.org/licenses/gpl.html
 */

package uk.co.nickthecoder.webwidgets.util;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.log4j.Logger;

/**
 * I want to sort strings, which may contain numbers in a human like way, not just based on unicode values.
 * For example, these are sorted :
 * 
 * bob1, bob2, bob10, bob12a, bob12b
 */
public class NumberStringComparator implements Comparator<String>
{

    protected static Logger _logger = Logger.getLogger(NumberStringComparator.class);

    public int compare( String a, String b )
    {
        if (a == null) {
            return b == null ? 0 : -1;
        }

        if (b == null) {
            return 1;
        }

        String sa = (String) a;
        String sb = (String) b;

        int minLength = sa.length() < sb.length() ? sa.length() : sb.length();

        // _logger.debug( "Comparing '" + sa + "' and '" + sb + "'" );

        int j = 0;
        for (int i = 0; i < minLength; i++) {

            char ca = sa.charAt(i);
            char cb = sb.charAt(j);

            if (Character.isDigit(ca) && Character.isDigit(cb)) {
                // We have found two numbers in similar positions.
                // Extract the numeric values of each, and if they are different, then return the comparison of the numbers.
                // If they are the same, then we will need to continue comparing the rest of the string after the digits

                int starti = i;
                for (; i < sa.length(); i++) {

                    ca = sa.charAt(i);

                    if (!Character.isDigit(ca)) {
                        break;
                    }
                }
                int startj = j;
                for (; j < sb.length(); j++) {

                    cb = sb.charAt(j);

                    if (!Character.isDigit(cb)) {
                        break;
                    }
                }

                // System.out.println( "starti, startj, i, j " + starti + "," + startj + "," + i + "," + j );

                String subA = sa.substring(starti, i);
                String subB = sb.substring(startj, j);

                try {
                    int intA = Integer.parseInt(subA);
                    int intB = Integer.parseInt(subB);

                    // System.out.println( intA + " vs " + intB );
                    i--;
                    j--;

                    if (intA != intB) {
                        return intA - intB;
                    } else if (!subA.equals(subB)) {
                        return subA.compareTo(subB);
                    }

                } catch (Exception e) {
                    // If there are too many digits, then the parseInt will fail, so we just compare the strings instead.
                    if (!subA.equals(subB)) {
                        return subA.compareTo(subB);
                    }
                }

            } else {

                if (ca != cb) {
                    return ca - cb;
                }

            }

            j++;

        }

        // Everything is the same so far, so if one is longer than the other, then that is the only difference.
        return sa.length() - sb.length();
    }

    public static void main( String argv[] )
    {
        Arrays.sort(argv, new NumberStringComparator());

        for (int i = 0; i < argv.length; i++) {
            System.out.println(argv[i]);
        }
    }

}
