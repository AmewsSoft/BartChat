// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

// Referenced classes of package org.jivesoftware.smack.util:
//            StringUtils

public class TLSUtils
{
    public static class AcceptAllTrustManager
        implements X509TrustManager
    {

        public void checkClientTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
        }

        public void checkServerTrusted(X509Certificate ax509certificate[], String s)
            throws CertificateException
        {
        }

        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[0];
        }

        public AcceptAllTrustManager()
        {
        }
    }


    private static final HostnameVerifier DOES_NOT_VERIFY_VERIFIER = new HostnameVerifier() {

        public boolean verify(String s, SSLSession sslsession)
        {
            return true;
        }

    };
    public static final String PROTO_SSL3 = "SSLv3";
    public static final String PROTO_TLSV1 = "TLSv1";
    public static final String PROTO_TLSV1_1 = "TLSv1.1";
    public static final String PROTO_TLSV1_2 = "TLSv1.2";
    public static final String SSL = "SSL";
    public static final String TLS = "TLS";

    public TLSUtils()
    {
    }

    public static org.jivesoftware.smack.ConnectionConfiguration.Builder acceptAllCertificates(org.jivesoftware.smack.ConnectionConfiguration.Builder builder)
        throws NoSuchAlgorithmException, KeyManagementException
    {
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        AcceptAllTrustManager acceptalltrustmanager = new AcceptAllTrustManager();
        SecureRandom securerandom = new SecureRandom();
        sslcontext.init(null, new TrustManager[] {
            acceptalltrustmanager
        }, securerandom);
        builder.setCustomSSLContext(sslcontext);
        return builder;
    }

    public static org.jivesoftware.smack.ConnectionConfiguration.Builder disableHostnameVerificationForTlsCertificicates(org.jivesoftware.smack.ConnectionConfiguration.Builder builder)
    {
        builder.setHostnameVerifier(DOES_NOT_VERIFY_VERIFIER);
        return builder;
    }

    public static void setEnabledProtocolsAndCiphers(SSLSocket sslsocket, String as[], String as1[])
        throws org.jivesoftware.smack.SmackException.SecurityNotPossibleException
    {
        if (as != null)
        {
            as = new HashSet(Arrays.asList(as));
            HashSet hashset = new HashSet(Arrays.asList(sslsocket.getSupportedProtocols()));
            HashSet hashset2 = new HashSet(hashset);
            hashset2.retainAll(as);
            if (hashset2.isEmpty())
            {
                throw new org.jivesoftware.smack.SmackException.SecurityNotPossibleException((new StringBuilder()).append("Request to enable SSL/TLS protocols '").append(StringUtils.collectionToString(as)).append("', but only '").append(StringUtils.collectionToString(hashset)).append("' are supported.").toString());
            }
            sslsocket.setEnabledProtocols((String[])hashset2.toArray(new String[hashset2.size()]));
        }
        if (as1 != null)
        {
            as = new HashSet(Arrays.asList(as1));
            as1 = new HashSet(Arrays.asList(sslsocket.getEnabledCipherSuites()));
            HashSet hashset1 = new HashSet(as1);
            hashset1.retainAll(as);
            if (hashset1.isEmpty())
            {
                throw new org.jivesoftware.smack.SmackException.SecurityNotPossibleException((new StringBuilder()).append("Request to enable SSL/TLS ciphers '").append(StringUtils.collectionToString(as)).append("', but only '").append(StringUtils.collectionToString(as1)).append("' are supported.").toString());
            }
            sslsocket.setEnabledCipherSuites((String[])hashset1.toArray(new String[hashset1.size()]));
        }
    }

    public static org.jivesoftware.smack.ConnectionConfiguration.Builder setSSLv3AndTLSOnly(org.jivesoftware.smack.ConnectionConfiguration.Builder builder)
    {
        builder.setEnabledSSLProtocols(new String[] {
            "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"
        });
        return builder;
    }

    public static org.jivesoftware.smack.ConnectionConfiguration.Builder setTLSOnly(org.jivesoftware.smack.ConnectionConfiguration.Builder builder)
    {
        builder.setEnabledSSLProtocols(new String[] {
            "TLSv1.2", "TLSv1.1", "TLSv1"
        });
        return builder;
    }

}
