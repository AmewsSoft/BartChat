// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps;

import java.util.Comparator;
import org.jivesoftware.smackx.xdata.FormField;

// Referenced classes of package org.jivesoftware.smackx.caps:
//            EntityCapsManager

static final class 
    implements Comparator
{

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((FormField)obj, (FormField)obj1);
    }

    public int compare(FormField formfield, FormField formfield1)
    {
        return formfield.getVariable().compareTo(formfield1.getVariable());
    }

    ()
    {
    }
}
