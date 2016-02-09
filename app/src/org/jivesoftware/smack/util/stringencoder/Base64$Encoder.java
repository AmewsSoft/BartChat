// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.stringencoder;


// Referenced classes of package org.jivesoftware.smack.util.stringencoder:
//            Base64

public static interface 
{

    public abstract byte[] decode(String s);

    public abstract byte[] decode(byte abyte0[], int i, int j);

    public abstract byte[] encode(byte abyte0[], int i, int j);

    public abstract String encodeToString(byte abyte0[], int i, int j);
}
