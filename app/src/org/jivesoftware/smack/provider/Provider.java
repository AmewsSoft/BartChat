// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.util.ParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class Provider
{

    public Provider()
    {
    }

    public final Element parse(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException
    {
        ParserUtils.assertAtStartTag(xmlpullparser);
        int i = xmlpullparser.getDepth();
        Element element = parse(xmlpullparser, i);
        ParserUtils.forwardToEndTagOfDepth(xmlpullparser, i);
        return element;
    }

    public abstract Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException;
}
