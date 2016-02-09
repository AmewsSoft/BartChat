// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.search;

import java.util.List;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.xdata.Form;

// Referenced classes of package org.jivesoftware.smackx.search:
//            UserSearch, ReportedData

public class UserSearchManager
{

    private XMPPConnection con;
    private UserSearch userSearch;

    public UserSearchManager(XMPPConnection xmppconnection)
    {
        con = xmppconnection;
        userSearch = new UserSearch();
    }

    public Form getSearchForm(String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return userSearch.getSearchForm(con, s);
    }

    public ReportedData getSearchResults(Form form, String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return userSearch.sendSearchForm(con, form, s);
    }

    public List getSearchServices()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        return ServiceDiscoveryManager.getInstanceFor(con).findServices("jabber:iq:search", false, false);
    }
}
