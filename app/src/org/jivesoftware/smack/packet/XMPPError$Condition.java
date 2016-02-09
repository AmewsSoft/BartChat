// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;


// Referenced classes of package org.jivesoftware.smack.packet:
//            XMPPError

public static final class  extends Enum
{

    private static final unexpected_request $VALUES[];
    public static final unexpected_request bad_request;
    public static final unexpected_request conflict;
    public static final unexpected_request feature_not_implemented;
    public static final unexpected_request forbidden;
    public static final unexpected_request gone;
    public static final unexpected_request internal_server_error;
    public static final unexpected_request item_not_found;
    public static final unexpected_request jid_malformed;
    public static final unexpected_request not_acceptable;
    public static final unexpected_request not_allowed;
    public static final unexpected_request not_authorized;
    public static final unexpected_request policy_violation;
    public static final unexpected_request recipient_unavailable;
    public static final unexpected_request redirect;
    public static final unexpected_request registration_required;
    public static final unexpected_request remote_server_not_found;
    public static final unexpected_request remote_server_timeout;
    public static final unexpected_request resource_constraint;
    public static final unexpected_request service_unavailable;
    public static final unexpected_request subscription_required;
    public static final unexpected_request undefined_condition;
    public static final unexpected_request unexpected_request;

    public static  fromString(String s)
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
        return ((valueOf) (obj));
    }

    public static valueOf valueOf(String s)
    {
        return (valueOf)Enum.valueOf(org/jivesoftware/smack/packet/XMPPError$Condition, s);
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
        bad_request = new <init>("bad_request", 0);
        conflict = new <init>("conflict", 1);
        feature_not_implemented = new <init>("feature_not_implemented", 2);
        forbidden = new <init>("forbidden", 3);
        gone = new <init>("gone", 4);
        internal_server_error = new <init>("internal_server_error", 5);
        item_not_found = new <init>("item_not_found", 6);
        jid_malformed = new <init>("jid_malformed", 7);
        not_acceptable = new <init>("not_acceptable", 8);
        not_allowed = new <init>("not_allowed", 9);
        not_authorized = new <init>("not_authorized", 10);
        policy_violation = new <init>("policy_violation", 11);
        recipient_unavailable = new <init>("recipient_unavailable", 12);
        redirect = new <init>("redirect", 13);
        registration_required = new <init>("registration_required", 14);
        remote_server_not_found = new <init>("remote_server_not_found", 15);
        remote_server_timeout = new <init>("remote_server_timeout", 16);
        resource_constraint = new <init>("resource_constraint", 17);
        service_unavailable = new <init>("service_unavailable", 18);
        subscription_required = new <init>("subscription_required", 19);
        undefined_condition = new <init>("undefined_condition", 20);
        unexpected_request = new <init>("unexpected_request", 21);
        $VALUES = (new .VALUES[] {
            bad_request, conflict, feature_not_implemented, forbidden, gone, internal_server_error, item_not_found, jid_malformed, not_acceptable, not_allowed, 
            not_authorized, policy_violation, recipient_unavailable, redirect, registration_required, remote_server_not_found, remote_server_timeout, resource_constraint, service_unavailable, subscription_required, 
            undefined_condition, unexpected_request
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
