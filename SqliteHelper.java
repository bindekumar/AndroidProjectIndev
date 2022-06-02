package com.indev.ari_tracker.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import com.indev.ari_tracker.Model.AQIcategory;
import com.indev.ari_tracker.Model.Block;
import com.indev.ari_tracker.Model.DistrictPojo;
import com.indev.ari_tracker.Model.PatientPojo;
import com.indev.ari_tracker.Model.Pollution;
import com.indev.ari_tracker.Model.StatesPojo;
import com.indev.ari_tracker.Model.Symptom;

import java.io.File;
import java.util.HashMap;

@SuppressLint("Range")
public class SqliteHelper  extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Ari.db";
    static final int DATABASE_VERSION = 1;
    String DB_PATH_SUFFIX = "/databases/";
    int version;
    Context ctx;

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StatesPojo.CREATE_TABLE);
        db.execSQL(DistrictPojo.CREATE_TABLE);
        db.execSQL(AQIcategory.CREATE_TABLE);
        db.execSQL(PatientPojo.CREATE_TABLE);
        db.execSQL(Block.CREATE_TABLE);
        db.execSQL(Symptom.CREATE_TABLE);
        db.execSQL(Pollution.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public SQLiteDatabase openDataBase() throws SQLException {
       Log.e("version", "outside" + version);

        File dbFile = ctx.getDatabasePath(DATABASE_NAME);
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    public void dropTable(String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM'" + tablename + "'");
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
    }
    public void deleteData(String table,String column, String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean ss=db.delete(table, column+"=" + id, null) > 0;

    }
    public void saveMasterTable(ContentValues contentValues, String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        long idsds = db.insert(tablename, null, contentValues);
        Log.d("LOG", idsds + " id");
        db.close();
    }
    public long saveHouseHolder(PatientPojo patientPojo){
        SQLiteDatabase db=this.getWritableDatabase();
        long ids=0;
        try{
            if(db!=null&&!db.isReadOnly())
            {
                ContentValues contentValues=new ContentValues();
                contentValues.put("ihip_id",patientPojo.getIhip_id());
                contentValues.put("patient_name",patientPojo.getPatient_name());
                contentValues.put("patient_middle_name",patientPojo.getPatient_middle_name());
                contentValues.put("patient_last_name",patientPojo.getPatient_last_name());
                contentValues.put("facility_reference_code",patientPojo.getFacility_reference_code());
                contentValues.put("state",patientPojo.getState());
                contentValues.put("district",patientPojo.getDistrict());
                contentValues.put("sub_district",patientPojo.getDistrict());
                contentValues.put("block",patientPojo.getBlock());
                contentValues.put("village",patientPojo.getVillage());
                contentValues.put("pin_code",patientPojo.getPin_code());
                contentValues.put("ward_number",patientPojo.getWard_number());
                contentValues.put("horse_number",patientPojo.getHorse_number());
                contentValues.put("mobile_number",patientPojo.getMobile_number());
                contentValues.put("permanent_residental_address",patientPojo.getPermanent_residental_address());
                ids=db.insert("patient",null,contentValues);
                db.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
            db.close();
        }
        return ids;
    }

    public HashMap<String, Integer> getSpinner_data(String id,String name,String table_name,String where) {

        HashMap<String, Integer> data_db = new HashMap<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        try {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {

                String query="Select "+id+","+name+" from "+table_name+"";

                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {

                        if(table_name.equalsIgnoreCase("state"))
                        {
                            StatesPojo statesPojo = new StatesPojo();
                            statesPojo.setStateId(cursor.getInt(cursor.getColumnIndex("state_id")));
                            statesPojo.setStateName(cursor.getString(cursor.getColumnIndex("state_name")));
                            cursor.moveToNext();
                            data_db.put(statesPojo.getStateName(), statesPojo.getStateId());
                        }

                        if(table_name.equalsIgnoreCase("district")){
                            cursor.moveToFirst();
                            while(!cursor.isAfterLast()){
                                DistrictPojo districtPojo=new DistrictPojo();
                                districtPojo.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
                                districtPojo.setDistrict_name(cursor.getString(cursor.getColumnIndex("district_name")));
                                cursor.moveToNext();
                                data_db.put(districtPojo.getDistrict_name(),districtPojo.getDistrict_id());
                        }
                    }
                }
            }

        }

        }catch(Exception e){
            e.getStackTrace();
        }

        return data_db;
    }

///State ...........................
//    public HashMap<String, Integer> getSpinner_state() {
//
//        HashMap<String, Integer> state_new = new HashMap<>();
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        try {
//            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()) {
//
//                String query="Select state_id,state_name from state";
//
//                Cursor cursor = sqLiteDatabase.rawQuery(query, null);
//                if (cursor != null && cursor.getCount() > 0) {
//                    cursor.moveToFirst();
//                    while (!cursor.isAfterLast()) {
//                        StatesPojo statesPojo = new StatesPojo();
//                        statesPojo.setStateId(cursor.getInt(cursor.getColumnIndex("state_id")));
//                        statesPojo.setStateName(cursor.getString(cursor.getColumnIndex("state_name")));
//                        cursor.moveToNext();
//                        state_new.put(statesPojo.getStateName(), statesPojo.getStateId());
//
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            sqLiteDatabase.close();
//        }
//        return state_new;
//    }
////District.............................
//    public HashMap<String,Integer> getSpinner_district(){
//        HashMap<String, Integer> district_new = new HashMap<>();
//        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
//        try{
//            if (sqLiteDatabase != null && sqLiteDatabase.isOpen() && !sqLiteDatabase.isReadOnly()){
//                String query="Select district_id,district_name from district";
//                Cursor cursor=sqLiteDatabase.rawQuery(query,null);
//                if(cursor!=null&&cursor.getCount()>0){
//                    cursor.moveToFirst();
//                    while(!cursor.isAfterLast()){
//                        DistrictPojo districtPojo=new DistrictPojo();
//                        districtPojo.setDistrict_id(cursor.getInt(cursor.getColumnIndex("district_id")));
//                        districtPojo.setDistrict_name(cursor.getString(cursor.getColumnIndex("district_name")));
//                        cursor.moveToNext();
//                        district_new.put(districtPojo.getDistrict_name(),districtPojo.getDistrict_id());
//                    }
//                }
//            }
//        }catch(Exception e){
//            e.getStackTrace();
//        }
//        return district_new;
//    }
////Block..........................
//    public HashMap<String,Integer> getSpinner_Block(){
//        HashMap<String,Integer> block_new=new HashMap<>();
//        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
//        try{
//            if(sqLiteDatabase!=null&& sqLiteDatabase.isOpen()&&!sqLiteDatabase.isReadOnly()){
//                String query="select blockId,blockName from Block";
//                Cursor cursor=sqLiteDatabase.rawQuery(query,null);
//                if(cursor!=null&&cursor.getCount()>0){
//                    cursor.moveToFirst();
//                    while(!cursor.isAfterLast()){
//                      Block block=new Block();
//                      block.setBlockId(cursor.getInt(cursor.getColumnIndex("blockId")));
//                      block.setBlockName(cursor.getString(cursor.getColumnIndex("BlockName")));
//                      cursor.moveToNext();
//                      block_new.put(block.getBlockName(),block.getBlockId());
//                    }
//                }
//            }
//        }catch(Exception e){
//            e.getStackTrace();
//        }
//        return block_new;
//    }
//  //Sub District....................
//    public HashMap<String,Integer> getSpinner_SubDistrict(){
//           HashMap<String,Integer> sub_district_new=new HashMap<>();
//           SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
//
//           if(sqLiteDatabase!=null&&sqLiteDatabase.isOpen()&&!sqLiteDatabase.isReadOnly()){
//
//           }
//
//
//        return sub_district_new;
//    }
//
//    //Village..................
//    public HashMap<String,Integer> getSpinner_Village(){
//        HashMap<String,Integer> sub_district_new=new HashMap<>();
//        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
//
//        if(sqLiteDatabase!=null&&sqLiteDatabase.isOpen()&&!sqLiteDatabase.isReadOnly()){
//
//        }
//        return sub_district_new;
//    }
}