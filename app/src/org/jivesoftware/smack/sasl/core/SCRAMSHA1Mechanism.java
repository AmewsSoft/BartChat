// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.sasl.core;

import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.util.ByteUtils;
import org.jivesoftware.smack.util.MAC;
import org.jivesoftware.smack.util.SHA1;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jxmpp.util.cache.Cache;
import org.jxmpp.util.cache.LruCache;

public class SCRAMSHA1Mechanism extends SASLMechanism
{
    private static class Keys
    {

        private final byte clientKey[];
        private final byte serverKey[];



        public Keys(byte abyte0[], byte abyte1[])
        {
            clientKey = abyte0;
            serverKey = abyte1;
        }
    }

    private static final class State extends Enum
    {

        private static final State $VALUES[];
        public static final State AUTH_TEXT_SENT;
        public static final State INITIAL;
        public static final State RESPONSE_SENT;
        public static final State VALID_SERVER_RESPONSE;

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(org/jivesoftware/smack/sasl/core/SCRAMSHA1Mechanism$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        static 
        {
            INITIAL = new State("INITIAL", 0);
            AUTH_TEXT_SENT = new State("AUTH_TEXT_SENT", 1);
            RESPONSE_SENT = new State("RESPONSE_SENT", 2);
            VALID_SERVER_RESPONSE = new State("VALID_SERVER_RESPONSE", 3);
            $VALUES = (new State[] {
                INITIAL, AUTH_TEXT_SENT, RESPONSE_SENT, VALID_SERVER_RESPONSE
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    private static final Cache CACHE = new LruCache(10);
    private static final byte CLIENT_KEY_BYTES[] = toBytes("Client Key");
    private static final String DEFAULT_GS2_HEADER = "n,,";
    public static final String NAME = "SCRAM-SHA-1";
    private static final byte ONE[] = {
        0, 0, 0, 1
    };
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int RANDOM_ASCII_BYTE_COUNT = 32;
    private static final byte SERVER_KEY_BYTES[] = toBytes("Server Key");
    private String clientFirstMessageBare;
    private String clientRandomAscii;
    private byte serverSignature[];
    private State state;

    public SCRAMSHA1Mechanism()
    {
        state = State.INITIAL;
    }

    private static String escape(String s)
    {
        StringBuilder stringbuilder;
        int i;
        stringbuilder = new StringBuilder((int)((double)s.length() * 1.1000000000000001D));
        i = 0;
_L2:
        if (i >= s.length())
        {
            break MISSING_BLOCK_LABEL_93;
        }
        char c = s.charAt(i);
        switch (c)
        {
        default:
            stringbuilder.append(c);
            break;

        case 44: // ','
            break; /* Loop/switch isn't completed */

        case 61: // '='
            break MISSING_BLOCK_LABEL_83;
        }
_L3:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        stringbuilder.append("=2C");
          goto _L3
        stringbuilder.append("=3D");
          goto _L3
        return stringbuilder.toString();
    }

    private static byte[] hi(String s, byte abyte0[], int i)
        throws SmackException
    {
        byte abyte1[] = s.getBytes();
        s = hmac(abyte1, ByteUtils.concact(abyte0, ONE));
        abyte0 = (byte[])s.clone();
        for (int j = 1; j < i; j++)
        {
            s = hmac(abyte1, s);
            for (int k = 0; k < s.length; k++)
            {
                abyte0[k] = (byte)(abyte0[k] ^ s[k]);
            }

        }

        return abyte0;
    }

    private static byte[] hmac(byte abyte0[], byte abyte1[])
        throws SmackException
    {
        try
        {
            abyte0 = MAC.hmacsha1(abyte0, abyte1);
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new SmackException("SCRAM-SHA-1 HMAC-SHA1 Exception", abyte0);
        }
        return abyte0;
    }

    private static boolean isPrintableNonCommaAsciiChar(char c)
    {
        while (c == ',' || c < ' ' || c >= '\177') 
        {
            return false;
        }
        return true;
    }

    private static Map parseAttributes(String s)
        throws SmackException
    {
        if (s.length() != 0) goto _L2; else goto _L1
_L1:
        s = Collections.emptyMap();
_L4:
        return s;
_L2:
        String as[] = s.split(",");
        HashMap hashmap = new HashMap(as.length, 1.0F);
        int j = as.length;
        int i = 0;
        do
        {
            s = hashmap;
            if (i >= j)
            {
                continue;
            }
            s = as[i];
            if (s.length() < 3)
            {
                throw new SmackException((new StringBuilder()).append("Invalid Key-Value pair: ").append(s).toString());
            }
            char c = s.charAt(0);
            if (s.charAt(1) != '=')
            {
                throw new SmackException((new StringBuilder()).append("Invalid Key-Value pair: ").append(s).toString());
            }
            hashmap.put(Character.valueOf(c), s.substring(2));
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void authenticateInternal(CallbackHandler callbackhandler)
        throws SmackException
    {
        throw new UnsupportedOperationException("CallbackHandler not (yet) supported");
    }

    public void checkIfSuccessfulOrThrow()
        throws SmackException
    {
        if (state != State.VALID_SERVER_RESPONSE)
        {
            throw new SmackException("SCRAM-SHA1 is missing valid server response");
        } else
        {
            return;
        }
    }

    protected byte[] evaluateChallenge(byte abyte0[])
        throws SmackException
    {
        byte abyte1[] = new String(abyte0);
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$sasl$core$SCRAMSHA1Mechanism$State[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$sasl$core$SCRAMSHA1Mechanism$State = new int[State.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$sasl$core$SCRAMSHA1Mechanism$State[State.AUTH_TEXT_SENT.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$sasl$core$SCRAMSHA1Mechanism$State[State.RESPONSE_SENT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.jivesoftware.smack.sasl.core.SCRAMSHA1Mechanism.State[state.ordinal()])
        {
        default:
            throw new SmackException("Invalid state");

        case 1: // '\001'
            abyte0 = parseAttributes(abyte1);
            String s = (String)abyte0.get(Character.valueOf('r'));
            if (s == null)
            {
                throw new SmackException("Server random ASCII is null");
            }
            if (s.length() <= clientRandomAscii.length())
            {
                throw new SmackException("Server random ASCII is shorter then client random ASCII");
            }
            if (!s.substring(0, clientRandomAscii.length()).equals(clientRandomAscii))
            {
                throw new SmackException("Received client random ASCII does not match client random ASCII");
            }
            String s1 = (String)abyte0.get(Character.valueOf('i'));
            if (s1 == null)
            {
                throw new SmackException("Iterations attribute not set");
            }
            int i;
            try
            {
                i = Integer.parseInt(s1);
            }
            // Misplaced declaration of an exception variable
            catch (byte abyte0[])
            {
                throw new SmackException("Exception parsing iterations", abyte0);
            }
            abyte0 = (String)abyte0.get(Character.valueOf('s'));
            if (abyte0 == null)
            {
                throw new SmackException("SALT not send");
            }
            s = (new StringBuilder()).append("c=").append(Base64.encode("n,,")).append(",r=").append(s).toString();
            byte abyte2[] = toBytes((new StringBuilder()).append(clientFirstMessageBare).append(',').append(abyte1).append(',').append(s).toString());
            String s2 = (new StringBuilder()).append(password).append(',').append(abyte0).toString();
            Keys keys = (Keys)CACHE.get(s2);
            if (keys == null)
            {
                abyte0 = hi(saslPrep(password), Base64.decode(abyte0), i);
                abyte1 = hmac(abyte0, SERVER_KEY_BYTES);
                abyte0 = hmac(abyte0, CLIENT_KEY_BYTES);
                keys = new Keys(abyte0, abyte1);
                CACHE.put(s2, keys);
            } else
            {
                abyte1 = keys.serverKey;
                abyte0 = keys.clientKey;
            }
            serverSignature = hmac(abyte1, abyte2);
            abyte1 = hmac(SHA1.bytes(abyte0), abyte2);
            abyte2 = new byte[abyte0.length];
            for (int j = 0; j < abyte2.length; j++)
            {
                abyte2[j] = (byte)(abyte0[j] ^ abyte1[j]);
            }

            abyte0 = (new StringBuilder()).append(s).append(",p=").append(Base64.encodeToString(abyte2)).toString();
            state = State.RESPONSE_SENT;
            return toBytes(abyte0);

        case 2: // '\002'
            break;
        }
        if (!(new StringBuilder()).append("v=").append(Base64.encodeToString(serverSignature)).toString().equals(abyte1))
        {
            throw new SmackException("Server final message does not match calculated one");
        } else
        {
            state = State.VALID_SERVER_RESPONSE;
            return null;
        }
    }

    protected byte[] getAuthenticationText()
        throws SmackException
    {
        clientRandomAscii = getRandomAscii();
        String s = saslPrep(authenticationId);
        clientFirstMessageBare = (new StringBuilder()).append("n=").append(escape(s)).append(",r=").append(clientRandomAscii).toString();
        s = (new StringBuilder()).append("n,,").append(clientFirstMessageBare).toString();
        state = State.AUTH_TEXT_SENT;
        return toBytes(s);
    }

    public String getName()
    {
        return "SCRAM-SHA-1";
    }

    public int getPriority()
    {
        return 110;
    }

    String getRandomAscii()
    {
        char ac[] = new char[32];
        int i = 0;
        do
        {
            if (i >= 32)
            {
                break;
            }
            char c = (char)RANDOM.nextInt(128);
            if (isPrintableNonCommaAsciiChar(c))
            {
                ac[i] = c;
                i++;
            }
        } while (true);
        return new String(ac);
    }

    public volatile SASLMechanism newInstance()
    {
        return newInstance();
    }

    public SCRAMSHA1Mechanism newInstance()
    {
        return new SCRAMSHA1Mechanism();
    }

}
