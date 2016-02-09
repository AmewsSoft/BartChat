// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.packet;

import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smackx.muc.packet:
//            MUCUser

public static class code
    implements NamedElement
{

    public static final EmptyElement BANNED_301 = create(Integer.valueOf(301));
    public static final String ELEMENT = "status";
    public static final create KICKED_307 = create(Integer.valueOf(307));
    public static final create NEW_NICKNAME_303 = create(Integer.valueOf(303));
    public static final create REMOVED_AFFIL_CHANGE_321 = create(Integer.valueOf(321));
    public static final create ROOM_CREATED_201 = create(Integer.valueOf(201));
    private static final Map statusMap = new HashMap(8);
    private final Integer code;

    public static code create(Integer integer)
    {
        code code2 = (code)statusMap.get(integer);
        code code1 = code2;
        if (code2 == null)
        {
            code1 = new <init>(integer.intValue());
            statusMap.put(integer, code1);
        }
        return code1;
    }

    public static statusMap create(String s)
    {
        return create(Integer.valueOf(s));
    }

    public boolean equals(Object obj)
    {
        while (obj == null || !(obj instanceof create)) 
        {
            return false;
        }
        obj = (create)obj;
        return code.equals(Integer.valueOf(((code) (obj)).getCode()));
    }

    public int getCode()
    {
        return code.intValue();
    }

    public String getElementName()
    {
        return "status";
    }

    public int hashCode()
    {
        return code.intValue();
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder(this);
        xmlstringbuilder.attribute("code", getCode());
        xmlstringbuilder.closeEmptyElement();
        return xmlstringbuilder;
    }


    private (int i)
    {
        code = Integer.valueOf(i);
    }
}
