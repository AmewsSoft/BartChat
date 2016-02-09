// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.provider;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smack.provider:
//            ExtensionElementProvider, IntrospectionProvider

public static abstract class elementClass extends ExtensionElementProvider
{

    private final Class elementClass;

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public ExtensionElement parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        xmlpullparser = (ExtensionElement)IntrospectionProvider.parseWithIntrospection(elementClass, xmlpullparser, i);
        return xmlpullparser;
        xmlpullparser;
_L2:
        throw new SmackException(xmlpullparser);
        xmlpullparser;
        continue; /* Loop/switch isn't completed */
        xmlpullparser;
        continue; /* Loop/switch isn't completed */
        xmlpullparser;
        continue; /* Loop/switch isn't completed */
        xmlpullparser;
        continue; /* Loop/switch isn't completed */
        xmlpullparser;
        continue; /* Loop/switch isn't completed */
        xmlpullparser;
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected (Class class1)
    {
        elementClass = class1;
    }
}
