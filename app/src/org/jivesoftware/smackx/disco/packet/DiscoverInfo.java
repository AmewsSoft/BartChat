// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.disco.packet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.TypedCloneable;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jxmpp.util.XmppStringUtils;

public class DiscoverInfo extends IQ
    implements TypedCloneable
{
    public static class Feature
        implements TypedCloneable
    {

        private final String variable;

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Feature clone()
        {
            return new Feature(this);
        }

        public boolean equals(Object obj)
        {
            if (obj != null)
            {
                if (obj == this)
                {
                    return true;
                }
                if (obj.getClass() == getClass())
                {
                    obj = (Feature)obj;
                    return variable.equals(((Feature) (obj)).variable);
                }
            }
            return false;
        }

        public String getVar()
        {
            return variable;
        }

        public int hashCode()
        {
            return variable.hashCode();
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
            xmlstringbuilder.halfOpenElement("feature");
            xmlstringbuilder.attribute("var", variable);
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }

        public Feature(String s)
        {
            variable = (String)StringUtils.requireNotNullOrEmpty(s, "variable cannot be null");
        }

        public Feature(Feature feature)
        {
            variable = feature.variable;
        }
    }

    public static class Identity
        implements Comparable, TypedCloneable
    {

        private final String category;
        private final String key;
        private final String lang;
        private final String name;
        private final String type;

        private String getKey()
        {
            return key;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public Identity clone()
        {
            return new Identity(this);
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((Identity)obj);
        }

        public int compareTo(Identity identity)
        {
            String s;
            String s1;
            String s2;
            String s3;
            if (identity.lang == null)
            {
                s = "";
            } else
            {
                s = identity.lang;
            }
            if (lang == null)
            {
                s1 = "";
            } else
            {
                s1 = lang;
            }
            if (identity.type == null)
            {
                s2 = "";
            } else
            {
                s2 = identity.type;
            }
            if (type == null)
            {
                s3 = "";
            } else
            {
                s3 = type;
            }
            if (category.equals(identity.category))
            {
                if (s3.equals(s2))
                {
                    if (s1.equals(s))
                    {
                        return 0;
                    } else
                    {
                        return s1.compareTo(s);
                    }
                } else
                {
                    return s3.compareTo(s2);
                }
            } else
            {
                return category.compareTo(identity.category);
            }
        }

        public boolean equals(Object obj)
        {
            if (obj != null)
            {
                if (obj == this)
                {
                    return true;
                }
                if (obj.getClass() == getClass())
                {
                    Identity identity = (Identity)obj;
                    if (key.equals(identity.key))
                    {
                        String s;
                        if (identity.lang == null)
                        {
                            obj = "";
                        } else
                        {
                            obj = identity.lang;
                        }
                        if (lang == null)
                        {
                            s = "";
                        } else
                        {
                            s = lang;
                        }
                        if (((String) (obj)).equals(s))
                        {
                            if (identity.name == null)
                            {
                                obj = "";
                            } else
                            {
                                obj = identity.name;
                            }
                            if (name == null)
                            {
                                s = "";
                            } else
                            {
                                s = identity.name;
                            }
                            if (s.equals(obj))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        public String getCategory()
        {
            return category;
        }

        public String getLanguage()
        {
            return lang;
        }

        public String getName()
        {
            return name;
        }

        public String getType()
        {
            return type;
        }

        public int hashCode()
        {
            int j = 0;
            int k = key.hashCode();
            int i;
            if (lang == null)
            {
                i = 0;
            } else
            {
                i = lang.hashCode();
            }
            if (name != null)
            {
                j = name.hashCode();
            }
            return ((k + 37) * 37 + i) * 37 + j;
        }

        public boolean isOfCategoryAndType(String s, String s1)
        {
            return category.equals(s) && type.equals(s1);
        }

        public XmlStringBuilder toXML()
        {
            XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
            xmlstringbuilder.halfOpenElement("identity");
            xmlstringbuilder.xmllangAttribute(lang);
            xmlstringbuilder.attribute("category", category);
            xmlstringbuilder.optAttribute("name", name);
            xmlstringbuilder.optAttribute("type", type);
            xmlstringbuilder.closeEmptyElement();
            return xmlstringbuilder;
        }


        public Identity(String s, String s1)
        {
            this(s, s1, null, null);
        }

        public Identity(String s, String s1, String s2)
        {
            this(s, s2, s1, null);
        }

        public Identity(String s, String s1, String s2, String s3)
        {
            category = (String)StringUtils.requireNotNullOrEmpty(s, "category cannot be null");
            type = (String)StringUtils.requireNotNullOrEmpty(s1, "type cannot be null");
            key = XmppStringUtils.generateKey(s, s1);
            name = s2;
            lang = s3;
        }

        public Identity(Identity identity)
        {
            category = identity.category;
            type = identity.type;
            key = identity.type;
            name = identity.name;
            lang = identity.lang;
        }
    }


    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "http://jabber.org/protocol/disco#info";
    private boolean containsDuplicateFeatures;
    private final List features;
    private final Set featuresSet;
    private final List identities;
    private final Set identitiesSet;
    private String node;

    public DiscoverInfo()
    {
        super("query", "http://jabber.org/protocol/disco#info");
        features = new LinkedList();
        featuresSet = new HashSet();
        identities = new LinkedList();
        identitiesSet = new HashSet();
    }

    public DiscoverInfo(DiscoverInfo discoverinfo)
    {
        super(discoverinfo);
        features = new LinkedList();
        featuresSet = new HashSet();
        identities = new LinkedList();
        identitiesSet = new HashSet();
        setNode(discoverinfo.getNode());
        for (Iterator iterator = discoverinfo.features.iterator(); iterator.hasNext(); addFeature(((Feature)iterator.next()).clone())) { }
        for (discoverinfo = discoverinfo.identities.iterator(); discoverinfo.hasNext(); addIdentity(((Identity)discoverinfo.next()).clone())) { }
    }

    public boolean addFeature(String s)
    {
        return addFeature(new Feature(s));
    }

    public boolean addFeature(Feature feature)
    {
        features.add(feature);
        boolean flag = featuresSet.add(feature);
        if (!flag)
        {
            containsDuplicateFeatures = true;
        }
        return flag;
    }

    public void addFeatures(Collection collection)
    {
        if (collection != null)
        {
            collection = collection.iterator();
            while (collection.hasNext()) 
            {
                addFeature((String)collection.next());
            }
        }
    }

    public void addIdentities(Collection collection)
    {
        if (collection != null)
        {
            collection = collection.iterator();
            while (collection.hasNext()) 
            {
                addIdentity((Identity)collection.next());
            }
        }
    }

    public void addIdentity(Identity identity)
    {
        identities.add(identity);
        identitiesSet.add(identity.getKey());
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public DiscoverInfo clone()
    {
        return new DiscoverInfo(this);
    }

    public boolean containsDuplicateFeatures()
    {
        return containsDuplicateFeatures;
    }

    public boolean containsDuplicateIdentities()
    {
        LinkedList linkedlist = new LinkedList();
        Iterator iterator = identities.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Identity identity = (Identity)iterator.next();
            for (Iterator iterator1 = linkedlist.iterator(); iterator1.hasNext();)
            {
                if (identity.equals((Identity)iterator1.next()))
                {
                    return true;
                }
            }

            linkedlist.add(identity);
        } while (true);
        return false;
    }

    public boolean containsFeature(String s)
    {
        return features.contains(new Feature(s));
    }

    public List getFeatures()
    {
        return Collections.unmodifiableList(features);
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.optAttribute("node", getNode());
        iqchildelementxmlstringbuilder.rightAngleBracket();
        for (Iterator iterator = identities.iterator(); iterator.hasNext(); iqchildelementxmlstringbuilder.append(((Identity)iterator.next()).toXML())) { }
        for (Iterator iterator1 = features.iterator(); iterator1.hasNext(); iqchildelementxmlstringbuilder.append(((Feature)iterator1.next()).toXML())) { }
        return iqchildelementxmlstringbuilder;
    }

    public List getIdentities()
    {
        return Collections.unmodifiableList(identities);
    }

    public List getIdentities(String s, String s1)
    {
        ArrayList arraylist = new ArrayList(identities.size());
        Iterator iterator = identities.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Identity identity = (Identity)iterator.next();
            if (identity.getCategory().equals(s) && identity.getType().equals(s1))
            {
                arraylist.add(identity);
            }
        } while (true);
        return arraylist;
    }

    public String getNode()
    {
        return node;
    }

    public boolean hasIdentity(String s, String s1)
    {
        s = XmppStringUtils.generateKey(s, s1);
        return identitiesSet.contains(s);
    }

    public void setNode(String s)
    {
        node = s;
    }
}
