package org.lsposed.hijack;

import android.app.Application;
import android.content.Context;
import org.lsposed.hijack.util.AppUtil;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.CrashHandler.getInstance().registerPart(this);
    }
}
