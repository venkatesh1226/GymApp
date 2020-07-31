package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.gymapp.RecAdapter.BUNDLE_KEY;
import static com.example.gymapp.RecAdapter.WOROUT_KEY;

public class WorkOut extends AppCompatActivity implements DialogSelect.SelectedTraining {
    ImageView workImage;
    TextView workName,workDesc;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);
        init();
        Intent intent =getIntent();
        Bundle bundle=intent.getBundleExtra(BUNDLE_KEY);
        if(null!=bundle){
            Name name=(Name)bundle.get(WOROUT_KEY);
            if(name!=null){
                fill(name);
                btnAction(name);
            }
        }

    }
    void init(){
        workImage=findViewById(R.id.workImage);
        workName=findViewById(R.id.workName);
        workDesc=findViewById(R.id.workDesc);
        add=findViewById(R.id.addTrainingbtn);

    }
    void fill(Name name){
        Glide.with(this).load(name.getImageUrl()).into(workImage);
        workName.setText(name.getName());
        workDesc.setText(name.getDescription());
    }
    void btnAction(final Name name){
      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              DialogSelect dialogSelect=new DialogSelect();
              Bundle bundle=new Bundle();
              bundle.putParcelable(WOROUT_KEY,name);
              dialogSelect.setArguments(bundle);
              dialogSelect.show(getSupportFragmentManager(),"DIALOG SELECTED");
          }
      });
    }

    @Override
    public void setTraining(Training training) {
        Utils.addMyTraining(training);
        Intent intent =new Intent(this,MyTraining.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}