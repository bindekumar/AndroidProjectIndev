package com.indev.ari_tracker.Model;

public class Block {
    private Integer blockId;
    private String blockName;
    private String districtId;
    private String stateId;
    public Integer getBlockId() {
        return blockId;
    }
    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    private static final String TABLE_NAME = "block";

    private static final String COLUMN_blockId = "blockId";
    private static final String COLUMN_blockName = "blockName";
    private static final String COLUMN_stateId = "stateId";
    private static final String COLUMN_districtId = "districtId";

    public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_blockId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_blockName + " TEXT, "
            + COLUMN_stateId + " TEXT,"
            + COLUMN_districtId + " Text "
            +")";

}
