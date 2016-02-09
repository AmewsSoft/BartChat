// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChat

public interface InvitationListener
{

    public abstract void invitationReceived(XMPPConnection xmppconnection, MultiUserChat multiuserchat, String s, String s1, String s2, Message message);
}
