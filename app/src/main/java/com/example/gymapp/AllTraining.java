package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllTraining extends AppCompatActivity {

    RecyclerView trainList;
    ArrayList<Name> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_training);
        trainList=findViewById(R.id.trainList);

        names=Utils.initAllTraining();


        RecAdapter rec=new RecAdapter();
        rec.setNames(names);
        trainList.setAdapter(rec);
        trainList.setLayoutManager(new GridLayoutManager(this,2));
    }



}