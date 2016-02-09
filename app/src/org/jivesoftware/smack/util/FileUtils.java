// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileUtils
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/util/FileUtils.getName());

    public FileUtils()
    {
    }

    public static boolean addLines(String s, Set set)
        throws MalformedURLException, IOException
    {
        s = getStreamForUrl(s, null);
        if (s == null)
        {
            return false;
        }
        s = new BufferedReader(new InputStreamReader(s));
        do
        {
            String s1 = s.readLine();
            if (s1 != null)
            {
                set.add(s1);
            } else
            {
                return true;
            }
        } while (true);
    }

    public static List getClassLoaders()
    {
        ClassLoader aclassloader[] = new ClassLoader[2];
        aclassloader[0] = org/jivesoftware/smack/util/FileUtils.getClassLoader();
        aclassloader[1] = Thread.currentThread().getContextClassLoader();
        ArrayList arraylist = new ArrayList(aclassloader.length);
        int j = aclassloader.length;
        for (int i = 0; i < j; i++)
        {
            ClassLoader classloader = aclassloader[i];
            if (classloader != null)
            {
                arraylist.add(classloader);
            }
        }

        return arraylist;
    }

    public static InputStream getStreamForUrl(String s, ClassLoader classloader)
        throws MalformedURLException, IOException
    {
label0:
        {
            URI uri = URI.create(s);
            if (uri.getScheme() == null)
            {
                throw new MalformedURLException((new StringBuilder()).append("No protocol found in file URL: ").append(s).toString());
            }
            if (uri.getScheme().equals("classpath"))
            {
                s = getClassLoaders();
                if (classloader != null)
                {
                    s.add(0, classloader);
                }
                s = s.iterator();
                do
                {
                    if (!s.hasNext())
                    {
                        break label0;
                    }
                    classloader = ((ClassLoader)s.next()).getResourceAsStream(uri.getSchemeSpecificPart());
                } while (classloader == null);
                return classloader;
            } else
            {
                return uri.toURL().openStream();
            }
        }
        return null;
    }

    public static String readFile(File file)
    {
        file = readFileOrThrow(file);
        return file;
        file;
        LOGGER.log(Level.FINE, "readFile", file);
_L2:
        return null;
        file;
        LOGGER.log(Level.WARNING, "readFile", file);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static String readFileOrThrow(File file)
        throws FileNotFoundException, IOException
    {
        Object obj = null;
        file = new FileReader(file);
        StringBuilder stringbuilder;
        obj = new char[8192];
        stringbuilder = new StringBuilder();
_L3:
        int i = file.read(((char []) (obj)));
        if (i < 0) goto _L2; else goto _L1
_L1:
        stringbuilder.append(((char []) (obj)), 0, i);
          goto _L3
        obj;
_L5:
        if (file != null)
        {
            file.close();
        }
        throw obj;
_L2:
        obj = stringbuilder.toString();
        if (file != null)
        {
            file.close();
        }
        return ((String) (obj));
        Exception exception;
        exception;
        file = ((File) (obj));
        obj = exception;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static boolean writeFile(File file, String s)
    {
        try
        {
            writeFileOrThrow(file, s);
        }
        // Misplaced declaration of an exception variable
        catch (File file)
        {
            LOGGER.log(Level.WARNING, "writeFile", file);
            return false;
        }
        return true;
    }

    public static void writeFileOrThrow(File file, String s)
        throws IOException
    {
        file = new FileWriter(file, false);
        file.write(s);
        file.close();
    }

}
