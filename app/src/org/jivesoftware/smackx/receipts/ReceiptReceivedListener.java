// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.receipts;

import org.jivesoftware.smack.packet.Stanza;

public interface ReceiptReceivedListener
{

    public abstract void onReceiptReceived(String s, String s1, String s2, Stanza stanza);
}
