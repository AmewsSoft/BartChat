// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.commands.AdHocCommandNote;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jivesoftware.smackx.xdata.provider.DataFormProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AdHocCommandDataProvider extends IQProvider
{
    public static class BadActionError extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError parse(XmlPullParser xmlpullparser, int i)
        {
            return new org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError(org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.badAction);
        }

        public BadActionError()
        {
        }
    }

    public static class BadLocaleError extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError parse(XmlPullParser xmlpullparser, int i)
        {
            return new org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError(org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.badLocale);
        }

        public BadLocaleError()
        {
        }
    }

    public static class BadPayloadError extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError parse(XmlPullParser xmlpullparser, int i)
        {
            return new org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError(org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.badPayload);
        }

        public BadPayloadError()
        {
        }
    }

    public static class BadSessionIDError extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError parse(XmlPullParser xmlpullparser, int i)
        {
            return new org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError(org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.badSessionid);
        }

        public BadSessionIDError()
        {
        }
    }

    public static class MalformedActionError extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError parse(XmlPullParser xmlpullparser, int i)
        {
            return new org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError(org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.malformedAction);
        }

        public MalformedActionError()
        {
        }
    }

    public static class SessionExpiredError extends ExtensionElementProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError parse(XmlPullParser xmlpullparser, int i)
        {
            return new org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError(org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition.sessionExpired);
        }

        public SessionExpiredError()
        {
        }
    }


    public AdHocCommandDataProvider()
    {
    }

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public AdHocCommandData parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        int j = 0;
        AdHocCommandData adhoccommanddata = new AdHocCommandData();
        DataFormProvider dataformprovider = new DataFormProvider();
        adhoccommanddata.setSessionID(xmlpullparser.getAttributeValue("", "sessionid"));
        adhoccommanddata.setNode(xmlpullparser.getAttributeValue("", "node"));
        Object obj = xmlpullparser.getAttributeValue("", "status");
        if (org.jivesoftware.smackx.commands.AdHocCommand.Status.executing.toString().equalsIgnoreCase(((String) (obj))))
        {
            adhoccommanddata.setStatus(org.jivesoftware.smackx.commands.AdHocCommand.Status.executing);
        } else
        if (org.jivesoftware.smackx.commands.AdHocCommand.Status.completed.toString().equalsIgnoreCase(((String) (obj))))
        {
            adhoccommanddata.setStatus(org.jivesoftware.smackx.commands.AdHocCommand.Status.completed);
        } else
        if (org.jivesoftware.smackx.commands.AdHocCommand.Status.canceled.toString().equalsIgnoreCase(((String) (obj))))
        {
            adhoccommanddata.setStatus(org.jivesoftware.smackx.commands.AdHocCommand.Status.canceled);
        }
        obj = xmlpullparser.getAttributeValue("", "action");
        i = j;
        if (obj != null)
        {
            obj = org.jivesoftware.smackx.commands.AdHocCommand.Action.valueOf(((String) (obj)));
            if (obj == null || ((org.jivesoftware.smackx.commands.AdHocCommand.Action) (obj)).equals(org.jivesoftware.smackx.commands.AdHocCommand.Action.unknown))
            {
                adhoccommanddata.setAction(org.jivesoftware.smackx.commands.AdHocCommand.Action.unknown);
                i = j;
            } else
            {
                adhoccommanddata.setAction(((org.jivesoftware.smackx.commands.AdHocCommand.Action) (obj)));
                i = j;
            }
        }
        while (i == 0) 
        {
            j = xmlpullparser.next();
            obj = xmlpullparser.getName();
            String s = xmlpullparser.getNamespace();
            if (j == 2)
            {
                if (xmlpullparser.getName().equals("actions"))
                {
                    obj = xmlpullparser.getAttributeValue("", "execute");
                    if (obj != null)
                    {
                        adhoccommanddata.setExecuteAction(org.jivesoftware.smackx.commands.AdHocCommand.Action.valueOf(((String) (obj))));
                    }
                } else
                if (xmlpullparser.getName().equals("next"))
                {
                    adhoccommanddata.addAction(org.jivesoftware.smackx.commands.AdHocCommand.Action.next);
                } else
                if (xmlpullparser.getName().equals("complete"))
                {
                    adhoccommanddata.addAction(org.jivesoftware.smackx.commands.AdHocCommand.Action.complete);
                } else
                if (xmlpullparser.getName().equals("prev"))
                {
                    adhoccommanddata.addAction(org.jivesoftware.smackx.commands.AdHocCommand.Action.prev);
                } else
                if (((String) (obj)).equals("x") && s.equals("jabber:x:data"))
                {
                    adhoccommanddata.setForm((DataForm)dataformprovider.parse(xmlpullparser));
                } else
                if (xmlpullparser.getName().equals("note"))
                {
                    adhoccommanddata.addNote(new AdHocCommandNote(org.jivesoftware.smackx.commands.AdHocCommandNote.Type.valueOf(xmlpullparser.getAttributeValue("", "type")), xmlpullparser.nextText()));
                } else
                if (xmlpullparser.getName().equals("error"))
                {
                    adhoccommanddata.setError(PacketParserUtils.parseError(xmlpullparser));
                }
            } else
            if (j == 3 && xmlpullparser.getName().equals("command"))
            {
                i = 1;
            }
        }
        return adhoccommanddata;
    }
}
