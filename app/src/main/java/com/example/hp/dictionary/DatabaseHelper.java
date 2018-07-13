package com.example.hp.dictionary;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.hp.dictionary.Dictionary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelper extends SQLiteOpenHelper
{
private static final String DATABASE_NAME="dictionary.db";
private static final String TAG="DatabaseHelper";
private static final String TABLE_NAME="Dictionary1";
private static int  DATABASE_VERSION=1;
private static final String KEY_ID="id";
private static final String WORD="Word";
private static final String WORD_TYPE ="Wordtype";
private static final String DEFINITION="Definition";
private static  String DB_PATH="";
private final Context context;
private SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        if(Build.VERSION.SDK_INT >= 17){
            DB_PATH=context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {DB_PATH ="/data/data/" + context.getPackageName() +  "/databases/";

    }
this.context =context;
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String createTable="CREATE TABLE "+TABLE_NAME +"("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+WORD+ " TEXT,"+ WORD_TYPE+ " TEXT,"+DEFINITION+" TEXT  )";
        //db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void createDataBase () throws IOException
    {
        boolean mDataBaseExist = checkDB();
    if(!mDataBaseExist)
    {
        this.getReadableDatabase();
        this.close();
        try
        {
            copyDB();

        }
        catch(IOException mIOException)
        {
            throw new Error("ErrorCopyingDataBase");
        }
    }
}
private boolean checkDB(){
    File dbFile = new File(DB_PATH + "dictionary.db");
        return dbFile.exists();
}

private  void copyDB() throws IOException
{
    InputStream ips= context.getAssets().open("dictionary.db");
    String FileName =DB_PATH +"dictionary.db";
        OutputStream out =new FileOutputStream(FileName);
    byte[] buff =new byte[1824];
    int len;
    while ((len =ips.read(buff))>0){
    out.write(buff ,0,len);}
    out.flush();
    out.close();
    out.close();
    DATABASE_VERSION++;
}
public boolean openDatabase() throws SQLException
{
    String mPath =DB_PATH + "dictionary.db";
    db = SQLiteDatabase.openDatabase(mPath,null,SQLiteDatabase.CREATE_IF_NECESSARY);
    return db !=null;
}

public Cursor getAll(int i)
{

    SQLiteDatabase db=this.getReadableDatabase();

    //Cursor cursor=db.rawQuery("select word, wordtype, definition from "+TABLE_NAME+" where word= ?",new String[]{word});
    Cursor cursor=db.rawQuery("select word, wordtype, definition from "+TABLE_NAME , null);
    cursor.moveToPosition(i);
    return cursor;
}



    public Cursor getrecord(String word)
    {

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select word, wordtype, definition from "+TABLE_NAME+" where word= ?",new String[]{word});
        //Cursor cursor=db.rawQuery("select * from "+TABLE_NAME , null);
        cursor.moveToFirst();
        return cursor;
    }

    public boolean check(String word) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select word from " + TABLE_NAME + " where word= ?", new String[]{word});
        if (cursor.getCount() > 0) {
            return true;

        } else return false;

    }
}


