// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;


// Referenced classes of package org.jivesoftware.smackx.commands:
//            LocalCommandFactory, AdHocCommandManager, LocalCommand

class val.clazz
    implements LocalCommandFactory
{

    final AdHocCommandManager this$0;
    final Class val$clazz;

    public LocalCommand getInstance()
        throws InstantiationException, IllegalAccessException
    {
        return (LocalCommand)val$clazz.newInstance();
    }

    ()
    {
        this$0 = final_adhoccommandmanager;
        val$clazz = Class.this;
        super();
    }
}
