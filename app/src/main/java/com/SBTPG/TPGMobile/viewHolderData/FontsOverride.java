package com.SBTPG.TPGMobile.viewHolderData;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by Ajay on 10/6/2017.
 */

public class FontsOverride {

    public static void setDefaultFont(Context context, String TypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
        replaceFont(TypefaceFieldName, regular);
    }

    protected static void replaceFont(String TypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field declaredField = Typeface.class.getDeclaredField(TypefaceFieldName);
            declaredField.setAccessible(true);
            declaredField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
