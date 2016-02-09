// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;


// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            FileTransfer

public static final class msg extends Enum
{

    private static final stream $VALUES[];
    public static final stream bad_file;
    public static final stream connection;
    public static final stream no_response;
    public static final stream none;
    public static final stream not_acceptable;
    public static final stream stream;
    private final String msg;

    public static msg valueOf(String s)
    {
        return (msg)Enum.valueOf(org/jivesoftware/smackx/filetransfer/FileTransfer$Error, s);
    }

    public static msg[] values()
    {
        return (msg[])$VALUES.clone();
    }

    public String getMessage()
    {
        return msg;
    }

    public String toString()
    {
        return msg;
    }

    static 
    {
        none = new <init>("none", 0, "No error");
        not_acceptable = new <init>("not_acceptable", 1, "The peer did not find any of the provided stream mechanisms acceptable.");
        bad_file = new <init>("bad_file", 2, "The provided file to transfer does not exist or could not be read.");
        no_response = new <init>("no_response", 3, "The remote user did not respond or the connection timed out.");
        connection = new <init>("connection", 4, "An error occured over the socket connected to send the file.");
        stream = new <init>("stream", 5, "An error occured while sending or recieving the file.");
        $VALUES = (new .VALUES[] {
            none, not_acceptable, bad_file, no_response, connection, stream
        });
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        msg = s1;
    }
}
