package com.cattechnologies.tpg.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 11/17/2017.
 */

public class PreferencesManager {


    public static final String PREFS_NAME_TYPE = "ERO_SB_PREF";
    public static final String PREFS_KEY_TYPE = "ERO_SB_PREF_KEY";
    public static final String PREFS_EMP = "EMP_PREF";
    public static final String PREFS_EMP_KEY = "EMP_PREF_KEY";
    public static final String PREFS_EMP_BOOL = "EMP_PREF";
    public static final String PREFS_EMP_KEY_BOOL = "EMP_PREF_KEY";


    public PreferencesManager() {
        super();
    }

    public void saveAccountType(Context context, String text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME_TYPE, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(PREFS_KEY_TYPE, text); //3
        editor.commit(); //4
    }

    public void saveBoolean(Context context, boolean text) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_EMP_BOOL, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putBoolean(PREFS_EMP_KEY_BOOL, text); //3
        editor.commit(); //4
    }

    public boolean getBoolean(Context context) {
        SharedPreferences settings;
        boolean text;
        settings = context.getSharedPreferences(PREFS_EMP_BOOL, Context.MODE_PRIVATE);
        text = settings.getBoolean(PREFS_EMP_KEY_BOOL, false);
        return text;
    }

    public String getAccountType(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(PREFS_NAME_TYPE, Context.MODE_PRIVATE);
        text = settings.getString(PREFS_KEY_TYPE, null);
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME_TYPE, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME_TYPE, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(PREFS_KEY_TYPE);
        editor.commit();
    }


    public void saveUserId(Context context, String app_uid) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_EMP, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString(PREFS_EMP_KEY, app_uid); //3
        editor.commit(); //4
    }

    public String getUserId(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(PREFS_EMP, Context.MODE_PRIVATE);
        text = settings.getString(PREFS_EMP_KEY, null);
        return text;
    }


}

