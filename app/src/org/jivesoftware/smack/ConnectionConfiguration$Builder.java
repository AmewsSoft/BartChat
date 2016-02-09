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
//            ConnectionConfiguration, SmackConfiguration

public static abstract class allowEmptyOrNullUsername
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
    private Mode securityMode;
    private boolean sendPresence;
    private String serviceName;
    private SocketFactory socketFactory;
    private CharSequence username;

    public allowEmptyOrNullUsername allowEmptyOrNullUsernames()
    {
        allowEmptyOrNullUsername = true;
        return getThis();
    }

    public abstract ConnectionConfiguration build();

    protected abstract getThis getThis();

    public getThis setCallbackHandler(CallbackHandler callbackhandler)
    {
        callbackHandler = callbackhandler;
        return getThis();
    }

    public getThis setCustomSSLContext(SSLContext sslcontext)
    {
        customSSLContext = sslcontext;
        return getThis();
    }

    public getThis setDebuggerEnabled(boolean flag)
    {
        debuggerEnabled = flag;
        return getThis();
    }

    public getThis setEnabledSSLCiphers(String as[])
    {
        enabledSSLCiphers = as;
        return getThis();
    }

    public getThis setEnabledSSLProtocols(String as[])
    {
        enabledSSLProtocols = as;
        return getThis();
    }

    public getThis setHost(String s)
    {
        host = s;
        return getThis();
    }

    public getThis setHostnameVerifier(HostnameVerifier hostnameverifier)
    {
        hostnameVerifier = hostnameverifier;
        return getThis();
    }

    public getThis setKeystorePath(String s)
    {
        keystorePath = s;
        return getThis();
    }

    public getThis setKeystoreType(String s)
    {
        keystoreType = s;
        return getThis();
    }

    public getThis setLegacySessionDisabled(boolean flag)
    {
        legacySessionDisabled = flag;
        return getThis();
    }

    public getThis setPKCS11Library(String s)
    {
        pkcs11Library = s;
        return getThis();
    }

    public getThis setPort(int i)
    {
        port = i;
        return getThis();
    }

    public getThis setResource(String s)
    {
        resource = s;
        return getThis();
    }

    public Mode setSecurityMode(Mode mode)
    {
        securityMode = mode;
        return getThis();
    }

    public getThis setSendPresence(boolean flag)
    {
        sendPresence = flag;
        return getThis();
    }

    public getThis setServiceName(String s)
    {
        serviceName = s;
        return getThis();
    }

    public getThis setSocketFactory(SocketFactory socketfactory)
    {
        socketFactory = socketfactory;
        return getThis();
    }

    public getThis setUsernameAndPassword(CharSequence charsequence, String s)
    {
        username = charsequence;
        password = s;
        return getThis();
    }






















    protected Mode()
    {
        securityMode = Mode.ifpossible;
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
