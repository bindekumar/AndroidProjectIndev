package com.indev.ari_tracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.indev.ari_tracker.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Add_Diagnosis_Details extends AppCompatActivity {
    Spinner spinner_doctor;
    @BindView(R.id.Patient_radio_button_Yes)
    RadioButton Patient_radio_button_Yes;
    @BindView(R.id.patient_radio_button_no)
    RadioButton Patient_radio_button_no;

    @BindView(R.id.nebulization_radio_button_yes)
    RadioButton nebulization_radio_button_yes;
    @BindView(R.id.nebulization_radio_button_No)
    RadioButton nebulization_radio_button_No;

    @BindView(R.id.ventilator_radio_button_yes)
    RadioButton ventilator_radio_button_yes;
    @BindView(R.id.ventilator_radio_button_no)
    RadioButton getVentilator_radio_button_No;

    @BindView(R.id.invasive_ventilator_radio_button_yes)
            RadioButton invasive_ventilator_radio_button_yes;
    @BindView(R.id.invasive_ventilator_radio_button_No)
            RadioButton invasive_ventilator_radio_button_No;


    String patient="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diagnosis_details);
        ButterKnife.bind(this);
        init();
        getSpinnerDoctor();
       onClick();
    }
    private void onClick() {
        Patient_radio_button_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient ="Yes";
            }
        });
        Patient_radio_button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient="No";
            }
        });

        nebulization_radio_button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient="Yes";
            }
        });

        nebulization_radio_button_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient="No";
            }
        });

        ventilator_radio_button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient="Yes";
            }
        });
        getVentilator_radio_button_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient="No";
            }
        });

        invasive_ventilator_radio_button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient="Yes";
            }
        });

        invasive_ventilator_radio_button_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient="No";
            }
        });

    }

    public void init(){
        spinner_doctor=findViewById(R.id.spinner_doctor);
    }

    private void getSpinnerDoctor() {
        ArrayList<String> doctorArrayList=new ArrayList<>();
        doctorArrayList.add(0,"Select Doctor");
        ArrayAdapter<String> doctorArrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,doctorArrayList);
        doctorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_doctor.setAdapter(doctorArrayAdapter);
        spinner_doctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){}
        });
    }
}