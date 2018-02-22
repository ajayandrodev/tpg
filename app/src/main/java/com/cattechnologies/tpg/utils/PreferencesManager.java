package com.cattechnologies.tpg.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ajay on 11/17/2017.
 */

public class PreferencesManager {

    public static final String PREFS_NAME_TYPE = "ERO_SB_PREF";
    public static final String PREFS_KEY_TYPE = "ERO_SB_PREF_KEY";
    public static final String PREFS_EMP = "EMP_PREF";
    public static final String PREFS_EMP_KEY = "EMP_PREF_KEY";
    private SharedPreferences settings;
    SharedPreferences.Editor editor;
    String text;

    public PreferencesManager() {
        super();
    }


    public void saT(Context context, String text) {
        settings = context.getSharedPreferences(PREFS_NAME_TYPE, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(PREFS_KEY_TYPE, text);
        editor.commit();
    }

    public String gaT(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME_TYPE, Context.MODE_PRIVATE);
        text = settings.getString(PREFS_KEY_TYPE, null);
        return text;
    }

    public void clearSharedPreference(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME_TYPE, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }


    public void suD(Context context, String app_uid) {
        settings = context.getSharedPreferences(PREFS_EMP, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(PREFS_EMP_KEY, app_uid);
        editor.commit();
    }

    public String getUserId(Context context) {
        settings = context.getSharedPreferences(PREFS_EMP, Context.MODE_PRIVATE);
        text = settings.getString(PREFS_EMP_KEY, null);
        return text;
    }


}

