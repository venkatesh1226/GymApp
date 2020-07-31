package com.example.gymapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.ViewHolder> {
     TextView txtDay,txtEdit;
    RecyclerView dayTrain;
    Context context;
    ArrayList<String> day;
    public static final String DAY_KEY="DAY";

    public void setDay(ArrayList<String> day) {
        this.day = day;
    }



    public MyRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.my_train_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        txtDay.setText(day.get(position));
        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(context,DayExpand.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(DAY_KEY,day.get(position));
                context.startActivity(i);

            }
        });
        DayRecAdapter adapter =new DayRecAdapter(context);
        adapter.setDayTraining(getDayTraining(day.get(position)));
        dayTrain.setAdapter(adapter);
        dayTrain.setLayoutManager(new LinearLayoutManager(context, HORIZONTAL,false));


    }

    @Override
    public int getItemCount() {
        return day.size();
    }


    public static ArrayList<Training> getDayTraining(String day){
        ArrayList<Training> train=new ArrayList<>();
        for(Training t: Utils.myTraining){
           if(t.getDay().equals(day))
               train.add(t);
        }
        return train;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
     public ViewHolder(@NonNull View itemView) {
         super(itemView);
         txtDay=itemView.findViewById(R.id.txtDay);
         txtEdit=itemView.findViewById(R.id.txtEdit);
         dayTrain=itemView.findViewById(R.id.dayTrain);
     }
 }
}
