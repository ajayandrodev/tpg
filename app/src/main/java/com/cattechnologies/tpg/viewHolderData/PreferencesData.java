package com.cattechnologies.tpg.viewHolderData;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 11/9/2017.
 */

public class PreferencesData {

    private static PreferencesData preferencesData;
    private SharedPreferences sharedPreferences;

    public static PreferencesData getInstance(Context context) {
        if (preferencesData == null) {
            preferencesData = new PreferencesData(context);
        }
        return preferencesData;
    }

    private PreferencesData(Context context) {
        sharedPreferences = context.getSharedPreferences("YourCustomNamedPreference", Context.MODE_PRIVATE);
    }

    public void saveData(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public String getData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }
}
