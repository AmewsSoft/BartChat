// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.muc.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;

// Referenced classes of package org.jivesoftware.smackx.muc.packet:
//            MUCItem

public class MUCAdmin extends IQ
{

    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "http://jabber.org/protocol/muc#admin";
    private final List items = new ArrayList();

    public MUCAdmin()
    {
        super("query", "http://jabber.org/protocol/muc#admin");
    }

    public void addItem(MUCItem mucitem)
    {
        synchronized (items)
        {
            items.add(mucitem);
        }
        return;
        mucitem;
        list;
        JVM INSTR monitorexit ;
        throw mucitem;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        List list = items;
        list;
        JVM INSTR monitorenter ;
        for (Iterator iterator = items.iterator(); iterator.hasNext(); iqchildelementxmlstringbuilder.append(((MUCItem)iterator.next()).toXML())) { }
        break MISSING_BLOCK_LABEL_56;
        iqchildelementxmlstringbuilder;
        list;
        JVM INSTR monitorexit ;
        throw iqchildelementxmlstringbuilder;
        list;
        JVM INSTR monitorexit ;
        return iqchildelementxmlstringbuilder;
    }

    public List getItems()
    {
        List list1;
        synchronized (items)
        {
            list1 = Collections.unmodifiableList(new ArrayList(items));
        }
        return list1;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
