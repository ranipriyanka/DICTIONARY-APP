package com.example.hp.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class WordDisplay extends AppCompatActivity {

    EditText et;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_display);

        et=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.prevbtn);
        b2=(Button)findViewById(R.id.nexbtn);
    }
}
