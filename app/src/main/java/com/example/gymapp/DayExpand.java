package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.gymapp.MyRecAdapter.DAY_KEY;

public class DayExpand extends AppCompatActivity {
    TextView txtDay;
    RecyclerView dayItems;
    String day;
    FloatingActionButton addMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_expand);
        init();
        Intent i=getIntent();
        if(i!=null)
        day=i.getStringExtra(DAY_KEY);
        if(day!=null)
            txtDay.setText(day);
        DayRecAdapter dayRecAdapter=new DayRecAdapter(this);
        dayItems.setAdapter(dayRecAdapter);
        dayItems.setLayoutManager(new LinearLayoutManager(this));
        dayRecAdapter.setDayTraining(MyRecAdapter.getDayTraining(txtDay.getText().toString()));
        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DayExpand.this,AllTraining.class);
                startActivity(i);

            }
        });
    }
    void init(){
        txtDay=findViewById(R.id.txtDay);
        dayItems=findViewById(R.id.dayItems);
        addMore=findViewById(R.id.addMore);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}