package org.lsposed.hijack.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import org.lsposed.hijack.BuildConfig;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class AppUtil {

    public static Event event;
    public static Toast toast;
    public static String domainName = "flexhook.eu.org";
    public static SharedPreferences prefs;
    public static Context mContext;
    public static String LocalVersion = String.format("%s_%s.apk", BuildConfig.APP_NAME, BuildConfig.VERSION_NAME), NetworkDiskLink = "https://viphook.lanzoui.com/b03p9x4ni", NetworkDiskPassword = "6666";

    public static void makeText(Context mContext, final Object message) {
        if (mContext == null) return;
        new Handler(Looper.getMainLooper())
                .post(
                        () -> {
                            if (toast != null) toast.cancel();
                            toast =
                                    Toast.makeText(
                                            mContext, String.valueOf(message), Toast.LENGTH_SHORT);
                            toast.show();
                        });
    }

    // 异常处理器
    public static class CrashHandler {
        private static CrashHandler sInstance;
        private PartCrashHandler mPartCrashHandler;

        public static CrashHandler getInstance() {
            if (sInstance == null) {
                synchronized (CrashHandler.class) {
                    if (sInstance == null) {
                        sInstance = new CrashHandler();
                    }
                }
            }
            return sInstance;
        }

        public void registerPart(Context context) {
            unregisterPart();
            mPartCrashHandler = new PartCrashHandler(context);
            new Handler(Looper.getMainLooper()).post(mPartCrashHandler);
        }

        public void unregisterPart() {
            if (mPartCrashHandler != null) {
                mPartCrashHandler.stop();
                mPartCrashHandler = null;
            }
        }

        private static class PartCrashHandler implements Runnable {
            private final Context mContext;
            private AtomicBoolean isRunning = new AtomicBoolean(true);

            PartCrashHandler(Context context) {
                this.mContext = context;
            }

            void stop() {
                isRunning.set(false);
            }

            @Override
            public void run() {
                while (isRunning.get()) {
                    try {
                        Looper.loop();
                    } catch (final Throwable e) {
                        handleException(e);
                    }
                }
            }

            public void handleException(Throwable e) {
                if (isRunning.get()) {
                    Log.d("CrashHandler", Log.getStackTraceString(e));
                } else {
                    if (e instanceof RuntimeException) {
                        throw (RuntimeException) e;
                    } else {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
