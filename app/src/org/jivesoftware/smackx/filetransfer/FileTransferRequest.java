// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import org.jivesoftware.smackx.si.packet.StreamInitiation;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            FileTransferManager, IncomingFileTransfer

public class FileTransferRequest
{

    private final FileTransferManager manager;
    private final StreamInitiation streamInitiation;

    public FileTransferRequest(FileTransferManager filetransfermanager, StreamInitiation streaminitiation)
    {
        streamInitiation = streaminitiation;
        manager = filetransfermanager;
    }

    public IncomingFileTransfer accept()
    {
        return manager.createIncomingFileTransfer(this);
    }

    public String getDescription()
    {
        return streamInitiation.getFile().getDesc();
    }

    public String getFileName()
    {
        return streamInitiation.getFile().getName();
    }

    public long getFileSize()
    {
        return streamInitiation.getFile().getSize();
    }

    public String getMimeType()
    {
        return streamInitiation.getMimeType();
    }

    public String getRequestor()
    {
        return streamInitiation.getFrom();
    }

    public String getStreamID()
    {
        return streamInitiation.getSessionID();
    }

    protected StreamInitiation getStreamInitiation()
    {
        return streamInitiation;
    }

    public void reject()
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        manager.rejectIncomingFileTransfer(this);
    }
}
