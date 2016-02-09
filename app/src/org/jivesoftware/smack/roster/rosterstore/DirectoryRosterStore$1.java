// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.roster.rosterstore;

import java.io.File;
import java.io.FileFilter;

// Referenced classes of package org.jivesoftware.smack.roster.rosterstore:
//            DirectoryRosterStore

static final class 
    implements FileFilter
{

    public boolean accept(File file)
    {
        return file.getName().startsWith("entry-");
    }

    ()
    {
    }
}
