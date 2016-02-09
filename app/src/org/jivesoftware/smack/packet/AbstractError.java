// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.util.PacketUtil;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            ExtensionElement

public class AbstractError
{

    protected final Map descriptiveTexts;
    private final List extensions;
    private final String textNamespace;

    protected AbstractError(Map map)
    {
        this(map, null);
    }

    protected AbstractError(Map map, String s, List list)
    {
        if (map != null)
        {
            descriptiveTexts = map;
        } else
        {
            descriptiveTexts = Collections.emptyMap();
        }
        textNamespace = s;
        if (list != null)
        {
            extensions = list;
            return;
        } else
        {
            extensions = Collections.emptyList();
            return;
        }
    }

    protected AbstractError(Map map, List list)
    {
        this(map, null, list);
    }

    protected void addDescriptiveTextsAndExtensions(XmlStringBuilder xmlstringbuilder)
    {
        for (Iterator iterator = descriptiveTexts.entrySet().iterator(); iterator.hasNext(); xmlstringbuilder.closeElement("text"))
        {
            Object obj = (java.util.Map.Entry)iterator.next();
            String s = (String)((java.util.Map.Entry) (obj)).getKey();
            obj = (String)((java.util.Map.Entry) (obj)).getValue();
            xmlstringbuilder.halfOpenElement("text").xmlnsAttribute(textNamespace).xmllangAttribute(s).rightAngleBracket();
            xmlstringbuilder.escape(((String) (obj)));
        }

        for (Iterator iterator1 = extensions.iterator(); iterator1.hasNext(); xmlstringbuilder.append(((ExtensionElement)iterator1.next()).toXML())) { }
    }

    public String getDescriptiveText()
    {
        String s1 = getDescriptiveText(Locale.getDefault().getLanguage());
        String s = s1;
        if (s1 == null)
        {
            s = getDescriptiveText("");
        }
        return s;
    }

    public String getDescriptiveText(String s)
    {
        return (String)descriptiveTexts.get(s);
    }

    public ExtensionElement getExtension(String s, String s1)
    {
        return PacketUtil.extensionElementFrom(extensions, s, s1);
    }
}
