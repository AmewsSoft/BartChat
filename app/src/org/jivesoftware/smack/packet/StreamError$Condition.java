// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;


// Referenced classes of package org.jivesoftware.smack.packet:
//            StreamError

public static final class _cls9 extends Enum
{

    private static final unsupported_version $VALUES[];
    public static final unsupported_version bad_format;
    public static final unsupported_version bad_namespace_prefix;
    public static final unsupported_version conflict;
    public static final unsupported_version connection_timeout;
    public static final unsupported_version host_gone;
    public static final unsupported_version host_unknown;
    public static final unsupported_version improper_addressing;
    public static final unsupported_version internal_server_error;
    public static final unsupported_version invalid_from;
    public static final unsupported_version invalid_namespace;
    public static final unsupported_version invalid_xml;
    public static final unsupported_version not_authorized;
    public static final unsupported_version not_well_formed;
    public static final unsupported_version policy_violation;
    public static final unsupported_version remote_connection_failed;
    public static final unsupported_version reset;
    public static final unsupported_version resource_constraint;
    public static final unsupported_version restricted_xml;
    public static final unsupported_version see_other_host;
    public static final unsupported_version system_shutdown;
    public static final unsupported_version undeficed_condition;
    public static final unsupported_version unsupported_encoding;
    public static final unsupported_version unsupported_feature;
    public static final unsupported_version unsupported_stanza_type;
    public static final unsupported_version unsupported_version;

    public static _cls9 fromString(String s)
    {
        s = s.replace('-', '_');
        _cls9 _lcls9;
        try
        {
            _lcls9 = valueOf(s);
        }
        catch (Exception exception)
        {
            throw new IllegalStateException((new StringBuilder()).append("Could not transform string '").append(s).append("' to XMPPErrorCondition").toString(), exception);
        }
        return _lcls9;
    }

    public static valueOf valueOf(String s)
    {
        return (valueOf)Enum.valueOf(org/jivesoftware/smack/packet/StreamError$Condition, s);
    }

    public static valueOf[] values()
    {
        return (valueOf[])$VALUES.clone();
    }

    public String toString()
    {
        return name().replace('_', '-');
    }

    static 
    {
        bad_format = new <init>("bad_format", 0);
        bad_namespace_prefix = new <init>("bad_namespace_prefix", 1);
        conflict = new <init>("conflict", 2);
        connection_timeout = new <init>("connection_timeout", 3);
        host_gone = new <init>("host_gone", 4);
        host_unknown = new <init>("host_unknown", 5);
        improper_addressing = new <init>("improper_addressing", 6);
        internal_server_error = new <init>("internal_server_error", 7);
        invalid_from = new <init>("invalid_from", 8);
        invalid_namespace = new <init>("invalid_namespace", 9);
        invalid_xml = new <init>("invalid_xml", 10);
        not_authorized = new <init>("not_authorized", 11);
        not_well_formed = new <init>("not_well_formed", 12);
        policy_violation = new <init>("policy_violation", 13);
        remote_connection_failed = new <init>("remote_connection_failed", 14);
        reset = new <init>("reset", 15);
        resource_constraint = new <init>("resource_constraint", 16);
        restricted_xml = new <init>("restricted_xml", 17);
        see_other_host = new <init>("see_other_host", 18);
        system_shutdown = new <init>("system_shutdown", 19);
        undeficed_condition = new <init>("undeficed_condition", 20);
        unsupported_encoding = new <init>("unsupported_encoding", 21);
        unsupported_feature = new <init>("unsupported_feature", 22);
        unsupported_stanza_type = new <init>("unsupported_stanza_type", 23);
        unsupported_version = new <init>("unsupported_version", 24);
        $VALUES = (new .VALUES[] {
            bad_format, bad_namespace_prefix, conflict, connection_timeout, host_gone, host_unknown, improper_addressing, internal_server_error, invalid_from, invalid_namespace, 
            invalid_xml, not_authorized, not_well_formed, policy_violation, remote_connection_failed, reset, resource_constraint, restricted_xml, see_other_host, system_shutdown, 
            undeficed_condition, unsupported_encoding, unsupported_feature, unsupported_stanza_type, unsupported_version
        });
    }

    private _cls9(String s, int i)
    {
        super(s, i);
    }
}
