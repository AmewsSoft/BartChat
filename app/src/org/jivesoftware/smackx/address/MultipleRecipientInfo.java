// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.address;

import java.util.List;
import org.jivesoftware.smackx.address.packet.MultipleAddresses;

public class MultipleRecipientInfo
{

    MultipleAddresses extension;

    MultipleRecipientInfo(MultipleAddresses multipleaddresses)
    {
        extension = multipleaddresses;
    }

    public List getCCAddresses()
    {
        return extension.getAddressesOfType(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.cc);
    }

    public org.jivesoftware.smackx.address.packet.MultipleAddresses.Address getReplyAddress()
    {
        List list = extension.getAddressesOfType(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.replyto);
        if (list.isEmpty())
        {
            return null;
        } else
        {
            return (org.jivesoftware.smackx.address.packet.MultipleAddresses.Address)list.get(0);
        }
    }

    public String getReplyRoom()
    {
        List list = extension.getAddressesOfType(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.replyroom);
        if (list.isEmpty())
        {
            return null;
        } else
        {
            return ((org.jivesoftware.smackx.address.packet.MultipleAddresses.Address)list.get(0)).getJid();
        }
    }

    public List getTOAddresses()
    {
        return extension.getAddressesOfType(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.to);
    }

    public boolean shouldNotReply()
    {
        return !extension.getAddressesOfType(org.jivesoftware.smackx.address.packet.MultipleAddresses.Type.noreply).isEmpty();
    }
}
