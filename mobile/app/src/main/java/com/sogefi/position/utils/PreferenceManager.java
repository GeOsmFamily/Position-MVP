package com.sogefi.position.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "position";
    private static final String TUTO = "oui";

    public PreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void firstLaunch(String tuto) {
        editor.putString(TUTO,tuto );
        editor.apply();
    }

    public String getLauch(){
        return pref.getString(TUTO,"oui");

    }


}

