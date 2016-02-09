// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.search;

import java.io.IOException;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.SimpleIQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.search:
//            SimpleUserSearch, ReportedData

public class UserSearch extends SimpleIQ
{
    public static class Provider extends IQProvider
    {

        public volatile Element parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            return parse(xmlpullparser, i);
        }

        public IQ parse(XmlPullParser xmlpullparser, int i)
            throws XmlPullParserException, IOException, SmackException
        {
            UserSearch usersearch;
            SimpleUserSearch simpleusersearch;
            usersearch = null;
            simpleusersearch = new SimpleUserSearch();
            i = 0;
_L8:
            if (i != 0) goto _L2; else goto _L1
_L1:
            int j = xmlpullparser.next();
            if (j != 2 || !xmlpullparser.getName().equals("instructions")) goto _L4; else goto _L3
_L3:
            UserSearch.buildDataForm(simpleusersearch, xmlpullparser.nextText(), xmlpullparser);
_L6:
            return simpleusersearch;
_L4:
            if (j == 2 && xmlpullparser.getName().equals("item"))
            {
                simpleusersearch.parseItems(xmlpullparser);
                return simpleusersearch;
            }
            if (j == 2 && xmlpullparser.getNamespace().equals("jabber:x:data"))
            {
                usersearch = new UserSearch();
                PacketParserUtils.addExtensionElement(usersearch, xmlpullparser);
            } else
            if (j == 3 && xmlpullparser.getName().equals("query"))
            {
                i = 1;
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if (usersearch == null) goto _L6; else goto _L5
_L5:
            return usersearch;
            if (true) goto _L8; else goto _L7
_L7:
        }

        public Provider()
        {
        }
    }


    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:search";

    public UserSearch()
    {
        super("query", "jabber:iq:search");
    }

    private static void buildDataForm(SimpleUserSearch simpleusersearch, String s, XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        DataForm dataform;
        boolean flag;
        dataform = new DataForm(org.jivesoftware.smackx.xdata.packet.DataForm.Type.form);
        flag = false;
        dataform.setTitle("User Search");
        dataform.addInstruction(s);
_L7:
        int i;
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        i = xmlpullparser.next();
        if (i != 2 || xmlpullparser.getNamespace().equals("jabber:x:data")) goto _L2; else goto _L1
_L1:
        FormField formfield;
        s = xmlpullparser.getName();
        formfield = new FormField(s);
        if (!s.equals("first")) goto _L4; else goto _L3
_L3:
        formfield.setLabel("First Name");
_L5:
        formfield.setType(org.jivesoftware.smackx.xdata.FormField.Type.text_single);
        dataform.addField(formfield);
        continue; /* Loop/switch isn't completed */
_L4:
        if (s.equals("last"))
        {
            formfield.setLabel("Last Name");
        } else
        if (s.equals("email"))
        {
            formfield.setLabel("Email Address");
        } else
        if (s.equals("nick"))
        {
            formfield.setLabel("Nickname");
        }
        if (true) goto _L5; else goto _L2
_L2:
        if (i == 3)
        {
            if (xmlpullparser.getName().equals("query"))
            {
                flag = true;
            }
        } else
        if (i == 2 && xmlpullparser.getNamespace().equals("jabber:x:data"))
        {
            PacketParserUtils.addExtensionElement(simpleusersearch, xmlpullparser);
            flag = true;
        }
        if (true) goto _L7; else goto _L6
_L6:
        if (simpleusersearch.getExtension("x", "jabber:x:data") == null)
        {
            simpleusersearch.addExtension(dataform);
        }
        return;
    }

    public Form getSearchForm(XMPPConnection xmppconnection, String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        UserSearch usersearch = new UserSearch();
        usersearch.setType(org.jivesoftware.smack.packet.IQ.Type.get);
        usersearch.setTo(s);
        return Form.getFormFrom((IQ)xmppconnection.createPacketCollectorAndSend(usersearch).nextResultOrThrow());
    }

    public ReportedData sendSearchForm(XMPPConnection xmppconnection, Form form, String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        UserSearch usersearch = new UserSearch();
        usersearch.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        usersearch.setTo(s);
        usersearch.addExtension(form.getDataFormToSend());
        return ReportedData.getReportedDataFrom((IQ)xmppconnection.createPacketCollectorAndSend(usersearch).nextResultOrThrow());
    }

    public ReportedData sendSimpleSearchForm(XMPPConnection xmppconnection, Form form, String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        SimpleUserSearch simpleusersearch = new SimpleUserSearch();
        simpleusersearch.setForm(form);
        simpleusersearch.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        simpleusersearch.setTo(s);
        return ((SimpleUserSearch)xmppconnection.createPacketCollectorAndSend(simpleusersearch).nextResultOrThrow()).getReportedData();
    }

}
