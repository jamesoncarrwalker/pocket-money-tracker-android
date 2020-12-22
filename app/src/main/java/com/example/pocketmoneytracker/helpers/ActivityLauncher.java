package com.example.pocketmoneytracker.helpers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.pocketmoneytracker.enums.EnvVar;
import com.example.pocketmoneytracker.interfaces.ActivityLauncherInterface;


public class ActivityLauncher implements ActivityLauncherInterface {

    @Override
    public void launchActivity(String packageName, String activityName, Context context, Bundle bundle) {
        try {
            Class<?> className = Class.forName(EnvVar.BASE_PACKAGE.getVar() + "." + packageName + "." + activityName);
            Intent intent = new Intent(context, className);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if (bundle != null) {
                intent.putExtra("bundle", bundle);
            }
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
