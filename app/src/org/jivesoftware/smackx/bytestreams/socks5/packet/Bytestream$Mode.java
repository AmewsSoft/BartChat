// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5.packet;


// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5.packet:
//            Bytestream

public static final class  extends Enum
{

    private static final udp $VALUES[];
    public static final udp tcp;
    public static final udp udp;

    public static  fromName(String s)
    {
        try
        {
            s = valueOf(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return tcp;
        }
        return s;
    }

    public static tcp valueOf(String s)
    {
        return (tcp)Enum.valueOf(org/jivesoftware/smackx/bytestreams/socks5/packet/Bytestream$Mode, s);
    }

    public static tcp[] values()
    {
        return (tcp[])$VALUES.clone();
    }

    static 
    {
        tcp = new <init>("tcp", 0);
        udp = new <init>("udp", 1);
        $VALUES = (new .VALUES[] {
            tcp, udp
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
