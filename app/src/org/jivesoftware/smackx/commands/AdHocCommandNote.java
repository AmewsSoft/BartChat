// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.commands;


public class AdHocCommandNote
{
    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type error;
        public static final Type info;
        public static final Type warn;

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smackx/commands/AdHocCommandNote$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            info = new Type("info", 0);
            warn = new Type("warn", 1);
            error = new Type("error", 2);
            $VALUES = (new Type[] {
                info, warn, error
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    private Type type;
    private String value;

    public AdHocCommandNote(Type type1, String s)
    {
        type = type1;
        value = s;
    }

    public Type getType()
    {
        return type;
    }

    public String getValue()
    {
        return value;
    }
}
