package com.indev.ari_tracker.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.indev.ari_tracker.R;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Provisional_Diagnostice extends AppCompatActivity{
    Button diagnostic_button;
    @BindView(R.id.symptom_breathing_issue) CheckBox symptom_breathing_issue;
    @BindView(R.id.symptom_chest_pain) CheckBox symptom_chest_pain;
    @BindView(R.id.symptom_cough)CheckBox symptom_cough;
    @BindView(R.id.symptom_sore_throat) CheckBox symptom_sore_throat;
    @BindView(R.id.symptom_fever) CheckBox symptom_fever;
    @BindView(R.id.symptom_morning_sneezing)CheckBox symptom_morning_sneezing;
    @BindView(R.id.symptom_nasal_congestion)CheckBox symptom_nasal_congestion;
    @BindView(R.id.symptom_fast_breathing)CheckBox symptom_fast_breathing;
    @BindView(R.id.symptom_wheezing)CheckBox symptom_wheezing;
    @BindView(R.id.symptom_palpitation)CheckBox symptom_palpitation;
    @BindView(R.id.symptom_chest_tightness) CheckBox symptom_chest_tightness;
    @BindView(R.id.symptom_headache) CheckBox symptom_headache;
    @BindView(R.id.symptom_giddiness)CheckBox symptom_giddiness;
    @BindView(R.id.symtom_limb_weekness)CheckBox symtom_limb_weekness;
    @BindView(R.id.symtom_facial_deviation)CheckBox symtom_facial_deviation;
    @BindView(R.id.symptom_seizures)CheckBox symptom_seizures;


    // initialize variables

    TextView textView;
    boolean[] selectedLevel;
    ArrayList<Integer> langList = new ArrayList<>();
    String[] langArray = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6"};



    String symptom="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provisional_diagnostice);
        ButterKnife.bind(this);
        init();
        getLevel();
        intentDiagnostic();
        getSymptom();
        getLevel();
    }



    //Symptom
    private void getSymptom() {
        symptom_breathing_issue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    symptom="Yse";
                }
                else{
                    symptom="No";
                }
            }
        });

        symptom_chest_pain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });

        symptom_cough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });

        symptom_sore_throat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });


        symptom_fever.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    symptom="Yse";
                }
                else{
                    symptom="No";
                }
            }
        });

        symptom_morning_sneezing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    symptom="Yse";
                }
                else{
                    symptom="No";
                }
            }
        });

        symptom_nasal_congestion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });


        symptom_fast_breathing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });


        symptom_wheezing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });


        symptom_palpitation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });

        symptom_chest_tightness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });

        symptom_headache.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });

        symptom_headache.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });
        symptom_giddiness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });
        symtom_limb_weekness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });


        symtom_facial_deviation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });

        symptom_seizures.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    symptom="Yes";
                else
                    symptom="No";
            }
        });
    }


    private void init(){

      //  spnLevelFirst=findViewById(R.id.spnLevelFirst);
        diagnostic_button=findViewById(R.id.diagnostic_button);
    }

    private void getLevel() {
            textView = findViewById(R.id.textView);
            selectedLevel = new boolean[langArray.length];
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Provisional_Diagnostice.this);
                    builder.setTitle("Select Level");
                    builder.setCancelable(false);

                    builder.setMultiChoiceItems(langArray, selectedLevel, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            if (b) {
                                langList.add(i);
                                Collections.sort(langList);
                            } else {
                                langList.remove(Integer.valueOf(i));
                            }
                        }
                    });

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int j = 0; j < langList.size(); j++) {
                                stringBuilder.append(langArray[langList.get(j)]);
                                if (j != langList.size() - 1) {
                                    stringBuilder.append(", ");
                                }
                            }
                            textView.setText(stringBuilder.toString());
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (int j = 0; j < selectedLevel.length; j++) {
                                selectedLevel[j] = false;
                                langList.clear();
                                textView.setText("");
                            }
                        }
                    });
                    builder.show();
                }
            });
        }

    private void intentDiagnostic() {
        diagnostic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Provisional_Diagnostice.this,Add_Diagnosis_Details.class);
                startActivity(intent);
            }
        });
    }

}
