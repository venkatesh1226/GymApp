package com.example.gymapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    Button myTraining,allTraining,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listen();
        Utils utils=new Utils(this);



    }



    void init(){
        myTraining=findViewById(R.id.myTraining);
        allTraining=findViewById(R.id.allTraining);
        about=findViewById(R.id.about);
    }
    void listen(){
        allTraining.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,AllTraining.class);
                startActivity(i);
            }
        });
        myTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,MyTraining.class);
                startActivity(i);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("This App Is Created By Venkatesh To Know More Connect With Him");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent =new Intent(MainActivity.this,Web.class);
                        startActivity(intent);
                    }
                });
                builder.create().show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}