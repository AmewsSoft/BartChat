// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smack.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.jivesoftware.smack.util.TypedCloneable;
import org.jivesoftware.smack.util.XmlStringBuilder;

// Referenced classes of package org.jivesoftware.smack.packet:
//            Stanza

public final class Message extends Stanza
    implements TypedCloneable
{
    public static class Body
    {

        private final String language;
        private final String message;

        public boolean equals(Object obj)
        {
            if (this != obj)
            {
                if (obj == null)
                {
                    return false;
                }
                if (getClass() != obj.getClass())
                {
                    return false;
                }
                obj = (Body)obj;
                if (!language.equals(((Body) (obj)).language) || !message.equals(((Body) (obj)).message))
                {
                    return false;
                }
            }
            return true;
        }

        public String getLanguage()
        {
            return language;
        }

        public String getMessage()
        {
            return message;
        }

        public int hashCode()
        {
            return (language.hashCode() + 31) * 31 + message.hashCode();
        }



        private Body(String s, String s1)
        {
            if (s == null)
            {
                throw new NullPointerException("Language cannot be null.");
            }
            if (s1 == null)
            {
                throw new NullPointerException("Message cannot be null.");
            } else
            {
                language = s;
                message = s1;
                return;
            }
        }

    }

    public static class Subject
    {

        private final String language;
        private final String subject;

        public boolean equals(Object obj)
        {
            if (this != obj)
            {
                if (obj == null)
                {
                    return false;
                }
                if (getClass() != obj.getClass())
                {
                    return false;
                }
                obj = (Subject)obj;
                if (!language.equals(((Subject) (obj)).language) || !subject.equals(((Subject) (obj)).subject))
                {
                    return false;
                }
            }
            return true;
        }

        public String getLanguage()
        {
            return language;
        }

        public String getSubject()
        {
            return subject;
        }

        public int hashCode()
        {
            return (language.hashCode() + 31) * 31 + subject.hashCode();
        }



        private Subject(String s, String s1)
        {
            if (s == null)
            {
                throw new NullPointerException("Language cannot be null.");
            }
            if (s1 == null)
            {
                throw new NullPointerException("Subject cannot be null.");
            } else
            {
                language = s;
                subject = s1;
                return;
            }
        }

    }

    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type chat;
        public static final Type error;
        public static final Type groupchat;
        public static final Type headline;
        public static final Type normal;

        public static Type fromString(String s)
        {
            return valueOf(s.toLowerCase(Locale.US));
        }

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(org/jivesoftware/smack/packet/Message$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            normal = new Type("normal", 0);
            chat = new Type("chat", 1);
            groupchat = new Type("groupchat", 2);
            headline = new Type("headline", 3);
            error = new Type("error", 4);
            $VALUES = (new Type[] {
                normal, chat, groupchat, headline, error
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String BODY = "body";
    public static final String ELEMENT = "message";
    private final Set bodies;
    private final Set subjects;
    private String thread;
    private Type type;

    public Message()
    {
        thread = null;
        subjects = new HashSet();
        bodies = new HashSet();
    }

    public Message(String s)
    {
        thread = null;
        subjects = new HashSet();
        bodies = new HashSet();
        setTo(s);
    }

    public Message(String s, String s1)
    {
        this(s);
        setBody(s1);
    }

    public Message(String s, Type type1)
    {
        this(s);
        setType(type1);
    }

    public Message(Message message)
    {
        super(message);
        thread = null;
        subjects = new HashSet();
        bodies = new HashSet();
        type = message.type;
        thread = message.thread;
        subjects.addAll(message.subjects);
        bodies.addAll(message.bodies);
    }

    private String determineLanguage(String s)
    {
        String s1 = s;
        if ("".equals(s))
        {
            s1 = null;
        }
        if (s1 == null && language != null)
        {
            s = language;
        } else
        {
            s = s1;
            if (s1 == null)
            {
                return getDefaultLanguage();
            }
        }
        return s;
    }

    private Body getMessageBody(String s)
    {
        s = determineLanguage(s);
        for (Iterator iterator = bodies.iterator(); iterator.hasNext();)
        {
            Body body = (Body)iterator.next();
            if (s.equals(body.language))
            {
                return body;
            }
        }

        return null;
    }

    private Subject getMessageSubject(String s)
    {
        s = determineLanguage(s);
        for (Iterator iterator = subjects.iterator(); iterator.hasNext();)
        {
            Subject subject = (Subject)iterator.next();
            if (s.equals(subject.language))
            {
                return subject;
            }
        }

        return null;
    }

    public Body addBody(String s, String s1)
    {
        s = new Body(determineLanguage(s), s1);
        bodies.add(s);
        return s;
    }

    public Subject addSubject(String s, String s1)
    {
        s = new Subject(determineLanguage(s), s1);
        subjects.add(s);
        return s;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public Message clone()
    {
        return new Message(this);
    }

    public Set getBodies()
    {
        return Collections.unmodifiableSet(bodies);
    }

    public String getBody()
    {
        return getBody(null);
    }

    public String getBody(String s)
    {
        s = getMessageBody(s);
        if (s == null)
        {
            return null;
        } else
        {
            return ((Body) (s)).message;
        }
    }

    public List getBodyLanguages()
    {
        Body body = getMessageBody(null);
        ArrayList arraylist = new ArrayList();
        Iterator iterator = bodies.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Body body1 = (Body)iterator.next();
            if (!body1.equals(body))
            {
                arraylist.add(body1.language);
            }
        } while (true);
        return Collections.unmodifiableList(arraylist);
    }

    public String getSubject()
    {
        return getSubject(null);
    }

    public String getSubject(String s)
    {
        s = getMessageSubject(s);
        if (s == null)
        {
            return null;
        } else
        {
            return ((Subject) (s)).subject;
        }
    }

    public List getSubjectLanguages()
    {
        Subject subject = getMessageSubject(null);
        ArrayList arraylist = new ArrayList();
        Iterator iterator = subjects.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Subject subject1 = (Subject)iterator.next();
            if (!subject1.equals(subject))
            {
                arraylist.add(subject1.language);
            }
        } while (true);
        return Collections.unmodifiableList(arraylist);
    }

    public Set getSubjects()
    {
        return Collections.unmodifiableSet(subjects);
    }

    public String getThread()
    {
        return thread;
    }

    public Type getType()
    {
        if (type == null)
        {
            return Type.normal;
        } else
        {
            return type;
        }
    }

    public boolean removeBody(String s)
    {
        s = determineLanguage(s);
        for (Iterator iterator = bodies.iterator(); iterator.hasNext();)
        {
            Body body = (Body)iterator.next();
            if (s.equals(body.language))
            {
                return bodies.remove(body);
            }
        }

        return false;
    }

    public boolean removeBody(Body body)
    {
        return bodies.remove(body);
    }

    public boolean removeSubject(String s)
    {
        s = determineLanguage(s);
        for (Iterator iterator = subjects.iterator(); iterator.hasNext();)
        {
            Subject subject = (Subject)iterator.next();
            if (s.equals(subject.language))
            {
                return subjects.remove(subject);
            }
        }

        return false;
    }

    public boolean removeSubject(Subject subject)
    {
        return subjects.remove(subject);
    }

    public void setBody(String s)
    {
        if (s == null)
        {
            removeBody("");
            return;
        } else
        {
            addBody(null, s);
            return;
        }
    }

    public void setSubject(String s)
    {
        if (s == null)
        {
            removeSubject("");
            return;
        } else
        {
            addSubject(null, s);
            return;
        }
    }

    public void setThread(String s)
    {
        thread = s;
    }

    public void setType(Type type1)
    {
        type = type1;
    }

    public volatile CharSequence toXML()
    {
        return toXML();
    }

    public XmlStringBuilder toXML()
    {
        XmlStringBuilder xmlstringbuilder = new XmlStringBuilder();
        xmlstringbuilder.halfOpenElement("message");
        addCommonAttributes(xmlstringbuilder);
        xmlstringbuilder.optAttribute("type", type);
        xmlstringbuilder.rightAngleBracket();
        Object obj = getMessageSubject(null);
        if (obj != null)
        {
            xmlstringbuilder.element("subject", ((Subject) (obj)).subject);
        }
        Iterator iterator = getSubjects().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Subject subject = (Subject)iterator.next();
            if (!subject.equals(obj))
            {
                xmlstringbuilder.halfOpenElement("subject").xmllangAttribute(subject.language).rightAngleBracket();
                xmlstringbuilder.escape(subject.subject);
                xmlstringbuilder.closeElement("subject");
            }
        } while (true);
        obj = getMessageBody(null);
        if (obj != null)
        {
            xmlstringbuilder.element("body", ((Body) (obj)).message);
        }
        iterator = getBodies().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Body body = (Body)iterator.next();
            if (!body.equals(obj))
            {
                xmlstringbuilder.halfOpenElement("body").xmllangAttribute(body.getLanguage()).rightAngleBracket();
                xmlstringbuilder.escape(body.getMessage());
                xmlstringbuilder.closeElement("body");
            }
        } while (true);
        xmlstringbuilder.optElement("thread", thread);
        if (type == Type.error)
        {
            appendErrorIfExists(xmlstringbuilder);
        }
        xmlstringbuilder.append(getExtensionsXML());
        xmlstringbuilder.closeElement("message");
        return xmlstringbuilder;
    }
}
