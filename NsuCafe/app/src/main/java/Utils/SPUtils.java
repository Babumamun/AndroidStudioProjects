package Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPUtils {// Get user information from saveUser information
    public static Map<String,String> getUser(Context context){
        SharedPreferences sP=
                context.getSharedPreferences("data", Context.MODE_PRIVATE);
        Map<String,String> usermap=new HashMap<String,String>();
        String UseraName= sP.getString("UserEmailKey",null);
        String pwd= sP.getString("UserPasswordKey",null);
        usermap.put("login",UseraName);
        usermap.put("pwd",pwd);
        return usermap;

    }
    /**
     *Context
     * String

     */
//Save user name and password
    public static boolean saveUser(Context context,String uName,String pass){
        SharedPreferences sP=
                context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sP.edit();
        editor.putString("UserEmailKey",uName);
        editor.putString("UserPasswordKey",pass);
        editor.commit();
        return true;
    }

    // save data after click on remember me
    public static boolean saveRemMe(Context context,boolean state){
        SharedPreferences sP=
                context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sP.edit();
        editor.putBoolean("Remember_me",state);
        editor.commit();
        return true;
    }
    // get information from save remember me
    public static Map<String ,Boolean>getRemMe(Context context){
        SharedPreferences sP=
                context.getSharedPreferences("data", Context.MODE_PRIVATE);
        boolean state = sP.getBoolean("Remember_me",false);
        Map<String ,Boolean> remMap = new HashMap<String ,Boolean>();
        remMap.put("Remember_me",state);
        return remMap;
    }
    //Save data after click on Auto Login
    public static boolean saveAuto(Context context,boolean state){
        SharedPreferences sP=
                context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sP.edit();
        editor.putBoolean("Auto_login",state);
        editor.commit();
        return true;
    }
    //Get information from save Auto Login
    public static Map<String ,Boolean>getAuto(Context context){
        SharedPreferences sP=
                context.getSharedPreferences("data", Context.MODE_PRIVATE);
        boolean state = sP.getBoolean("Auto_login",false);
        Map<String ,Boolean> remMap = new HashMap<String ,Boolean>();
        remMap.put("Auto_login",state);
        return remMap;
    }

    public static class TextUtils {
        public static boolean isEmpty(String s){

            if (s.trim().length()>0)
                return false;
            else
                return true;
        }

    }
}
