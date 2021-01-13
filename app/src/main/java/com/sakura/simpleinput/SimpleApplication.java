package com.sakura.simpleinput;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author zhangzheng
 * @Date 2019-07-10 10:28
 * @ClassName SimpleApplication
 * <p>
 * Desc : 
 */
public class SimpleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initBugly();
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "9166fb21e9", false);

    }
}
