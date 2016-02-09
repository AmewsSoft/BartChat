// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;

// Referenced classes of package org.jivesoftware.smack.debugger:
//            SmackDebuggerFactory, SmackDebugger

public class ReflectionDebuggerFactory
    implements SmackDebuggerFactory
{

    private static final String DEBUGGER_CLASS_PROPERTY_NAME = "smack.debuggerClass";
    private static final String DEFAULT_DEBUGGERS[] = {
        "org.jivesoftware.smackx.debugger.EnhancedDebugger", "org.jivesoftware.smackx.debugger.android.AndroidDebugger", "org.jivesoftware.smack.debugger.ConsoleDebugger", "org.jivesoftware.smack.debugger.LiteDebugger", "org.jivesoftware.smack.debugger.JulDebugger"
    };
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/debugger/ReflectionDebuggerFactory.getName());

    public ReflectionDebuggerFactory()
    {
    }

    private static String getCustomDebuggerClassName()
    {
        String s;
        try
        {
            s = System.getProperty("smack.debuggerClass");
        }
        catch (Throwable throwable)
        {
            return null;
        }
        return s;
    }

    public static Class getDebuggerClass()
    {
        String s = getCustomDebuggerClassName();
        if (s == null)
        {
            return getOneOfDefaultDebuggerClasses();
        }
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch (Exception exception)
        {
            LOGGER.log(Level.WARNING, (new StringBuilder()).append("Unable to instantiate debugger class ").append(s).toString(), exception);
            return null;
        }
        return class1;
    }

    private static Class getOneOfDefaultDebuggerClasses()
    {
        String as[];
        int i;
        int j;
        as = DEFAULT_DEBUGGERS;
        j = as.length;
        i = 0;
_L2:
        String s;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        s = as[i];
        if (!SmackConfiguration.isDisabledSmackClass(s))
        {
            break; /* Loop/switch isn't completed */
        }
_L3:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        Class class1 = Class.forName(s);
        return class1;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        LOGGER.fine((new StringBuilder()).append("Did not find debugger class '").append(s).append("'").toString());
          goto _L3
        Object obj;
        obj;
        LOGGER.warning("Found debugger class that does not appears to implement SmackDebugger interface");
          goto _L3
        obj;
        LOGGER.warning("Unable to instantiate either Smack debugger class");
          goto _L3
        return null;
    }

    public static void setDebuggerClass(Class class1)
    {
        if (class1 == null)
        {
            System.clearProperty("smack.debuggerClass");
            return;
        } else
        {
            System.setProperty("smack.debuggerClass", class1.getCanonicalName());
            return;
        }
    }

    public SmackDebugger create(XMPPConnection xmppconnection, Writer writer, Reader reader)
        throws IllegalArgumentException
    {
        Class class1 = getDebuggerClass();
        if (class1 != null)
        {
            try
            {
                xmppconnection = (SmackDebugger)class1.getConstructor(new Class[] {
                    org/jivesoftware/smack/XMPPConnection, java/io/Writer, java/io/Reader
                }).newInstance(new Object[] {
                    xmppconnection, writer, reader
                });
            }
            // Misplaced declaration of an exception variable
            catch (XMPPConnection xmppconnection)
            {
                throw new IllegalArgumentException("Can't initialize the configured debugger!", xmppconnection);
            }
            return xmppconnection;
        } else
        {
            return null;
        }
    }

}
