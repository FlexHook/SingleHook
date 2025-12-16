package org.lsposed.hijack.hook.util;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MethodDataParcelable extends CacheUtil implements Parcelable {
    
    private List<CacheUtil.MethodData> methods;

    public MethodDataParcelable(List<CacheUtil.MethodData> methodDataList) {
        this.methods = methodDataList;
    }

    protected MethodDataParcelable(Parcel in) {
        int size = in.readInt();
        methods = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            CacheUtil.MethodData method = new CacheUtil.MethodData();
            method.ClassName = in.readString();
            method.MethodName = in.readString();
            method.ReturnTypeName = in.readString();
            method.paramTypes = in.createStringArrayList();
            method.UsingStrings = in.createStringArrayList();
            method.Modifiers = in.readInt();
            method.isStaticInitializer = Boolean.parseBoolean(in.readString());
            method.isConstructor = Boolean.parseBoolean(in.readString());
            method.isMethod = Boolean.parseBoolean(in.readString());
            
            int ISize = in.readInt();
            List<CacheUtil.ChildMethodData> InvokesMethods = new ArrayList<>();
            for (int i2 = 0; i2 < ISize; i2++) {
                CacheUtil.ChildMethodData childMethod = new CacheUtil.ChildMethodData();
                childMethod.ClassName = in.readString();
                childMethod.MethodName = in.readString();
                childMethod.ReturnTypeName = in.readString();
                childMethod.paramTypes = in.createStringArrayList();
                childMethod.UsingStrings = in.createStringArrayList();
                childMethod.Modifiers = in.readInt();
                childMethod.isStaticInitializer = Boolean.parseBoolean(in.readString());
                childMethod.isConstructor = Boolean.parseBoolean(in.readString());
                childMethod.isMethod = Boolean.parseBoolean(in.readString());
                int childASize = in.readInt();
                List<CacheUtil.mAnnotations> childAnnotations = new ArrayList<>();
                for (int i3 = 0; i3 < childASize; i3++) {
                    CacheUtil.mAnnotations field = new CacheUtil.mAnnotations();
                    field.type = in.readString();
                    HashMap<String, String> dataMap = new HashMap<>();
                    Bundle bundle = in.readBundle(mClassLoader);
                    if (bundle != null) {
                        for (String key : bundle.keySet()) {
                            dataMap.put(key, bundle.getString(key));
                        }
                    }
                    field.Elements = dataMap;
                    childAnnotations.add(field);
                }
                childMethod.Annotations = childAnnotations;
                InvokesMethods.add(childMethod);
            }
            method.Invokes = InvokesMethods;
            
            int CSize = in.readInt();
            List<CacheUtil.ChildMethodData> CallersMethods = new ArrayList<>();
            for (int i2 = 0; i2 < CSize; i2++) {
                CacheUtil.ChildMethodData childMethod = new CacheUtil.ChildMethodData();
                childMethod.ClassName = in.readString();
                childMethod.MethodName = in.readString();
                childMethod.ReturnTypeName = in.readString();
                childMethod.paramTypes = in.createStringArrayList();
                childMethod.UsingStrings = in.createStringArrayList();
                childMethod.Modifiers = in.readInt();
                childMethod.isStaticInitializer = Boolean.parseBoolean(in.readString());
                childMethod.isConstructor = Boolean.parseBoolean(in.readString());
                childMethod.isMethod = Boolean.parseBoolean(in.readString());
                int childASize = in.readInt();
                List<CacheUtil.mAnnotations> childAnnotations = new ArrayList<>();
                for (int i3 = 0; i3 < childASize; i3++) {
                    CacheUtil.mAnnotations field = new CacheUtil.mAnnotations();
                    field.type = in.readString();
                    HashMap<String, String> dataMap = new HashMap<>();
                    Bundle bundle = in.readBundle(mClassLoader);
                    if (bundle != null) {
                        for (String key : bundle.keySet()) {
                            dataMap.put(key, bundle.getString(key));
                        }
                    }
                    field.Elements = dataMap;
                    childAnnotations.add(field);
                }
                childMethod.Annotations = childAnnotations;
                CallersMethods.add(childMethod);
            }
            method.Callers = CallersMethods;
            
            int USize = in.readInt();
            List<CacheUtil.UsingFieldsData> usingFields = new ArrayList<>();
            for (int i2 = 0; i2 < USize; i2++) {
                CacheUtil.UsingFieldsData field = new CacheUtil.UsingFieldsData();
                field.ClassName = in.readString();
                field.FieldName = in.readString();
                field.TypeName = in.readString();
                field.UsingType = in.readString();
                field.Modifiers = in.readInt();
                int childASize = in.readInt();
                List<CacheUtil.mAnnotations> childAnnotations = new ArrayList<>();
                for (int i3 = 0; i3 < childASize; i3++) {
                    CacheUtil.mAnnotations cfield = new CacheUtil.mAnnotations();
                    cfield.type = in.readString();
                    HashMap<String, String> dataMap = new HashMap<>();
                    Bundle bundle = in.readBundle(mClassLoader);
                    if (bundle != null) {
                        for (String key : bundle.keySet()) {
                            dataMap.put(key, bundle.getString(key));
                        }
                    }
                    cfield.Elements = dataMap;
                    childAnnotations.add(cfield);
                }
                field.Annotations = childAnnotations;
                usingFields.add(field);
            }
            method.UsingFields = usingFields;
            
            int ASize = in.readInt();
            List<CacheUtil.mAnnotations> mAnnotations = new ArrayList<>();
            for (int i2 = 0; i2 < ASize; i2++) {
                CacheUtil.mAnnotations field = new CacheUtil.mAnnotations();
                field.type = in.readString();
                HashMap<String, String> dataMap = new HashMap<>();
                Bundle bundle = in.readBundle(mClassLoader);
                if (bundle != null) {
                    for (String key : bundle.keySet()) {
                        dataMap.put(key, bundle.getString(key));
                    }
                }
                field.Elements = dataMap;
                mAnnotations.add(field);
            }
            method.Annotations = mAnnotations;
            
            methods.add(method);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(methods.size());
        for (CacheUtil.MethodData method : methods) {
            dest.writeString(method.ClassName);
            dest.writeString(method.MethodName);
            dest.writeString(method.ReturnTypeName);
            dest.writeStringList(method.paramTypes);
            dest.writeStringList(method.UsingStrings);
            dest.writeInt(method.Modifiers);
            dest.writeString(method.isStaticInitializer ? "true" : "false");
            dest.writeString(method.isConstructor ? "true" : "false");
            dest.writeString(method.isMethod ? "true" : "false");
            dest.writeInt(method.Invokes.size());
            for (CacheUtil.ChildMethodData childMethod : method.Invokes) {
                dest.writeString(childMethod.ClassName);
                dest.writeString(childMethod.MethodName);
                dest.writeString(childMethod.ReturnTypeName);
                dest.writeStringList(childMethod.paramTypes);
                dest.writeStringList(childMethod.UsingStrings);
                dest.writeInt(childMethod.Modifiers);
                dest.writeString(childMethod.isStaticInitializer ? "true" : "false");
                dest.writeString(childMethod.isConstructor ? "true" : "false");
                dest.writeString(childMethod.isMethod ? "true" : "false");
                dest.writeInt(childMethod.Annotations.size());
                for (CacheUtil.mAnnotations annotation : childMethod.Annotations) {
                    dest.writeString(annotation.type);
                    Bundle bundle = new Bundle();
                    annotation.Elements.forEach((k,v)->{
                        bundle.putString(k, v);
                    });
                    dest.writeBundle(bundle);
                }
            }
            dest.writeInt(method.Callers.size());
            for (CacheUtil.ChildMethodData childMethod : method.Callers) {
                dest.writeString(childMethod.ClassName);
                dest.writeString(childMethod.MethodName);
                dest.writeString(childMethod.ReturnTypeName);
                dest.writeStringList(childMethod.paramTypes);
                dest.writeStringList(childMethod.UsingStrings);
                dest.writeInt(childMethod.Modifiers);
                dest.writeString(childMethod.isStaticInitializer ? "true" : "false");
                dest.writeString(childMethod.isConstructor ? "true" : "false");
                dest.writeString(childMethod.isMethod ? "true" : "false");
                dest.writeInt(childMethod.Annotations.size());
                for (CacheUtil.mAnnotations annotation : childMethod.Annotations) {
                    dest.writeString(annotation.type);
                    Bundle bundle = new Bundle();
                    annotation.Elements.forEach((k,v)->{
                        bundle.putString(k, v);
                    });
                    dest.writeBundle(bundle);
                }
            }
            dest.writeInt(method.UsingFields.size());
            for (CacheUtil.UsingFieldsData usingFields : method.UsingFields) {
                dest.writeString(usingFields.ClassName);
                dest.writeString(usingFields.FieldName);
                dest.writeString(usingFields.TypeName);
                dest.writeString(usingFields.UsingType);
                dest.writeInt(usingFields.Modifiers);
                dest.writeInt(usingFields.Annotations.size());
                for (CacheUtil.mAnnotations annotation : usingFields.Annotations) {
                    dest.writeString(annotation.type);
                    Bundle bundle = new Bundle();
                    annotation.Elements.forEach((k,v)->{
                        bundle.putString(k, v);
                    });
                    dest.writeBundle(bundle);
                }
            }
            dest.writeInt(method.Annotations.size());
            for (CacheUtil.mAnnotations annotation : method.Annotations) {
                dest.writeString(annotation.type);
                Bundle bundle = new Bundle();
                annotation.Elements.forEach((k,v)->{
                    bundle.putString(k, v);
                });
                dest.writeBundle(bundle);
            }
        }
    }
    
    public List<CacheUtil.MethodData> getData() {
    	return this.methods;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MethodDataParcelable{Methods=[");
        for (CacheUtil.MethodData m : methods) {
            sb.append(m.toString()).append(", ");
        }
        if (!methods.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MethodDataParcelable> CREATOR = new Creator<MethodDataParcelable>() {
        @Override
        public MethodDataParcelable createFromParcel(Parcel in) {
            return new MethodDataParcelable(in);
        }
        @Override
        public MethodDataParcelable[] newArray(int size) {
            return new MethodDataParcelable[size];
        }
    };
}