package com.example.invisible.learnc;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Newaccountactivity extends AppCompatActivity {

    private EditText idtext;
    private EditText usertext;
    private EditText passwordtext;
    private Button save;
    Studentdetails d;
    private  int error=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccountactivity);

        d=new Studentdetails(this);
        idtext=findViewById(R.id.Idtext);
        usertext=findViewById(R.id.Usernametext);
        passwordtext=findViewById(R.id.PasswordText);
        save=findViewById(R.id.Savedetails);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               User info=new User();
               info.id= Integer.parseInt(idtext.getText().toString());
               info.username=usertext.getText().toString();
               String un=info.username;
               info.password=passwordtext.getText().toString();
               info.highscore=0;
               Cursor cur=d.getCusor();
               if(cur.getCount()==0)
               {
                   d.Insertinfo(info.id,info.username,info.password,info.highscore);
               }
               else
               {
                   while(cur.moveToNext())
                   {
                       if(un.equals(cur.getString(1))||info.id== Integer.parseInt(cur.getString(0)))
                       {
                           Alert("Error","This username or Id is already taken Enter a unique username and Id");
                           error=1;
                           break;
                       }
                   }
                   if(error==0)
                   {
                       d.Insertinfo(info.id,info.username,info.password,info.highscore);
                   }
                   error=0;
               }
            }
        });
    }
    public void Alert(String alt,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(alt);
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.show();
    }
}
