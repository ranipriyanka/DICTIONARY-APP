package com.example.hp.dictionary;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WordDisplay extends AppCompatActivity implements View.OnClickListener{

    int pos;

    ArrayList<String> wordcombimelist;

    ArrayList<String> meancombimelist;
    LinkedHashMap<String, String> namelist;

    DatabaseHelper db;

    public static ArrayList<Dictionary> data;
    Button right;
    Button left;
    TextView TV;
    TextView TV1;

    Cursor cursor1=null;


    /*EditText et;
    Button prev,next;*/
    Bundle b;


    DatabaseHelper dbh=null;
    Dictionary dict=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_display);

        TV =findViewById(R.id.tv);
        TV1=findViewById(R.id.tv1);


   //     tx = (TextSwitcher) findViewById(R.id.textSwitcher);

        right = (Button) findViewById(R.id.next);

        left = (Button) findViewById(R.id.previous);

        left.setOnClickListener(this);
        right.setOnClickListener(this);
        dbh=new DatabaseHelper(this);

        Bundle b = getIntent().getExtras();
        String Word = b.getString("Word");
         Cursor cursor=dbh.getrecord(Word);
         String name=cursor.getString(0);
         String def = cursor.getString(2);
       pos=cursor.getPosition();

        //Toast.makeText(getApplicationContext(),def,Toast.LENGTH_SHORT).show();
        TV.setText(def);
        TV1.setText(name);



     /*   cursor.moveToFirst();
        if( cursor.getCount()>0)
        {
            Toast.makeText(getApplicationContext(),"Ab data aa gyaa ...",Toast.LENGTH_LONG).show();

        }*/



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.previous: {
                if (pos<=0){ Toast.makeText(this,"can't go beyond this", Toast.LENGTH_LONG).show();}
                else{
                    pos--;
                    cursor1 = dbh.getAll(pos);

                    cursor1.moveToPosition(pos);

                    String val = cursor1.getString(2);
                    String name = cursor1.getString(0);
                    //    Toast.makeText(this, val, Toast.LENGTH_LONG).show();
                    TV.setText(val);
                    TV1.setText(name);
                }

            }
            break;
            case R.id.next: {
                cursor1=dbh.getAll(pos);
                if (pos>cursor1.getCount()){ Toast.makeText(this,"You are on last word!", Toast.LENGTH_LONG).show();}
                else{
                pos++;

                cursor1.moveToPosition(pos);
                String name=cursor1.getString(0);
                String val=cursor1.getString(2);
          //      Toast.makeText(this, val, Toast.LENGTH_LONG).show();
                TV.setText(val);
                TV1.setText(name);}
            }
            break;
        }
    }}