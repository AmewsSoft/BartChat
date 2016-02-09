// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jxmpp.stringprep;


// Referenced classes of package org.jxmpp.stringprep:
//            XmppStringprepException

public interface XmppStringprep
{

    public abstract String domainprep(String s)
        throws XmppStringprepException;

    public abstract String localprep(String s)
        throws XmppStringprepException;

    public abstract String resourceprep(String s)
        throws XmppStringprepException;
}
