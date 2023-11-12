package com.example.hello_world2;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.collection.ArrayMap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello_world2.R.id;
import com.example.hello_world2.data.BayListAdapter;
import com.example.hello_world2.model.Bays;
import com.example.hello_world2.data.SaveFile;
import com.example.hello_world2.ui.XmlParser;
import com.example.hello_world2.data.GetRecords;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private BayListAdapter adapter = null;
    private ArrayList<Bays> bays = null;
    private LinearLayoutManager layoutManager = null;
    Context context;
    Bays bay = new Bays();
    ArrayList<Bays> bayObjects = bay.get_bay_list();



    private static int count = 0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bays = new ArrayList<Bays>();
        layoutManager = new LinearLayoutManager(this);
        context = this;

        TextView load_data = (TextView) findViewById(R.id.load_data);
        load_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveFile saveObject = new SaveFile(Bays.get_bay_list(),context);
                int result = saveObject.loadFile();
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
                int saveResult = saveFileObject.saveFile();
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