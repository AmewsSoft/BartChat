// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;
import org.jivesoftware.smack.debugger.ReflectionDebuggerFactory;
import org.jivesoftware.smack.debugger.SmackDebugger;
import org.jivesoftware.smack.debugger.SmackDebuggerFactory;
import org.jivesoftware.smack.parsing.ExceptionThrowingCallback;
import org.jivesoftware.smack.parsing.ParsingExceptionCallback;

// Referenced classes of package org.jivesoftware.smack:
//            SmackInitialization, XMPPConnection

public final class SmackConfiguration
{

    public static boolean DEBUG = false;
    static final List compressionHandlers = new ArrayList(2);
    private static SmackDebuggerFactory debuggerFactory = new ReflectionDebuggerFactory();
    private static ParsingExceptionCallback defaultCallback = new ExceptionThrowingCallback();
    private static HostnameVerifier defaultHostnameVerififer;
    private static List defaultMechs = new ArrayList();
    private static int defaultPacketReplyTimeout = 5000;
    static Set disabledSmackClasses = new HashSet();
    private static int packetCollectorSize = 5000;
    static boolean smackInitialized = false;

    public SmackConfiguration()
    {
    }

    public static void addCompressionHandler(XMPPInputOutputStream xmppinputoutputstream)
    {
        compressionHandlers.add(xmppinputoutputstream);
    }

    public static void addDisabledSmackClass(Class class1)
    {
        addDisabledSmackClass(class1.getName());
    }

    public static void addDisabledSmackClass(String s)
    {
        disabledSmackClasses.add(s);
    }

    public static void addSaslMech(String s)
    {
        if (!defaultMechs.contains(s))
        {
            defaultMechs.add(s);
        }
    }

    public static void addSaslMechs(Collection collection)
    {
        for (collection = collection.iterator(); collection.hasNext(); addSaslMech((String)collection.next())) { }
    }

    public static SmackDebugger createDebugger(XMPPConnection xmppconnection, Writer writer, Reader reader)
    {
        SmackDebuggerFactory smackdebuggerfactory = getDebuggerFactory();
        if (smackdebuggerfactory == null)
        {
            return null;
        } else
        {
            return smackdebuggerfactory.create(xmppconnection, writer, reader);
        }
    }

    public static List getCompresionHandlers()
    {
        ArrayList arraylist = new ArrayList(compressionHandlers.size());
        Iterator iterator = compressionHandlers.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            XMPPInputOutputStream xmppinputoutputstream = (XMPPInputOutputStream)iterator.next();
            if (xmppinputoutputstream.isSupported())
            {
                arraylist.add(xmppinputoutputstream);
            }
        } while (true);
        return arraylist;
    }

    public static SmackDebuggerFactory getDebuggerFactory()
    {
        return debuggerFactory;
    }

    static HostnameVerifier getDefaultHostnameVerifier()
    {
        return defaultHostnameVerififer;
    }

    public static int getDefaultPacketReplyTimeout()
    {
        if (defaultPacketReplyTimeout <= 0)
        {
            defaultPacketReplyTimeout = 5000;
        }
        return defaultPacketReplyTimeout;
    }

    public static ParsingExceptionCallback getDefaultParsingExceptionCallback()
    {
        return defaultCallback;
    }

    public static int getPacketCollectorSize()
    {
        return packetCollectorSize;
    }

    public static List getSaslMechs()
    {
        return Collections.unmodifiableList(defaultMechs);
    }

    public static String getVersion()
    {
        return SmackInitialization.SMACK_VERSION;
    }

    public static boolean isDisabledSmackClass(String s)
    {
        for (Iterator iterator = disabledSmackClasses.iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            if (s1.equals(s))
            {
                return true;
            }
            int i = s1.lastIndexOf('.');
            if (s1.length() > i && !Character.isUpperCase(s1.charAt(i + 1)) && s.startsWith(s1))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isSmackInitialized()
    {
        return smackInitialized;
    }

    public static void removeSaslMech(String s)
    {
        defaultMechs.remove(s);
    }

    public static void removeSaslMechs(Collection collection)
    {
        defaultMechs.removeAll(collection);
    }

    public static void setDebuggerFactory(SmackDebuggerFactory smackdebuggerfactory)
    {
        debuggerFactory = smackdebuggerfactory;
    }

    public static void setDefaultHostnameVerifier(HostnameVerifier hostnameverifier)
    {
        defaultHostnameVerififer = hostnameverifier;
    }

    public static void setDefaultPacketReplyTimeout(int i)
    {
        if (i <= 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            defaultPacketReplyTimeout = i;
            return;
        }
    }

    public static void setDefaultParsingExceptionCallback(ParsingExceptionCallback parsingexceptioncallback)
    {
        defaultCallback = parsingexceptioncallback;
    }

    public static void setPacketCollectorSize(int i)
    {
        packetCollectorSize = i;
    }

}
