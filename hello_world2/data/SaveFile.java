package com.example.hello_world2.data;

import android.content.Context;
import android.util.Log;

import com.example.hello_world2.model.Bays;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner;

public class SaveFile {
    private ArrayList<Bays> bayList;
    private String Xml;

    private String error_message;

    private Context context;

    private String stringFile;
    public SaveFile(ArrayList<Bays> bayList,Context context)
    {
       this.bayList = bayList;
       this.context = context;
       //this.Xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        this.Xml = "";
       this.stringFile = "";
    }

    private void convertToXml()
    {
           Xml += "<BayLocations>";
            for(int i = 0; i < bayList.size(); ++i)
            {
                Xml += "<Bay>";
                    Xml += "<DeptNumber>"+bayList.get(i).getDeptNumber()+"</DeptNumber>";
                    Xml += "<DeptName>"+bayList.get(i).getDeptName()+"</DeptName>";
                    Xml += "<AliseNumber>"+bayList.get(i).getAliseNumber()+"</AliseNumber>";
                    Xml+= "<BayLocation>"+bayList.get(i).getBaylocation()+"</BayLocation>";
                Xml += "</Bay>";
            }
           Xml += "</BayLocations>";
    }
    public int saveFile()
    {
        try
        {
            this.convertToXml();
            File fileObject = new File(context.getFilesDir(),"Bays.txt");
            FileWriter myWriter = new FileWriter(fileObject.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(myWriter);
            myWriter.write(Xml);
            myWriter.close();
            return 0;
        }
        catch(IOException e)
        {
            error_message = e.toString();
            Log.d("error:", error_message);
            return -1;
        }
    }
    public String getErrorMessage()
    {
        return error_message;
    }

    public int loadFile()
    {
        try {
            File fileObject = new File(context.getFilesDir(), "Bays.txt");
            /*FileReader myReader = new FileReader(fileObject.getAbsoluteFile());
            BufferedReader br = new BufferedReader(myReader);
            myReader.read(stringFile);*/
            Scanner sc = new Scanner(fileObject);

            while(sc.hasNext()) {
                stringFile += sc.next();
            }

            sc.close();

            return 0;
        }
        catch(IOException e)
        {
            error_message = e.toString();
            return -1;
        }


    }

    public String getString()
    {
        return stringFile;
    }
}




