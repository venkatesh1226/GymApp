package com.example.gymapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DayRecAdapter extends RecyclerView.Adapter<DayRecAdapter.ViewHolder>{
    TextView txtName;
    ImageView isAcomplished,itemImage;

    public void setDayTraining(ArrayList<Training> dayTraining) {
        this.dayTraining = dayTraining;
    }

    ArrayList<Training> dayTraining;
    Context context;

    public DayRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.day_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        txtName.setText(dayTraining.get(position).getName().getName());
        isAcomplished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dayTraining.get(position).getAcomplished()){
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage
                        ("Are You Sure That You Completed "+
                                (dayTraining.get(position).getName().getName())+
                                " for "+dayTraining.get(position).getMinutes()+" Minutes");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                       Utils.updateMyTrainingAcomplish(dayTraining.get(position));
                        dayTraining.get(position).setAcomplished(true);
                        Glide.with(context).load(R.drawable.ic_complete).into(isAcomplished);
                    }
                });
               builder.setNeutralButton("NO", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });

                builder.create().show();
            }}
        });
        if(dayTraining.get(position).getAcomplished())
            Glide.with(context).load(R.drawable.ic_complete).into(isAcomplished);
        else
            Glide.with(context).load(R.drawable.ic_incomplete).into(isAcomplished);

        Glide.with(context).
                load(dayTraining.get(position).getName().getImageUrl())
                .override(750,500)
                .into(itemImage);

        itemImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(view,"Going To Delete",Snackbar.LENGTH_LONG)
                        .setTextColor(context.getResources().getColor(R.color.Red))
                         .setAction("Okay", new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 Utils.deleteMyTraining(dayTraining.get(position));
                                 dayTraining.remove(position);
                                 notifyDataSetChanged();

                             }
                         })
                        .setActionTextColor(context.getResources().getColor(R.color.colorAccent)).show();



                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dayTraining.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            isAcomplished=itemView.findViewById(R.id.isAcomplished);
            itemImage=itemView.findViewById(R.id.itemImage);
        }
    }
}
