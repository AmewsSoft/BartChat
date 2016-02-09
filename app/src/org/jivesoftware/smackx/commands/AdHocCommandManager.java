// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.disco.AbstractNodeInformationProvider;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.xdata.Form;

// Referenced classes of package org.jivesoftware.smackx.commands:
//            LocalCommand, RemoteCommand, LocalCommandFactory

public class AdHocCommandManager extends Manager
{
    private static class AdHocCommandInfo
    {

        private LocalCommandFactory factory;
        private String name;
        private String node;
        private String ownerJID;

        public LocalCommand getCommandInstance()
            throws InstantiationException, IllegalAccessException
        {
            return factory.getInstance();
        }

        public String getName()
        {
            return name;
        }

        public String getNode()
        {
            return node;
        }

        public String getOwnerJID()
        {
            return ownerJID;
        }

        public AdHocCommandInfo(String s, String s1, String s2, LocalCommandFactory localcommandfactory)
        {
            node = s;
            name = s1;
            ownerJID = s2;
            factory = localcommandfactory;
        }
    }


    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/commands/AdHocCommandManager.getName());
    public static final String NAMESPACE = "http://jabber.org/protocol/commands";
    private static final int SESSION_TIMEOUT = 120;
    private static Map instances = new WeakHashMap();
    private final Map commands = new ConcurrentHashMap();
    private final Map executingCommands = new ConcurrentHashMap();
    private final ServiceDiscoveryManager serviceDiscoveryManager;
    private Thread sessionsSweeper;

    private AdHocCommandManager(XMPPConnection xmppconnection)
    {
        super(xmppconnection);
        serviceDiscoveryManager = ServiceDiscoveryManager.getInstanceFor(xmppconnection);
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).addFeature("http://jabber.org/protocol/commands");
        ServiceDiscoveryManager.getInstanceFor(xmppconnection).setNodeInformationProvider("http://jabber.org/protocol/commands", new AbstractNodeInformationProvider() {

            final AdHocCommandManager this$0;

            public List getNodeItems()
            {
                ArrayList arraylist = new ArrayList();
                org.jivesoftware.smackx.disco.packet.DiscoverItems.Item item;
                for (Iterator iterator = getRegisteredCommands().iterator(); iterator.hasNext(); arraylist.add(item))
                {
                    AdHocCommandInfo adhoccommandinfo = (AdHocCommandInfo)iterator.next();
                    item = new org.jivesoftware.smackx.disco.packet.DiscoverItems.Item(adhoccommandinfo.getOwnerJID());
                    item.setName(adhoccommandinfo.getName());
                    item.setNode(adhoccommandinfo.getNode());
                }

                return arraylist;
            }

            
            {
                this$0 = AdHocCommandManager.this;
                super();
            }
        });
        xmppconnection.registerIQRequestHandler(new AbstractIqRequestHandler("command", "http://jabber.org/protocol/commands", org.jivesoftware.smack.packet.IQ.Type.set, org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode.async) {

            final AdHocCommandManager this$0;

            public IQ handleIQRequest(IQ iq)
            {
                iq = (AdHocCommandData)iq;
                iq = processAdHocCommand(iq);
                return iq;
                iq;
_L2:
                AdHocCommandManager.LOGGER.log(Level.INFO, "processAdHocCommand threw exceptino", iq);
                return null;
                iq;
                if (true) goto _L2; else goto _L1
_L1:
            }

            
            {
                this$0 = AdHocCommandManager.this;
                super(s, s1, type, mode);
            }
        });
        sessionsSweeper = null;
    }

    public static AdHocCommandManager getAddHocCommandsManager(XMPPConnection xmppconnection)
    {
        org/jivesoftware/smackx/commands/AdHocCommandManager;
        JVM INSTR monitorenter ;
        AdHocCommandManager adhoccommandmanager1 = (AdHocCommandManager)instances.get(xmppconnection);
        AdHocCommandManager adhoccommandmanager;
        adhoccommandmanager = adhoccommandmanager1;
        if (adhoccommandmanager1 != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        adhoccommandmanager = new AdHocCommandManager(xmppconnection);
        instances.put(xmppconnection, adhoccommandmanager);
        org/jivesoftware/smackx/commands/AdHocCommandManager;
        JVM INSTR monitorexit ;
        return adhoccommandmanager;
        xmppconnection;
        throw xmppconnection;
    }

    private Collection getRegisteredCommands()
    {
        return commands.values();
    }

    private LocalCommand newInstanceOfCmd(String s, String s1)
        throws org.jivesoftware.smack.XMPPException.XMPPErrorException
    {
        s = (AdHocCommandInfo)commands.get(s);
        LocalCommand localcommand;
        try
        {
            localcommand = s.getCommandInstance();
            localcommand.setSessionID(s1);
            localcommand.setName(s.getName());
            localcommand.setNode(s.getNode());
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new org.jivesoftware.smack.XMPPException.XMPPErrorException(new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.internal_server_error));
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw new org.jivesoftware.smack.XMPPException.XMPPErrorException(new XMPPError(org.jivesoftware.smack.packet.XMPPError.Condition.internal_server_error));
        }
        return localcommand;
    }

    private IQ processAdHocCommand(AdHocCommandData adhoccommanddata)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        AdHocCommandData adhoccommanddata1;
        Object obj1;
        String s;
        adhoccommanddata1 = new AdHocCommandData();
        adhoccommanddata1.setTo(adhoccommanddata.getFrom());
        adhoccommanddata1.setStanzaId(adhoccommanddata.getStanzaId());
        adhoccommanddata1.setNode(adhoccommanddata.getNode());
        adhoccommanddata1.setId(adhoccommanddata.getTo());
        s = adhoccommanddata.getSessionID();
        obj1 = adhoccommanddata.getNode();
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_321;
        }
        if (commands.containsKey(obj1)) goto _L2; else goto _L1
_L1:
        adhoccommanddata = respondError(adhoccommanddata1, org.jivesoftware.smack.packet.XMPPError.Condition.item_not_found);
_L4:
        return adhoccommanddata;
_L2:
        Object obj;
        obj = StringUtils.randomString(15);
        try
        {
            obj1 = newInstanceOfCmd(((String) (obj1)), ((String) (obj)));
            adhoccommanddata1.setType(org.jivesoftware.smack.packet.IQ.Type.result);
            ((LocalCommand) (obj1)).setData(adhoccommanddata1);
            if (!((LocalCommand) (obj1)).hasPermission(adhoccommanddata.getFrom()))
            {
                return respondError(adhoccommanddata1, org.jivesoftware.smack.packet.XMPPError.Condition.forbidden);
            }
            adhoccommanddata = adhoccommanddata.getAction();
        }
        // Misplaced declaration of an exception variable
        catch (AdHocCommandData adhoccommanddata)
        {
            adhoccommanddata = adhoccommanddata.getXMPPError();
            if (org.jivesoftware.smack.packet.XMPPError.Type.CANCEL.equals(adhoccommanddata.getType()))
            {
                adhoccommanddata1.setStatus(AdHocCommand.Status.canceled);
                executingCommands.remove(obj);
            }
            return respondError(adhoccommanddata1, adhoccommanddata);
        }
        if (adhoccommanddata == null)
        {
            break MISSING_BLOCK_LABEL_161;
        }
        if (adhoccommanddata.equals(AdHocCommand.Action.unknown))
        {
            return respondError(adhoccommanddata1, org.jivesoftware.smack.packet.XMPPError.Condition.bad_request, AdHocCommand.SpecificErrorCondition.malformedAction);
        }
        if (adhoccommanddata == null)
        {
            break MISSING_BLOCK_LABEL_186;
        }
        if (!adhoccommanddata.equals(AdHocCommand.Action.execute))
        {
            return respondError(adhoccommanddata1, org.jivesoftware.smack.packet.XMPPError.Condition.bad_request, AdHocCommand.SpecificErrorCondition.badAction);
        }
        ((LocalCommand) (obj1)).incrementStage();
        ((LocalCommand) (obj1)).execute();
        if (!((LocalCommand) (obj1)).isLastStage())
        {
            break MISSING_BLOCK_LABEL_256;
        }
        adhoccommanddata1.setStatus(AdHocCommand.Status.completed);
        return adhoccommanddata1;
        adhoccommanddata1.setStatus(AdHocCommand.Status.executing);
        executingCommands.put(obj, obj1);
        adhoccommanddata = adhoccommanddata1;
        if (sessionsSweeper != null) goto _L4; else goto _L3
_L3:
        sessionsSweeper = new Thread(new Runnable() {

            final AdHocCommandManager this$0;

            public void run()
            {
                do
                {
                    Iterator iterator = executingCommands.keySet().iterator();
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            break;
                        }
                        String s1 = (String)iterator.next();
                        LocalCommand localcommand1 = (LocalCommand)executingCommands.get(s1);
                        if (localcommand1 != null)
                        {
                            long l1 = localcommand1.getCreationDate();
                            if (System.currentTimeMillis() - l1 > 0x3a980L)
                            {
                                executingCommands.remove(s1);
                            }
                        }
                    } while (true);
                    try
                    {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException interruptedexception) { }
                } while (true);
            }

            
            {
                this$0 = AdHocCommandManager.this;
                super();
            }
        });
        sessionsSweeper.setDaemon(true);
        sessionsSweeper.start();
        return adhoccommanddata1;
        LocalCommand localcommand;
        localcommand = (LocalCommand)executingCommands.get(s);
        if (localcommand == null)
        {
            return respondError(adhoccommanddata1, org.jivesoftware.smack.packet.XMPPError.Condition.bad_request, AdHocCommand.SpecificErrorCondition.badSessionid);
        }
        long l = localcommand.getCreationDate();
        if (System.currentTimeMillis() - l > 0x1d4c0L)
        {
            executingCommands.remove(s);
            return respondError(adhoccommanddata1, org.jivesoftware.smack.packet.XMPPError.Condition.not_allowed, AdHocCommand.SpecificErrorCondition.sessionExpired);
        }
        localcommand;
        JVM INSTR monitorenter ;
        obj1 = adhoccommanddata.getAction();
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_443;
        }
        if (!((AdHocCommand.Action) (obj1)).equals(AdHocCommand.Action.unknown))
        {
            break MISSING_BLOCK_LABEL_443;
        }
        adhoccommanddata = respondError(adhoccommanddata1, org.jivesoftware.smack.packet.XMPPError.Condition.bad_request, AdHocCommand.SpecificErrorCondition.malformedAction);
        localcommand;
        JVM INSTR monitorexit ;
        return adhoccommanddata;
        adhoccommanddata;
        localcommand;
        JVM INSTR monitorexit ;
        throw adhoccommanddata;
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_462;
        }
        obj = obj1;
        if (!AdHocCommand.Action.execute.equals(obj1))
        {
            break MISSING_BLOCK_LABEL_468;
        }
        obj = localcommand.getExecuteAction();
        if (localcommand.isValidAction(((AdHocCommand.Action) (obj))))
        {
            break MISSING_BLOCK_LABEL_493;
        }
        adhoccommanddata = respondError(adhoccommanddata1, org.jivesoftware.smack.packet.XMPPError.Condition.bad_request, AdHocCommand.SpecificErrorCondition.badAction);
        localcommand;
        JVM INSTR monitorexit ;
        return adhoccommanddata;
        adhoccommanddata1.setType(org.jivesoftware.smack.packet.IQ.Type.result);
        localcommand.setData(adhoccommanddata1);
        if (!AdHocCommand.Action.next.equals(obj)) goto _L6; else goto _L5
_L5:
        localcommand.incrementStage();
        localcommand.next(new Form(adhoccommanddata.getForm()));
        if (!localcommand.isLastStage()) goto _L8; else goto _L7
_L7:
        adhoccommanddata1.setStatus(AdHocCommand.Status.completed);
_L9:
        localcommand;
        JVM INSTR monitorexit ;
        return adhoccommanddata1;
_L8:
        adhoccommanddata1.setStatus(AdHocCommand.Status.executing);
          goto _L9
        adhoccommanddata;
        adhoccommanddata = adhoccommanddata.getXMPPError();
        if (org.jivesoftware.smack.packet.XMPPError.Type.CANCEL.equals(adhoccommanddata.getType()))
        {
            adhoccommanddata1.setStatus(AdHocCommand.Status.canceled);
            executingCommands.remove(s);
        }
        adhoccommanddata = respondError(adhoccommanddata1, adhoccommanddata);
        localcommand;
        JVM INSTR monitorexit ;
        return adhoccommanddata;
_L6:
label0:
        {
            if (!AdHocCommand.Action.complete.equals(obj))
            {
                break label0;
            }
            localcommand.incrementStage();
            localcommand.complete(new Form(adhoccommanddata.getForm()));
            adhoccommanddata1.setStatus(AdHocCommand.Status.completed);
            executingCommands.remove(s);
        }
          goto _L9
        if (!AdHocCommand.Action.prev.equals(obj)) goto _L11; else goto _L10
_L10:
        localcommand.decrementStage();
        localcommand.prev();
          goto _L9
_L11:
        if (!AdHocCommand.Action.cancel.equals(obj)) goto _L9; else goto _L12
_L12:
        localcommand.cancel();
        adhoccommanddata1.setStatus(AdHocCommand.Status.canceled);
        executingCommands.remove(s);
          goto _L9
    }

    private IQ respondError(AdHocCommandData adhoccommanddata, org.jivesoftware.smack.packet.XMPPError.Condition condition)
    {
        return respondError(adhoccommanddata, new XMPPError(condition));
    }

    private static IQ respondError(AdHocCommandData adhoccommanddata, org.jivesoftware.smack.packet.XMPPError.Condition condition, AdHocCommand.SpecificErrorCondition specificerrorcondition)
    {
        return respondError(adhoccommanddata, new XMPPError(condition, new org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError(specificerrorcondition)));
    }

    private static IQ respondError(AdHocCommandData adhoccommanddata, XMPPError xmpperror)
    {
        adhoccommanddata.setType(org.jivesoftware.smack.packet.IQ.Type.error);
        adhoccommanddata.setError(xmpperror);
        return adhoccommanddata;
    }

    public DiscoverItems discoverCommands(String s)
        throws XMPPException, SmackException
    {
        return serviceDiscoveryManager.discoverItems(s, "http://jabber.org/protocol/commands");
    }

    public RemoteCommand getRemoteCommand(String s, String s1)
    {
        return new RemoteCommand(connection(), s1, s);
    }

    public void publishCommands(String s)
        throws XMPPException, SmackException
    {
        DiscoverItems discoveritems = new DiscoverItems();
        org.jivesoftware.smackx.disco.packet.DiscoverItems.Item item;
        for (Iterator iterator = getRegisteredCommands().iterator(); iterator.hasNext(); discoveritems.addItem(item))
        {
            AdHocCommandInfo adhoccommandinfo = (AdHocCommandInfo)iterator.next();
            item = new org.jivesoftware.smackx.disco.packet.DiscoverItems.Item(adhoccommandinfo.getOwnerJID());
            item.setName(adhoccommandinfo.getName());
            item.setNode(adhoccommandinfo.getNode());
        }

        serviceDiscoveryManager.publishItems(s, "http://jabber.org/protocol/commands", discoveritems);
    }

    public void registerCommand(String s, String s1, final Class clazz)
    {
        registerCommand(s, s1, new LocalCommandFactory() {

            final AdHocCommandManager this$0;
            final Class val$clazz;

            public LocalCommand getInstance()
                throws InstantiationException, IllegalAccessException
            {
                return (LocalCommand)clazz.newInstance();
            }

            
            {
                this$0 = AdHocCommandManager.this;
                clazz = class1;
                super();
            }
        });
    }

    public void registerCommand(String s, final String name, LocalCommandFactory localcommandfactory)
    {
        localcommandfactory = new AdHocCommandInfo(s, name, connection().getUser(), localcommandfactory);
        commands.put(s, localcommandfactory);
        serviceDiscoveryManager.setNodeInformationProvider(s, new AbstractNodeInformationProvider() {

            final AdHocCommandManager this$0;
            final String val$name;

            public List getNodeFeatures()
            {
                ArrayList arraylist = new ArrayList();
                arraylist.add("http://jabber.org/protocol/commands");
                arraylist.add("jabber:x:data");
                return arraylist;
            }

            public List getNodeIdentities()
            {
                ArrayList arraylist = new ArrayList();
                arraylist.add(new org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity("automation", name, "command-node"));
                return arraylist;
            }

            
            {
                this$0 = AdHocCommandManager.this;
                name = s;
                super();
            }
        });
    }

    static 
    {
        XMPPConnectionRegistry.addConnectionCreationListener(new ConnectionCreationListener() {

            public void connectionCreated(XMPPConnection xmppconnection)
            {
                AdHocCommandManager.getAddHocCommandsManager(xmppconnection);
            }

        });
    }




}
