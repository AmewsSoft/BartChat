// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.initializer;

import java.util.Collections;
import java.util.List;

// Referenced classes of package org.jivesoftware.smack.initializer:
//            UrlInitializer

public class VmArgInitializer extends UrlInitializer
{

    public VmArgInitializer()
    {
    }

    protected String getFilePath()
    {
        return System.getProperty("smack.provider.file");
    }

    public List initialize()
    {
        if (getFilePath() != null)
        {
            super.initialize();
        }
        return Collections.emptyList();
    }
}
