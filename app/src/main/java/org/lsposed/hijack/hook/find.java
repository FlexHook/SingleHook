package org.lsposed.hijack.hook;

import com.tencent.mmkv.MMKV;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.lsposed.hijack.hook.util.CacheUtil;
import org.lsposed.hijack.hook.util.ClassDataParcelable;
import org.lsposed.hijack.hook.util.MethodDataParcelable;
import org.luckypray.dexkit.DexKitBridge;
import org.luckypray.dexkit.query.FindClass;
import org.luckypray.dexkit.query.FindField;
import org.luckypray.dexkit.query.FindMethod;
import org.luckypray.dexkit.query.matchers.ClassMatcher;
import org.luckypray.dexkit.query.matchers.FieldMatcher;
import org.luckypray.dexkit.query.matchers.MethodMatcher;
import org.luckypray.dexkit.result.MethodData;

public class find extends HookUtils {
    
    private String mKey;
    private FindMethod findMethod;
    private FindClass findClass;
    private FindField findField;
    private static MMKV cacheMethod, cacheClass;
    private static List<String> cachesMethodKey = new ArrayList<>();
    private static List<String> cachesClassKey = new ArrayList<>();
    
    public static void init() {
        cacheMethod = MMKV.mmkvWithID("HookCacheMethod", MMKV.MULTI_PROCESS_MODE);
        cacheClass = MMKV.mmkvWithID("HookCacheClass", MMKV.MULTI_PROCESS_MODE);
    }
    
    public static void repair() {
    	for (String key : cacheMethod.allKeys()) {
            if (!cachesMethodKey.contains(key)) {
                cacheMethod.removeValueForKey(key);
            }
        }
        for (String key : cacheClass.allKeys()) {
            if (!cachesClassKey.contains(key)) {
                cacheClass.removeValueForKey(key);
            }
        }
        if (DEBUG) l("成功更新"+ (cachesClassKey.size() + cachesMethodKey.size()) + "个配置");
    }
    
    public find(String key) {
    	this.mKey = key;
    }
    
    public static find key(String key) {
        return new find(key);
    }

    public find.ClassDatas findClass(FindClass findClass) {
    	this.findClass = findClass;
        return new find.ClassDatas();
    }
    
    public find.ClassDatas findClass(ClassMatcher classMatcher) {
    	this.findClass = FindClass.create().matcher(classMatcher);
        return new find.ClassDatas();
    }
    
    public find.MethodDatas findMethod(FindMethod findMethod) {
    	this.findMethod = findMethod;
        return new find.MethodDatas();
    }
    
    public find.MethodDatas findMethod(MethodMatcher methodMatcher) {
    	this.findMethod = FindMethod.create().matcher(methodMatcher);
        return new find.MethodDatas();
    }

    public find.FieldDataList findField(FindField findField) {
    	this.findField = findField;
        return new find.FieldDataList();
    }
    
    public find.FieldDataList findField(FieldMatcher findField) {
    	this.findField = FindField.create().matcher(findField);
        return new find.FieldDataList();
    }
    
    interface IClassData {
        void call(CacheUtil.ClassData classDataList);
    }

    public class ClassDatas {
        public void fi(IClassData iClassData) {
            if (findClass == null || iClassData == null) return;
        	try {
                if (cacheClass.containsKey(mKey)) {
                    ClassDataParcelable data = cacheClass.decodeParcelable(mKey, ClassDataParcelable.class);
                    if (data != null) {
                        boolean isOk = data.getData().stream().allMatch(clazz -> clazz.isOk());
                        if (isOk) {
                            data.getData().forEach(clazz -> iClassData.call(clazz));
                            if (DEBUG) l("缓存-Class-" + mKey, data.toString());
                            if (!can) return;
                        }
                    }
                }
            } catch(Throwable err) {}
            List<String> keys = new ArrayList<>();
            List<CacheUtil.ClassData> classDatas = new ArrayList<>();
            dexKit((Bridge) -> {
                Bridge.get().findClass(findClass).forEach(clazz ->{
                    if (keys.contains(clazz.getName())) return;
                    keys.add(clazz.getName());
                    CacheUtil.ClassData c = new CacheUtil.ClassData();
                    c.ClassName = clazz.getName();
                    c.SuperClassName = clazz.getSuperClass().getName();
                    c.SourceFile = clazz.getSourceFile();
                    c.MethodCount = clazz.getMethodCount();
                    c.FieldCount = clazz.getFieldCount();
                    c.InterfaceCount = clazz.getInterfaceCount();
                    c.Modifiers = clazz.getModifiers();
                    List<String> InterfaceNames = new ArrayList<>();
                    clazz.getInterfaces().forEach(If ->{
                        InterfaceNames.add(If.getName());
                    });
                    c.InterfaceNames = InterfaceNames;
                    List<CacheUtil.ChildMethodData> methods = new ArrayList<>();
                    List<CacheUtil.UsingFieldsData> fields = new ArrayList<>();
                    clazz.getMethods().forEach(method ->{
                        CacheUtil.ChildMethodData ChildMethod = new CacheUtil.ChildMethodData();
                        ChildMethod.ClassName = method.getClassName();
                        ChildMethod.MethodName = method.getMethodName();
                        ChildMethod.paramTypes = method.getParamTypeNames();
                        ChildMethod.ReturnTypeName = method.getReturnTypeName();
                        ChildMethod.UsingStrings = method.getUsingStrings();
                        ChildMethod.isConstructor = method.isConstructor();
                        ChildMethod.isMethod = method.isMethod();
                        ChildMethod.isStaticInitializer = method.isStaticInitializer();
                        ChildMethod.Modifiers = method.getModifiers();
                        List<CacheUtil.mAnnotations> mAnnotations2 = new ArrayList<>();
                        method.getAnnotations().forEach(t->{
                            CacheUtil.mAnnotations mA = new CacheUtil.mAnnotations();
                            mA.type = t.getTypeName();
                            HashMap<String, String> mElements = new HashMap<>();
                            t.getElements().forEach(y->{
                                mElements.put(y.getName(), String.valueOf(y.getValue().getValue()));
                            });
                            mA.Elements = mElements;
                            mAnnotations2.add(mA);
                        });
                        ChildMethod.Annotations = mAnnotations2;
                        methods.add(ChildMethod);
                    });
                    clazz.getFields().forEach(field ->{
                        CacheUtil.UsingFieldsData UsingField = new CacheUtil.UsingFieldsData();
                        UsingField.ClassName = field.getClassName();
                        UsingField.FieldName = field.getFieldName();
                        UsingField.TypeName = field.getTypeName();
                        UsingField.UsingType = "null";
                        UsingField.Modifiers = field.getModifiers();
                        List<CacheUtil.mAnnotations> mAnnotations2 = new ArrayList<>();
                        field.getAnnotations().forEach(t->{
                            CacheUtil.mAnnotations mA = new CacheUtil.mAnnotations();
                            mA.type = t.getTypeName();
                            HashMap<String, String> mElements = new HashMap<>();
                            t.getElements().forEach(y->{
                                mElements.put(y.getName(), String.valueOf(y.getValue().getValue()));
                            });
                            mA.Elements = mElements;
                            mAnnotations2.add(mA);
                        });
                        UsingField.Annotations = mAnnotations2;
                        fields.add(UsingField);
                    });
                    c.Methods = methods;
                    c.Fields = fields;
                    List<CacheUtil.mAnnotations> mAnnotations = new ArrayList<>();
                    clazz.getAnnotations().forEach(t->{
                        CacheUtil.mAnnotations mA = new CacheUtil.mAnnotations();
                        mA.type = t.getTypeName();
                        HashMap<String, String> mElements = new HashMap<>();
                        t.getElements().forEach(y->{
                            mElements.put(y.getName(), String.valueOf(y.getValue().getValue()));
                        });
                        mA.Elements = mElements;
                        mAnnotations.add(mA);
                    });
                    c.Annotations = mAnnotations;
                    classDatas.add(c);
                    iClassData.call(c);
                });
                Bridge.close();
                if (Bridge.isOk() && classDatas.size() > 0) {
                    ClassDataParcelable data = new ClassDataParcelable(classDatas);
                    cacheClass.encode(mKey, data);
                    cachesClassKey.add(mKey);
                    if (DEBUG) l("kit-Class-" + mKey, data.toString());
                }
            });
        }
        public find.MethodDatas findMethod(MethodMatcher methodMatcher) {
            findMethod = FindMethod.create().matcher(methodMatcher);
            find.MethodDatas met = new find.MethodDatas();
            met.hasClassMatcher = true;
            return met;
        }
        public find.MethodDatas findMethod(FindMethod nFindMethod) {
            findMethod = nFindMethod;
            find.MethodDatas met = new find.MethodDatas();
            met.hasClassMatcher = true;
            return met;
        }
    }
    
    interface IMethodDatas {
        void call(CacheUtil.MethodData methodDataList);
    }

    public class MethodDatas {
        public boolean hasClassMatcher;
        private interface it {
            void call(MethodData methodData);
        }
        private void forEach(DexKitBridge Bridge, it it) {
        	if (hasClassMatcher) {
                if (findClass != null) {
                    Bridge.findClass(findClass).findMethod(findMethod).forEach(methodData -> {
                        it.call(methodData);
                    });
                }
            } else {
                Bridge.findMethod(findMethod).forEach(methodData -> {
                    it.call(methodData);
                });
            }
        }
        public void hook(Object mCallback) {
        	fi(v->{
                v.hook(mCallback);
            });
        }
        public void fi(IMethodDatas iMethodDatas) {
            if (iMethodDatas == null || findMethod == null) return;
        	try {
                if (cacheMethod.containsKey(mKey)) {
                    MethodDataParcelable data = cacheMethod.decodeParcelable(mKey, MethodDataParcelable.class);
                    if (data != null) {
                        boolean isOk = data.getData().stream().allMatch(method -> method.isOk());
                        if (isOk) {
                            data.getData().forEach(met -> iMethodDatas.call(met));
                            if (DEBUG) l("缓存-Method-" + mKey, data.toString());
                            if (!can) return;
                        }
                    }
                }
            } catch(Throwable err) {}
            List<String> keys = new ArrayList<>();
            List<CacheUtil.MethodData> methods = new ArrayList<>();
            dexKit((Bridge) -> {
                forEach(Bridge.get(), methodData -> {
                    String key = methodData.getClassName() + "." + methodData.getMethodName() + "(" + String.join(", ", methodData.getParamTypeNames()) + ")";
                    if (keys.contains(key)) return;
                    keys.add(key);
                    CacheUtil.MethodData CacheMethod = new CacheUtil.MethodData();
                    CacheMethod.ClassName = methodData.getClassName();
                    CacheMethod.MethodName = methodData.getMethodName();
                    CacheMethod.paramTypes = methodData.getParamTypeNames();
                    CacheMethod.ReturnTypeName = methodData.getReturnTypeName();
                    CacheMethod.UsingStrings = methodData.getUsingStrings();
                    CacheMethod.Modifiers = methodData.getModifiers();
                    CacheMethod.isConstructor = methodData.isConstructor();
                    CacheMethod.isMethod = methodData.isMethod();
                    CacheMethod.isStaticInitializer = methodData.isStaticInitializer();
                    List<CacheUtil.mAnnotations> mAnnotations = new ArrayList<>();
                    methodData.getAnnotations().forEach(t->{
                        CacheUtil.mAnnotations mA = new CacheUtil.mAnnotations();
                        mA.type = t.getTypeName();
                        HashMap<String, String> mElements = new HashMap<>();
                        t.getElements().forEach(y->{
                            mElements.put(y.getName(), String.valueOf(y.getValue().getValue()));
                        });
                        mA.Elements = mElements;
                        mAnnotations.add(mA);
                    });
                    CacheMethod.Annotations = mAnnotations;
                    List<CacheUtil.ChildMethodData> CacheMethodCallers = new ArrayList<>();
                    List<CacheUtil.ChildMethodData> CacheMethodInvokes = new ArrayList<>();
                    List<CacheUtil.UsingFieldsData> CacheUsingFields = new ArrayList<>();
                    methodData.getCallers().forEach(method ->{
                        CacheUtil.ChildMethodData ChildMethod = new CacheUtil.ChildMethodData();
                        ChildMethod.ClassName = method.getClassName();
                        ChildMethod.MethodName = method.getMethodName();
                        ChildMethod.paramTypes = method.getParamTypeNames();
                        ChildMethod.ReturnTypeName = method.getReturnTypeName();
                        ChildMethod.UsingStrings = method.getUsingStrings();
                        ChildMethod.isConstructor = method.isConstructor();
                        ChildMethod.isMethod = method.isMethod();
                        ChildMethod.isStaticInitializer = method.isStaticInitializer();
                        ChildMethod.Modifiers = method.getModifiers();
                        ChildMethod.isStaticInitializer = methodData.isStaticInitializer();
                        List<CacheUtil.mAnnotations> mAnnotations2 = new ArrayList<>();
                        method.getAnnotations().forEach(t->{
                            CacheUtil.mAnnotations mA = new CacheUtil.mAnnotations();
                            mA.type = t.getTypeName();
                            HashMap<String, String> mElements = new HashMap<>();
                            t.getElements().forEach(y->{
                                mElements.put(y.getName(), String.valueOf(y.getValue().getValue()));
                            });
                            mA.Elements = mElements;
                            mAnnotations2.add(mA);
                        });
                        ChildMethod.Annotations = mAnnotations2;
                        CacheMethodCallers.add(ChildMethod);
                    });
                    methodData.getInvokes().forEach(method ->{
                        CacheUtil.ChildMethodData ChildMethod = new CacheUtil.ChildMethodData();
                        ChildMethod.ClassName = method.getClassName();
                        ChildMethod.MethodName = method.getMethodName();
                        ChildMethod.paramTypes = method.getParamTypeNames();
                        ChildMethod.ReturnTypeName = method.getReturnTypeName();
                        ChildMethod.UsingStrings = method.getUsingStrings();
                        ChildMethod.isConstructor = method.isConstructor();
                        ChildMethod.isMethod = method.isMethod();
                        ChildMethod.isStaticInitializer = method.isStaticInitializer();
                        ChildMethod.Modifiers = method.getModifiers();
                        List<CacheUtil.mAnnotations> mAnnotations2 = new ArrayList<>();
                        method.getAnnotations().forEach(t->{
                            CacheUtil.mAnnotations mA = new CacheUtil.mAnnotations();
                            mA.type = t.getTypeName();
                            HashMap<String, String> mElements = new HashMap<>();
                            t.getElements().forEach(y->{
                                mElements.put(y.getName(), String.valueOf(y.getValue().getValue()));
                            });
                            mA.Elements = mElements;
                            mAnnotations2.add(mA);
                        });
                        ChildMethod.Annotations = mAnnotations2;
                        CacheMethodInvokes.add(ChildMethod);
                    });
                    methodData.getUsingFields().forEach(field ->{
                        CacheUtil.UsingFieldsData UsingField = new CacheUtil.UsingFieldsData();
                        UsingField.ClassName = field.getField().getClassName();
                        UsingField.FieldName = field.getField().getFieldName();
                        UsingField.TypeName = field.getField().getTypeName();
                        UsingField.UsingType = field.getUsingType().isRead() ? "get" : "set";
                        UsingField.Modifiers = field.getField().getModifiers();
                        List<CacheUtil.mAnnotations> mAnnotations2 = new ArrayList<>();
                        field.getField().getAnnotations().forEach(t->{
                            CacheUtil.mAnnotations mA = new CacheUtil.mAnnotations();
                            mA.type = t.getTypeName();
                            HashMap<String, String> mElements = new HashMap<>();
                            t.getElements().forEach(y->{
                                mElements.put(y.getName(), String.valueOf(y.getValue().getValue()));
                            });
                            mA.Elements = mElements;
                            mAnnotations2.add(mA);
                        });
                        UsingField.Annotations = mAnnotations2;
                        CacheUsingFields.add(UsingField);
                    });
                    CacheMethod.Callers = CacheMethodCallers;
                    CacheMethod.Invokes = CacheMethodInvokes;
                    CacheMethod.UsingFields = CacheUsingFields;
                    methods.add(CacheMethod);
                    iMethodDatas.call(CacheMethod);
                });
                Bridge.close();
                if (Bridge.isOk() && methods.size() > 0) {
                    MethodDataParcelable data = new MethodDataParcelable(methods);
                    cacheMethod.encode(mKey, data);
                    cachesMethodKey.add(mKey);
                    if (DEBUG) l("kit-Method-" + mKey, data.toString());
                }
            });
        }
    }

    public class FieldDataList {
        
    }
}
