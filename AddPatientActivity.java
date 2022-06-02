package com.indev.ari_tracker.Activity;

import static com.indev.ari_tracker.Util.CommonMethods.getListData;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.indev.ari_tracker.Database.SqliteHelper;
import com.indev.ari_tracker.Model.PatientPojo;
import com.indev.ari_tracker.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class AddPatientActivity extends AppCompatActivity {

    SqliteHelper sqliteHelper=null;

    @BindView(R.id.patient_ihip_id) EditText patient_ihip_id;
    @BindView(R.id.patient_first_name) EditText Patient_first_name;
    @BindView(R.id.patient_middle_name) EditText patient_middle_name;
    @BindView(R.id.patient_last_name) EditText patient_last_name;
    @BindView(R.id.patient_residential_address) EditText patient_residential_address;
    @BindView(R.id.patient_facility_reference_code) EditText patient_facility_reference_code;
    @BindView(R.id.patient_mobile_number) EditText patient_mobile_number;

    //Spinner
    @BindView(R.id.spn_state) Spinner spn_state;
    @BindView(R.id.spn_district) Spinner spn_district;
    @BindView(R.id.spn_block) Spinner spn_block;
    @BindView(R.id.spn_gender) Spinner spn_gender;
    @BindView(R.id.spn_emergency) Spinner spn_emergency;
    @BindView(R.id.spn_sub_district) Spinner spn_sub_district;
    @BindView(R.id.spn_village) Spinner spn_village;
    @BindView(R.id.spn_wardNumber) Spinner spn_wardNumber;
    @BindView(R.id.spn_house_number) Spinner spn_house_number;

    @BindView(R.id.age_radio_button_Yes) RadioButton age_radio_button_Yes;
    @BindView(R.id.age_radio_button_no) RadioButton age_radio_button_no;

    @BindView(R.id.permanent_visitor) RadioButton permanent_visitor;
    @BindView(R.id.permanent_city) RadioButton permanent_city;

    String rg_age = "",mState="",mDistrict="",mBlock="",mVillag="",mSubDistrict="";
    String[] gender_arr={"Select Gender","Male","Female","Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        ButterKnife.bind(this);
        sqliteHelper = new SqliteHelper(this);

        OnClick();
        setSpinner();
    }

    public void aad_patient_details(View view) {

        if (checkValidation()) {
            PatientPojo PatientPojo = new PatientPojo();
            PatientPojo.setIhip_id(patient_ihip_id.getText().toString().trim());
            PatientPojo.setPatient_name(Patient_first_name.getText().toString().trim());
            PatientPojo.setPatient_middle_name(patient_middle_name.getText().toString().trim());
            PatientPojo.setPatient_last_name(patient_last_name.getText().toString().trim());
            PatientPojo.setPermanent_residental_address(patient_residential_address.getText().toString().trim());
            PatientPojo.setFacility_reference_code(patient_facility_reference_code.getText().toString().trim());
            PatientPojo.setMobile_number(patient_mobile_number.getText().toString().trim());
            sqliteHelper.saveHouseHolder(PatientPojo);

            Intent intent = new Intent(this, Provisional_Diagnostice.class);
            startActivity(intent);
        }

    }

    private boolean checkValidation() {
        if (Patient_first_name.getText().toString().trim().length() == 0) {
            Patient_first_name.setError("Please enter first name!");
            Patient_first_name.requestFocus();
            return false;
        }
        return true;
    }


    private void OnClick(){
        age_radio_button_Yes.setOnClickListener(view -> rg_age = "Yse");
        age_radio_button_no.setOnClickListener(view -> rg_age = "No");
        permanent_visitor.setOnClickListener(view -> rg_age="Visitor");
        permanent_city.setOnClickListener(view -> rg_age = "Permanent");
    }

    private void setSpinner() {

        ArrayAdapter<String> gender_aArrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,gender_arr);
        gender_aArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_gender.setAdapter(gender_aArrayAdapter);
        spn_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        HashMap<String, Integer> statesHM = sqliteHelper.getSpinner_data("state_id","state_name","state","");
        ArrayList<String> stateArrayList = getListData(statesHM);
        Collections.sort(stateArrayList);
        stateArrayList.add(0, getString(R.string.select_state));
        ArrayAdapter<String> state_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stateArrayList);
        state_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn_state.setAdapter(state_adapter);
        spn_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mState = String.valueOf(statesHM.get(spn_state.getSelectedItem().toString().trim()));
                getDistrictSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> emergencyArrayList = new ArrayList<>();
        emergencyArrayList.add(0, "Select Emergency Type");
        emergencyArrayList.add(1, "A");
        emergencyArrayList.add(2, "B");

        ArrayAdapter<String> emergencyArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, emergencyArrayList);
        emergencyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_emergency.setAdapter(emergencyArrayAdapter);

        spn_emergency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayListWardNumber=new ArrayList<>();
        arrayListWardNumber.add(0,"Select Ward Number");
        arrayListWardNumber.add(1,"5");
        arrayListWardNumber.add(2,"6");
        arrayListWardNumber.add(3,"52");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arrayListWardNumber);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_wardNumber.setAdapter(arrayAdapter);
        spn_wardNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayList<String> ArrayListHorseNumber=new ArrayList<>();
        ArrayListHorseNumber.add(0,"Select House Number");
        ArrayListHorseNumber.add(1,"5");
        ArrayListHorseNumber.add(2,"6");
        ArrayListHorseNumber.add(3,"52");

        ArrayAdapter<String> spn_house_arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,ArrayListHorseNumber);
        spn_house_arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_house_number.setAdapter(spn_house_arrayAdapter);
        spn_house_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void getDistrictSpinner(){
        HashMap<String,Integer>districtsHM=sqliteHelper.getSpinner_data("district_id","district_name","district","");
        ArrayList<String> districtArrayList=getListData(districtsHM);
        Collections.sort(districtArrayList);
        districtArrayList.add(0,"select District");
        ArrayAdapter<String> districtAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,districtArrayList);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn_district.setAdapter(districtAdapter);
        spn_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDistrict = String.valueOf(districtsHM.get(spn_district.getSelectedItem().toString().trim()));
                getSubDistrict();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

           }

//    private void getBlockSpinner(){
//        HashMap<String,Integer>blocksHM=sqliteHelper.getSpinner_Block();
//        ArrayList<String> blockArrayList=getListData(blocksHM);
//        Collections.sort(blockArrayList);
//        blockArrayList.add(0,"select Block");
//        ArrayAdapter<String> block_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, blockArrayList);
//        block_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spn_block.setAdapter(block_adapter);
//        spn_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                mBlock = String.valueOf(blocksHM.get(spn_block.getSelectedItem().toString().trim()));
//                getVillage();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) { }
//        });
//    }

    private void getSubDistrict() {
        ArrayList<String> subDistrict=new ArrayList<>();
        subDistrict.add(0,"Select Sub District");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,subDistrict);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn_sub_district.setAdapter(arrayAdapter);
        spn_sub_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //getBlockSpinner();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getVillage() {
        ArrayList<String> arrayListVillage=new ArrayList<>();
        arrayListVillage.add(0,"Select Village");
        arrayListVillage.add(1,"Motnaje");
        arrayListVillage.add(2,"Banka");
        arrayListVillage.add(3,"Ladania");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arrayListVillage);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_village.setAdapter(arrayAdapter);
        spn_village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}