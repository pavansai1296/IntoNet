package com.kitswgl.into;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.kitswgl.into.Register.DATABASE_NAME;
import static com.kitswgl.into.Register.TABLE_USER;

public class Home extends AppCompatActivity {
    String id;
    String phone;
    String uname;

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
        setContentView(R.layout.activity_home);

        Button b = (Button) findViewById(R.id.btn_udet);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase mydatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

                Cursor cursor = mydatabase.query(TABLE_USER, new String[]{"*"},
                        null,
                        null, null, null, null, null);

                if (cursor != null) {
                    if (cursor.moveToFirst()) {


                        id = cursor.getString(0); // DeviceID
                        phone = cursor.getString(1); // Event
                        uname = cursor.getString(2);
                    }
                }

                cursor.close();
                mydatabase.close();

                //Show success message
                AlertDialog alertDialog = new AlertDialog.Builder(Home.this).create();
                alertDialog.setTitle("USER DETAILS");
                alertDialog.setMessage("User ID: " + id + "\n" + "USER NAME: " + uname + "\n" + "USER PHONE: " + phone);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                alertDialog.show();
            }
        });

        Button c = (Button) findViewById(R.id.btn_about);

        c.setOnClickListener(new View.OnClickListener()

                             {
                                 @Override
                                 public void onClick(View view) {
                                     Intent i = new Intent(Home.this, About.class);
                                     startActivity(i);
                                 }

                             }

        );
        Button p = (Button) findViewById(R.id.btn_peers);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Peer_Finder.class);
                startActivity(i);
            }
        });

    }
}


