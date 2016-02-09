// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands.packet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.commands.AdHocCommandNote;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class AdHocCommandData extends IQ
{
    public static class SpecificError
        implements ExtensionElement
    {

        public static final String namespace = "http://jabber.org/protocol/commands";
        public org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition condition;

        public org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition getCondition()
        {
            return condition;
        }

        public String getElementName()
        {
            return condition.toString();
        }

        public String getNamespace()
        {
            return "http://jabber.org/protocol/commands";
        }

        public volatile CharSequence toXML()
        {
            return toXML();
        }

        public String toXML()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("<").append(getElementName());
            stringbuilder.append(" xmlns=\"").append(getNamespace()).append("\"/>");
            return stringbuilder.toString();
        }

        public SpecificError(org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition specificerrorcondition)
        {
            condition = specificerrorcondition;
        }
    }


    public static final String ELEMENT = "command";
    public static final String NAMESPACE = "http://jabber.org/protocol/commands";
    private org.jivesoftware.smackx.commands.AdHocCommand.Action action;
    private ArrayList actions;
    private org.jivesoftware.smackx.commands.AdHocCommand.Action executeAction;
    private DataForm form;
    private String id;
    private String name;
    private String node;
    private List notes;
    private String sessionID;
    private org.jivesoftware.smackx.commands.AdHocCommand.Status status;

    public AdHocCommandData()
    {
        super("command", "http://jabber.org/protocol/commands");
        notes = new ArrayList();
        actions = new ArrayList();
    }

    public void addAction(org.jivesoftware.smackx.commands.AdHocCommand.Action action1)
    {
        actions.add(action1);
    }

    public void addNote(AdHocCommandNote adhoccommandnote)
    {
        notes.add(adhoccommandnote);
    }

    public org.jivesoftware.smackx.commands.AdHocCommand.Action getAction()
    {
        return action;
    }

    public List getActions()
    {
        return actions;
    }

    public org.jivesoftware.smackx.commands.AdHocCommand.Action getExecuteAction()
    {
        return executeAction;
    }

    public DataForm getForm()
    {
        return form;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.attribute("node", node);
        iqchildelementxmlstringbuilder.optAttribute("sessionid", sessionID);
        iqchildelementxmlstringbuilder.optAttribute("status", status);
        iqchildelementxmlstringbuilder.optAttribute("action", action);
        iqchildelementxmlstringbuilder.rightAngleBracket();
        if (getType() == org.jivesoftware.smack.packet.IQ.Type.result)
        {
            iqchildelementxmlstringbuilder.halfOpenElement("actions");
            iqchildelementxmlstringbuilder.optAttribute("execute", executeAction);
            Iterator iterator;
            AdHocCommandNote adhoccommandnote;
            if (actions.size() == 0)
            {
                iqchildelementxmlstringbuilder.closeEmptyElement();
            } else
            {
                iqchildelementxmlstringbuilder.rightAngleBracket();
                for (Iterator iterator1 = actions.iterator(); iterator1.hasNext(); iqchildelementxmlstringbuilder.emptyElement((org.jivesoftware.smackx.commands.AdHocCommand.Action)iterator1.next())) { }
                iqchildelementxmlstringbuilder.closeElement("actions");
            }
        }
        if (form != null)
        {
            iqchildelementxmlstringbuilder.append(form.toXML());
        }
        for (iterator = notes.iterator(); iterator.hasNext(); iqchildelementxmlstringbuilder.closeElement("note"))
        {
            adhoccommandnote = (AdHocCommandNote)iterator.next();
            iqchildelementxmlstringbuilder.halfOpenElement("note").attribute("type", adhoccommandnote.getType().toString()).rightAngleBracket();
            iqchildelementxmlstringbuilder.append(adhoccommandnote.getValue());
        }

        return iqchildelementxmlstringbuilder;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getNode()
    {
        return node;
    }

    public List getNotes()
    {
        return notes;
    }

    public String getSessionID()
    {
        return sessionID;
    }

    public org.jivesoftware.smackx.commands.AdHocCommand.Status getStatus()
    {
        return status;
    }

    public void remveNote(AdHocCommandNote adhoccommandnote)
    {
        notes.remove(adhoccommandnote);
    }

    public void setAction(org.jivesoftware.smackx.commands.AdHocCommand.Action action1)
    {
        action = action1;
    }

    public void setExecuteAction(org.jivesoftware.smackx.commands.AdHocCommand.Action action1)
    {
        executeAction = action1;
    }

    public void setForm(DataForm dataform)
    {
        form = dataform;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setNode(String s)
    {
        node = s;
    }

    public void setSessionID(String s)
    {
        sessionID = s;
    }

    public void setStatus(org.jivesoftware.smackx.commands.AdHocCommand.Status status1)
    {
        status = status1;
    }
}
