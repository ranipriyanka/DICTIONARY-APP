package com.example.hp.dictionary;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity {
    EditText et;
    Button b;
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        et=(EditText)findViewById(R.id.editText);
        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbh = new DatabaseHelper(getApplicationContext());
                try {

                    dbh.createDataBase();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "Database created", Toast.LENGTH_SHORT).show();
                if (dbh.openDatabase()&&dbh.check(et.getText().toString())) {
                          Intent i= new Intent(HomeActivity.this,WordDisplay.class);
                           i.putExtra("Word",et.getText().toString());
                          startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),"word is not found",Toast.LENGTH_LONG).show();
            }

        });
    }
}
