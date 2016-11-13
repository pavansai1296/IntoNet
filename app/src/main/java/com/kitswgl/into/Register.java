package com.kitswgl.into;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button reg = (Button) findViewById(R.id.btn_reg);
       reg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {



               //Show success message
               AlertDialog alertDialog = new AlertDialog.Builder(Register.this).create();
               alertDialog.setTitle("Done!");
               alertDialog.setMessage("You have successfully registered.");
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
