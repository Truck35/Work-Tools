package com.example.hello_world2.data;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.hello_world2.model.Bays;

import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class GetRecords
{
    Document xmlDocs;

    Context context;

    String result;
    public GetRecords(Document xmlDocs)
    {
        this.xmlDocs = xmlDocs;
    }

    public ArrayList<Bays> getXmlRecords()
    {
       ArrayList<Bays> baysList = new ArrayList<Bays>();
        xmlDocs.getDocumentElement().normalize();
        NodeList nodeList = xmlDocs.getElementsByTagName("Bay");


        for (int i = 0; i < nodeList.getLength(); ++i)
        {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element)node;
                Bays bay = new Bays();
                //Log.d("data: ",element.getElementsByTagName("BayLocation").item(0).getTextContent());

                bay.setBaylocation(element.getElementsByTagName("BayLocation").item(0).getTextContent());
                baysList.add(bay);
            }
        }
        return baysList;
    }


}
