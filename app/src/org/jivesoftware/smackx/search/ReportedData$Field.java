// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.search;

import java.util.Collections;
import java.util.List;

// Referenced classes of package org.jivesoftware.smackx.search:
//            ReportedData

public static class values
{

    private List values;
    private String variable;

    public List getValues()
    {
        return Collections.unmodifiableList(values);
    }

    public String getVariable()
    {
        return variable;
    }

    public (String s, List list)
    {
        variable = s;
        values = list;
    }
}
