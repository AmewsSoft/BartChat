// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub.listener;

import org.jivesoftware.smackx.pubsub.ConfigurationEvent;

public interface NodeConfigListener
{

    public abstract void handleNodeConfiguration(ConfigurationEvent configurationevent);
}