package com.example.gymapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {
    public static final String BUNDLE_KEY="BUNDLE";
    public static final String WOROUT_KEY="WORKOUT";
    ArrayList<Name> names =new ArrayList<>();

    public ArrayList<Name> getNames() {
        return names;
    }

    public void setNames(ArrayList<Name> names) {
        this.names = names;
    }

    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rec_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
     holder.workName.setText(names.get(position).getName());
        Glide.with(context).load(names.get(position).getImageUrl()).override(Target.SIZE_ORIGINAL).into(holder.workImage);
        holder.work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,WorkOut.class);
                Bundle bundle =new Bundle();
                bundle.putParcelable(WOROUT_KEY,names.get(position));
                intent.putExtra(BUNDLE_KEY,bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView workName;
        ImageView workImage;
        CardView work;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            workImage=itemView.findViewById(R.id.workImage);
            workName=itemView.findViewById(R.id.workName);
            work=itemView.findViewById(R.id.recItem);
        }
    }
}
