// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster.rosterstore;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.util.FileUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smack.util.stringencoder.Base32;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

// Referenced classes of package org.jivesoftware.smack.roster.rosterstore:
//            RosterStore

public class DirectoryRosterStore
    implements RosterStore
{

    private static final String ENTRY_PREFIX = "entry-";
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/roster/rosterstore/DirectoryRosterStore.getName());
    private static final String STORE_ID = "DEFAULT_ROSTER_STORE";
    private static final String VERSION_FILE_NAME = "__version__";
    private static final FileFilter rosterDirFilter = new FileFilter() {

        public boolean accept(File file)
        {
            return file.getName().startsWith("entry-");
        }

    };
    private final File fileDir;

    private DirectoryRosterStore(File file)
    {
        fileDir = file;
    }

    private boolean addEntryRaw(org.jivesoftware.smack.roster.packet.RosterPacket.Item item)
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.openElement("item");
        xmlstringbuilder.element("user", item.getUser());
        xmlstringbuilder.optElement("name", item.getName());
        xmlstringbuilder.optElement("type", item.getItemType());
        xmlstringbuilder.optElement("status", item.getItemStatus());
        for (Iterator iterator = item.getGroupNames().iterator(); iterator.hasNext(); xmlstringbuilder.closeElement("group"))
        {
            String s = (String)iterator.next();
            xmlstringbuilder.openElement("group");
            xmlstringbuilder.element("groupName", s);
        }

        xmlstringbuilder.closeElement("item");
        return FileUtils.writeFile(getBareJidFile(item.getUser()), xmlstringbuilder.toString());
    }

    private File getBareJidFile(String s)
    {
        s = Base32.encode(s);
        return new File(fileDir, (new StringBuilder()).append("entry-").append(s).toString());
    }

    private File getVersionFile()
    {
        return new File(fileDir, "__version__");
    }

    public static DirectoryRosterStore init(File file)
    {
        file = new DirectoryRosterStore(file);
        if (file.setRosterVersion(""))
        {
            return file;
        } else
        {
            return null;
        }
    }

    private void log(String s)
    {
        System.err.println(s);
    }

    public static DirectoryRosterStore open(File file)
    {
        file = new DirectoryRosterStore(file);
        String s = FileUtils.readFile(file.getVersionFile());
        if (s != null && s.startsWith("DEFAULT_ROSTER_STORE\n"))
        {
            return file;
        } else
        {
            return null;
        }
    }

    private org.jivesoftware.smack.roster.packet.RosterPacket.Item readEntry(File file)
    {
        String s1 = FileUtils.readFile(file);
        if (s1 != null) goto _L2; else goto _L1
_L1:
        Object obj1 = null;
_L6:
        return ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj1));
_L2:
        Object obj;
        String s;
        Object obj2;
        ArrayList arraylist;
        boolean flag;
        int i;
        obj2 = null;
        obj1 = null;
        s = null;
        obj = null;
        arraylist = new ArrayList();
        XmlPullParser xmlpullparser;
        try
        {
            xmlpullparser = XmlPullParserFactory.newInstance().newPullParser();
            xmlpullparser.setInput(new StringReader(s1));
        }
        // Misplaced declaration of an exception variable
        catch (File file)
        {
            LOGGER.log(Level.SEVERE, "readEntry()", file);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            log((new StringBuilder()).append("Invalid group entry in store entry file ").append(file).toString());
            LOGGER.log(Level.SEVERE, "readEntry()", ((Throwable) (obj)));
            return null;
        }
        flag = false;
_L4:
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        i = xmlpullparser.next();
        s1 = xmlpullparser.getName();
        if (i != 2)
        {
            break MISSING_BLOCK_LABEL_365;
        }
        if (s1.equals("item"))
        {
            obj = null;
            s = null;
            obj1 = null;
            obj2 = null;
            continue; /* Loop/switch isn't completed */
        }
        if (s1.equals("user"))
        {
            xmlpullparser.next();
            obj2 = xmlpullparser.getText();
            continue; /* Loop/switch isn't completed */
        }
        if (s1.equals("name"))
        {
            xmlpullparser.next();
            obj1 = xmlpullparser.getText();
            continue; /* Loop/switch isn't completed */
        }
        if (s1.equals("type"))
        {
            xmlpullparser.next();
            s = xmlpullparser.getText();
            continue; /* Loop/switch isn't completed */
        }
        if (s1.equals("status"))
        {
            xmlpullparser.next();
            obj = xmlpullparser.getText();
            continue; /* Loop/switch isn't completed */
        }
        if (!s1.equals("group"))
        {
            continue; /* Loop/switch isn't completed */
        }
        xmlpullparser.next();
        xmlpullparser.next();
        s1 = xmlpullparser.getText();
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_301;
        }
        arraylist.add(s1);
        continue; /* Loop/switch isn't completed */
        log((new StringBuilder()).append("Invalid group entry in store entry file ").append(file).toString());
        continue; /* Loop/switch isn't completed */
        if (i != 3)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag1 = s1.equals("item");
        if (flag1)
        {
            flag = true;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (obj2 == null)
        {
            return null;
        }
        obj2 = new org.jivesoftware.smack.roster.packet.RosterPacket.Item(((String) (obj2)), ((String) (obj1)));
        for (obj1 = arraylist.iterator(); ((Iterator) (obj1)).hasNext(); ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj2)).addGroupName((String)((Iterator) (obj1)).next())) { }
        obj1 = obj2;
        if (s != null)
        {
            try
            {
                ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj2)).setItemType(org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.valueOf(s));
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                log((new StringBuilder()).append("Invalid type in store entry file ").append(file).toString());
                return null;
            }
            obj1 = obj2;
            if (obj != null)
            {
                obj = org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus.fromString(((String) (obj)));
                if (obj == null)
                {
                    log((new StringBuilder()).append("Invalid status in store entry file ").append(file).toString());
                    return null;
                } else
                {
                    ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj2)).setItemStatus(((org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus) (obj)));
                    return ((org.jivesoftware.smack.roster.packet.RosterPacket.Item) (obj2));
                }
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    private boolean setRosterVersion(String s)
    {
        return FileUtils.writeFile(getVersionFile(), (new StringBuilder()).append("DEFAULT_ROSTER_STORE\n").append(s).toString());
    }

    public boolean addEntry(org.jivesoftware.smack.roster.packet.RosterPacket.Item item, String s)
    {
        return addEntryRaw(item) && setRosterVersion(s);
    }

    public volatile Collection getEntries()
    {
        return getEntries();
    }

    public List getEntries()
    {
        ArrayList arraylist = new ArrayList();
        File afile[] = fileDir.listFiles(rosterDirFilter);
        int j = afile.length;
        int i = 0;
        while (i < j) 
        {
            File file = afile[i];
            org.jivesoftware.smack.roster.packet.RosterPacket.Item item = readEntry(file);
            if (item == null)
            {
                log((new StringBuilder()).append("Roster store file '").append(file).append("' is invalid.").toString());
            } else
            {
                arraylist.add(item);
            }
            i++;
        }
        return arraylist;
    }

    public org.jivesoftware.smack.roster.packet.RosterPacket.Item getEntry(String s)
    {
        return readEntry(getBareJidFile(s));
    }

    public String getRosterVersion()
    {
        String s = FileUtils.readFile(getVersionFile());
        String as[];
        if (s != null)
        {
            if ((as = s.split("\n", 2)).length >= 2)
            {
                return as[1];
            }
        }
        return null;
    }

    public boolean removeEntry(String s, String s1)
    {
        return getBareJidFile(s).delete() && setRosterVersion(s1);
    }

    public boolean resetEntries(Collection collection, String s)
    {
        File afile[] = fileDir.listFiles(rosterDirFilter);
        int j = afile.length;
        for (int i = 0; i < j; i++)
        {
            afile[i].delete();
        }

        for (collection = collection.iterator(); collection.hasNext();)
        {
            if (!addEntryRaw((org.jivesoftware.smack.roster.packet.RosterPacket.Item)collection.next()))
            {
                return false;
            }
        }

        return setRosterVersion(s);
    }

}
