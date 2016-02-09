// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;

// Referenced classes of package org.jivesoftware.smackx.bytestreams.socks5:
//            Socks5Utils

public class Socks5Proxy
{
    private class Socks5ServerProcess
        implements Runnable
    {

        final Socks5Proxy this$0;

        private void establishConnection(Socket socket)
            throws SmackException, IOException
        {
            DataOutputStream dataoutputstream = new DataOutputStream(socket.getOutputStream());
            DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
            if (datainputstream.read() != 5)
            {
                throw new SmackException("Only SOCKS5 supported");
            }
            byte abyte1[] = new byte[datainputstream.read()];
            datainputstream.readFully(abyte1);
            byte abyte2[] = new byte[2];
            abyte2[0] = 5;
            boolean flag1 = false;
            int i = 0;
label0:
            do
            {
label1:
                {
                    boolean flag = flag1;
                    if (i < abyte1.length)
                    {
                        if (abyte1[i] != 0)
                        {
                            break label1;
                        }
                        flag = true;
                    }
                    if (!flag)
                    {
                        abyte2[1] = -1;
                        dataoutputstream.write(abyte2);
                        dataoutputstream.flush();
                        throw new SmackException("Authentication method not supported");
                    }
                    break label0;
                }
                i++;
            } while (true);
            abyte2[1] = 0;
            dataoutputstream.write(abyte2);
            dataoutputstream.flush();
            byte abyte0[] = Socks5Utils.receiveSocks5Message(datainputstream);
            String s = new String(abyte0, 5, abyte0[4]);
            if (!allowedConnections.contains(s))
            {
                abyte0[1] = 5;
                dataoutputstream.write(abyte0);
                dataoutputstream.flush();
                throw new SmackException("Connection is not allowed");
            } else
            {
                abyte0[1] = 0;
                dataoutputstream.write(abyte0);
                dataoutputstream.flush();
                connectionMap.put(s, socket);
                return;
            }
        }

        public void run()
        {
_L2:
            Object obj;
            Socket socket;
            socket = null;
            obj = socket;
            if (serverSocket.isClosed())
            {
                break; /* Loop/switch isn't completed */
            }
            obj = socket;
            if (Thread.currentThread().isInterrupted())
            {
                return;
            }
            obj = socket;
            socket = serverSocket.accept();
            obj = socket;
            try
            {
                establishConnection(socket);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj) { }
            catch (Exception exception)
            {
                if (obj != null)
                {
                    try
                    {
                        ((Socket) (obj)).close();
                    }
                    catch (IOException ioexception) { }
                }
            }
            if (true) goto _L2; else goto _L1
_L1:
        }

        private Socks5ServerProcess()
        {
            this$0 = Socks5Proxy.this;
            super();
        }

    }


    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/bytestreams/socks5/Socks5Proxy.getName());
    private static boolean localSocks5ProxyEnabled = true;
    private static int localSocks5ProxyPort = -7777;
    private static Socks5Proxy socks5Server;
    private final List allowedConnections = Collections.synchronizedList(new LinkedList());
    private final Map connectionMap = new ConcurrentHashMap();
    private final Set localAddresses = new LinkedHashSet(4);
    private Socks5ServerProcess serverProcess;
    private ServerSocket serverSocket;
    private Thread serverThread;

    private Socks5Proxy()
    {
        serverProcess = new Socks5ServerProcess();
        Object obj;
        Object obj1;
        try
        {
            obj1 = NetworkInterface.getNetworkInterfaces();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw new IllegalStateException(((Throwable) (obj)));
        }
        obj = new HashSet();
        for (obj1 = Collections.list(((java.util.Enumeration) (obj1))).iterator(); ((Iterator) (obj1)).hasNext();)
        {
            Iterator iterator = Collections.list(((NetworkInterface)((Iterator) (obj1)).next()).getInetAddresses()).iterator();
            while (iterator.hasNext()) 
            {
                ((Set) (obj)).add(((InetAddress)iterator.next()).getHostAddress());
            }
        }

        if (((Set) (obj)).isEmpty())
        {
            throw new IllegalStateException("Could not determine any local host address");
        } else
        {
            replaceLocalAddresses(((Collection) (obj)));
            return;
        }
    }

    public static int getLocalSocks5ProxyPort()
    {
        return localSocks5ProxyPort;
    }

    public static Socks5Proxy getSocks5Proxy()
    {
        org/jivesoftware/smackx/bytestreams/socks5/Socks5Proxy;
        JVM INSTR monitorenter ;
        Socks5Proxy socks5proxy;
        if (socks5Server == null)
        {
            socks5Server = new Socks5Proxy();
        }
        if (isLocalSocks5ProxyEnabled())
        {
            socks5Server.start();
        }
        socks5proxy = socks5Server;
        org/jivesoftware/smackx/bytestreams/socks5/Socks5Proxy;
        JVM INSTR monitorexit ;
        return socks5proxy;
        Exception exception;
        exception;
        throw exception;
    }

    public static boolean isLocalSocks5ProxyEnabled()
    {
        return localSocks5ProxyEnabled;
    }

    public static void setLocalSocks5ProxyEnabled(boolean flag)
    {
        localSocks5ProxyEnabled = flag;
    }

    public static void setLocalSocks5ProxyPort(int i)
    {
        if (Math.abs(i) > 65535)
        {
            throw new IllegalArgumentException("localSocks5ProxyPort must be within (-65535,65535)");
        } else
        {
            localSocks5ProxyPort = i;
            return;
        }
    }

    public void addLocalAddress(String s)
    {
        if (s == null)
        {
            return;
        }
        synchronized (localAddresses)
        {
            localAddresses.add(s);
        }
        return;
        s;
        set;
        JVM INSTR monitorexit ;
        throw s;
    }

    protected void addTransfer(String s)
    {
        allowedConnections.add(s);
    }

    public List getLocalAddresses()
    {
        LinkedList linkedlist;
        synchronized (localAddresses)
        {
            linkedlist = new LinkedList(localAddresses);
        }
        return linkedlist;
        exception;
        set;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getPort()
    {
        if (!isRunning())
        {
            return -1;
        } else
        {
            return serverSocket.getLocalPort();
        }
    }

    protected Socket getSocket(String s)
    {
        return (Socket)connectionMap.get(s);
    }

    public boolean isRunning()
    {
        return serverSocket != null;
    }

    public boolean removeLocalAddress(String s)
    {
        boolean flag;
        synchronized (localAddresses)
        {
            flag = localAddresses.remove(s);
        }
        return flag;
        s;
        set;
        JVM INSTR monitorexit ;
        throw s;
    }

    protected void removeTransfer(String s)
    {
        allowedConnections.remove(s);
        connectionMap.remove(s);
    }

    public void replaceLocalAddresses(Collection collection)
    {
        if (collection == null)
        {
            throw new IllegalArgumentException("list must not be null");
        }
        synchronized (localAddresses)
        {
            localAddresses.clear();
            localAddresses.addAll(collection);
        }
        return;
        collection;
        set;
        JVM INSTR monitorexit ;
        throw collection;
    }

    public void start()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isRunning();
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        int j;
        if (getLocalSocks5ProxyPort() >= 0)
        {
            break MISSING_BLOCK_LABEL_133;
        }
        j = Math.abs(getLocalSocks5ProxyPort());
        int i = 0;
_L6:
        if (i >= 65535 - j) goto _L4; else goto _L3
_L3:
        serverSocket = new ServerSocket(j + i);
_L4:
        if (serverSocket == null) goto _L1; else goto _L5
_L5:
        serverThread = new Thread(serverProcess);
        serverThread.start();
          goto _L1
        Object obj;
        obj;
        LOGGER.log(Level.SEVERE, (new StringBuilder()).append("couldn't setup local SOCKS5 proxy on port ").append(getLocalSocks5ProxyPort()).toString(), ((Throwable) (obj)));
          goto _L1
        obj;
        throw obj;
        obj;
        i++;
          goto _L6
        serverSocket = new ServerSocket(getLocalSocks5ProxyPort());
          goto _L4
    }

    public void stop()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isRunning();
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Exception exception;
        try
        {
            serverSocket.close();
        }
        catch (IOException ioexception) { }
        if (serverThread == null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        flag = serverThread.isAlive();
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        try
        {
            serverThread.interrupt();
            serverThread.join();
        }
        catch (InterruptedException interruptedexception) { }
        serverThread = null;
        serverSocket = null;
        if (true) goto _L1; else goto _L3
_L3:
        exception;
        throw exception;
    }




}
