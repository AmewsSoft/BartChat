// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.packet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.muc.packet:
//            Destroy, MUCItem

public class MUCUser
    implements ExtensionElement
{
    public static class Decline
        implements NamedElement
    {

        public static final String ELEMENT = "decline";
        private String from;
        private String reason;
        private String to;

        public String getElementName()
        {
            return "decline";
        }

        public String getFrom()
        {
            return from;
        }

        public String getReason()
        {
            return reason;
        }

        public String getTo()
        {
            return to;
        }

        public void setFrom(String s)
        {
            from = s;
        }

        public void setReason(String s)
        {
            reason = s;
        }

        public void setTo(String s)
        {
            to = s;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.optAttribute("to", getTo());
            xmlstringbuilder.optAttribute("from", getFrom());
            xmlstringbuilder.rightAngleBracket();
            xmlstringbuilder.optElement("reason", getReason());
            xmlstringbuilder.closeElement(this);
            return xmlstringbuilder;
        }

        public Decline()
        {
        }
    }

    public static class Invite
        implements NamedElement
    {

        public static final String ELEMENT = "invite";
        private String from;
        private String reason;
        private String to;

        public String getElementName()
        {
            return "invite";
        }

        public String getFrom()
        {
            return from;
        }

        public String getReason()
        {
            return reason;
        }

        public String getTo()
        {
            return to;
        }

        public void setFrom(String s)
        {
            from = s;
        }

        public void setReason(String s)
        {
            reason = s;
        }

        public void setTo(String s)
        {
            to = s;
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.optAttribute("to", getTo());
            xmlstringbuilder.optAttribute("from", getFrom());
            xmlstringbuilder.rightAngleBracket();
            xmlstringbuilder.optElement("reason", getReason());
            xmlstringbuilder.closeElement(this);
            return xmlstringbuilder;
        }

        public Invite()
        {
        }
    }

    public static class Status
        implements NamedElement
    {

        public static final Status BANNED_301 = create(Integer.valueOf(301));
        public static final String ELEMENT = "status";
        public static final Status KICKED_307 = create(Integer.valueOf(307));
        public static final Status NEW_NICKNAME_303 = create(Integer.valueOf(303));
        public static final Status REMOVED_AFFIL_CHANGE_321 = create(Integer.valueOf(321));
        public static final Status ROOM_CREATED_201 = create(Integer.valueOf(201));
        private static final Map statusMap = new HashMap(8);
        private final Integer code;

        public static Status create(Integer integer)
        {
            Status status1 = (Status)statusMap.get(integer);
            Status status = status1;
            if (status1 == null)
            {
                status = new Status(integer.intValue());
                statusMap.put(integer, status);
            }
            return status;
        }

        public static Status create(String s)
        {
            return create(Integer.valueOf(s));
        }

        public boolean equals(Object obj)
        {
            while (obj == null || !(obj instanceof Status)) 
            {
                return false;
            }
            obj = (Status)obj;
            return code.equals(Integer.valueOf(((Status) (obj)).getCode()));
        }

        public int getCode()
        {
            return code.intValue();
        }

        public String getElementName()
        {
            return "status";
        }

        public int hashCode()
        {
            return code.intValue();
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
            xmlstringbuilder.attribute("code", getCode());
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }


        private Status(int i)
        {
            code = Integer.valueOf(i);
        }
    }


    public static final String ELEMENT = "x";
    public static final String NAMESPACE = "http://jabber.org/protocol/muc#user";
    private Decline decline;
    private Destroy destroy;
    private Invite invite;
    private MUCItem item;
    private String password;
    private final Set statusCodes = new HashSet(4);

    public MUCUser()
    {
    }

    public static MUCUser from(Stanza stanza)
    {
        return (MUCUser)stanza.getExtension("x", "http://jabber.org/protocol/muc#user");
    }

    public static MUCUser getFrom(Stanza stanza)
    {
        return from(stanza);
    }

    public void addStatusCode(Status status)
    {
        statusCodes.add(status);
    }

    public void addStatusCodes(Set set)
    {
        statusCodes.addAll(set);
    }

    public Decline getDecline()
    {
        return decline;
    }

    public Destroy getDestroy()
    {
        return destroy;
    }

    public String getElementName()
    {
        return "x";
    }

    public Invite getInvite()
    {
        return invite;
    }

    public MUCItem getItem()
    {
        return item;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/muc#user";
    }

    public String getPassword()
    {
        return password;
    }

    public Set getStatus()
    {
        return statusCodes;
    }

    public boolean hasStatus()
    {
        return !statusCodes.isEmpty();
    }

    public void setDecline(Decline decline1)
    {
        decline = decline1;
    }

    public void setDestroy(Destroy destroy1)
    {
        destroy = destroy1;
    }

    public void setInvite(Invite invite1)
    {
        invite = invite1;
    }

    public void setItem(MUCItem mucitem)
    {
        item = mucitem;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.optElement(getInvite());
        xmlstringbuilder.optElement(getDecline());
        xmlstringbuilder.optElement(getItem());
        xmlstringbuilder.optElement("password", getPassword());
        xmlstringbuilder.append(statusCodes);
        xmlstringbuilder.optElement(getDestroy());
        xmlstringbuilder.closeElement(this);
        return xmlstringbuilder;
    }
}
