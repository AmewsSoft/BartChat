// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.provider;

import java.util.Collection;

public interface ProviderLoader
{

    public abstract Collection getExtensionProviderInfo();

    public abstract Collection getIQProviderInfo();

    public abstract Collection getStreamFeatureProviderInfo();
}
