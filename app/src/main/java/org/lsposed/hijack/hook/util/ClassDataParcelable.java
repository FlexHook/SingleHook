package org.lsposed.hijack.hook.util;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassDataParcelable extends CacheUtil implements Parcelable {
    private List<CacheUtil.ClassData> classData;
    public ClassDataParcelable(List<CacheUtil.ClassData> classData) {
        this.classData = classData;
    }
    protected ClassDataParcelable(Parcel in) {
        int size = in.readInt();
        classData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            CacheUtil.ClassData clazz = new CacheUtil.ClassData();
            clazz.ClassName = in.readString();
            clazz.SuperClassName = in.readString();
            clazz.SourceFile = in.readString();
            clazz.Modifiers = in.readInt();
            clazz.MethodCount = in.readInt();
            clazz.FieldCount = in.readInt();
            clazz.InterfaceCount = in.readInt();
            clazz.InterfaceNames = in.createStringArrayList();
            
            int MSize = in.readInt();
            List<CacheUtil.ChildMethodData> Methods = new ArrayList<>();
            for (int i2 = 0; i2 < MSize; i2++) {
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
                Methods.add(childMethod);
            }
            clazz.Methods = Methods;
            
            int FSize = in.readInt();
            List<CacheUtil.UsingFieldsData> Fields = new ArrayList<>();
            for (int i2 = 0; i2 < FSize; i2++) {
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
                Fields.add(field);
            }
            clazz.Fields = Fields;
            
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
            clazz.Annotations = mAnnotations;
            classData.add(clazz);
        }
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.classData.size());
        for (CacheUtil.ClassData clazz : this.classData) {
            dest.writeString(clazz.ClassName);
            dest.writeString(clazz.SuperClassName);
            dest.writeString(clazz.SourceFile);
            dest.writeInt(clazz.Modifiers);
            dest.writeInt(clazz.MethodCount);
            dest.writeInt(clazz.FieldCount);
            dest.writeInt(clazz.InterfaceCount);
            dest.writeStringList(clazz.InterfaceNames);
            dest.writeInt(clazz.Methods.size());
            for (CacheUtil.ChildMethodData childMethod : clazz.Methods) {
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
            dest.writeInt(clazz.Fields.size());
            for (CacheUtil.UsingFieldsData usingFields : clazz.Fields) {
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
            dest.writeInt(clazz.Annotations.size());
            for (CacheUtil.mAnnotations annotation : clazz.Annotations) {
                dest.writeString(annotation.type);
                Bundle bundle = new Bundle();
                annotation.Elements.forEach((k,v)->{
                    bundle.putString(k, v);
                });
                dest.writeBundle(bundle);
            }
        }
    }
    
    public List<CacheUtil.ClassData> getData() {
    	return this.classData;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<ClassDataParcelable> CREATOR = new Creator<ClassDataParcelable>() {
        @Override
        public ClassDataParcelable createFromParcel(Parcel in) {
            return new ClassDataParcelable(in);
        }
        @Override
        public ClassDataParcelable[] newArray(int size) {
            return new ClassDataParcelable[size];
        }
    };
}
