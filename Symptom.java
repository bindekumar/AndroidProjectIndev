package com.indev.ari_tracker.Model;

public class Symptom {

    private String symptomId;
    private String symptomName;

    public String getSymptomId() {
        return symptomId;
    }
    public void setSymptomId(String symptomId){
        this.symptomId = symptomId;
    }
    public String getSymptomName() {
        return symptomName;
    }
    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }
    private static final String TABLE_NAME = "symptom";

    private static final String COLUMN_symptomId = "symptomId";
    private static final String COLUMN_symptomName = "symptomName";

    public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_symptomId + "TEXT,"
            + COLUMN_symptomName + " TEXT "
            + ")";
}