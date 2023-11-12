package com.example.hello_world2.ui;

import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser
{
    private Document doc;
    public int setXml(String Xml)
    {
        try
        {
            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+Xml;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));

            return 0;

        }
        catch(Exception e)
        {
            String error_message = e.toString();
            return -1;
        }


    }

    public Document get_xml()
    {
        return doc;
    }


}

