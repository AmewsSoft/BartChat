// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            AbstractError, PlainStreamElement

public class StreamError extends AbstractError
    implements PlainStreamElement
{
    public static final class Condition extends Enum
    {

        private static final Condition $VALUES[];
        public static final Condition bad_format;
        public static final Condition bad_namespace_prefix;
        public static final Condition conflict;
        public static final Condition connection_timeout;
        public static final Condition host_gone;
        public static final Condition host_unknown;
        public static final Condition improper_addressing;
        public static final Condition internal_server_error;
        public static final Condition invalid_from;
        public static final Condition invalid_namespace;
        public static final Condition invalid_xml;
        public static final Condition not_authorized;
        public static final Condition not_well_formed;
        public static final Condition policy_violation;
        public static final Condition remote_connection_failed;
        public static final Condition reset;
        public static final Condition resource_constraint;
        public static final Condition restricted_xml;
        public static final Condition see_other_host;
        public static final Condition system_shutdown;
        public static final Condition undeficed_condition;
        public static final Condition unsupported_encoding;
        public static final Condition unsupported_feature;
        public static final Condition unsupported_stanza_type;
        public static final Condition unsupported_version;

        public static Condition fromString(String s)
        {
            s = s.replace('-', '_');
            Condition condition1;
            try
            {
                condition1 = valueOf(s);
            }
            catch (Exception exception)
            {
                throw new IllegalStateException((new StringBuilder()).append("Could not transform string '").append(s).append("' to XMPPErrorCondition").toString(), exception);
            }
            return condition1;
        }

        public static Condition valueOf(String s)
        {
            return (Condition)Enum.valueOf(org/jivesoftware/smack/packet/StreamError$Condition, s);
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
            bad_format = new Condition("bad_format", 0);
            bad_namespace_prefix = new Condition("bad_namespace_prefix", 1);
            conflict = new Condition("conflict", 2);
            connection_timeout = new Condition("connection_timeout", 3);
            host_gone = new Condition("host_gone", 4);
            host_unknown = new Condition("host_unknown", 5);
            improper_addressing = new Condition("improper_addressing", 6);
            internal_server_error = new Condition("internal_server_error", 7);
            invalid_from = new Condition("invalid_from", 8);
            invalid_namespace = new Condition("invalid_namespace", 9);
            invalid_xml = new Condition("invalid_xml", 10);
            not_authorized = new Condition("not_authorized", 11);
            not_well_formed = new Condition("not_well_formed", 12);
            policy_violation = new Condition("policy_violation", 13);
            remote_connection_failed = new Condition("remote_connection_failed", 14);
            reset = new Condition("reset", 15);
            resource_constraint = new Condition("resource_constraint", 16);
            restricted_xml = new Condition("restricted_xml", 17);
            see_other_host = new Condition("see_other_host", 18);
            system_shutdown = new Condition("system_shutdown", 19);
            undeficed_condition = new Condition("undeficed_condition", 20);
            unsupported_encoding = new Condition("unsupported_encoding", 21);
            unsupported_feature = new Condition("unsupported_feature", 22);
            unsupported_stanza_type = new Condition("unsupported_stanza_type", 23);
            unsupported_version = new Condition("unsupported_version", 24);
            $VALUES = (new Condition[] {
                bad_format, bad_namespace_prefix, conflict, connection_timeout, host_gone, host_unknown, improper_addressing, internal_server_error, invalid_from, invalid_namespace, 
                invalid_xml, not_authorized, not_well_formed, policy_violation, remote_connection_failed, reset, resource_constraint, restricted_xml, see_other_host, system_shutdown, 
                undeficed_condition, unsupported_encoding, unsupported_feature, unsupported_stanza_type, unsupported_version
            });
        }

        private Condition(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String ELEMENT = "stream:error";
    public static final String NAMESPACE = "urn:ietf:params:xml:ns:xmpp-streams";
    private final Condition condition;
    private final String conditionText;

    public StreamError(Condition condition1, String s, Map map, List list)
    {
        super(map, list);
        map = s;
        if (StringUtils.isNullOrEmpty(s))
        {
            map = null;
        }
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smack$packet$StreamError$Condition[];

            static 
            {
                $SwitchMap$org$jivesoftware$smack$packet$StreamError$Condition = new int[Condition.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smack$packet$StreamError$Condition[Condition.see_other_host.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
            }
        }

        if (map != null)
        {
            switch (_cls1..SwitchMap.org.jivesoftware.smack.packet.StreamError.Condition[condition1.ordinal()])
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("The given condition '").append(condition1).append("' can not contain a conditionText").toString());

            case 1: // '\001'
                break;
            }
        }
        condition = condition1;
        conditionText = map;
    }

    public Condition getCondition()
    {
        return condition;
    }

    public String getConditionText()
    {
        return conditionText;
    }

    public String toString()
    {
        return toXML().toString();
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.openElement("stream:error");
        xmlstringbuilder.halfOpenElement(condition.toString()).xmlnsAttribute("urn:ietf:params:xml:ns:xmpp-streams").closeEmptyElement();
        addDescriptiveTextsAndExtensions(xmlstringbuilder);
        xmlstringbuilder.closeElement("stream:error");
        return xmlstringbuilder;
    }
}
