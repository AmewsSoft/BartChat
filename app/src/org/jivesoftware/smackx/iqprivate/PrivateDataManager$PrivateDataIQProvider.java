// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.iqprivate;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.iqprivate.packet.DefaultPrivateData;
import org.jivesoftware.smackx.iqprivate.packet.PrivateDataIQ;
import org.jivesoftware.smackx.iqprivate.provider.PrivateDataProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.iqprivate:
//            PrivateDataManager

public static class  extends IQProvider
{

    public volatile Element parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        return parse(xmlpullparser, i);
    }

    public PrivateDataIQ parse(XmlPullParser xmlpullparser, int i)
        throws XmlPullParserException, IOException, SmackException
    {
        Object obj = null;
        i = 0;
label0:
        do
        {
            if (i != 0)
            {
                break;
            }
            int j = xmlpullparser.next();
            if (j == 2)
            {
                String s = xmlpullparser.getName();
                obj = xmlpullparser.getNamespace();
                PrivateDataProvider privatedataprovider = PrivateDataManager.getPrivateDataProvider(s, ((String) (obj)));
                if (privatedataprovider != null)
                {
                    obj = privatedataprovider.parsePrivateData(xmlpullparser);
                    continue;
                }
                obj = new DefaultPrivateData(s, ((String) (obj)));
                j = 0;
                do
                {
                    if (j != 0)
                    {
                        continue label0;
                    }
                    int k = xmlpullparser.next();
                    if (k == 2)
                    {
                        String s1 = xmlpullparser.getName();
                        if (xmlpullparser.isEmptyElementTag())
                        {
                            ((DefaultPrivateData) (obj)).setValue(s1, "");
                        } else
                        if (xmlpullparser.next() == 4)
                        {
                            ((DefaultPrivateData) (obj)).setValue(s1, xmlpullparser.getText());
                        }
                    } else
                    if (k == 3 && xmlpullparser.getName().equals(s))
                    {
                        j = 1;
                    }
                } while (true);
            }
            if (j == 3 && xmlpullparser.getName().equals("query"))
            {
                i = 1;
            }
        } while (true);
        return new PrivateDataIQ(((org.jivesoftware.smackx.iqprivate.packet.PrivateData) (obj)));
    }

    public ()
    {
    }
}
