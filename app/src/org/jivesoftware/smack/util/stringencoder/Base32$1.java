// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.stringencoder;


// Referenced classes of package org.jivesoftware.smack.util.stringencoder:
//            StringEncoder, Base32

static final class oder
    implements StringEncoder
{

    public String decode(String s)
    {
        return Base32.decode(s);
    }

    public String encode(String s)
    {
        return Base32.encode(s);
    }

    oder()
    {
    }
}
