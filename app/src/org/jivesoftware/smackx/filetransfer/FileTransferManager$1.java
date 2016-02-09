// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.si.packet.StreamInitiation;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            FileTransferManager, FileTransferRequest, FileTransferListener

class init> extends AbstractIqRequestHandler
{

    final FileTransferManager this$0;

    public IQ handleIQRequest(IQ iq)
    {
        iq = (StreamInitiation)iq;
        iq = new FileTransferRequest(FileTransferManager.this, iq);
        for (Iterator iterator = FileTransferManager.access$000(FileTransferManager.this).iterator(); iterator.hasNext(); ((FileTransferListener)iterator.next()).fileTransferRequest(iq)) { }
        return null;
    }

    (String s, String s1, org.jivesoftware.smack.packet.ager ager, org.jivesoftware.smack.iqrequest.r r)
    {
        this$0 = FileTransferManager.this;
        super(s, s1, ager, r);
    }
}
