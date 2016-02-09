// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.filetransfer;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.si.packet.StreamInitiation;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;

// Referenced classes of package org.jivesoftware.smackx.filetransfer:
//            Socks5TransferNegotiator, IBBTransferNegotiator, FaultTolerantNegotiator, FileTransferRequest, 
//            StreamNegotiator

public class FileTransferNegotiator extends Manager
{

    public static boolean IBB_ONLY = false;
    private static final Map INSTANCES = new WeakHashMap();
    private static final String NAMESPACE[] = {
        "http://jabber.org/protocol/si", "http://jabber.org/protocol/si/profile/file-transfer"
    };
    public static final String SI_NAMESPACE = "http://jabber.org/protocol/si";
    public static final String SI_PROFILE_FILE_TRANSFER_NAMESPACE = "http://jabber.org/protocol/si/profile/file-transfer";
    protected static final String STREAM_DATA_FIELD_NAME = "stream-method";
    private static final String STREAM_INIT_PREFIX = "jsi_";
    private static final Random randomGenerator = new Random();
    private final StreamNegotiator byteStreamTransferManager;
    private final StreamNegotiator inbandTransferManager;

    private FileTransferNegotiator(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        byteStreamTransferManager = new Socks5TransferNegotiator(xmppconnection);
        inbandTransferManager = new IBBTransferNegotiator(xmppconnection);
        setServiceEnabled(xmppconnection, true);
    }

    private DataForm createDefaultInitiationForm()
    {
        DataForm dataform = new DataForm(org.jivesoftware.smackx.xdata.packet.DataForm.Type.form);
        FormField formfield = new FormField("stream-method");
        formfield.setType(org.jivesoftware.smackx.xdata.FormField.Type.list_single);
        if (!IBB_ONLY)
        {
            formfield.addOption(new org.jivesoftware.smackx.xdata.FormField.Option("http://jabber.org/protocol/bytestreams"));
        }
        formfield.addOption(new org.jivesoftware.smackx.xdata.FormField.Option("http://jabber.org/protocol/ibb"));
        dataform.addField(formfield);
        return dataform;
    }

    public static FileTransferNegotiator getInstanceFor(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/filetransfer/FileTransferNegotiator;
        JVM INSTR monitorenter ;
        FileTransferNegotiator filetransfernegotiator1 = (FileTransferNegotiator)INSTANCES.get(xmppconnection);
        FileTransferNegotiator filetransfernegotiator;
        filetransfernegotiator = filetransfernegotiator1;
        if (filetransfernegotiator1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        filetransfernegotiator = new FileTransferNegotiator(xmppconnection);
        INSTANCES.put(xmppconnection, filetransfernegotiator);
        org/jivesoftware/smackx/filetransfer/FileTransferNegotiator;
        JVM INSTR monitorexit ;
        return filetransfernegotiator;
        xmppconnection;
        throw xmppconnection;
    }

    private StreamNegotiator getNegotiator(FormField formfield)
        throws FileTransferException.NoAcceptableTransferMechanisms
    {
        boolean flag1 = false;
        boolean flag = false;
        formfield = formfield.getOptions().iterator();
        do
        {
            if (!formfield.hasNext())
            {
                break;
            }
            String s = ((org.jivesoftware.smackx.xdata.FormField.Option)formfield.next()).getValue();
            if (s.equals("http://jabber.org/protocol/bytestreams") && !IBB_ONLY)
            {
                flag1 = true;
            } else
            if (s.equals("http://jabber.org/protocol/ibb"))
            {
                flag = true;
            }
        } while (true);
        if (!flag1 && !flag)
        {
            throw new FileTransferException.NoAcceptableTransferMechanisms();
        }
        if (flag1 && flag)
        {
            return new FaultTolerantNegotiator(connection(), byteStreamTransferManager, inbandTransferManager);
        }
        if (flag1)
        {
            return byteStreamTransferManager;
        } else
        {
            return inbandTransferManager;
        }
    }

    private StreamNegotiator getOutgoingNegotiator(FormField formfield)
        throws FileTransferException.NoAcceptableTransferMechanisms
    {
        boolean flag1 = false;
        boolean flag = false;
        formfield = formfield.getValues().iterator();
        do
        {
            if (!formfield.hasNext())
            {
                break;
            }
            String s = (String)formfield.next();
            if (s.equals("http://jabber.org/protocol/bytestreams") && !IBB_ONLY)
            {
                flag1 = true;
            } else
            if (s.equals("http://jabber.org/protocol/ibb"))
            {
                flag = true;
            }
        } while (true);
        if (!flag1 && !flag)
        {
            throw new FileTransferException.NoAcceptableTransferMechanisms();
        }
        if (flag1 && flag)
        {
            return new FaultTolerantNegotiator(connection(), byteStreamTransferManager, inbandTransferManager);
        }
        if (flag1)
        {
            return byteStreamTransferManager;
        } else
        {
            return inbandTransferManager;
        }
    }

    private FormField getStreamMethodField(DataForm dataform)
    {
        for (dataform = dataform.getFields().iterator(); dataform.hasNext();)
        {
            FormField formfield = (FormField)dataform.next();
            if (formfield.getVariable().equals("stream-method"))
            {
                return formfield;
            }
        }

        return null;
    }

    public static Collection getSupportedProtocols()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add("http://jabber.org/protocol/ibb");
        if (!IBB_ONLY)
        {
            arraylist.add("http://jabber.org/protocol/bytestreams");
        }
        return Collections.unmodifiableList(arraylist);
    }

    public static boolean isServiceEnabled(XMPPConnection xmppconnection)
    {
        xmppconnection = ServiceDiscoveryManager.getInstanceFor(xmppconnection);
        Object obj = new ArrayList();
        ((List) (obj)).addAll(Arrays.asList(NAMESPACE));
        ((List) (obj)).add("http://jabber.org/protocol/ibb");
        if (!IBB_ONLY)
        {
            ((List) (obj)).add("http://jabber.org/protocol/bytestreams");
        }
        for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            if (!xmppconnection.includesFeature((String)((Iterator) (obj)).next()))
            {
                return false;
            }
        }

        return true;
    }

    private static void setServiceEnabled(XMPPConnection xmppconnection, boolean flag)
    {
        xmppconnection = ServiceDiscoveryManager.getInstanceFor(xmppconnection);
        Object obj = new ArrayList();
        ((List) (obj)).addAll(Arrays.asList(NAMESPACE));
        ((List) (obj)).add("http://jabber.org/protocol/ibb");
        if (!IBB_ONLY)
        {
            ((List) (obj)).add("http://jabber.org/protocol/bytestreams");
        }
        for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
        {
            String s = (String)((Iterator) (obj)).next();
            if (flag)
            {
                xmppconnection.addFeature(s);
            } else
            {
                xmppconnection.removeFeature(s);
            }
        }

    }

    public String getNextStreamID()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("jsi_");
        stringbuilder.append(Math.abs(randomGenerator.nextLong()));
        return stringbuilder.toString();
    }

    public StreamNegotiator negotiateOutgoingTransfer(String s, String s1, String s2, long l, String s3, int i)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.SmackException.NoResponseException, FileTransferException.NoAcceptableTransferMechanisms
    {
        StreamInitiation streaminitiation = new StreamInitiation();
        streaminitiation.setSessionID(s1);
        streaminitiation.setMimeType(URLConnection.guessContentTypeFromName(s2));
        s1 = new org.jivesoftware.smackx.si.packet.StreamInitiation.File(s2, l);
        s1.setDesc(s3);
        streaminitiation.setFile(s1);
        streaminitiation.setFeatureNegotiationForm(createDefaultInitiationForm());
        streaminitiation.setFrom(connection().getUser());
        streaminitiation.setTo(s);
        streaminitiation.setType(org.jivesoftware.smack.packet.IQ.Type.set);
        s = connection().createPacketCollectorAndSend(streaminitiation).nextResultOrThrow(i);
        if (s instanceof IQ)
        {
            s1 = (IQ)s;
            if (s1.getType().equals(org.jivesoftware.smack.packet.IQ.Type.result))
            {
                return getOutgoingNegotiator(getStreamMethodField(((StreamInitiation)s).getFeatureNegotiationForm()));
            } else
            {
                throw new org.jivesoftware.smack.XMPPException.XMPPErrorException(s1.getError());
            }
        } else
        {
            return null;
        }
    }

    public StreamNegotiator selectStreamNegotiator(FileTransferRequest filetransferrequest)
        throws org.jivesoftware.smack.SmackException.NotConnectedException, FileTransferException.NoStreamMethodsOfferedException, FileTransferException.NoAcceptableTransferMechanisms
    {
        filetransferrequest = filetransferrequest.getStreamInitiation();
        Object obj = getStreamMethodField(filetransferrequest.getFeatureNegotiationForm());
        if (obj == null)
        {
            filetransferrequest = IQ.createErrorResponse(filetransferrequest, XMPPError.from(org.jivesoftware.smack.packet.XMPPError.Condition.bad_request, "No stream methods contained in stanza."));
            connection().sendStanza(filetransferrequest);
            throw new FileTransferException.NoStreamMethodsOfferedException();
        }
        try
        {
            obj = getNegotiator(((FormField) (obj)));
        }
        catch (FileTransferException.NoAcceptableTransferMechanisms noacceptabletransfermechanisms)
        {
            filetransferrequest = IQ.createErrorResponse(filetransferrequest, XMPPError.from(org.jivesoftware.smack.packet.XMPPError.Condition.bad_request, "No acceptable transfer mechanism"));
            connection().sendStanza(filetransferrequest);
            throw noacceptabletransfermechanisms;
        }
        return ((StreamNegotiator) (obj));
    }

    static 
    {
        boolean flag = true;
        if (System.getProperty("ibb") == null)
        {
            flag = false;
        }
        IBB_ONLY = flag;
    }
}
