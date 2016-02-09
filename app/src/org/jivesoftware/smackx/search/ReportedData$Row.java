// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package org.jivesoftware.smackx.search:
//            ReportedData

public static class fields
{

    private List fields;

    private List getFields()
    {
        return Collections.unmodifiableList(new ArrayList(fields));
    }

    public List getValues(String s)
    {
        for (Iterator iterator = getFields().iterator(); iterator.hasNext();)
        {
            d d = (d)iterator.next();
            if (s.equalsIgnoreCase(d.getVariable()))
            {
                return d.getValues();
            }
        }

        return null;
    }

    public d(List list)
    {
        fields = new ArrayList();
        fields = list;
    }
}
