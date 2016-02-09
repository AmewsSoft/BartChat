// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps.cache;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smack.util.stringencoder.Base32;
import org.jivesoftware.smack.util.stringencoder.StringEncoder;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;

// Referenced classes of package org.jivesoftware.smackx.caps.cache:
//            EntityCapsPersistentCache

public class SimpleDirectoryPersistentCache
    implements EntityCapsPersistentCache
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/caps/cache/SimpleDirectoryPersistentCache.getName());
    private File cacheDir;
    private StringEncoder filenameEncoder;

    public SimpleDirectoryPersistentCache(File file)
    {
        this(file, Base32.getStringEncoder());
    }

    public SimpleDirectoryPersistentCache(File file, StringEncoder stringencoder)
    {
        if (!file.exists())
        {
            throw new IllegalStateException((new StringBuilder()).append("Cache directory \"").append(file).append("\" does not exist").toString());
        }
        if (!file.isDirectory())
        {
            throw new IllegalStateException((new StringBuilder()).append("Cache directory \"").append(file).append("\" is not a directory").toString());
        } else
        {
            cacheDir = file;
            filenameEncoder = stringencoder;
            return;
        }
    }

    private File getFileFor(String s)
    {
        s = filenameEncoder.encode(s);
        return new File(cacheDir, s);
    }

    private static DiscoverInfo restoreInfoFromFile(File file)
        throws Exception
    {
        file = new DataInputStream(new FileInputStream(file));
        Object obj = file.readUTF();
        file.close();
        if (obj == null)
        {
            return null;
        } else
        {
            return (DiscoverInfo)PacketParserUtils.parseStanza(((String) (obj)));
        }
        obj;
        file.close();
        throw obj;
    }

    private static void writeInfoToFile(File file, DiscoverInfo discoverinfo)
        throws IOException
    {
        file = new DataOutputStream(new FileOutputStream(file));
        file.writeUTF(discoverinfo.toXML().toString());
        file.close();
        return;
        discoverinfo;
        file.close();
        throw discoverinfo;
    }

    public void addDiscoverInfoByNodePersistent(String s, DiscoverInfo discoverinfo)
    {
        s = getFileFor(s);
        try
        {
            if (s.createNewFile())
            {
                writeInfoToFile(s, discoverinfo);
            }
            return;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            LOGGER.log(Level.SEVERE, "Failed to write disco info to file", s);
        }
    }

    public void emptyCache()
    {
        File afile[] = cacheDir.listFiles();
        int j = afile.length;
        for (int i = 0; i < j; i++)
        {
            afile[i].delete();
        }

    }

    public DiscoverInfo lookup(String s)
    {
        s = getFileFor(s);
        if (!s.isFile())
        {
            return null;
        }
        try
        {
            s = restoreInfoFromFile(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            LOGGER.log(Level.WARNING, "Coud not restore info from file", s);
            return null;
        }
        return s;
    }

}
