package com.vcinsidedigital.barbearia_diaz.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

public class FormatApp
{
    Context context;

    public FormatApp(Context context)
    {
        this.context = context;
    }

    public boolean deleteDatabase()
    {
        if(DBHelper.deleteDatabse(this.context))
        {
            return true;
        }else
        {
            return false;
        }
    }

    public boolean clearAppData(){
        try {
            if(Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT){
                ((ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE)).clearApplicationUserData();
            }else{
                String packeageName = context.getPackageName();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("pm clear "+packeageName);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean formatApp(){
        if(this.clearAppData()){
            return true;
        }else{
            return false;
        }
    }
}
