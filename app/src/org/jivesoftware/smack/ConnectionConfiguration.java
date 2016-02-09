// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.proxy.ProxyInfo;

// Referenced classes of package org.jivesoftware.smack:
//            SmackConfiguration

public abstract class ConnectionConfiguration
{
    public static abstract class Builder
    {

        private boolean allowEmptyOrNullUsername;
        private CallbackHandler callbackHandler;
        private SSLContext customSSLContext;
        private boolean debuggerEnabled;
        private String enabledSSLCiphers[];
        private String enabledSSLProtocols[];
        private String host;
        private HostnameVerifier hostnameVerifier;
        private String keystorePath;
        private String keystoreType;
        private boolean legacySessionDisabled;
        private String password;
        private String pkcs11Library;
        private int port;
        private ProxyInfo proxy;
        private String resource;
        private SecurityMode securityMode;
        private boolean sendPresence;
        private String serviceName;
        private SocketFactory socketFactory;
        private CharSequence username;

        public Builder allowEmptyOrNullUsernames()
        {
            allowEmptyOrNullUsername = true;
            return getThis();
        }

        public abstract ConnectionConfiguration build();

        protected abstract Builder getThis();

        public Builder setCallbackHandler(CallbackHandler callbackhandler)
        {
            callbackHandler = callbackhandler;
            return getThis();
        }

        public Builder setCustomSSLContext(SSLContext sslcontext)
        {
            customSSLContext = sslcontext;
            return getThis();
        }

        public Builder setDebuggerEnabled(boolean flag)
        {
            debuggerEnabled = flag;
            return getThis();
        }

        public Builder setEnabledSSLCiphers(String as[])
        {
            enabledSSLCiphers = as;
            return getThis();
        }

        public Builder setEnabledSSLProtocols(String as[])
        {
            enabledSSLProtocols = as;
            return getThis();
        }

        public Builder setHost(String s)
        {
            host = s;
            return getThis();
        }

        public Builder setHostnameVerifier(HostnameVerifier hostnameverifier)
        {
            hostnameVerifier = hostnameverifier;
            return getThis();
        }

        public Builder setKeystorePath(String s)
        {
            keystorePath = s;
            return getThis();
        }

        public Builder setKeystoreType(String s)
        {
            keystoreType = s;
            return getThis();
        }

        public Builder setLegacySessionDisabled(boolean flag)
        {
            legacySessionDisabled = flag;
            return getThis();
        }

        public Builder setPKCS11Library(String s)
        {
            pkcs11Library = s;
            return getThis();
        }

        public Builder setPort(int i)
        {
            port = i;
            return getThis();
        }

        public Builder setResource(String s)
        {
            resource = s;
            return getThis();
        }

        public Builder setSecurityMode(SecurityMode securitymode)
        {
            securityMode = securitymode;
            return getThis();
        }

        public Builder setSendPresence(boolean flag)
        {
            sendPresence = flag;
            return getThis();
        }

        public Builder setServiceName(String s)
        {
            serviceName = s;
            return getThis();
        }

        public Builder setSocketFactory(SocketFactory socketfactory)
        {
            socketFactory = socketfactory;
            return getThis();
        }

        public Builder setUsernameAndPassword(CharSequence charsequence, String s)
        {
            username = charsequence;
            password = s;
            return getThis();
        }






















        protected Builder()
        {
            securityMode = SecurityMode.ifpossible;
            keystorePath = System.getProperty("javax.net.ssl.keyStore");
            keystoreType = "jks";
            pkcs11Library = "pkcs11.config";
            resource = "Smack";
            sendPresence = true;
            legacySessionDisabled = false;
            debuggerEnabled = SmackConfiguration.DEBUG;
            port = 5222;
            allowEmptyOrNullUsername = false;
        }
    }

    public static final class SecurityMode extends Enum
    {

        private static final SecurityMode $VALUES[];
        public static final SecurityMode disabled;
        public static final SecurityMode ifpossible;
        public static final SecurityMode required;

        public static SecurityMode valueOf(String s)
        {
            return (SecurityMode)Enum.valueOf(org/jivesoftware/smack/ConnectionConfiguration$SecurityMode, s);
        }

        public static SecurityMode[] values()
        {
            return (SecurityMode[])$VALUES.clone();
        }

        static 
        {
            required = new SecurityMode("required", 0);
            ifpossible = new SecurityMode("ifpossible", 1);
            disabled = new SecurityMode("disabled", 2);
            $VALUES = (new SecurityMode[] {
                required, ifpossible, disabled
            });
        }

        private SecurityMode(String s, int i)
        {
            super(s, i);
        }
    }


    protected final boolean allowNullOrEmptyUsername;
    private final CallbackHandler callbackHandler;
    private final SSLContext customSSLContext;
    private final boolean debuggerEnabled;
    private final String enabledSSLCiphers[];
    private final String enabledSSLProtocols[];
    protected final String host;
    private final HostnameVerifier hostnameVerifier;
    private final String keystorePath;
    private final String keystoreType;
    private final boolean legacySessionDisabled;
    private final String password;
    private final String pkcs11Library;
    protected final int port;
    protected final ProxyInfo proxy;
    private final String resource;
    private final SecurityMode securityMode;
    private final boolean sendPresence;
    protected final String serviceName;
    private final SocketFactory socketFactory;
    private final CharSequence username;

    protected ConnectionConfiguration(Builder builder)
    {
        username = builder.username;
        password = builder.password;
        callbackHandler = builder.callbackHandler;
        resource = builder.resource;
        serviceName = builder.serviceName;
        if (serviceName == null)
        {
            throw new IllegalArgumentException("Must provide XMPP service name");
        }
        host = builder.host;
        port = builder.port;
        proxy = builder.proxy;
        if (proxy != null)
        {
            if (builder.socketFactory != null)
            {
                throw new IllegalArgumentException("Can not use proxy together with custom socket factory");
            }
            socketFactory = proxy.getSocketFactory();
        } else
        {
            socketFactory = builder.socketFactory;
        }
        securityMode = builder.securityMode;
        keystoreType = builder.keystoreType;
        keystorePath = builder.keystorePath;
        pkcs11Library = builder.pkcs11Library;
        customSSLContext = builder.customSSLContext;
        enabledSSLProtocols = builder.enabledSSLProtocols;
        enabledSSLCiphers = builder.enabledSSLCiphers;
        hostnameVerifier = builder.hostnameVerifier;
        sendPresence = builder.sendPresence;
        legacySessionDisabled = builder.legacySessionDisabled;
        debuggerEnabled = builder.debuggerEnabled;
        allowNullOrEmptyUsername = builder.allowEmptyOrNullUsername;
    }

    public CallbackHandler getCallbackHandler()
    {
        return callbackHandler;
    }

    public SSLContext getCustomSSLContext()
    {
        return customSSLContext;
    }

    public String[] getEnabledSSLCiphers()
    {
        return enabledSSLCiphers;
    }

    public String[] getEnabledSSLProtocols()
    {
        return enabledSSLProtocols;
    }

    public HostnameVerifier getHostnameVerifier()
    {
        if (hostnameVerifier != null)
        {
            return hostnameVerifier;
        } else
        {
            return SmackConfiguration.getDefaultHostnameVerifier();
        }
    }

    public String getKeystorePath()
    {
        return keystorePath;
    }

    public String getKeystoreType()
    {
        return keystoreType;
    }

    public String getPKCS11Library()
    {
        return pkcs11Library;
    }

    public String getPassword()
    {
        return password;
    }

    public String getResource()
    {
        return resource;
    }

    public SecurityMode getSecurityMode()
    {
        return securityMode;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public SocketFactory getSocketFactory()
    {
        return socketFactory;
    }

    public CharSequence getUsername()
    {
        return username;
    }

    public boolean isCompressionEnabled()
    {
        return false;
    }

    public boolean isDebuggerEnabled()
    {
        return debuggerEnabled;
    }

    public boolean isLegacySessionDisabled()
    {
        return legacySessionDisabled;
    }

    public boolean isSendPresence()
    {
        return sendPresence;
    }

    static 
    {
        SmackConfiguration.getVersion();
    }
}
