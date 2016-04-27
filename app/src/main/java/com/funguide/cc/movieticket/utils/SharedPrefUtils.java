package com.funguide.cc.movieticket.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by c on 4/19 0019.
 * 偏好共享的工具类
 */
public class SharedPrefUtils {

    private final static String PREF_NAME = "pfyp";
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    public static void getInstants(Context context){
        if (sharedPref == null){
            sharedPref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        }
        if (editor == null){
            editor = sharedPref.edit();
        }
    }

    public static <T> void saveEntity(String key,T value){
        if (value instanceof String){
            editor.putString(key,(String)value);
        }else if (value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if (value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }
        editor.commit();
    }

    public static String getEntity(String key) {
        return sharedPref.getString(key,"null");
    }

    public static int getIntEntity(String key) {
        return sharedPref.getInt(key,0);
    }

    public static boolean getBooleanEntity(String key) {
        return sharedPref.getBoolean(key,false);
    }

    public static void clear(){
        editor.clear();
        editor.commit();
    }

    /**删除一条数据**/
    public static void delete(String key){
        editor.remove(key);
        editor.commit();
    }

}
