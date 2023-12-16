/*formerly activity_main.xml*/
package com.example.hello_world2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello_world2.File_Resources.PDFConverter;
import com.example.hello_world2.R.id;
import com.example.hello_world2.data.BayListAdapter;
import com.example.hello_world2.model.Bays;
import com.example.hello_world2.data.SaveFile;
import com.example.hello_world2.ui.XmlParser;
import com.example.hello_world2.data.GetRecords;

//import org.apache.fop.apps.FOPException;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class List_View extends AppCompatActivity {
    private BayListAdapter adapter = null;
    private ArrayList<Bays> bays = null;
    private LinearLayoutManager layoutManager = null;
    Context context;
    Bays bay = new Bays();
    ArrayList<Bays> bayObjects = bay.get_bay_list();



    private static int count = 0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        bays = new ArrayList<Bays>();
        layoutManager = new LinearLayoutManager(this);
        context = this;
        int pageWidth = 1200;
        int number = 1;

        TextView saveToPDF = (TextView) findViewById(id.saveToPDF);
        saveToPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                try {
                    // create a new document

                    PDFConverter converter = new PDFConverter("OUTSTANDING BAYS","Bays that need picture taken");
                   PdfDocument document = converter.convert2Pdf(1200,2010);


                        // write the document content
                        //File fileObject = new File(context.getFilesDir(),"output.pdf");
                        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                        String fileName = "output_pages2.pdf";
                        File file = new File(downloadDir, fileName);

                        document.writeTo(new FileOutputStream(file));

                        // close the document
                        document.close();
                    Toast.makeText(context, "PDF Created.", Toast.LENGTH_LONG).show();
                }
                catch(IOException e){
                    e.printStackTrace();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        TextView load_data = (TextView) findViewById(R.id.load_data);
        load_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveFile saveObject = new SaveFile(Bays.get_bay_list(),context);
                int result = saveObject.loadFile("Bay.txt");
                if(!Objects.isNull(result) && result == 0)
                {
                  String stringResult = saveObject.getString();
                  XmlParser Xml = new XmlParser();
                  Xml.setXml(stringResult);
                  Document xmlDocs = Xml.get_xml();
                  GetRecords records = new GetRecords(xmlDocs);
                  ArrayList<Bays> bayList = records.getXmlRecords();

                    bayObjects = bayList;
                    adapter = new BayListAdapter(bayObjects,context);
                    Toast.makeText(context,Integer.toString(adapter.getItemCount()),Toast.LENGTH_LONG).show();

                    RecyclerView recyclerView = findViewById(R.id.recyclerview);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(layoutManager);
                    // recyclerView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();

                  Toast.makeText(context,"Data Loaded",Toast.LENGTH_LONG).show();
                }
            }
        });

        TextView save = (TextView) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //comment out code below if not working
                SaveFile saveFileObject = new SaveFile(Bays.get_bay_list(),context);
                int saveResult = saveFileObject.saveFile("Bay.txt");
                if(saveResult == 0)
                {
                    Toast.makeText(context,"Item saved",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context,saveFileObject.getErrorMessage(),Toast.LENGTH_LONG).show();
                }

                   // Toast.makeText(context,"Problem saving items",Toast.LENGTH_LONG).show();
                }
        });

        Button add_button = (Button) findViewById(R.id.add_button);

        //first on click listener
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textInput = (EditText) findViewById(id.EditText);
                String input = textInput.getText().toString();
                Bays bay = new Bays();
                bay.setBaylocation(input);
                //bay.setId(count++);
                bayObjects.add(bay);
                Toast.makeText(context,input+" added to list", Toast.LENGTH_SHORT).show();
                textInput.setText("");

      /*  for(int i = 0; i < adapter.getItemCount(); ++i)
        {

            bays.get(i).setBaylocation("24-00"+i);
            bays.get(i).setId(i);
        }*/
        Bays.set_bay_list(bayObjects);
        adapter = new BayListAdapter(bayObjects,context);
        //Toast.makeText(context,Integer.toString(adapter.getItemCount()),Toast.LENGTH_LONG).show();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
       // recyclerView.setAdapter(adapter);





        adapter.notifyDataSetChanged();
            }
        });//end listener



        Button remove_button = (Button) findViewById(R.id.remove_button);
        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if(bayObjects.size() == 0)
                {
                    Toast.makeText(context,"There are no bays in the list.",Toast.LENGTH_SHORT).show();
                }
                for(int i = 0; i < bayObjects.size(); ++i) {
                    CheckBox checkBox = (CheckBox) findViewById(i);
                    if(checkBox != null && checkBox.isChecked())
                    {
                        {
                            bayObjects.remove(i);
                        }
                    }

                }
                Toast.makeText(context,"Bay(s) removed.",Toast.LENGTH_SHORT).show();



                adapter = new BayListAdapter(bayObjects,context);
                //Toast.makeText(context,Integer.toString(adapter.getItemCount()),Toast.LENGTH_LONG).show();

                RecyclerView recyclerView = findViewById(R.id.recyclerview);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);

                adapter.notifyDataSetChanged();
            }
        });
    }


}