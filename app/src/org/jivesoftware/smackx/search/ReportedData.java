// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.jivesoftware.smackx.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class ReportedData
{
    public static class Column
    {

        private final String label;
        private final org.jivesoftware.smackx.xdata.FormField.Type type;
        private final String variable;

        public String getLabel()
        {
            return label;
        }

        public org.jivesoftware.smackx.xdata.FormField.Type getType()
        {
            return type;
        }

        public String getVariable()
        {
            return variable;
        }

        public Column(String s, String s1, org.jivesoftware.smackx.xdata.FormField.Type type1)
        {
            label = s;
            variable = s1;
            type = type1;
        }
    }

    public static class Field
    {

        private List values;
        private String variable;

        public List getValues()
        {
            return Collections.unmodifiableList(values);
        }

        public String getVariable()
        {
            return variable;
        }

        public Field(String s, List list)
        {
            variable = s;
            values = list;
        }
    }

    public static class Row
    {

        private List fields;

        private List getFields()
        {
            return Collections.unmodifiableList(new ArrayList(fields));
        }

        public List getValues(String s)
        {
            for (Iterator iterator = getFields().iterator(); iterator.hasNext();)
            {
                Field field = (Field)iterator.next();
                if (s.equalsIgnoreCase(field.getVariable()))
                {
                    return field.getValues();
                }
            }

            return null;
        }

        public Row(List list)
        {
            fields = new ArrayList();
            fields = list;
        }
    }


    private List columns;
    private List rows;
    private String title;

    public ReportedData()
    {
        columns = new ArrayList();
        rows = new ArrayList();
        title = "";
    }

    private ReportedData(DataForm dataform)
    {
        columns = new ArrayList();
        rows = new ArrayList();
        title = "";
        FormField formfield;
        for (Iterator iterator = dataform.getReportedData().getFields().iterator(); iterator.hasNext(); columns.add(new Column(formfield.getLabel(), formfield.getVariable(), formfield.getType())))
        {
            formfield = (FormField)iterator.next();
        }

        ArrayList arraylist;
        for (Iterator iterator1 = dataform.getItems().iterator(); iterator1.hasNext(); rows.add(new Row(arraylist)))
        {
            Object obj = (org.jivesoftware.smackx.xdata.packet.DataForm.Item)iterator1.next();
            arraylist = new ArrayList(columns.size());
            FormField formfield1;
            ArrayList arraylist1;
            for (obj = ((org.jivesoftware.smackx.xdata.packet.DataForm.Item) (obj)).getFields().iterator(); ((Iterator) (obj)).hasNext(); arraylist.add(new Field(formfield1.getVariable(), arraylist1)))
            {
                formfield1 = (FormField)((Iterator) (obj)).next();
                arraylist1 = new ArrayList();
                for (Iterator iterator2 = formfield1.getValues().iterator(); iterator2.hasNext(); arraylist1.add((String)iterator2.next())) { }
            }

        }

        title = dataform.getTitle();
    }

    public static ReportedData getReportedDataFrom(Stanza stanza)
    {
        stanza = DataForm.from(stanza);
        if (stanza != null && stanza.getReportedData() != null)
        {
            return new ReportedData(stanza);
        } else
        {
            return null;
        }
    }

    public void addColumn(Column column)
    {
        columns.add(column);
    }

    public void addRow(Row row)
    {
        rows.add(row);
    }

    public List getColumns()
    {
        return Collections.unmodifiableList(new ArrayList(columns));
    }

    public List getRows()
    {
        return Collections.unmodifiableList(new ArrayList(rows));
    }

    public String getTitle()
    {
        return title;
    }
}
