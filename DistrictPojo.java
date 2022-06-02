package com.indev.ari_tracker.Model;

public class DistrictPojo {

    String district_name,state_id;
    int district_id;

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    static final String TABLE_NAME = "district";
    static final String columnId = "district_id";
    static final String columnName = "district_name";
    static final String columnStateId = "state_id";

    public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "("
            + columnStateId + " TEXT,"
            + columnId + " INTEGER,"
            + columnName + " TEXT"
            + ")";
}


