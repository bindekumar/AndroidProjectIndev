package com.indev.ari_tracker.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonMethods{
    public static ArrayList<String> getListData(HashMap<String, Integer> hashMapHM) {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < hashMapHM.size(); i++) {
            data.add(hashMapHM.keySet().toArray()[i].toString().trim());
        }
        return data;
    }
}
