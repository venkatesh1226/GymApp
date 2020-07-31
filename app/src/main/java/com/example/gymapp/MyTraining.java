package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MyTraining extends AppCompatActivity {
    ArrayList<Training> myTraining;
    RelativeLayout noPlan;
    RecyclerView recMyTrain;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_training);
        noPlan=findViewById(R.id.noPlan);
        recMyTrain=findViewById(R.id.recMyTrain);
        myTraining=Utils.getMyTraining();

        if(myTraining.size()==0){
            add=findViewById(R.id.button);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(MyTraining.this,AllTraining.class);
                    startActivity(i);
                }
            });
            noPlan.setVisibility(View.VISIBLE);
            recMyTrain.setVisibility(View.INVISIBLE);
        }else{

            MyRecAdapter adapter=new MyRecAdapter(this);
            adapter.setDay(Utils.getDayList());
            recMyTrain.setAdapter(adapter);
            recMyTrain.setLayoutManager(new LinearLayoutManager(this));
             }
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(i);
    }
}