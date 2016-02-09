// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package org.jivesoftware.smackx.search:
//            ReportedData

class SimpleUserSearch extends IQ
{

    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:search";
    private ReportedData data;
    private Form form;

    public SimpleUserSearch()
    {
        super("query", "jabber:iq:search");
    }

    private String getItemsToSearch()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if (form == null)
        {
            form = Form.getFormFrom(this);
        }
        if (form == null)
        {
            return "";
        }
        Iterator iterator = form.getFields().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = (FormField)iterator.next();
            String s = ((FormField) (obj)).getVariable();
            obj = getSingleValue(((FormField) (obj)));
            if (((String) (obj)).trim().length() > 0)
            {
                stringbuilder.append("<").append(s).append(">").append(((String) (obj))).append("</").append(s).append(">");
            }
        } while (true);
        return stringbuilder.toString();
    }

    private static String getSingleValue(FormField formfield)
    {
        formfield = formfield.getValues();
        if (formfield.isEmpty())
        {
            return "";
        } else
        {
            return (String)formfield.get(0);
        }
    }

    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder iqchildelementxmlstringbuilder)
    {
        iqchildelementxmlstringbuilder.rightAngleBracket();
        iqchildelementxmlstringbuilder.append(getItemsToSearch());
        return iqchildelementxmlstringbuilder;
    }

    public ReportedData getReportedData()
    {
        return data;
    }

    protected void parseItems(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        ReportedData reporteddata = new ReportedData();
        reporteddata.addColumn(new ReportedData.Column("JID", "jid", org.jivesoftware.smackx.xdata.FormField.Type.text_single));
        boolean flag = false;
        ArrayList arraylist = new ArrayList();
        do
        {
            if (flag)
            {
                break;
            }
            if (xmlpullparser.getAttributeCount() > 0)
            {
                String s = xmlpullparser.getAttributeValue("", "jid");
                ArrayList arraylist1 = new ArrayList();
                arraylist1.add(s);
                arraylist.add(new ReportedData.Field("jid", arraylist1));
            }
            int i = xmlpullparser.next();
            if (i == 2 && xmlpullparser.getName().equals("item"))
            {
                arraylist = new ArrayList();
                continue;
            }
            if (i == 3 && xmlpullparser.getName().equals("item"))
            {
                reporteddata.addRow(new ReportedData.Row(arraylist));
                continue;
            }
            if (i == 2)
            {
                String s1 = xmlpullparser.getName();
                Object obj = xmlpullparser.nextText();
                ArrayList arraylist2 = new ArrayList();
                arraylist2.add(obj);
                arraylist.add(new ReportedData.Field(s1, arraylist2));
                boolean flag1 = false;
                obj = reporteddata.getColumns().iterator();
                do
                {
                    i = ((flag1) ? 1 : 0);
                    if (!((Iterator) (obj)).hasNext())
                    {
                        break;
                    }
                    if (!((ReportedData.Column)((Iterator) (obj)).next()).getVariable().equals(s1))
                    {
                        continue;
                    }
                    i = 1;
                    break;
                } while (true);
                if (i == 0)
                {
                    reporteddata.addColumn(new ReportedData.Column(s1, s1, org.jivesoftware.smackx.xdata.FormField.Type.text_single));
                }
                continue;
            }
            if (i == 3 && xmlpullparser.getName().equals("query"))
            {
                flag = true;
            }
        } while (true);
        data = reporteddata;
    }

    public void setForm(Form form1)
    {
        form = form1;
    }
}
