// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc;

import org.jivesoftware.smack.SmackException;

// Referenced classes of package org.jivesoftware.smackx.muc:
//            MultiUserChat

public class MUCNotJoinedException extends SmackException
{

    private static final long serialVersionUID = 0xb7c45b923e625798L;

    public MUCNotJoinedException(MultiUserChat multiuserchat)
    {
        super((new StringBuilder()).append("Client not currently joined ").append(multiuserchat.getRoom()).toString());
    }
}
