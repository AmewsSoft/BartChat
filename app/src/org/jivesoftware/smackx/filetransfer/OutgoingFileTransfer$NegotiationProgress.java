// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.io.OutputStream;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            OutgoingFileTransfer

public static interface 
{

    public abstract void errorEstablishingStream(Exception exception);

    public abstract void outputStreamEstablished(OutputStream outputstream);

    public abstract void statusUpdated( ,  1);
}
