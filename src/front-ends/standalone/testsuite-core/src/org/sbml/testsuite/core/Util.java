//
// @file Util.java
// @brief Util, a collection of static functions used all over the place
// @author Frank T. Bergmann
// @date Created 2012-06-06 <fbergman@caltech.edu>
//
//
// ----------------------------------------------------------------------------
// This file is part of the SBML Testsuite. Please visit http://sbml.org for
// more information about SBML, and the latest version of the SBML Test Suite.
//
// Copyright (C) 2009-2012 jointly by the following organizations:
// 1. California Institute of Technology, Pasadena, CA, USA
// 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
//
// Copyright (C) 2006-2008 by the California Institute of Technology,
// Pasadena, CA, USA
//
// Copyright (C) 2002-2005 jointly by the following organizations:
// 1. California Institute of Technology, Pasadena, CA, USA
// 2. Japan Science and Technology Agency, Japan
//
// This library is free software; you can redistribute it and/or modify it
// under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation. A copy of the license agreement is provided
// in the file named "LICENSE.txt" included with this software distribution
// and also available online as http://sbml.org/software/libsbml/license.html
// ----------------------------------------------------------------------------
//

package org.sbml.testsuite.core;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Util, a collection of static functions used all over the place
 */
public class Util
{

    /**
     * Utility function copying streams
     * @param in input stream
     * @param bufferedOutputStream output stream
     * @throws IOException io exceptions
     */
    public static final void
            copyInputStream(InputStream in,
                            BufferedOutputStream bufferedOutputStream)
                throws IOException
    {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            bufferedOutputStream.write(buffer, 0, len);
        in.close();
        bufferedOutputStream.close();
    }


    /**
     * recursively deletes the given file
     * @param file the file  or directory to delete
     * @throws IOException io exception
     */
    public static void delete(File file)
        throws IOException
    {
        if (file == null || !file.exists()) return;
        if (file.isDirectory())
        {
            for (File c : file.listFiles())
                delete(c);
        }
        if (!file.delete())
            throw new FileNotFoundException("Failed to delete file: " + file);
    }


    /**
     * @return the directory of the bundled test suite
     */
    public static File getInternalTestSuiteDir()
    {
        File destinationDir = new File(getUserDir() + File.separator
            + ".test-suite");
        if (!destinationDir.exists()) destinationDir.mkdirs();
        return destinationDir;
    }


    /**
     * Parses the given level / version string in the formats: 
     * 
     *  - level[.]version
     *  - [l|L]level[v|V]version
     *  
     *  and returns a two element integer array representing level and version
     *  
     * @param levelVersion the level and version string
     * @return int array with the level and version
     */
    public static int[] getLevelAndVersion(String levelVersion)
    {
        int[] result = new int[2];
        String temp[] = levelVersion.split("\\.|l|L|v|V| ");
        try
        {
            if (temp.length == 2)
            {
                result[0] = Integer.parseInt(temp[0]);
                result[1] = Integer.parseInt(temp[1]);
            }
        }
        catch (Exception ex)
        {
            System.err.println("Error in getLevelAndVersion with: '"
                + levelVersion + "'");
            ex.printStackTrace();
        }
        /*
         * Vector<String> temp = (Vector<String>) split(levelVersion, new char[]
         * {
         * '.','l', 'L', 'v', 'V'});
         * if (temp.size() == 2)
         * {
         * result[0] = Integer.parseInt(temp.get(0));
         * result[1] = Integer.parseInt(temp.get(1));
         * }
         */
        return result;
    }


    /**
     * Returns the snippet of contents that starts with 'start' and ends with
     * 'end'
     * 
     * @param contents
     *            the contents
     * @param start
     *            the start element
     * @param end
     *            the end element
     * @return the snippet between start and end
     */
    public static String getSnippet(String contents, String start, String end)
    {
        try
        {
            int startIndex = contents.indexOf(start) + start.length();
            int endIndex = contents.indexOf(end, startIndex);

            String result = contents.substring(startIndex, endIndex).trim();

            Collection<String> lines = split(result, new char[] {'\n'}, true);
            StringBuilder builder = new StringBuilder();
            for (String item : lines)
            {
                builder.append(item.trim() + " ");
            }
            return builder.toString();
        }
        catch (Exception ex)
        {
            return "";
        }

    }


    /**
     * @return the user directory (user.home) on OSX / Linux and %APPDATA% on windows.
     */
    public static String getUserDir()
    {
        String userDir = System.getenv("APPDATA");
        if (userDir == null) userDir = System.getProperty("user.home");
        return userDir;
    }


    /**
     * Returns true, if the string is null or empty
     * @param string the string
     * @return boolean indicating whether the string is null or empty
     */
    public static boolean isNullOrEmpty(String string)
    {
        return string == null || string.length() == 0;
    }


    /**
     * Opens the given file with the associated application
     * @param file the file to open
     */
    public static void openFile(File file)
    {
        if (file == null || !file.exists()) return;
        try
        {
            Desktop.getDesktop().open(file);
        }
        catch (IOException e)
        {

        }
    }


    /**
     * Parses the given string and returns its double value (or 0.0 in case of error)
     * @param value the string to parse
     * @return the strings value as double
     */
    public static double parseDouble(String value)
    {
        try
        {
            return Double.parseDouble(value.trim());
        }
        catch (Exception ex)
        {
            return 0.0;
        }
    }


    /**
     * Parses the given string and returns its int value (or 0 in case of error)
     * @param value the string to parse
     * @return the strings value as int
     */
    public static int parseInt(String value)
    {
        try
        {
            return Integer.parseInt(value.trim());
        }
        catch (Exception ex)
        {
            return 0;
        }
    }


    /**
     * Read the given file and return its contents as string
     * 
     * @param file
     *            the file to read
     * @return its contents as string
     */
    public static String readAllText(File file)
    {
        if (file == null || !file.exists()) return null;

        BufferedReader reader;
        StringBuilder builder = new StringBuilder();
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line + "\n");
            reader.close();
        }
        catch (Exception e)
        {}
        return builder.toString();

    }


    /**
     * Sleep for the number of milliseconds
     * @param millies the number of milliseconds to sleep
     */
    public static void sleep(int millies)
    {
        try
        {
            Thread.sleep(millies);
        }
        catch (Exception e)
        {}

    }


    /**
     * Splits the given string by whitespaces
     * 
     * @param snippet
     *            the snippet to split
     * @return the pieces of the snippet
     */
    public static Collection<String> split(String snippet)
    {
        return split(snippet, new char[] {',', ' ', '\n'});
    }


    /**
     * Splits the given snippet by all given characters
     * @param snippet the snippet to split
     * @param characters all characters to split
     * @return the pieces of the snippet
     */
    public static Collection<String> split(String snippet, char[] characters)
    {
        return split(snippet, characters, true);
    }


    /**
     * Splits the given snippet by all given characters, removing all empty elements
     * @param snippet the snippet to split
     * @param characters the characters to split by
     * @param removeEmptyEntries boolean indicating whether empty elements ought to be removed
     * @return the pieces of the snippet
     */
    public static Collection<String> split(String snippet, char[] characters,
                                           boolean removeEmptyEntries)
    {
        Vector<String> result = new Vector<String>();
        if (characters == null || characters.length == 0)
        {
            result.add(snippet);
        }
        else
        {
            for (int i = 1; i < characters.length; i++)
                snippet = snippet.replace(characters[i], characters[0]);

            result.addAll(Arrays.asList(snippet.split("" + characters[0])));
        }

        if (removeEmptyEntries)
        {
            for (int i = result.size() - 1; i >= 0; i--)
            {
                String current = result.get(i);
                if (current == null || current.length() == 0
                    || current.trim().equalsIgnoreCase("")) result.remove(i);
            }
        }

        return result;
    }


    /**
     * Returns the items of the given vector as comma separated list
     * 
     * @param items
     *            the vector to flatten
     * @return string of comma separated items
     */
    public static String toString(Vector<String> items)
    {
        if (items == null || items.size() == 0) return "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < items.size(); i++)
        {
            builder.append(items.get(i));
            if (i + 1 < items.size()) builder.append(", ");
        }
        return builder.toString();
    }


    /**
     * Unzips the given file
     * @param file the file to unzip
     */
    public static void unzipArchive(File file)
    {
        unzipArchive(file, null);
    }


    /**
     * Unzips the given file, adhering to cancallation callback
     * @param file the file to unzip
     * @param callback a cancellation callback
     */
    public static void unzipArchive(File file, CancelCallback callback)
    {
        if (!file.exists()) return;
        try
        {
            delete(getInternalTestSuiteDir());
        }
        catch (IOException e)
        {}

        File destinationDir = getInternalTestSuiteDir();
        int ncount = 0;
        try
        {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements())
            {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File currentFile = new File(destinationDir, entry.getName());
                if (entry.isDirectory())
                {
                    if (!currentFile.exists()) currentFile.mkdirs();
                    continue;
                }
                if (currentFile.exists()) currentFile.delete();

                copyInputStream(zipFile.getInputStream(entry),
                                new BufferedOutputStream(
                                                         new FileOutputStream(
                                                                              currentFile)));
                ncount++;
                if (ncount % 100 == 0 && callback != null)
                    if (callback.cancellationRequested()) return;
            }
            zipFile.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }

    }


    /**
     * Writes the given string into the given file
     * @param file the file to write to
     * @param content the content to write
     */
    public static void writeAllText(File file, String content)
    {
        BufferedWriter writer;
        try
        {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {}
    }


    /**
     * Return a date read from our special archive date file.
     * @return a date, read as YYYY-MM-DD.
     */
    public static Date readArchiveDateFile(File dateFile)
    {
        if (! dateFile.exists() || ! dateFile.canRead())
            return null;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(dateFile));
            try
            {
                return readArchiveDateFile(br);
            }
            finally
            {
                br.close();
            }
        }
        catch (Exception e)
        {
            return null;
        }
    }


    /**
     * Return a date read from our special archive date file.
     * @return a date, read as YYYY-MM-DD.
     */
    public static Date readArchiveDateFile(File dir, String filename)
    {
        return readArchiveDateFile(new File(dir, filename));
    }


    /**
     * Return a date read from our special archive date file.
     * @return a date, read as YYYY-MM-DD.
     */
    public static Date readArchiveDateFileStream(InputStream is)
    {
        try
        {
            Reader reader = new InputStreamReader(is);
            try
            {
                return readArchiveDateFile(reader);
            }
            finally
            {
                reader.close();
            }
        }
        catch (Exception e)
        {
            return null;
        }
    }


    public static Date readArchiveDateFile(Reader reader)
    {
        try
        {
            char[] buffer = new char[10];
            reader.read(buffer, 0, 10);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(new String(buffer));
        }
        catch (Exception e)
        {
            return null;
        }
    }
}