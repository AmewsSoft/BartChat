// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;

public class RoomInfo
{

    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/muc/RoomInfo.getName());
    private final List contactJid;
    private final String description;
    private final Form form;
    private final String lang;
    private final String ldapgroup;
    private final URL logs;
    private final int maxhistoryfetch;
    private final boolean membersOnly;
    private final boolean moderated;
    private final String name;
    private final boolean nonanonymous;
    private final int occupantsCount;
    private final boolean passwordProtected;
    private final boolean persistent;
    private final String pubsub;
    private final String room;
    private final String subject;
    private final Boolean subjectmod;

    RoomInfo(DiscoverInfo discoverinfo)
    {
        room = discoverinfo.getFrom();
        membersOnly = discoverinfo.containsFeature("muc_membersonly");
        moderated = discoverinfo.containsFeature("muc_moderated");
        nonanonymous = discoverinfo.containsFeature("muc_nonanonymous");
        passwordProtected = discoverinfo.containsFeature("muc_passwordprotected");
        persistent = discoverinfo.containsFeature("muc_persistent");
        Object obj = discoverinfo.getIdentities();
        Object obj1;
        String s;
        String s1;
        Boolean boolean1;
        Object obj2;
        Object obj3;
        Object obj4;
        String s2;
        String s3;
        String s4;
        Object obj5;
        String s5;
        Boolean boolean2;
        Object obj6;
        FormField formfield;
        int j;
        byte byte0;
        int k;
        int l;
        if (!((List) (obj)).isEmpty())
        {
            name = ((org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity)((List) (obj)).get(0)).getName();
        } else
        {
            LOGGER.warning((new StringBuilder()).append("DiscoverInfo does not contain any Identity: ").append(discoverinfo.toXML()).toString());
            name = "";
        }
        obj1 = "";
        j = -1;
        obj = "";
        byte0 = -1;
        obj3 = null;
        s = null;
        s2 = null;
        s1 = null;
        s3 = null;
        boolean1 = null;
        boolean2 = null;
        obj2 = null;
        obj5 = null;
        formfield = null;
        obj6 = null;
        form = Form.getFormFrom(discoverinfo);
        obj4 = obj;
        k = byte0;
        l = j;
        s5 = obj6;
        s4 = ((String) (obj1));
        if (form != null)
        {
            obj3 = form.getField("muc#roominfo_description");
            discoverinfo = ((DiscoverInfo) (obj));
            if (obj3 != null)
            {
                discoverinfo = ((DiscoverInfo) (obj));
                if (!((FormField) (obj3)).getValues().isEmpty())
                {
                    discoverinfo = (String)((FormField) (obj3)).getValues().get(0);
                }
            }
            obj3 = form.getField("muc#roominfo_subject");
            obj = obj1;
            if (obj3 != null)
            {
                obj = obj1;
                if (!((FormField) (obj3)).getValues().isEmpty())
                {
                    obj = (String)((FormField) (obj3)).getValues().get(0);
                }
            }
            obj1 = form.getField("muc#roominfo_occupants");
            int i = j;
            if (obj1 != null)
            {
                i = j;
                if (!((FormField) (obj1)).getValues().isEmpty())
                {
                    i = Integer.parseInt((String)((FormField) (obj1)).getValues().get(0));
                }
            }
            obj1 = form.getField("muc#maxhistoryfetch");
            j = byte0;
            if (obj1 != null)
            {
                j = byte0;
                if (!((FormField) (obj1)).getValues().isEmpty())
                {
                    j = Integer.parseInt((String)((FormField) (obj1)).getValues().get(0));
                }
            }
            obj3 = form.getField("muc#roominfo_contactjid");
            obj1 = s;
            if (obj3 != null)
            {
                obj1 = s;
                if (!((FormField) (obj3)).getValues().isEmpty())
                {
                    obj1 = ((FormField) (obj3)).getValues();
                }
            }
            obj3 = form.getField("muc#roominfo_lang");
            s = s1;
            if (obj3 != null)
            {
                s = s1;
                if (!((FormField) (obj3)).getValues().isEmpty())
                {
                    s = (String)((FormField) (obj3)).getValues().get(0);
                }
            }
            obj3 = form.getField("muc#roominfo_ldapgroup");
            s1 = boolean1;
            if (obj3 != null)
            {
                s1 = boolean1;
                if (!((FormField) (obj3)).getValues().isEmpty())
                {
                    s1 = (String)((FormField) (obj3)).getValues().get(0);
                }
            }
            obj3 = form.getField("muc#roominfo_subjectmod");
            boolean1 = ((Boolean) (obj2));
            if (obj3 != null)
            {
                boolean1 = ((Boolean) (obj2));
                if (!((FormField) (obj3)).getValues().isEmpty())
                {
                    boolean1 = Boolean.valueOf((String)((FormField) (obj3)).getValues().get(0));
                }
            }
            obj3 = form.getField("muc#roominfo_logs");
            obj2 = formfield;
            if (obj3 != null)
            {
                obj2 = formfield;
                if (!((FormField) (obj3)).getValues().isEmpty())
                {
                    obj2 = (String)((FormField) (obj3)).getValues().get(0);
                    try
                    {
                        obj2 = new URL(((String) (obj2)));
                    }
                    catch (MalformedURLException malformedurlexception)
                    {
                        LOGGER.log(Level.SEVERE, "Could not parse URL", malformedurlexception);
                        malformedurlexception = formfield;
                    }
                }
            }
            formfield = form.getField("muc#roominfo_pubsub");
            obj3 = obj1;
            obj4 = discoverinfo;
            s2 = s;
            s3 = s1;
            obj5 = obj2;
            k = j;
            l = i;
            s5 = obj6;
            s4 = ((String) (obj));
            boolean2 = boolean1;
            if (formfield != null)
            {
                obj3 = obj1;
                obj4 = discoverinfo;
                s2 = s;
                s3 = s1;
                obj5 = obj2;
                k = j;
                l = i;
                s5 = obj6;
                s4 = ((String) (obj));
                boolean2 = boolean1;
                if (!formfield.getValues().isEmpty())
                {
                    s5 = (String)formfield.getValues().get(0);
                    boolean2 = boolean1;
                    s4 = ((String) (obj));
                    l = i;
                    k = j;
                    obj5 = obj2;
                    s3 = s1;
                    s2 = s;
                    obj4 = discoverinfo;
                    obj3 = obj1;
                }
            }
        }
        description = ((String) (obj4));
        subject = s4;
        occupantsCount = l;
        maxhistoryfetch = k;
        contactJid = ((List) (obj3));
        lang = s2;
        ldapgroup = s3;
        subjectmod = boolean2;
        logs = ((URL) (obj5));
        pubsub = s5;
    }

    public List getContactJids()
    {
        return contactJid;
    }

    public String getDescription()
    {
        return description;
    }

    public Form getForm()
    {
        return form;
    }

    public String getLang()
    {
        return lang;
    }

    public String getLdapGroup()
    {
        return ldapgroup;
    }

    public URL getLogsUrl()
    {
        return logs;
    }

    public int getMaxHistoryFetch()
    {
        return maxhistoryfetch;
    }

    public String getName()
    {
        return name;
    }

    public int getOccupantsCount()
    {
        return occupantsCount;
    }

    public String getPubSub()
    {
        return pubsub;
    }

    public String getRoom()
    {
        return room;
    }

    public String getSubject()
    {
        return subject;
    }

    public boolean isMembersOnly()
    {
        return membersOnly;
    }

    public boolean isModerated()
    {
        return moderated;
    }

    public boolean isNonanonymous()
    {
        return nonanonymous;
    }

    public boolean isPasswordProtected()
    {
        return passwordProtected;
    }

    public boolean isPersistent()
    {
        return persistent;
    }

    public Boolean isSubjectModifiable()
    {
        return subjectmod;
    }

}
