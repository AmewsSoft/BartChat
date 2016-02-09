// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;


// Referenced classes of package org.jivesoftware.smack.packet:
//            IQ

public class UnparsedIQ extends IQ
{

    private final CharSequence content;

    public UnparsedIQ(String s, String s1, CharSequence charsequence)
    {
        super(s, s1);
        content = charsequence;
    }

    public CharSequence getContent()
    {
        return content;
    }

    protected IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        throw new UnsupportedOperationException();
    }
}
