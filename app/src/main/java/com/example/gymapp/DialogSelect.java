package com.example.gymapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static com.example.gymapp.RecAdapter.WOROUT_KEY;

public class DialogSelect extends DialogFragment {
    TextView tvQuestion;
    EditText etMinutes;
    Spinner spinnerDay;
    Button btnDismiss,btnAdd;
    Name name;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_select,null);
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(view);
        init(view);
        Bundle bundle=getArguments();
        if(bundle!=null) {
            name=bundle.getParcelable(WOROUT_KEY);
            setAction();
        }
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etMinutes.getText().toString().length()>0) {
                    Integer minutes = Integer.valueOf(etMinutes.getText().toString());
                    String day = spinnerDay.getSelectedItem().toString();
                    Training training = new Training(name, false, minutes, day);
                    try {
                        SelectedTraining selectedTraining = (SelectedTraining) getActivity();
                        selectedTraining.setTraining(training);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                  etMinutes.setHintTextColor(getResources().getColor(R.color.Red));
                }

            }
        });
        return builder.show();
    }
    void init(View view){
        tvQuestion=view.findViewById(R.id.question);
        etMinutes=view.findViewById(R.id.etMinutes);
        spinnerDay=view.findViewById(R.id.spinnerDay);
        btnAdd=view.findViewById(R.id.btnAdd);
        btnDismiss=view.findViewById(R.id.btnDismiss);
    }
    void setAction(){
        tvQuestion.setText(tvQuestion.getText().toString()+name.getName()+" To Your Training");

    }
    public interface SelectedTraining{
        void setTraining(Training training);
    }
}
