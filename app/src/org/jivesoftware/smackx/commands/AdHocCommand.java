// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;

import java.util.List;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.xdata.Form;

// Referenced classes of package org.jivesoftware.smackx.commands:
//            AdHocCommandNote

public abstract class AdHocCommand
{
    public static final class Action extends Enum
    {

        private static final Action $VALUES[];
        public static final Action cancel;
        public static final Action complete;
        public static final Action execute;
        public static final Action next;
        public static final Action prev;
        public static final Action unknown;

        public static Action valueOf(String s)
        {
            return (Action)Enum.valueOf(org/jivesoftware/smackx/commands/AdHocCommand$Action, s);
        }

        public static Action[] values()
        {
            return (Action[])$VALUES.clone();
        }

        static 
        {
            execute = new Action("execute", 0);
            cancel = new Action("cancel", 1);
            prev = new Action("prev", 2);
            next = new Action("next", 3);
            complete = new Action("complete", 4);
            unknown = new Action("unknown", 5);
            $VALUES = (new Action[] {
                execute, cancel, prev, next, complete, unknown
            });
        }

        private Action(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class SpecificErrorCondition extends Enum
    {

        private static final SpecificErrorCondition $VALUES[];
        public static final SpecificErrorCondition badAction;
        public static final SpecificErrorCondition badLocale;
        public static final SpecificErrorCondition badPayload;
        public static final SpecificErrorCondition badSessionid;
        public static final SpecificErrorCondition malformedAction;
        public static final SpecificErrorCondition sessionExpired;
        private String value;

        public static SpecificErrorCondition valueOf(String s)
        {
            return (SpecificErrorCondition)Enum.valueOf(org/jivesoftware/smackx/commands/AdHocCommand$SpecificErrorCondition, s);
        }

        public static SpecificErrorCondition[] values()
        {
            return (SpecificErrorCondition[])$VALUES.clone();
        }

        public String toString()
        {
            return value;
        }

        static 
        {
            badAction = new SpecificErrorCondition("badAction", 0, "bad-action");
            malformedAction = new SpecificErrorCondition("malformedAction", 1, "malformed-action");
            badLocale = new SpecificErrorCondition("badLocale", 2, "bad-locale");
            badPayload = new SpecificErrorCondition("badPayload", 3, "bad-payload");
            badSessionid = new SpecificErrorCondition("badSessionid", 4, "bad-sessionid");
            sessionExpired = new SpecificErrorCondition("sessionExpired", 5, "session-expired");
            $VALUES = (new SpecificErrorCondition[] {
                badAction, malformedAction, badLocale, badPayload, badSessionid, sessionExpired
            });
        }

        private SpecificErrorCondition(String s, int i, String s1)
        {
            super(s, i);
            value = s1;
        }
    }

    public static final class Status extends Enum
    {

        private static final Status $VALUES[];
        public static final Status canceled;
        public static final Status completed;
        public static final Status executing;

        public static Status valueOf(String s)
        {
            return (Status)Enum.valueOf(org/jivesoftware/smackx/commands/AdHocCommand$Status, s);
        }

        public static Status[] values()
        {
            return (Status[])$VALUES.clone();
        }

        static 
        {
            executing = new Status("executing", 0);
            completed = new Status("completed", 1);
            canceled = new Status("canceled", 2);
            $VALUES = (new Status[] {
                executing, completed, canceled
            });
        }

        private Status(String s, int i)
        {
            super(s, i);
        }
    }


    private AdHocCommandData data;

    public AdHocCommand()
    {
        data = new AdHocCommandData();
    }

    public static SpecificErrorCondition getSpecificErrorCondition(XMPPError xmpperror)
    {
        SpecificErrorCondition aspecificerrorcondition[] = SpecificErrorCondition.values();
        int j = aspecificerrorcondition.length;
        for (int i = 0; i < j; i++)
        {
            SpecificErrorCondition specificerrorcondition = aspecificerrorcondition[i];
            if (xmpperror.getExtension(specificerrorcondition.toString(), "http://jabber.org/protocol/commands") != null)
            {
                return specificerrorcondition;
            }
        }

        return null;
    }

    protected void addActionAvailable(Action action)
    {
        data.addAction(action);
    }

    protected void addNote(AdHocCommandNote adhoccommandnote)
    {
        data.addNote(adhoccommandnote);
    }

    public abstract void cancel()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException;

    public abstract void complete(Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException;

    public abstract void execute()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException;

    protected List getActions()
    {
        return data.getActions();
    }

    AdHocCommandData getData()
    {
        return data;
    }

    protected Action getExecuteAction()
    {
        return data.getExecuteAction();
    }

    public Form getForm()
    {
        if (data.getForm() == null)
        {
            return null;
        } else
        {
            return new Form(data.getForm());
        }
    }

    public String getName()
    {
        return data.getName();
    }

    public String getNode()
    {
        return data.getNode();
    }

    public List getNotes()
    {
        return data.getNotes();
    }

    public abstract String getOwnerJID();

    public String getRaw()
    {
        return data.getChildElementXML().toString();
    }

    public Status getStatus()
    {
        return data.getStatus();
    }

    protected boolean isValidAction(Action action)
    {
        return getActions().contains(action) || Action.cancel.equals(action);
    }

    public abstract void next(Form form)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException;

    public abstract void prev()
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException;

    void setData(AdHocCommandData adhoccommanddata)
    {
        data = adhoccommanddata;
    }

    protected void setExecuteAction(Action action)
    {
        data.setExecuteAction(action);
    }

    protected void setForm(Form form)
    {
        data.setForm(form.getDataFormToSend());
    }

    public void setName(String s)
    {
        data.setName(s);
    }

    public void setNode(String s)
    {
        data.setNode(s);
    }
}
