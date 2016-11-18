package com.kitswgl.into;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static com.kitswgl.into.Register.DATABASE_NAME;
import static com.kitswgl.into.Register.TABLE_USER;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (!isUserRegistered())

        {
            Intent register = new Intent(MainActivity.this, Register.class);
            startActivity(register);
        } else {
            Intent home = new Intent(MainActivity.this, Home.class);
            startActivity(home);
        }
    }

    boolean isUserRegistered() {
        SQLiteDatabase mydatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        try {
            Cursor cursor = mydatabase.query(TABLE_USER, new String[]{"*"},
                    null,
                    null, null, null, null, null);
            return cursor != null;
        } catch (Exception e) {
            return false;
        }
    }

}