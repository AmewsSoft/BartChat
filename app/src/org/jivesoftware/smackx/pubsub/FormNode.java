// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.packet.DataForm;

// Referenced classes of package org.jivesoftware.smackx.pubsub:
//            NodeExtension, FormNodeType

public class FormNode extends NodeExtension
{

    private Form configForm;

    public FormNode(FormNodeType formnodetype, String s, Form form)
    {
        super(formnodetype.getNodeElement(), s);
        if (form == null)
        {
            throw new IllegalArgumentException("Submit form cannot be null");
        } else
        {
            configForm = form;
            return;
        }
    }

    public FormNode(FormNodeType formnodetype, Form form)
    {
        super(formnodetype.getNodeElement());
        if (form == null)
        {
            throw new IllegalArgumentException("Submit form cannot be null");
        } else
        {
            configForm = form;
            return;
        }
    }

    public Form getForm()
    {
        return configForm;
    }

    public CharSequence toXML()
    {
        if (configForm == null)
        {
            return super.toXML();
        }
        StringBuilder stringbuilder = new StringBuilder("<");
        stringbuilder.append(getElementName());
        if (getNode() != null)
        {
            stringbuilder.append(" node='");
            stringbuilder.append(getNode());
            stringbuilder.append("'>");
        } else
        {
            stringbuilder.append('>');
        }
        stringbuilder.append(configForm.getDataFormToSend().toXML());
        stringbuilder.append("</");
        stringbuilder.append((new StringBuilder()).append(getElementName()).append('>').toString());
        return stringbuilder.toString();
    }
}
