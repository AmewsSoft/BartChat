// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.TopLevelStreamElement;

// Referenced classes of package org.jivesoftware.smack:
//            AbstractXMPPConnection

public class SynchronizationPoint
{
    private static final class State extends Enum
    {

        private static final State $VALUES[];
        public static final State Failure;
        public static final State Initial;
        public static final State NoResponse;
        public static final State RequestSent;
        public static final State Success;

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(org/jivesoftware/smack/SynchronizationPoint$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        static 
        {
            Initial = new State("Initial", 0);
            RequestSent = new State("RequestSent", 1);
            NoResponse = new State("NoResponse", 2);
            Success = new State("Success", 3);
            Failure = new State("Failure", 4);
            $VALUES = (new State[] {
                Initial, RequestSent, NoResponse, Success, Failure
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    static final boolean $assertionsDisabled;
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/SynchronizationPoint.getName());
    private final Condition condition;
    private final AbstractXMPPConnection connection;
    private final Lock connectionLock;
    private Exception failureException;
    private State state;

    public SynchronizationPoint(AbstractXMPPConnection abstractxmppconnection)
    {
        connection = abstractxmppconnection;
        connectionLock = abstractxmppconnection.getConnectionLock();
        condition = abstractxmppconnection.getConnectionLock().newCondition();
        init();
    }

    private void checkForResponse()
        throws SmackException.NoResponseException
    {
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State = new int[State.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.Failure.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.Initial.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.NoResponse.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.RequestSent.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.jivesoftware.smack.SynchronizationPoint.State[state.ordinal()])
        {
        default:
            return;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
            throw SmackException.NoResponseException.newWith(connection);
        }
    }

    private void waitForConditionOrTimeout()
    {
        long l = TimeUnit.MILLISECONDS.toNanos(connection.getPacketReplyTimeout());
_L2:
        long l2;
        if (state != State.RequestSent && state != State.Initial)
        {
            break MISSING_BLOCK_LABEL_70;
        }
        l2 = l;
        long l1 = condition.awaitNanos(l);
        l = l1;
        if (l1 > 0L)
        {
            continue; /* Loop/switch isn't completed */
        }
        l2 = l1;
        state = State.NoResponse;
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        LOGGER.log(Level.WARNING, "Thread interrupt while waiting for condition or timeout ignored", interruptedexception);
        l = l2;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void checkIfSuccessOrWait()
        throws SmackException.NoResponseException
    {
        connectionLock.lock();
        State state1;
        State state2;
        state1 = state;
        state2 = State.Success;
        if (state1 == state2)
        {
            connectionLock.unlock();
            return;
        }
        waitForConditionOrTimeout();
        connectionLock.unlock();
        checkForResponse();
        return;
        Exception exception;
        exception;
        connectionLock.unlock();
        throw exception;
    }

    public void checkIfSuccessOrWaitOrThrow()
        throws SmackException.NoResponseException, Exception
    {
        checkIfSuccessOrWait();
        if (state == State.Failure)
        {
            throw failureException;
        } else
        {
            return;
        }
    }

    public void init()
    {
        connectionLock.lock();
        state = State.Initial;
        failureException = null;
        connectionLock.unlock();
    }

    public void reportFailure()
    {
        reportFailure(null);
    }

    public void reportFailure(Exception exception)
    {
        connectionLock.lock();
        state = State.Failure;
        failureException = exception;
        condition.signal();
        connectionLock.unlock();
        return;
        exception;
        connectionLock.unlock();
        throw exception;
    }

    public void reportSuccess()
    {
        connectionLock.lock();
        state = State.Success;
        condition.signal();
        connectionLock.unlock();
        return;
        Exception exception;
        exception;
        connectionLock.unlock();
        throw exception;
    }

    public boolean requestSent()
    {
        connectionLock.lock();
        State state1;
        State state2;
        state1 = state;
        state2 = State.RequestSent;
        boolean flag;
        if (state1 == state2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        connectionLock.unlock();
        return flag;
        Exception exception;
        exception;
        connectionLock.unlock();
        throw exception;
    }

    public void sendAndWaitForResponse(TopLevelStreamElement toplevelstreamelement)
        throws SmackException.NoResponseException, SmackException.NotConnectedException
    {
        if (!$assertionsDisabled && state != State.Initial)
        {
            throw new AssertionError();
        }
        connectionLock.lock();
        if (toplevelstreamelement == null) goto _L2; else goto _L1
_L1:
        if (!(toplevelstreamelement instanceof Stanza))
        {
            break MISSING_BLOCK_LABEL_80;
        }
        connection.sendStanza((Stanza)toplevelstreamelement);
_L3:
        state = State.RequestSent;
_L2:
        waitForConditionOrTimeout();
        connectionLock.unlock();
        checkForResponse();
        return;
        if (!(toplevelstreamelement instanceof PlainStreamElement))
        {
            break MISSING_BLOCK_LABEL_113;
        }
        connection.send((PlainStreamElement)toplevelstreamelement);
          goto _L3
        toplevelstreamelement;
        connectionLock.unlock();
        throw toplevelstreamelement;
        throw new IllegalStateException("Unsupported element type");
    }

    public void sendAndWaitForResponseOrThrow(PlainStreamElement plainstreamelement)
        throws Exception, SmackException.NoResponseException, SmackException.NotConnectedException
    {
        sendAndWaitForResponse(plainstreamelement);
        _cls1..SwitchMap.org.jivesoftware.smack.SynchronizationPoint.State[state.ordinal()];
        JVM INSTR tableswitch 1 1: default 36
    //                   1 37;
           goto _L1 _L2
_L1:
        return;
_L2:
        if (failureException != null)
        {
            throw failureException;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public boolean wasSuccessful()
    {
        connectionLock.lock();
        State state1;
        State state2;
        state1 = state;
        state2 = State.Success;
        boolean flag;
        if (state1 == state2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        connectionLock.unlock();
        return flag;
        Exception exception;
        exception;
        connectionLock.unlock();
        throw exception;
    }

    static 
    {
        boolean flag;
        if (!org/jivesoftware/smack/SynchronizationPoint.desiredAssertionStatus())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        $assertionsDisabled = flag;
    }
}
