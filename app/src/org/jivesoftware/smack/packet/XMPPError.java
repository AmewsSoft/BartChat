// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            AbstractError, ExtensionElement

public class XMPPError extends AbstractError
{
    public static final class Condition extends Enum
    {

        private static final Condition $VALUES[];
        public static final Condition bad_request;
        public static final Condition conflict;
        public static final Condition feature_not_implemented;
        public static final Condition forbidden;
        public static final Condition gone;
        public static final Condition internal_server_error;
        public static final Condition item_not_found;
        public static final Condition jid_malformed;
        public static final Condition not_acceptable;
        public static final Condition not_allowed;
        public static final Condition not_authorized;
        public static final Condition policy_violation;
        public static final Condition recipient_unavailable;
        public static final Condition redirect;
        public static final Condition registration_required;
        public static final Condition remote_server_not_found;
        public static final Condition remote_server_timeout;
        public static final Condition resource_constraint;
        public static final Condition service_unavailable;
        public static final Condition subscription_required;
        public static final Condition undefined_condition;
        public static final Condition unexpected_request;

        public static Condition fromString(String s)
        {
            Object obj = s;
            if ("xml-not-well-formed".equals(s))
            {
                obj = "not-well-formed";
            }
            s = ((String) (obj)).replace('-', '_');
            try
            {
                obj = valueOf(s);
            }
            catch (Exception exception)
            {
                throw new IllegalStateException((new StringBuilder()).append("Could not transform string '").append(s).append("' to XMPPErrorCondition").toString(), exception);
            }
            return ((Condition) (obj));
        }

        public static Condition valueOf(String s)
        {
            return (Condition)Enum.valueOf(org/jivesoftware/smack/packet/XMPPError$Condition, s);
        }

        public static Condition[] values()
        {
            return (Condition[])$VALUES.clone();
        }

        public String toString()
        {
            return name().replace('_', '-');
        }

        static 
        {
            bad_request = new Condition("bad_request", 0);
            conflict = new Condition("conflict", 1);
            feature_not_implemented = new Condition("feature_not_implemented", 2);
            forbidden = new Condition("forbidden", 3);
            gone = new Condition("gone", 4);
            internal_server_error = new Condition("internal_server_error", 5);
            item_not_found = new Condition("item_not_found", 6);
            jid_malformed = new Condition("jid_malformed", 7);
            not_acceptable = new Condition("not_acceptable", 8);
            not_allowed = new Condition("not_allowed", 9);
            not_authorized = new Condition("not_authorized", 10);
            policy_violation = new Condition("policy_violation", 11);
            recipient_unavailable = new Condition("recipient_unavailable", 12);
            redirect = new Condition("redirect", 13);
            registration_required = new Condition("registration_required", 14);
            remote_server_not_found = new Condition("remote_server_not_found", 15);
            remote_server_timeout = new Condition("remote_server_timeout", 16);
            resource_constraint = new Condition("resource_constraint", 17);
            service_unavailable = new Condition("service_unavailable", 18);
            subscription_required = new Condition("subscription_required", 19);
            undefined_condition = new Condition("undefined_condition", 20);
            unexpected_request = new Condition("unexpected_request", 21);
            $VALUES = (new Condition[] {
                bad_request, conflict, feature_not_implemented, forbidden, gone, internal_server_error, item_not_found, jid_malformed, not_acceptable, not_allowed, 
                not_authorized, policy_violation, recipient_unavailable, redirect, registration_required, remote_server_not_found, remote_server_timeout, resource_constraint, service_unavailable, subscription_required, 
                undefined_condition, unexpected_request
            });
        }

        private Condition(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type AUTH;
        public static final Type CANCEL;
        public static final Type CONTINUE;
        public static final Type MODIFY;
        public static final Type WAIT;

        public static Type fromString(String s)
        {
            return valueOf(s.toUpperCase());
        }

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smack/packet/XMPPError$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        public String toString()
        {
            return name().toLowerCase();
        }

        static 
        {
            WAIT = new Type("WAIT", 0);
            CANCEL = new Type("CANCEL", 1);
            MODIFY = new Type("MODIFY", 2);
            AUTH = new Type("AUTH", 3);
            CONTINUE = new Type("CONTINUE", 4);
            $VALUES = (new Type[] {
                WAIT, CANCEL, MODIFY, AUTH, CONTINUE
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    private static final Map CONDITION_TO_TYPE;
    public static final String ERROR = "error";
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smack/packet/XMPPError.getName());
    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-stanzas";
    private final Condition condition;
    private final String conditionText;
    private final String errorGenerator;
    private final Type type;

    public XMPPError(Condition condition1)
    {
        this(condition1, null, null, null, null, null);
    }

    public XMPPError(Condition condition1, String s, String s1, Type type1, Map map, List list)
    {
        super(map, "urn:ietf:params:xml:ns:xmpp-stanzas", list);
        condition = condition1;
        map = s;
        if (StringUtils.isNullOrEmpty(s))
        {
            map = null;
        }
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition = new int[Condition.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[Condition.gone.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$XMPPError$Condition[Condition.redirect.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        if (map != null)
        {
            switch (_cls1..SwitchMap.org.jivesoftware.smack.packet.XMPPError.Condition[condition1.ordinal()])
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Condition text can only be set with condtion types 'gone' and 'redirect', not ").append(condition1).toString());

            case 1: // '\001'
            case 2: // '\002'
                break;
            }
        }
        conditionText = map;
        errorGenerator = s1;
        if (type1 == null)
        {
            s1 = (Type)CONDITION_TO_TYPE.get(condition1);
            s = s1;
            if (s1 == null)
            {
                LOGGER.warning((new StringBuilder()).append("Could not determine type for condition: ").append(condition1).toString());
                s = Type.CANCEL;
            }
            type = s;
            return;
        } else
        {
            type = type1;
            return;
        }
    }

    public XMPPError(Condition condition1, ExtensionElement extensionelement)
    {
        this(condition1, null, null, null, null, Arrays.asList(new ExtensionElement[] {
            extensionelement
        }));
    }

    public static XMPPError from(Condition condition1, String s)
    {
        HashMap hashmap = new HashMap();
        hashmap.put("en", s);
        return new XMPPError(condition1, null, null, null, hashmap, null);
    }

    public Condition getCondition()
    {
        return condition;
    }

    public String getConditionText()
    {
        return conditionText;
    }

    public String getErrorGenerator()
    {
        return errorGenerator;
    }

    public Type getType()
    {
        return type;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("XMPPError: ");
        stringbuilder.append(condition.toString()).append(" - ").append(type.toString());
        if (errorGenerator != null)
        {
            stringbuilder.append(". Generated by ").append(errorGenerator);
        }
        return stringbuilder.toString();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("error");
        xmlstringbuilder.attribute("type", type.toString());
        xmlstringbuilder.optAttribute("by", errorGenerator);
        xmlstringbuilder.rightAngleBracket();
        xmlstringbuilder.halfOpenElement(condition.toString());
        xmlstringbuilder.xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-stanzas");
        if (conditionText != null)
        {
            xmlstringbuilder.rightAngleBracket();
            xmlstringbuilder.escape(conditionText);
            xmlstringbuilder.closeElement(condition.toString());
        } else
        {
            xmlstringbuilder.closeEmptyElement();
        }
        addDescriptiveTextsAndExtensions(xmlstringbuilder);
        xmlstringbuilder.closeElement("error");
        return xmlstringbuilder;
    }

    static 
    {
        CONDITION_TO_TYPE = new HashMap();
        CONDITION_TO_TYPE.put(Condition.bad_request, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.conflict, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.feature_not_implemented, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.forbidden, Type.AUTH);
        CONDITION_TO_TYPE.put(Condition.gone, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.internal_server_error, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.item_not_found, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.jid_malformed, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.not_acceptable, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.not_allowed, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.not_authorized, Type.AUTH);
        CONDITION_TO_TYPE.put(Condition.policy_violation, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.recipient_unavailable, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.redirect, Type.MODIFY);
        CONDITION_TO_TYPE.put(Condition.registration_required, Type.AUTH);
        CONDITION_TO_TYPE.put(Condition.remote_server_not_found, Type.CANCEL);
        CONDITION_TO_TYPE.put(Condition.remote_server_timeout, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.resource_constraint, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.service_unavailable, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.subscription_required, Type.WAIT);
        CONDITION_TO_TYPE.put(Condition.unexpected_request, Type.MODIFY);
    }
}
