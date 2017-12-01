package com.example.android.fitnessapp2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Madhura on 30-11-2017.
 */

public class SessionHandler {
    private SharedPreferences prefs;

    //constructor
    public SessionHandler(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //getters and setters
    public void setName(String name){
        prefs.edit().putString("name", name).commit();
    }

    public String getName(){
        String name = prefs.getString("name", "");
        return name;
    }
}
