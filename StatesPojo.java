package com.indev.ari_tracker.Model;

public class StatesPojo {

    private int stateId;
    private String stateName;


    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    private static final String TABLE_NAME = "state";
    private static final String COLUMN_stateId = "state_id";
    private static final String COLUMN_stateName = "state_name";


    public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_stateId + " INTEGER,"
            + COLUMN_stateName + " TEXT"
            + ")";
}