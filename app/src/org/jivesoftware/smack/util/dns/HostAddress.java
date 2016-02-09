// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util.dns;

import org.jivesoftware.smack.util.Objects;

public class HostAddress
{

    private Exception exception;
    private final String fqdn;
    private final int port;

    public HostAddress(String s)
    {
        this(s, 5222);
    }

    public HostAddress(String s, int i)
    {
        Objects.requireNonNull(s, "FQDN is null");
        if (i < 0 || i > 65535)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Port must be a 16-bit unsiged integer (i.e. between 0-65535. Port was: ").append(i).toString());
        }
        if (s.charAt(s.length() - 1) == '.')
        {
            fqdn = s.substring(0, s.length() - 1);
        } else
        {
            fqdn = s;
        }
        port = i;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof HostAddress))
            {
                return false;
            }
            obj = (HostAddress)obj;
            if (!fqdn.equals(((HostAddress) (obj)).fqdn))
            {
                return false;
            }
            if (port != ((HostAddress) (obj)).port)
            {
                return false;
            }
        }
        return true;
    }

    public String getErrorMessage()
    {
        if (exception == null)
        {
            return "No error logged";
        } else
        {
            return (new StringBuilder()).append("'").append(toString()).append("' failed because ").append(exception.toString()).toString();
        }
    }

    public Exception getException()
    {
        return exception;
    }

    public String getFQDN()
    {
        return fqdn;
    }

    public int getPort()
    {
        return port;
    }

    public int hashCode()
    {
        return (fqdn.hashCode() + 37) * 37 + port;
    }

    public void setException(Exception exception1)
    {
        exception = exception1;
    }

    public String toString()
    {
        return (new StringBuilder()).append(fqdn).append(":").append(port).toString();
    }
}
