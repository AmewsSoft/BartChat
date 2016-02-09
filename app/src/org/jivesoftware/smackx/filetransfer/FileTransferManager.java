// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.si.packet.StreamInitiation;
import org.jxmpp.util.XmppStringUtils;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            FileTransferNegotiator, IncomingFileTransfer, FileTransferRequest, OutgoingFileTransfer, 
//            FileTransferListener

public class FileTransferManager extends Manager
{

    private static final Map INSTANCES = new WeakHashMap();
    private final FileTransferNegotiator fileTransferNegotiator;
    private final List listeners = new CopyOnWriteArrayList();

    private FileTransferManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        fileTransferNegotiator = FileTransferNegotiator.getInstanceFor(xmppconnection);
        xmppconnection.registerIQRequestHandler(new AbstractIqRequestHandler("si", "http://jabber.org/protocol/si", org.jivesoftware.smack.packet.IQ.Type.set, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async) {

            final FileTransferManager this$0;

            public IQ handleIQRequest(IQ iq)
            {
                iq = (StreamInitiation)iq;
                iq = new FileTransferRequest(FileTransferManager.this, iq);
                for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((FileTransferListener)iterator.next()).fileTransferRequest(iq)) { }
                return null;
            }

            
            {
                this$0 = FileTransferManager.this;
                super(s, s1, type, mode);
            }
        });
    }

    public static FileTransferManager getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/filetransfer/FileTransferManager;
        JVM INSTR monitorenter ;
        FileTransferManager filetransfermanager1 = (FileTransferManager)INSTANCES.get(xmppconnection);
        FileTransferManager filetransfermanager;
        filetransfermanager = filetransfermanager1;
        if (filetransfermanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        filetransfermanager = new FileTransferManager(xmppconnection);
        INSTANCES.put(xmppconnection, filetransfermanager);
        org/jivesoftware/smackx/filetransfer/FileTransferManager;
        JVM INSTR monitorexit ;
        return filetransfermanager;
        xmppconnection;
        throw xmppconnection;
    }

    public void addFileTransferListener(FileTransferListener filetransferlistener)
    {
        listeners.add(filetransferlistener);
    }

    protected IncomingFileTransfer createIncomingFileTransfer(FileTransferRequest filetransferrequest)
    {
        if (filetransferrequest == null)
        {
            throw new NullPointerException("RecieveRequest cannot be null");
        } else
        {
            IncomingFileTransfer incomingfiletransfer = new IncomingFileTransfer(filetransferrequest, fileTransferNegotiator);
            incomingfiletransfer.setFileInfo(filetransferrequest.getFileName(), filetransferrequest.getFileSize());
            return incomingfiletransfer;
        }
    }

    public OutgoingFileTransfer createOutgoingFileTransfer(String s)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("userID was null");
        }
        if (!XmppStringUtils.isFullJID(s))
        {
            throw new IllegalArgumentException("The provided user id was not a full JID (i.e. with resource part)");
        } else
        {
            return new OutgoingFileTransfer(connection().getUser(), s, fileTransferNegotiator.getNextStreamID(), fileTransferNegotiator);
        }
    }

    protected void rejectIncomingFileTransfer(FileTransferRequest filetransferrequest)
        throws org.jivesoftware.smack.SmackException.NotConnectedException
    {
        filetransferrequest = IQ.createErrorResponse(filetransferrequest.getStreamInitiation(), new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.forbidden));
        connection().sendStanza(filetransferrequest);
    }

    public void removeFileTransferListener(FileTransferListener filetransferlistener)
    {
        listeners.remove(filetransferlistener);
    }


}
