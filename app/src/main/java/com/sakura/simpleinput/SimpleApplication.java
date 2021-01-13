package com.sakura.simpleinput;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author zhangzheng
 * @Date 2019-07-10 10:28
 * @ClassName SimpleApplication
 * <p>
 * Desc : 这里添加了一个文案，这里再添加一个文案 我还是想修改一下嘻嘻
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
