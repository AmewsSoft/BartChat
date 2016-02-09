// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Collection;
import java.util.List;
import java.util.Set;

// Referenced classes of package org.jivesoftware.smack.packet:
//            TopLevelStreamElement, ExtensionElement, XMPPError

public interface Packet
    extends TopLevelStreamElement
{

    public static final String ITEM = "item";
    public static final String TEXT = "text";

    public abstract void addExtension(ExtensionElement extensionelement);

    public abstract void addExtensions(Collection collection);

    public abstract XMPPError getError();

    public abstract ExtensionElement getExtension(String s);

    public abstract ExtensionElement getExtension(String s, String s1);

    public abstract List getExtensions();

    public abstract Set getExtensions(String s, String s1);

    public abstract String getFrom();

    public abstract String getLanguage();

    public abstract String getPacketID();

    public abstract String getStanzaId();

    public abstract String getTo();

    public abstract boolean hasExtension(String s);

    public abstract boolean hasExtension(String s, String s1);

    public abstract ExtensionElement removeExtension(String s, String s1);

    public abstract ExtensionElement removeExtension(ExtensionElement extensionelement);

    public abstract void setError(XMPPError xmpperror);

    public abstract void setFrom(String s);

    public abstract void setLanguage(String s);

    public abstract void setPacketID(String s);

    public abstract void setStanzaId(String s);

    public abstract void setTo(String s);

    public abstract String toString();
}
