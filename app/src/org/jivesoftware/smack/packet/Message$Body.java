// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;


// Referenced classes of package org.jivesoftware.smack.packet:
//            Message

public static class <init>
{

    private final String language;
    private final String message;

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            obj = (<init>)obj;
            if (!language.equals(((language) (obj)).language) || !message.equals(((message) (obj)).message))
            {
                return false;
            }
        }
        return true;
    }

    public String getLanguage()
    {
        return language;
    }

    public String getMessage()
    {
        return message;
    }

    public int hashCode()
    {
        return (language.hashCode() + 31) * 31 + message.hashCode();
    }



    private (String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException("Language cannot be null.");
        }
        if (s1 == null)
        {
            throw new NullPointerException("Message cannot be null.");
        } else
        {
            language = s;
            message = s1;
            return;
        }
    }

    message(String s, String s1, message message1)
    {
        this(s, s1);
    }
}
