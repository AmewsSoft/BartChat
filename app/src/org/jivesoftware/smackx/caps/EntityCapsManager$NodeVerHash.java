// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.caps;


// Referenced classes of package org.jivesoftware.smackx.caps:
//            EntityCapsManager, CapsVersionAndHash

public static class <init>
{

    private String hash;
    private String node;
    private String nodeVer;
    private String ver;

    public String getHash()
    {
        return hash;
    }

    public String getNode()
    {
        return node;
    }

    public String getNodeVer()
    {
        return nodeVer;
    }

    public String getVer()
    {
        return ver;
    }


    (String s, String s1, String s2)
    {
        node = s;
        ver = s1;
        hash = s2;
        nodeVer = (new StringBuilder()).append(s).append("#").append(s1).toString();
    }

    nodeVer(String s, CapsVersionAndHash capsversionandhash)
    {
        this(s, capsversionandhash.version, capsversionandhash.hash);
    }
}
