package com.indev.ari_tracker.Model;

public class AQIcategory {

    private String id,name,color_code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    static final String TABLE_NAME = "aqi_category";
    static final String columnId = "id";
    static final String columnName = "name";
    static final String columnColorcode = "color_code";

    public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "("
            + columnId  + " TEXT,"
            + columnName + " TEXT,"
            + columnColorcode + " TEXT"
            + ")";
}
