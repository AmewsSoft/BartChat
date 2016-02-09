// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqprivate.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smackx.iqprivate.packet.PrivateData;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public interface PrivateDataProvider
{

    public abstract PrivateData parsePrivateData(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException, SmackException;
}
