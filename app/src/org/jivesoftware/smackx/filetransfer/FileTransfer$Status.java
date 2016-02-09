// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;


// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            FileTransfer

public static final class status extends Enum
{

    private static final cancelled $VALUES[];
    public static final cancelled cancelled;
    public static final cancelled complete;
    public static final cancelled error;
    public static final cancelled in_progress;
    public static final cancelled initial;
    public static final cancelled negotiated;
    public static final cancelled negotiating_stream;
    public static final cancelled negotiating_transfer;
    public static final cancelled refused;
    private String status;

    public static status valueOf(String s)
    {
        return (status)Enum.valueOf(org/jivesoftware/smackx/filetransfer/FileTransfer$Status, s);
    }

    public static status[] values()
    {
        return (status[])$VALUES.clone();
    }

    public String toString()
    {
        return status;
    }

    static 
    {
        error = new <init>("error", 0, "Error");
        initial = new <init>("initial", 1, "Initial");
        negotiating_transfer = new <init>("negotiating_transfer", 2, "Negotiating Transfer");
        refused = new <init>("refused", 3, "Refused");
        negotiating_stream = new <init>("negotiating_stream", 4, "Negotiating Stream");
        negotiated = new <init>("negotiated", 5, "Negotiated");
        in_progress = new <init>("in_progress", 6, "In Progress");
        complete = new <init>("complete", 7, "Complete");
        cancelled = new <init>("cancelled", 8, "Cancelled");
        $VALUES = (new .VALUES[] {
            error, initial, negotiating_transfer, refused, negotiating_stream, negotiated, in_progress, complete, cancelled
        });
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        status = s1;
    }
}
