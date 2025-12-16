package org.lsposed.hijack.ui.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

import android.util.TypedValue;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;
import org.lsposed.hijack.ui.activity.MainActivity;
import org.lsposed.hijack.util.AppUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.lsposed.hijack.util.ImageSaveHelper;

public abstract class MainBase extends Fragment {

    public ExecutorService executor =
            new ThreadPoolExecutor(1, 5, 30L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    public Handler main = new Handler(Looper.getMainLooper());
    public String WxData = "wxp://f2f0f69-fcDJyYDSmcYrTuiX6PZmztk8OXItb4VDTbNcWgpCWsqVLyIuVQhWW6GgKf3S";
    public String AlipayData = "https://qr.alipay.com/2m610033juka1xak5646n6d";
    public ImageSaveHelper imageSaveHelper = new ImageSaveHelper(AppUtil.mContext);
    
    public void makeText(Object msg) {
        AppUtil.makeText(requireContext(), msg);
    }

    public void show(Object msg) {
        AppUtil.makeText(requireContext(), msg);
    }
    
    public boolean isHideIcon() {
    	Activity activity = requireActivity();
        if (activity instanceof MainActivity) {
            boolean isShow = !((MainActivity) activity).isShowIcon();
            return isShow;
        }
        return false;
    }
    
    public void setShowIcon(boolean isShow) {
    	Activity activity = requireActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).setShowIcon(isShow);
        }
    }

    public void showNavigation() {
        AppUtil.event.callShowNavigation(true);
    }

    public void hideNavigation() {
        AppUtil.event.callShowNavigation(false);
    }

    public int getH() {
        Activity a = requireActivity();
        if (a instanceof MainActivity) {
            return ((MainActivity) a).getH();
        }
        return 0;
    }
    
    public Bitmap getQRcode(String str, int size) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            HashMap<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1); // 最小边距
            BitMatrix bitMatrix = qrCodeWriter.encode(str, BarcodeFormat.QR_CODE, size, size, hints);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bitmap;
        } catch(Throwable e) {}
        return createErrorBitmap(size);
    }

    private Bitmap createErrorBitmap(int size) {
        Bitmap errorBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(errorBitmap);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(size / 10f);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("生成失败", size / 2f, size / 2f, paint);
        return errorBitmap;
    }
    
    public Bitmap getQRcodeWithText(String text, int width, String bottomText, int textSize) {
        try {
            // 1. 生成二维码矩阵（高纠错，UTF-8）
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1); // 可选：白边像素
            QRCodeWriter writer = new QRCodeWriter();
            var bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, width, hints);
            // 2. 创建 Bitmap（ARGB_8888 支持透明）
            Bitmap bitmap = Bitmap.createBitmap(width, width + textSize + 12, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(Color.WHITE);
            // 3. 绘制二维码
            int[] pixels = new int[width * width];
            for (int y = 0; y < width; y++) {
                for (int x = 0; x < width; x++) {
                    pixels[y * width + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
                }
            }
            Bitmap qr = Bitmap.createBitmap(pixels, width, width, Bitmap.Config.ARGB_8888);
            canvas.drawBitmap(qr, 0, 0, null);
            // 4. 绘制底部文字
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLACK);
            paint.setTextSize(textSize);
            paint.setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
            paint.setTextAlign(Paint.Align.CENTER);
            float textY = width + textSize + 4;
            canvas.drawText(bottomText, width / 2f, textY, paint);
            return bitmap;
        } catch (Throwable e) {}
        return createErrorBitmap(width);
    }

    public Map<String, ?> getAll() {
        return AppUtil.prefs.getAll();
    }

    public String getString(String key, String defValue) {
        return AppUtil.prefs.getString(key, defValue);
    }

    public Set<String> getStringSet(String key, Set<String> defValues) {
        return AppUtil.prefs.getStringSet(key, defValues);
    }

    public int getInt(String key, int defValue) {
        return AppUtil.prefs.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return AppUtil.prefs.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return AppUtil.prefs.getFloat(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return AppUtil.prefs.getBoolean(key, defValue);
    }

    public boolean contains(String key) {
        return AppUtil.prefs.contains(key);
    }

    public void putString(String key, String value) {
        AppUtil.prefs.edit().putString(key, value).apply();
    }

    public void putStringSet(String key, Set<String> values) {
        AppUtil.prefs.edit().putStringSet(key, values).apply();
    }

    public void putInt(String key, int value) {
        AppUtil.prefs.edit().putInt(key, value).apply();
    }

    public void putLong(String key, long value) {
        AppUtil.prefs.edit().putLong(key, value).apply();
    }

    public void putFloat(String key, float value) {
        AppUtil.prefs.edit().putFloat(key, value).apply();
    }

    public void putBoolean(String key, boolean value) {
        AppUtil.prefs.edit().putBoolean(key, value).apply();
    }

    public void remove(String key) {
        AppUtil.prefs.edit().remove(key).apply();
    }

    public void clear() {
        AppUtil.prefs.edit().clear().apply();
    }

    public boolean putStringE(String key, String value) {
        return AppUtil.prefs.edit().putString(key, value).commit();
    }

    public boolean putStringSetE(String key, Set<String> values) {
        return AppUtil.prefs.edit().putStringSet(key, values).commit();
    }

    public boolean putIntE(String key, int value) {
        return AppUtil.prefs.edit().putInt(key, value).commit();
    }

    public boolean putLongE(String key, long value) {
        return AppUtil.prefs.edit().putLong(key, value).commit();
    }

    public boolean putFloatE(String key, float value) {
        return AppUtil.prefs.edit().putFloat(key, value).commit();
    }

    public boolean putBooleanE(String key, boolean value) {
        return AppUtil.prefs.edit().putBoolean(key, value).commit();
    }

    public boolean removeE(String key) {
        return AppUtil.prefs.edit().remove(key).commit();
    }

    public boolean clearE() {
        return AppUtil.prefs.edit().clear().commit();
    }
}
