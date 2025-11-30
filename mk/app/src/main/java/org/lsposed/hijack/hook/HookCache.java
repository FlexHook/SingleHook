package org.lsposed.hijack.hook;

import android.os.Parcel;
import android.os.Parcelable;

import android.util.Log;
import de.robv.android.xposed.XposedHelpers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HookCache extends HookUtils implements Parcelable {

    private static List<MethodTriple> methods;

    public HookCache(List<MethodTriple> MethodTriples) {
        methods = MethodTriples;
    }

    protected HookCache(Parcel in) {
        int size = in.readInt();
        methods = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            MethodTriple methodTriple = new MethodTriple(in.readString(), in.readString(), in.createStringArrayList());
            if (in.readInt() == 1) {
                methodTriple.setParent(in.readString(), in.readString(), in.createStringArrayList());
            }
            methods.add(methodTriple);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(methods.size());
        for (MethodTriple method : methods) {
            dest.writeString(method.className);
            dest.writeString(method.methodName);
            dest.writeStringList(method.parameterTypes);
            if (method.parent != null) {
                dest.writeInt(1);
                dest.writeString(method.parent.className);
                dest.writeString(method.parent.methodName);
                dest.writeStringList(method.parent.parameterTypes);
            } else {
                dest.writeInt(0);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<Method> getMethods() {
        List<Method> result = new ArrayList<>();
        for (MethodTriple triple : methods) {
            try {
                result.add(triple.getMethod());
            } catch (Throwable e) {}
        }
        return result;
    }
    
    public List<MethodTriple> getData() {
        return methods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HookCache{Methods=[");
        for (MethodTriple m : methods) {
            sb.append(m.toString()).append(", ");
        }
        if (!methods.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]}");
        return sb.toString();
    }
    
    public String toClass() {
        StringBuilder sb = new StringBuilder("HookCache{Class=[");
        for (MethodTriple m : methods) {
            sb.append(m.className).append(", ");
        }
        if (!methods.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]}");
        return sb.toString();
    }

    public static final Creator<HookCache> CREATOR = new Creator<HookCache>() {
        @Override
        public HookCache createFromParcel(Parcel in) {
            return new HookCache(in);
        }

        @Override
        public HookCache[] newArray(int size) {
            return new HookCache[size];
        }
    };
}