package com.kitswgl.into;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.*;
import android.database.sqlite.*;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button reg = (Button) findViewById(R.id.btn_reg);
       reg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               SQLiteDatabase userdb = openOrCreateDatabase("INTODB",MODE_PRIVATE,null); //Database creation

               userdb.execSQL("CREATE TABLE IF NOT EXISTS USER_DETAILS(Name VARCHAR,PHONE VARCHAR);");//create user details table

               userdb.execSQL("INSERT INTO USER_DETAILS VALUES(R.id.text_name,43);");

               Cursor resultSet = userdb.rawQuery("Select * from USER_DETAILS",null);
               resultSet.moveToFirst();
               String name = resultSet.getString(1);
               String phone = resultSet.getString(2);
               //Show success message
               AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
               alertDialog.setTitle("Done!");
               alertDialog.setMessage("You have successfully registered "+name+" with phone number "+phone);
               alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                       new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {
                               dialog.dismiss();
                           }
                       });
               alertDialog.show();
           }
       });
    }
}
