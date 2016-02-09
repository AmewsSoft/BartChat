// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.xdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.xdata.packet.DataForm;

// Referenced classes of package org.jivesoftware.smackx.xdata:
//            FormField

public class Form
{

    private DataForm dataForm;

    public Form(org.jivesoftware.smackx.xdata.packet.DataForm.Type type)
    {
        dataForm = new DataForm(type);
    }

    public Form(DataForm dataform)
    {
        dataForm = dataform;
    }

    public static Form getFormFrom(Stanza stanza)
    {
        stanza = DataForm.from(stanza);
        if (stanza != null && stanza.getReportedData() == null)
        {
            return new Form(stanza);
        } else
        {
            return null;
        }
    }

    private boolean isFormType()
    {
        return org.jivesoftware.smackx.xdata.packet.DataForm.Type.form == dataForm.getType();
    }

    private boolean isSubmitType()
    {
        return org.jivesoftware.smackx.xdata.packet.DataForm.Type.submit == dataForm.getType();
    }

    private void setAnswer(FormField formfield, Object obj)
    {
        if (!isSubmitType())
        {
            throw new IllegalStateException("Cannot set an answer if the form is not of type \"submit\"");
        } else
        {
            formfield.resetValues();
            formfield.addValue(obj.toString());
            return;
        }
    }

    private static void validateThatFieldIsText(FormField formfield)
    {
        static class _cls1
        {

            static final int $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[];

            static 
            {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type = new int[FormField.Type.values().length];
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[FormField.Type.text_multi.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[FormField.Type.text_private.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[FormField.Type.text_single.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[FormField.Type.jid_single.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[FormField.Type.hidden.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[FormField.Type.jid_multi.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[FormField.Type.list_multi.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[FormField.Type.list_single.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.jivesoftware.smackx.xdata.FormField.Type[formfield.getType().ordinal()])
        {
        default:
            throw new IllegalArgumentException("This field is not of type text (multi, private or single).");

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            return;
        }
    }

    public void addField(FormField formfield)
    {
        dataForm.addField(formfield);
    }

    public Form createAnswerForm()
    {
        if (!isFormType())
        {
            throw new IllegalStateException("Only forms of type \"form\" could be answered");
        }
        Form form = new Form(org.jivesoftware.smackx.xdata.packet.DataForm.Type.submit);
        Iterator iterator = getFields().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            FormField formfield = (FormField)iterator.next();
            if (formfield.getVariable() != null)
            {
                FormField formfield1 = new FormField(formfield.getVariable());
                formfield1.setType(formfield.getType());
                form.addField(formfield1);
                if (formfield.getType() == FormField.Type.hidden)
                {
                    ArrayList arraylist = new ArrayList();
                    for (Iterator iterator1 = formfield.getValues().iterator(); iterator1.hasNext(); arraylist.add((String)iterator1.next())) { }
                    form.setAnswer(formfield.getVariable(), arraylist);
                }
            }
        } while (true);
        return form;
    }

    public DataForm getDataFormToSend()
    {
        Object obj;
        if (isSubmitType())
        {
            DataForm dataform = new DataForm(getType());
            Iterator iterator = getFields().iterator();
            do
            {
                obj = dataform;
                if (!iterator.hasNext())
                {
                    break;
                }
                obj = (FormField)iterator.next();
                if (!((FormField) (obj)).getValues().isEmpty())
                {
                    dataform.addField(((FormField) (obj)));
                }
            } while (true);
        } else
        {
            obj = dataForm;
        }
        return ((DataForm) (obj));
    }

    public FormField getField(String s)
    {
        if (s == null || s.equals(""))
        {
            throw new IllegalArgumentException("Variable must not be null or blank.");
        }
        for (Iterator iterator = getFields().iterator(); iterator.hasNext();)
        {
            FormField formfield = (FormField)iterator.next();
            if (s.equals(formfield.getVariable()))
            {
                return formfield;
            }
        }

        return null;
    }

    public List getFields()
    {
        return dataForm.getFields();
    }

    public String getInstructions()
    {
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = dataForm.getInstructions().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            stringbuilder.append((String)iterator.next());
            if (iterator.hasNext())
            {
                stringbuilder.append("\n");
            }
        } while (true);
        return stringbuilder.toString();
    }

    public String getTitle()
    {
        return dataForm.getTitle();
    }

    public org.jivesoftware.smackx.xdata.packet.DataForm.Type getType()
    {
        return dataForm.getType();
    }

    public void setAnswer(String s, double d)
    {
        s = getField(s);
        if (s == null)
        {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else
        {
            validateThatFieldIsText(s);
            setAnswer(((FormField) (s)), Double.valueOf(d));
            return;
        }
    }

    public void setAnswer(String s, float f)
    {
        s = getField(s);
        if (s == null)
        {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else
        {
            validateThatFieldIsText(s);
            setAnswer(((FormField) (s)), Float.valueOf(f));
            return;
        }
    }

    public void setAnswer(String s, int i)
    {
        s = getField(s);
        if (s == null)
        {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else
        {
            validateThatFieldIsText(s);
            setAnswer(((FormField) (s)), Integer.valueOf(i));
            return;
        }
    }

    public void setAnswer(String s, long l)
    {
        s = getField(s);
        if (s == null)
        {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        } else
        {
            validateThatFieldIsText(s);
            setAnswer(((FormField) (s)), Long.valueOf(l));
            return;
        }
    }

    public void setAnswer(String s, String s1)
    {
        s = getField(s);
        if (s == null)
        {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        }
        switch (_cls1..SwitchMap.org.jivesoftware.smackx.xdata.FormField.Type[s.getType().ordinal()])
        {
        default:
            throw new IllegalArgumentException("This field is not of type String.");

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            setAnswer(((FormField) (s)), s1);
            break;
        }
    }

    public void setAnswer(String s, List list)
    {
        if (!isSubmitType())
        {
            throw new IllegalStateException("Cannot set an answer if the form is not of type \"submit\"");
        }
        s = getField(s);
        if (s != null)
        {
            switch (_cls1..SwitchMap.org.jivesoftware.smackx.xdata.FormField.Type[s.getType().ordinal()])
            {
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            default:
                throw new IllegalArgumentException("This field only accept list of values.");

            case 1: // '\001'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
                s.resetValues();
                break;
            }
            s.addValues(list);
            return;
        } else
        {
            throw new IllegalArgumentException("Couldn't find a field for the specified variable.");
        }
    }

    public void setAnswer(String s, boolean flag)
    {
        FormField formfield = getField(s);
        if (formfield == null)
        {
            throw new IllegalArgumentException("Field not found for the specified variable name.");
        }
        if (formfield.getType() != FormField.Type.bool)
        {
            throw new IllegalArgumentException("This field is not of type boolean.");
        }
        if (flag)
        {
            s = "1";
        } else
        {
            s = "0";
        }
        setAnswer(formfield, s);
    }

    public void setDefaultAnswer(String s)
    {
        if (!isSubmitType())
        {
            throw new IllegalStateException("Cannot set an answer if the form is not of type \"submit\"");
        }
        s = getField(s);
        if (s != null)
        {
            s.resetValues();
            for (Iterator iterator = s.getValues().iterator(); iterator.hasNext(); s.addValue((String)iterator.next())) { }
        } else
        {
            throw new IllegalArgumentException("Couldn't find a field for the specified variable.");
        }
    }

    public void setInstructions(String s)
    {
        ArrayList arraylist = new ArrayList();
        for (s = new StringTokenizer(s, "\n"); s.hasMoreTokens(); arraylist.add(s.nextToken())) { }
        dataForm.setInstructions(arraylist);
    }

    public void setTitle(String s)
    {
        dataForm.setTitle(s);
    }
}
