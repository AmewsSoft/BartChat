// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp.packet;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smack.packet.ExtensionElement;

public class AMPExtension
    implements ExtensionElement
{
    public static final class Action extends Enum
    {

        private static final Action $VALUES[];
        public static final String ATTRIBUTE_NAME = "action";
        public static final Action alert;
        public static final Action drop;
        public static final Action error;
        public static final Action notify;

        public static Action valueOf(String s)
        {
            return (Action)Enum.valueOf(org/jivesoftware/smackx/amp/packet/AMPExtension$Action, s);
        }

        public static Action[] values()
        {
            return (Action[])$VALUES.clone();
        }

        static 
        {
            alert = new Action("alert", 0);
            drop = new Action("drop", 1);
            error = new Action("error", 2);
            notify = new Action("notify", 3);
            $VALUES = (new Action[] {
                alert, drop, error, notify
            });
        }

        private Action(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface Condition
    {

        public static final String ATTRIBUTE_NAME = "condition";

        public abstract String getName();

        public abstract String getValue();
    }

    public static class Rule
    {

        public static final String ELEMENT = "rule";
        private final Action action;
        private final Condition condition;

        private String toXML()
        {
            return (new StringBuilder()).append("<rule action=\"").append(action.toString()).append("\" ").append("condition").append("=\"").append(condition.getName()).append("\" ").append("value=\"").append(condition.getValue()).append("\"/>").toString();
        }

        public Action getAction()
        {
            return action;
        }

        public Condition getCondition()
        {
            return condition;
        }


        public Rule(Action action1, Condition condition1)
        {
            if (action1 == null)
            {
                throw new NullPointerException("Can't create Rule with null action");
            }
            if (condition1 == null)
            {
                throw new NullPointerException("Can't create Rule with null condition");
            } else
            {
                action = action1;
                condition = condition1;
                return;
            }
        }
    }

    public static final class Status extends Enum
    {

        private static final Status $VALUES[];
        public static final Status alert;
        public static final Status error;
        public static final Status notify;

        public static Status valueOf(String s)
        {
            return (Status)Enum.valueOf(org/jivesoftware/smackx/amp/packet/AMPExtension$Status, s);
        }

        public static Status[] values()
        {
            return (Status[])$VALUES.clone();
        }

        static 
        {
            alert = new Status("alert", 0);
            error = new Status("error", 1);
            notify = new Status("notify", 2);
            $VALUES = (new Status[] {
                alert, error, notify
            });
        }

        private Status(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String ELEMENT = "amp";
    public static final String NAMESPACE = "http://jabber.org/protocol/amp";
    private final String from;
    private boolean perHop;
    private CopyOnWriteArrayList rules;
    private final Status status;
    private final String to;

    public AMPExtension()
    {
        rules = new CopyOnWriteArrayList();
        perHop = false;
        from = null;
        to = null;
        status = null;
    }

    public AMPExtension(String s, String s1, Status status1)
    {
        rules = new CopyOnWriteArrayList();
        perHop = false;
        from = s;
        to = s1;
        status = status1;
    }

    public void addRule(Rule rule)
    {
        rules.add(rule);
    }

    public String getElementName()
    {
        return "amp";
    }

    public String getFrom()
    {
        return from;
    }

    public String getNamespace()
    {
        return "http://jabber.org/protocol/amp";
    }

    public List getRules()
    {
        return Collections.unmodifiableList(rules);
    }

    public int getRulesCount()
    {
        return rules.size();
    }

    public Status getStatus()
    {
        return status;
    }

    public String getTo()
    {
        return to;
    }

    public boolean isPerHop()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = perHop;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void setPerHop(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        perHop = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public String toXML()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\"");
        if (status != null)
        {
            stringbuilder.append(" status=\"").append(status.toString()).append("\"");
        }
        if (to != null)
        {
            stringbuilder.append(" to=\"").append(to).append("\"");
        }
        if (from != null)
        {
            stringbuilder.append(" from=\"").append(from).append("\"");
        }
        if (perHop)
        {
            stringbuilder.append(" per-hop=\"true\"");
        }
        stringbuilder.append(">");
        for (Iterator iterator = getRules().iterator(); iterator.hasNext(); stringbuilder.append(((Rule)iterator.next()).toXML())) { }
        stringbuilder.append("</").append(getElementName()).append(">");
        return stringbuilder.toString();
    }
}
