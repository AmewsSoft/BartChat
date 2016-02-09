// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.amp.packet;


// Referenced classes of package org.jivesoftware.smackx.amp.packet:
//            AMPExtension

public static class condition
{

    public static final String ELEMENT = "rule";
    private final n action;
    private final tion condition;

    private String toXML()
    {
        return (new StringBuilder()).append("<rule action=\"").append(action.toString()).append("\" ").append("condition").append("=\"").append(condition.getName()).append("\" ").append("value=\"").append(condition.getValue()).append("\"/>").toString();
    }

    public n getAction()
    {
        return action;
    }

    public tion getCondition()
    {
        return condition;
    }


    public tion(n n, tion tion)
    {
        if (n == null)
        {
            throw new NullPointerException("Can't create Rule with null action");
        }
        if (tion == null)
        {
            throw new NullPointerException("Can't create Rule with null condition");
        } else
        {
            action = n;
            condition = tion;
            return;
        }
    }
}
