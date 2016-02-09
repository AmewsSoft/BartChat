// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.address;

import org.jivesoftware.smack.packet.Stanza;

// Referenced classes of package org.jivesoftware.smackx.address:
//            MultipleRecipientManager

private static class text extends Stanza
{

    private CharSequence text;

    public CharSequence toXML()
    {
        return text;
    }

    public I(CharSequence charsequence)
    {
        text = charsequence;
    }
}
