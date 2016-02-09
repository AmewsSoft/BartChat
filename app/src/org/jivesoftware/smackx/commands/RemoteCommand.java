// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;

import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.xdata.Form;

// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommand

public class RemoteCommand extends AdHocCommand
{

    private XMPPConnection connection;
    private String jid;
    private String sessionID;

    protected RemoteCommand(XMPPConnection xmppconnection, String s, String s1)
    {
        connection = xmppconnection;
        jid = s1;
        setNode(s);
    }

    private void executeAction(AdHocCommand.Action action)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        executeAction(action, null);
    }

    private void executeAction(AdHocCommand.Action action, Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        AdHocCommandData adhoccommanddata = new AdHocCommandData();
        adhoccommanddata.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        adhoccommanddata.setTo(getOwnerJID());
        adhoccommanddata.setNode(getNode());
        adhoccommanddata.setSessionID(sessionID);
        adhoccommanddata.setAction(action);
        if (form != null)
        {
            adhoccommanddata.setForm(form.getDataFormToSend());
        }
        action = (AdHocCommandData)connection.createPacketCollectorAndSend(adhoccommanddata).nextResultOrThrow();
        sessionID = action.getSessionID();
        super.setData(action);
    }

    public void cancel()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        executeAction(AdHocCommand.Action.cancel);
    }

    public void complete(Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        executeAction(AdHocCommand.Action.complete, form);
    }

    public void execute()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        executeAction(AdHocCommand.Action.execute);
    }

    public void execute(Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        executeAction(AdHocCommand.Action.execute, form);
    }

    public String getOwnerJID()
    {
        return jid;
    }

    public void next(Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        executeAction(AdHocCommand.Action.next, form);
    }

    public void prev()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        executeAction(AdHocCommand.Action.prev);
    }
}
