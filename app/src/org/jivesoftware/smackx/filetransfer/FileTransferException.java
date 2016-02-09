// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import org.jivesoftware.smack.SmackException;

public abstract class FileTransferException extends SmackException
{
    public static class NoAcceptableTransferMechanisms extends FileTransferException
    {

        private static final long serialVersionUID = 1L;

        public NoAcceptableTransferMechanisms()
        {
        }
    }

    public static class NoStreamMethodsOfferedException extends FileTransferException
    {

        private static final long serialVersionUID = 1L;

        public NoStreamMethodsOfferedException()
        {
        }
    }


    private static final long serialVersionUID = 1L;

    public FileTransferException()
    {
    }
}
