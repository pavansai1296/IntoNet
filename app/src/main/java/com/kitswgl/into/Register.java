package com.kitswgl.into;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.text.TextUtils.isEmpty;


public class Register extends AppCompatActivity {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserDetails";
    public static final String TABLE_USER = "user_data";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PH_NO = "phone";
    String id;
    String uname;
    String phone;
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText phn = (EditText) findViewById(R.id.in_phone);
        final EditText nam = (EditText) findViewById(R.id.in_name);
        final int rand = numbGen();
        final SQLiteDatabase mydatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        SQLiteOpenHelper sqlHelper;
        Button reg = (Button) findViewById(R.id.btn_reg);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(phn.getText().toString()) && (!isEmpty(nam.getText()))) {
                    mydatabase.execSQL("CREATE TABLE IF NOT EXISTS user_data (id INTEGER PRIMARY KEY NOT NULL, phone TEXT NOT NULL, uname TEXT NOT NULL);");
                    mydatabase.execSQL("INSERT INTO user_data VALUES (" + rand + ", " + phn.getText() + ", '" + nam.getText() + "');");
                    Cursor cursor = mydatabase.query(TABLE_USER, new String[]{"*"},
                            null,
                            null, null, null, null, null);

                    if (cursor != null) {
                        if (cursor.moveToFirst()) {


                            id = cursor.getString(0);
                            phone = cursor.getString(1);
                            uname = cursor.getString(2);
                        }
                    }

                    cursor.close();
                    mydatabase.close();

                    //Show success message
                    AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
                    alertDialog.setTitle("Done!");
                    alertDialog.setMessage("You have successfully registered, " + uname);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(Register.this, Home.class);
                                    startActivity(i);
                                }
                            });
                    alertDialog.show();

                } else {

                    AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
                    alertDialog.setTitle("Alert!");
                    alertDialog.setMessage("Enter correct details.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }
                    );
                    alertDialog.show();
                }

            }


        });
    }

    public int numbGen() {
        return (int) (Math.random() * 90000000) + 10000000;
    }
}
