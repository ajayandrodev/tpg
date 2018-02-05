package com.cattechnologies.tpg.viewHolderData;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.cattechnologies.tpg.activities.LoginScreen;

import java.util.HashMap;

/**
 * Created by admin on 11/9/2017.
 */

public class SessionManager {

    private static SessionManager preferencesData;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefsEditor;
    // Context
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Pref_User_Details";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "user_name";
    public static final String KEY_EFIN = "user_efin";
    private boolean loggedIn;

    public static SessionManager getInstance(Context context) {
        if (preferencesData == null) {
            preferencesData = new SessionManager(context);
        }
        return preferencesData;
    }

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void createLoginSession(String efin) {
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(IS_LOGIN, true);
        prefsEditor.putString(KEY_EFIN, efin);
        prefsEditor.commit();
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(_context, LoginScreen.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, null));
        return user;
    }

    public void logoutUser() {
        prefsEditor.clear();
        prefsEditor.commit();
        Intent i = new Intent(_context, LoginScreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);    }
}
