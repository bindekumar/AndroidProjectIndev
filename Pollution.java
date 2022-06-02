package com.indev.ari_tracker.Model;

public class Pollution {

    private String readingId;
    private String cityId;
    private String date;
    private String PM10;
    private String PM2p5;
    private String Ozone;
    private String NO2;
    private String cO;
    private String SO2;

    public String getReadingId() {
        return readingId;
    }
    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPM10() {
        return PM10;
    }

    public void setPM10(String PM10) {
        this.PM10 = PM10;
    }

    public String getPM2p5() {
        return PM2p5;
    }

    public void setPM2p5(String PM2p5) {
        this.PM2p5 = PM2p5;
    }

    public String getOzone() {
        return Ozone;
    }

    public void setOzone(String ozone) {
        Ozone = ozone;
    }
    public String getNO2() {
        return NO2;
    }

    public void setNO2(String NO2){
        this.NO2 = NO2;
    }
    public String getcO() {
        return cO;
    }
    public void setcO(String cO) {
        this.cO = cO;
    }
    public String getSO2() {
        return SO2;
    }
    public void setSO2(String SO2){
        this.SO2 = SO2;
    }

    static final String tableName = "pollution";

    static final String columnId = "readingId";
    static final String columnCity = "cityId";
    static final String columnDate = "date";
    static final String columnePM10 = "PM10";
    static final String columnPM2p5 = "PM2p5";
    static final String columnOzone = "Ozone";
    static final String columnNO2 = "NO2";
    static final String columnCO = "cO";
    static final String columnSo2 = "SO2";

    public static final String CREATE_TABLE = " CREATE TABLE " + tableName + "("
            + columnId + " TEXT, "
            + columnCity + " TEXT, "
            + columnDate + " TEXT,"
            + columnePM10 + " Text "
            + columnPM2p5  + " Text "
            + columnOzone + " Text "
            + columnNO2 + " Text "
            + columnCO + " Text "
            + columnSo2 + " Text "
            +")";
}
