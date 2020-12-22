package com.example.pocketmoneytracker.interfaces;

import android.content.Context;
import android.os.Bundle;

public interface ActivityLauncherInterface {

    void launchActivity(String packageName, String activityName, Context context, Bundle bundle);
}
