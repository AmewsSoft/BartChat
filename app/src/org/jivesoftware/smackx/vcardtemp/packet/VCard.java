// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.vcardtemp.packet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jivesoftware.smackx.vcardtemp.VCardManager;

public class VCard extends IQ
{

    private static final String DEFAULT_MIME_TYPE = "image/jpeg";
    public static final String ELEMENT = "vCard";
    private static final Logger LOGGER = Logger.getLogger(org/jivesoftware/smackx/vcardtemp/packet/VCard.getName());
    public static final String NAMESPACE = "vcard-temp";
    private String emailHome;
    private String emailWork;
    private String firstName;
    private Map homeAddr;
    private Map homePhones;
    private String lastName;
    private String middleName;
    private String organization;
    private String organizationUnit;
    private Map otherSimpleFields;
    private Map otherUnescapableFields;
    private String photoBinval;
    private String photoMimeType;
    private Map workAddr;
    private Map workPhones;

    public VCard()
    {
        super("vCard", "vcard-temp");
        homePhones = new HashMap();
        workPhones = new HashMap();
        homeAddr = new HashMap();
        workAddr = new HashMap();
        otherSimpleFields = new HashMap();
        otherUnescapableFields = new HashMap();
    }

    private void copyFieldsFrom(VCard vcard)
    {
        Field afield[] = org/jivesoftware/smackx/vcardtemp/packet/VCard.getDeclaredFields();
        int j = afield.length;
        int i = 0;
        while (i < j) 
        {
            Field field = afield[i];
            if (field.getDeclaringClass() == org/jivesoftware/smackx/vcardtemp/packet/VCard && !Modifier.isFinal(field.getModifiers()))
            {
                try
                {
                    field.setAccessible(true);
                    field.set(this, field.get(vcard));
                }
                // Misplaced declaration of an exception variable
                catch (VCard vcard)
                {
                    throw new RuntimeException((new StringBuilder()).append("This cannot happen:").append(field).toString(), vcard);
                }
            }
            i++;
        }
    }

    public static byte[] getBytes(URL url)
        throws IOException
    {
        url = new File(url.getPath());
        if (url.exists())
        {
            return getFileBytes(url);
        } else
        {
            return null;
        }
    }

    private static byte[] getFileBytes(File file)
        throws IOException
    {
        Exception exception = null;
        Object obj = new BufferedInputStream(new FileInputStream(file));
        file = new byte[(int)file.length()];
        if (((BufferedInputStream) (obj)).read(file) != file.length)
        {
            throw new IOException("Entire file not read");
        }
          goto _L1
        exception;
        file = ((File) (obj));
        obj = exception;
_L3:
        if (file != null)
        {
            file.close();
        }
        throw obj;
_L1:
        if (obj != null)
        {
            ((BufferedInputStream) (obj)).close();
        }
        return file;
        obj;
        file = exception;
        if (true) goto _L3; else goto _L2
_L2:
    }

    private boolean hasContent()
    {
        return hasNameField() || hasOrganizationFields() || emailHome != null || emailWork != null || otherSimpleFields.size() > 0 || otherUnescapableFields.size() > 0 || homeAddr.size() > 0 || homePhones.size() > 0 || workAddr.size() > 0 || workPhones.size() > 0 || photoBinval != null;
    }

    private boolean hasNameField()
    {
        return firstName != null || lastName != null || middleName != null;
    }

    private boolean hasOrganizationFields()
    {
        return organization != null || organizationUnit != null;
    }

    private void updateFN()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (firstName != null)
        {
            stringbuilder.append(StringUtils.escapeForXML(firstName)).append(' ');
        }
        if (middleName != null)
        {
            stringbuilder.append(StringUtils.escapeForXML(middleName)).append(' ');
        }
        if (lastName != null)
        {
            stringbuilder.append(StringUtils.escapeForXML(lastName));
        }
        setField("FN", stringbuilder.toString());
    }

    public boolean equals(Object obj)
    {
        boolean flag1 = false;
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag1;
        if (obj == null) goto _L4; else goto _L3
_L3:
        flag = flag1;
        if (getClass() != obj.getClass()) goto _L4; else goto _L5
_L5:
        obj = (VCard)obj;
        if (emailHome == null) goto _L7; else goto _L6
_L6:
        flag = flag1;
        if (!emailHome.equals(((VCard) (obj)).emailHome)) goto _L4; else goto _L8
_L8:
        if (emailWork == null) goto _L10; else goto _L9
_L9:
        flag = flag1;
        if (!emailWork.equals(((VCard) (obj)).emailWork)) goto _L4; else goto _L11
_L11:
        if (firstName == null) goto _L13; else goto _L12
_L12:
        flag = flag1;
        if (!firstName.equals(((VCard) (obj)).firstName)) goto _L4; else goto _L14
_L14:
        flag = flag1;
        if (!homeAddr.equals(((VCard) (obj)).homeAddr)) goto _L4; else goto _L15
_L15:
        flag = flag1;
        if (!homePhones.equals(((VCard) (obj)).homePhones)) goto _L4; else goto _L16
_L16:
        if (lastName == null) goto _L18; else goto _L17
_L17:
        flag = flag1;
        if (!lastName.equals(((VCard) (obj)).lastName)) goto _L4; else goto _L19
_L19:
        if (middleName == null) goto _L21; else goto _L20
_L20:
        flag = flag1;
        if (!middleName.equals(((VCard) (obj)).middleName)) goto _L4; else goto _L22
_L22:
        if (organization == null) goto _L24; else goto _L23
_L23:
        flag = flag1;
        if (!organization.equals(((VCard) (obj)).organization)) goto _L4; else goto _L25
_L25:
        if (organizationUnit == null) goto _L27; else goto _L26
_L26:
        flag = flag1;
        if (!organizationUnit.equals(((VCard) (obj)).organizationUnit)) goto _L4; else goto _L28
_L28:
        flag = flag1;
        if (!otherSimpleFields.equals(((VCard) (obj)).otherSimpleFields)) goto _L4; else goto _L29
_L29:
        flag = flag1;
        if (!workAddr.equals(((VCard) (obj)).workAddr)) goto _L4; else goto _L30
_L30:
        if (photoBinval == null)
        {
            break MISSING_BLOCK_LABEL_368;
        }
        flag = flag1;
        if (!photoBinval.equals(((VCard) (obj)).photoBinval)) goto _L4; else goto _L31
_L31:
        return workPhones.equals(((VCard) (obj)).workPhones);
_L7:
        if (((VCard) (obj)).emailHome != null)
        {
            return false;
        }
          goto _L8
_L10:
        if (((VCard) (obj)).emailWork != null)
        {
            return false;
        }
          goto _L11
_L13:
        if (((VCard) (obj)).firstName != null)
        {
            return false;
        }
          goto _L14
_L18:
        if (((VCard) (obj)).lastName != null)
        {
            return false;
        }
          goto _L19
_L21:
        if (((VCard) (obj)).middleName != null)
        {
            return false;
        }
          goto _L22
_L24:
        if (((VCard) (obj)).organization != null)
        {
            return false;
        }
          goto _L25
_L27:
        if (((VCard) (obj)).organizationUnit != null)
        {
            return false;
        }
          goto _L28
        if (((VCard) (obj)).photoBinval != null)
        {
            return false;
        }
          goto _L31
    }

    public String getAddressFieldHome(String s)
    {
        return (String)homeAddr.get(s);
    }

    public String getAddressFieldWork(String s)
    {
        return (String)workAddr.get(s);
    }

    public byte[] getAvatar()
    {
        if (photoBinval == null)
        {
            return null;
        } else
        {
            return Base64.decode(photoBinval);
        }
    }

    public String getAvatarHash()
    {
        byte abyte0[] = getAvatar();
        if (abyte0 == null)
        {
            return null;
        }
        MessageDigest messagedigest;
        try
        {
            messagedigest = MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            LOGGER.log(Level.SEVERE, "Failed to get message digest", nosuchalgorithmexception);
            return null;
        }
        messagedigest.update(abyte0);
        return StringUtils.encodeHex(messagedigest.digest());
    }

    public String getAvatarMimeType()
    {
        return photoMimeType;
    }

    public String getEmailHome()
    {
        return emailHome;
    }

    public String getEmailWork()
    {
        return emailWork;
    }

    public String getField(String s)
    {
        return (String)otherSimpleFields.get(s);
    }

    public String getFirstName()
    {
        return firstName;
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        if (!hasContent())
        {
            iqchildelementxmlstringbuilder.setEmptyElement();
        } else
        {
            iqchildelementxmlstringbuilder.rightAngleBracket();
            if (hasNameField())
            {
                iqchildelementxmlstringbuilder.openElement("N");
                iqchildelementxmlstringbuilder.optElement("FAMILY", lastName);
                iqchildelementxmlstringbuilder.optElement("GIVEN", firstName);
                iqchildelementxmlstringbuilder.optElement("MIDDLE", middleName);
                iqchildelementxmlstringbuilder.closeElement("N");
            }
            if (hasOrganizationFields())
            {
                iqchildelementxmlstringbuilder.openElement("ORG");
                iqchildelementxmlstringbuilder.optElement("ORGNAME", organization);
                iqchildelementxmlstringbuilder.optElement("ORGUNIT", organizationUnit);
                iqchildelementxmlstringbuilder.closeElement("ORG");
            }
            java.util.Map.Entry entry;
            for (Iterator iterator = otherSimpleFields.entrySet().iterator(); iterator.hasNext(); iqchildelementxmlstringbuilder.optElement((String)entry.getKey(), (String)entry.getValue()))
            {
                entry = (java.util.Map.Entry)iterator.next();
            }

            Iterator iterator1 = otherUnescapableFields.entrySet().iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator1.next();
                String s = (String)entry1.getValue();
                if (s != null)
                {
                    iqchildelementxmlstringbuilder.openElement((String)entry1.getKey());
                    iqchildelementxmlstringbuilder.append(s);
                    iqchildelementxmlstringbuilder.closeElement((String)entry1.getKey());
                }
            } while (true);
            if (photoBinval != null)
            {
                iqchildelementxmlstringbuilder.openElement("PHOTO");
                iqchildelementxmlstringbuilder.escapedElement("BINVAL", photoBinval);
                iqchildelementxmlstringbuilder.element("TYPE", photoMimeType);
                iqchildelementxmlstringbuilder.closeElement("PHOTO");
            }
            if (emailWork != null)
            {
                iqchildelementxmlstringbuilder.openElement("EMAIL");
                iqchildelementxmlstringbuilder.emptyElement("WORK");
                iqchildelementxmlstringbuilder.emptyElement("INTERNET");
                iqchildelementxmlstringbuilder.emptyElement("PREF");
                iqchildelementxmlstringbuilder.element("USERID", emailWork);
                iqchildelementxmlstringbuilder.closeElement("EMAIL");
            }
            if (emailHome != null)
            {
                iqchildelementxmlstringbuilder.openElement("EMAIL");
                iqchildelementxmlstringbuilder.emptyElement("HOME");
                iqchildelementxmlstringbuilder.emptyElement("INTERNET");
                iqchildelementxmlstringbuilder.emptyElement("PREF");
                iqchildelementxmlstringbuilder.element("USERID", emailHome);
                iqchildelementxmlstringbuilder.closeElement("EMAIL");
            }
            iterator1 = workPhones.entrySet().iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry2 = (java.util.Map.Entry)iterator1.next();
                String s1 = (String)entry2.getValue();
                if (s1 != null)
                {
                    iqchildelementxmlstringbuilder.openElement("TEL");
                    iqchildelementxmlstringbuilder.emptyElement("WORK");
                    iqchildelementxmlstringbuilder.emptyElement((String)entry2.getKey());
                    iqchildelementxmlstringbuilder.element("NUMBER", s1);
                    iqchildelementxmlstringbuilder.closeElement("TEL");
                }
            } while (true);
            iterator1 = homePhones.entrySet().iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry3 = (java.util.Map.Entry)iterator1.next();
                String s2 = (String)entry3.getValue();
                if (s2 != null)
                {
                    iqchildelementxmlstringbuilder.openElement("TEL");
                    iqchildelementxmlstringbuilder.emptyElement("HOME");
                    iqchildelementxmlstringbuilder.emptyElement((String)entry3.getKey());
                    iqchildelementxmlstringbuilder.element("NUMBER", s2);
                    iqchildelementxmlstringbuilder.closeElement("TEL");
                }
            } while (true);
            if (!workAddr.isEmpty())
            {
                iqchildelementxmlstringbuilder.openElement("ADR");
                iqchildelementxmlstringbuilder.emptyElement("WORK");
                Iterator iterator2 = workAddr.entrySet().iterator();
                do
                {
                    if (!iterator2.hasNext())
                    {
                        break;
                    }
                    java.util.Map.Entry entry4 = (java.util.Map.Entry)iterator2.next();
                    String s3 = (String)entry4.getValue();
                    if (s3 != null)
                    {
                        iqchildelementxmlstringbuilder.element((String)entry4.getKey(), s3);
                    }
                } while (true);
                iqchildelementxmlstringbuilder.closeElement("ADR");
            }
            if (!homeAddr.isEmpty())
            {
                iqchildelementxmlstringbuilder.openElement("ADR");
                iqchildelementxmlstringbuilder.emptyElement("HOME");
                Iterator iterator3 = homeAddr.entrySet().iterator();
                do
                {
                    if (!iterator3.hasNext())
                    {
                        break;
                    }
                    java.util.Map.Entry entry5 = (java.util.Map.Entry)iterator3.next();
                    String s4 = (String)entry5.getValue();
                    if (s4 != null)
                    {
                        iqchildelementxmlstringbuilder.element((String)entry5.getKey(), s4);
                    }
                } while (true);
                iqchildelementxmlstringbuilder.closeElement("ADR");
                return iqchildelementxmlstringbuilder;
            }
        }
        return iqchildelementxmlstringbuilder;
    }

    public String getJabberId()
    {
        return (String)otherSimpleFields.get("JABBERID");
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public String getNickName()
    {
        return (String)otherSimpleFields.get("NICKNAME");
    }

    public String getOrganization()
    {
        return organization;
    }

    public String getOrganizationUnit()
    {
        return organizationUnit;
    }

    public String getPhoneHome(String s)
    {
        return (String)homePhones.get(s);
    }

    public String getPhoneWork(String s)
    {
        return (String)workPhones.get(s);
    }

    public int hashCode()
    {
        int l1 = 0;
        int i2 = homePhones.hashCode();
        int j2 = workPhones.hashCode();
        int k2 = homeAddr.hashCode();
        int l2 = workAddr.hashCode();
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int i3;
        if (firstName != null)
        {
            i = firstName.hashCode();
        } else
        {
            i = 0;
        }
        if (lastName != null)
        {
            j = lastName.hashCode();
        } else
        {
            j = 0;
        }
        if (middleName != null)
        {
            k = middleName.hashCode();
        } else
        {
            k = 0;
        }
        if (emailHome != null)
        {
            l = emailHome.hashCode();
        } else
        {
            l = 0;
        }
        if (emailWork != null)
        {
            i1 = emailWork.hashCode();
        } else
        {
            i1 = 0;
        }
        if (organization != null)
        {
            j1 = organization.hashCode();
        } else
        {
            j1 = 0;
        }
        if (organizationUnit != null)
        {
            k1 = organizationUnit.hashCode();
        } else
        {
            k1 = 0;
        }
        i3 = otherSimpleFields.hashCode();
        if (photoBinval != null)
        {
            l1 = photoBinval.hashCode();
        }
        return (((((((((((i2 * 29 + j2) * 29 + k2) * 29 + l2) * 29 + i) * 29 + j) * 29 + k) * 29 + l) * 29 + i1) * 29 + j1) * 29 + k1) * 29 + i3) * 29 + l1;
    }

    public void load(XMPPConnection xmppconnection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        load(xmppconnection, null);
    }

    public void load(XMPPConnection xmppconnection, String s)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        copyFieldsFrom(VCardManager.getInstanceFor(xmppconnection).loadVCard(s));
    }

    public void removeAvatar()
    {
        photoBinval = null;
        photoMimeType = null;
    }

    public void save(XMPPConnection xmppconnection)
        throws org.jivesoftware.smack.SmackException.NoResponseException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NotConnectedException
    {
        VCardManager.getInstanceFor(xmppconnection).saveVCard(this);
    }

    public void setAddressFieldHome(String s, String s1)
    {
        homeAddr.put(s, s1);
    }

    public void setAddressFieldWork(String s, String s1)
    {
        workAddr.put(s, s1);
    }

    public void setAvatar(String s, String s1)
    {
        photoBinval = s;
        photoMimeType = s1;
    }

    public void setAvatar(URL url)
    {
        byte abyte0[] = new byte[0];
        byte abyte1[] = getBytes(url);
        url = abyte1;
_L2:
        setAvatar(((byte []) (url)));
        return;
        IOException ioexception;
        ioexception;
        LOGGER.log(Level.SEVERE, (new StringBuilder()).append("Error getting bytes from URL: ").append(url).toString(), ioexception);
        url = abyte0;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void setAvatar(byte abyte0[])
    {
        setAvatar(abyte0, "image/jpeg");
    }

    public void setAvatar(byte abyte0[], String s)
    {
        if (abyte0 == null)
        {
            removeAvatar();
            return;
        } else
        {
            setAvatar(Base64.encodeToString(abyte0), s);
            return;
        }
    }

    public void setEmailHome(String s)
    {
        emailHome = s;
    }

    public void setEmailWork(String s)
    {
        emailWork = s;
    }

    public void setEncodedImage(String s)
    {
        setAvatar(s, "image/jpeg");
    }

    public void setField(String s, String s1)
    {
        setField(s, s1, false);
    }

    public void setField(String s, String s1, boolean flag)
    {
        if (!flag)
        {
            otherSimpleFields.put(s, s1);
            return;
        } else
        {
            otherUnescapableFields.put(s, s1);
            return;
        }
    }

    public void setFirstName(String s)
    {
        firstName = s;
        updateFN();
    }

    public void setJabberId(String s)
    {
        otherSimpleFields.put("JABBERID", s);
    }

    public void setLastName(String s)
    {
        lastName = s;
        updateFN();
    }

    public void setMiddleName(String s)
    {
        middleName = s;
        updateFN();
    }

    public void setNickName(String s)
    {
        otherSimpleFields.put("NICKNAME", s);
    }

    public void setOrganization(String s)
    {
        organization = s;
    }

    public void setOrganizationUnit(String s)
    {
        organizationUnit = s;
    }

    public void setPhoneHome(String s, String s1)
    {
        homePhones.put(s, s1);
    }

    public void setPhoneWork(String s, String s1)
    {
        workPhones.put(s, s1);
    }

}
