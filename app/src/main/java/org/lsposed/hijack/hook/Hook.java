package org.lsposed.hijack.hook;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import org.lsposed.hijack.BuildConfig;
import com.google.gson.Gson;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.lsposed.hijack.hook.find;
import org.lsposed.hijack.hook.util.CacheUtil;
import org.luckypray.dexkit.query.FindField;
import org.luckypray.dexkit.query.FindMethod;
import org.luckypray.dexkit.query.enums.UsingType;
import org.luckypray.dexkit.query.matchers.AnnotationElementMatcher;
import org.luckypray.dexkit.query.matchers.AnnotationMatcher;
import org.luckypray.dexkit.query.matchers.ClassMatcher;
import org.luckypray.dexkit.query.matchers.FieldMatcher;
import org.luckypray.dexkit.query.matchers.MethodMatcher;
import org.luckypray.dexkit.query.matchers.UsingFieldMatcher;

public class Hook extends HookUtils {

    public static void StartInject(String packages) {
        switch (packages) {
            case BuildConfig.APPLICATION_ID:
                find.key("isModuleActivated").findMethod(
                    MethodMatcher.create()
                        .returnType(Types.BooleanType)
                        .paramCount(0)
                        .name("isModuleActivated")
                ).hook(true);
                System.setProperty("isModuleActivated", "1");
                break;
            case "cn.trinea.android.developertools":
                Hook_开发助手();
                break;
            case "tv.danmaku.bili":
                Hook_哔哩哔哩();
                break;
            case "com.ycwlhz.tksp":
            case "com.jiandan.ji":
                Hook_大海视频系列();
                break;
            case "com.chaozh.iReader.dj":
                Hook_得间免费小说();
                break;
            case "com.github.eprendre.tingshu":
                Hook_我的听书();
                break;
            case "com.dragon.read":
                Hook_番茄免费小说();
                break;
            case "com.xs.fm":
                Hook_番茄畅听();
                break;
            case "com.kkdyC1V251122.T180136":
                Hook_可可影视();
                break;
            case "com.kmxs.reader":
                Hook_七猫免费小说();
                break;
            case "com.h4399.gamebox":
                Hook_4399在线玩();
                break;
            case "com.phoenix.read":
                Hook_红果免费短剧();
                break;
            case "com.gstarmc.android":
                Hook_CAD看图王();
                break;
            case "com.duapps.recorder":
                Hook_小熊录屏();
                break;
            case "com.cqy.ppttools":
                Hook_非凡PPT();
                break;
            case "com.cqy.wordtools":
                Hook_非凡文档();
                break;
            case "com.cqy.exceltools":
                Hook_非凡表格();
                break;
            case "com.ysg.ai.pptmaker":
                Hook_AiPPT制作师();
                break;
            case "com.kylin.read":
                Hook_红果免费漫剧();
                break;
            case "com.xhey.xcamera":
                Hook_今日水印相机();
                break;
            case "com.intsig.camscanner":
                Hook_扫描全能王();
                break;
            case "me.mapleaf.calendar":
                Hook_一叶日历();
                break;
            case "com.youloft.watcher":
                Hook_LinkUp();
                break;
            case "com.charm.clockdesktop":
                Hook_翻页时钟();
                break;
            case "com.ZArchiver.chengyuda":
                Hook_ZArchiver解压缩工具();
                break;
            case "com.clover.daysmatter":
                Hook_倒数日();
                break;
            case "com.apowersoft.backgrounderaser":
                Hook_傲软抠图();
                break;
            case "com.fanyiiap.wd":
                Hook_全能翻译宝();
                break;
            case "com.wander.android.wallpaper":
                Hook_元气壁纸();
                break;
            case "com.wangc.item":
                Hook_一木记物();
                break;
            case "com.wangc.todolist":
                Hook_一木清单();
                break;
            case "com.growing.topwidgets":
                Hook_万能小组件_Top_Widgets();
                break;
            case "cn.etouch.ecalendar":
                Hook_中华万年历();
                break;
            case "com.moji.mjweather":
                Hook_墨迹天气();
                break;
            case "com.mathfuns.mathfuns":
                Hook_Mathfuns();
                break;
            case "com.gameley.yyjz":
                Hook_元元记账();
                break;
            case "com.ideack.logodesign":
                Hook_logo设计大师();
                break;
            case "com.sywh.mediaeditor":
                Hook_照片去水印();
                break;
            case "com.feiya.accounting":
                Hook_飞鸭AI记账();
                break;
            case "net.huanci.hsj":
                Hook_画世界();
                break;
            case "com.oralcraft.android":
                Hook_可栗口语();
                break;
            case "com.tipsoon.android":
                Hook_简讯();
                break;
            case "com.juyingfun.mqjz":
                Hook_喵钱记账();
                break;
            case "com.hcn.mm":
                Hook_修改水印相机();
                break;
            case "cn.nineton.dailycheck":
                Hook_YoYo日常();
                break;
            case "com.musketeer.drawart":
                Hook_造画艺术滤镜();
                break;
            case "app.jjyy.passstore":
                Hook_PassStore();
                break;
            case "com.photovision.camera":
                Hook_轻图();
                break;
            case "com.zzdbwku.zizbnea":
                Hook_背书匠();
                break;
            case "com.ss.android.article.video":
                Hook_西瓜视频();
                break;
            case "com.zjzy.calendartime":
                Hook_指尖时光();
                break;
            case "com.nineton.todolist":
                Hook_我要做计划();
                break;
            case "ai.blurrr.video":
                Hook_Blurrr();
                break;
            case "com.qxwl.scanimg.scanassist":
                Hook_扫描Pro();
                break;
            case "com.chan.cwallpaper":
                Hook_图凌();
                break;
            case "cn.rxxlong.translate":
                Hook_外语翻译官();
                break;
            case "com.habits.todolist.plan.wish":
                Hook_元气打卡();
                break;
            
        };
    }
    
    private static void Hook_元气打卡() {
    	find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
    }
    
    private static void Hook_外语翻译官() {
        find.key("getVipTime").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramCount(0)
                .name("getVipTime")
        ).hook("永久");
    	find.key("isVipIsValid").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipIsValid")
        ).hook(true);
        find.key("isVipUser").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipUser")
        ).hook(true);
        find.key("getFreetime").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getFreetime")
        ).hook(Integer.MAX_VALUE);
        find.key("getVipIsValid").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVipIsValid")
        ).hook(1);
        find.key("isShowAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isShowAd")
        ).hook(false);
        find.key("isShowAdConfirm").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isShowAdConfirm")
        ).hook(false);
        find.key("getIsShowAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramCount(0)
                .name("getIsShowAd")
        ).hook("0");
    }
    
    private static void Hook_图凌() {
        find.key("isVIP").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVIP")
        ).hook(true);
    	find.key("getVIP").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanClass)
                .paramCount(0)
                .name("getVIP")
        ).hook(true);
        find.key("getLocalVIP").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanClass)
                .paramCount(0)
                .name("getLocalVIP")
        ).hook(true);
        find.key("getUserType").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntClass)
                .paramCount(0)
                .name("getUserType")
        ).hook(2);
        find.key("getUserType2").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getUserType")
        ).hook(2);
    }
    
    private static void Hook_扫描Pro() {
    	find.key("getUserVipType").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramTypes(Types.ContextClass)
                .name("getUserVipType")
        ).hook(1);
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramTypes(Types.ContextClass)
                .name("isVip")
        ).hook(true);
        find.key("getUserVIPEndDate").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramTypes(Types.ContextClass)
                .name("getUserVIPEndDate")
        ).hook("forever");
    }
    
    private static void Hook_Blurrr() {
        findAndHookConstructor("ai.blurrr.video.user.model.VipStatus", mClassLoader, String.class, int.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                param.args[0] = "FOREVER";
                param.args[1] = 6;
                param.args[2] = 6;
            }
        });
        findAndHookConstructor("ai.blurrr.video.user.model.VipGrade", mClassLoader, String.class, int.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                param.args[0] = "SVIP";
                param.args[1] = 1;
                param.args[2] = 2;
            }
        });
        findAndHookConstructor("ai.blurrr.video.user.model.VipStage", mClassLoader, String.class, int.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                param.args[0] = "VALID_PERIOD";
                param.args[1] = 2;
                param.args[2] = 2;
            }
        });
    	find.key("isVip").findClass(
            ClassMatcher.create()
                .addEqString("accountCache")
                .addEqString("vipStateCache")
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
        ).fi(v ->{
            v.hook(true);
            v.getInvokes()
                .paramCount(0)
                .returnType(Types.BooleanType)
                .hook(true);
        });
    }
    
    private static void Hook_我要做计划() {
        find.key("is_vip").findClass(
            ClassMatcher.create()
                .addField(
                    FieldMatcher.create()
                    .type(Types.BooleanType)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("is_vip")
                        )
                    )
                )
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .addUsingField(
                    FieldMatcher.create()
                    .type(Types.BooleanType)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("is_vip")
                        )
                    )
                )
        ).hook(true);
        find.key("vip_etime").findClass(
            ClassMatcher.create()
                .addField(
                    FieldMatcher.create()
                    .type(Types.LongType)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("vip_etime")
                        )
                    )
                )
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .addUsingField(
                    FieldMatcher.create()
                    .type(Types.LongType)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("vip_etime")
                        )
                    )
                )
        ).hook(VipTime);
    }
    
    private static void Hook_指尖时光() {
    	find.key("getVIP").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVIP")
        ).hook(1);
        find.key("getVipStatus").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("getVipStatus")
        ).hook(true);
    }
    
    private static void Hook_西瓜视频() {
        find.key("expire_time").findClass(
            ClassMatcher.create()
                .addField(
                    FieldMatcher.create()
                    .type(Types.LongClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("expire_time")
                        )
                    )
                )
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .addUsingField(
                    FieldMatcher.create()
                    .type(Types.LongClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("expire_time")
                        )
                    )
                )
        ).hook(true);
        XC_MethodHook vip = new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Field[] fields = param.thisObject.getClass().getDeclaredFields();
                Arrays.stream(fields).forEach(field ->{
                    try {
                        String type = field.getType().getName();
                        if (type.equalsIgnoreCase(Types.LongClass) || type.equalsIgnoreCase(Types.LongType)) {
                            field.setAccessible(true);
                            field.setLong(param.thisObject, VipTime / 1000);
                        } else if (type.equalsIgnoreCase(Types.IntClass)) {
                            field.setAccessible(true);
                            field.setInt(param.thisObject, 2);
                        }
                    } catch (Throwable err) {}
                });
                param.setResult(2);
            }
        };
        XC_MethodHook vipTime = new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Field[] fields = param.thisObject.getClass().getDeclaredFields();
                Arrays.stream(fields).forEach(field ->{
                    try {
                        String type = field.getType().getName();
                        if (type.equalsIgnoreCase(Types.LongClass) || type.equalsIgnoreCase(Types.LongType)) {
                            field.setAccessible(true);
                            field.setLong(param.thisObject, VipTime / 1000);
                        } else if (type.equalsIgnoreCase(Types.IntClass)) {
                            field.setAccessible(true);
                            field.setInt(param.thisObject, 2);
                        }
                    } catch (Throwable err) {}
                });
                param.setResult(VipTime / 1000);
            }
        };
        //1未开通 2会员，3过期
        find.key("membership_status").findClass(
            ClassMatcher.create()
                .addField(
                    FieldMatcher.create()
                    .type(Types.IntClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("membership_status")
                        )
                    )
                )
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.IntClass)
                .paramCount(0)
                .addUsingField(
                    FieldMatcher.create()
                    .type(Types.IntClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("membership_status")
                        )
                    )
                )
        ).hook(vip);
        find.key("tv_status").findClass(
            ClassMatcher.create()
                .addField(
                    FieldMatcher.create()
                    .type(Types.IntClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("tv_status")
                        )
                    )
                )
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.IntClass)
                .paramCount(0)
                .addUsingField(
                    FieldMatcher.create()
                    .type(Types.IntClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("tv_status")
                        )
                    )
                )
        ).hook(vip);
        find.key("renew_status").findClass(
            ClassMatcher.create()
                .addField(
                    FieldMatcher.create()
                    .type(Types.IntClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("renew_status")
                        )
                    )
                )
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.IntClass)
                .paramCount(0)
                .addUsingField(
                    FieldMatcher.create()
                    .type(Types.IntClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("renew_status")
                        )
                    )
                )
        ).hook(vip);
        find.key("free_remain_time").findClass(
            ClassMatcher.create()
                .addField(
                    FieldMatcher.create()
                    .type(Types.LongType)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("free_remain_time")
                        )
                    )
                )
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .addUsingField(
                    FieldMatcher.create()
                    .type(Types.LongType)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("free_remain_time")
                        )
                    )
                )
        ).hook(vipTime);
        find.key("tv_expire_time").findClass(
            ClassMatcher.create()
                .addField(
                    FieldMatcher.create()
                    .type(Types.LongClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("tv_expire_time")
                        )
                    )
                )
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.LongClass)
                .paramCount(0)
                .addUsingField(
                    FieldMatcher.create()
                    .type(Types.LongClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("tv_expire_time")
                        )
                    )
                )
        ).hook(vipTime);
        find.key("membership_expire_time").findClass(
            ClassMatcher.create()
                .addField(
                    FieldMatcher.create()
                    .type(Types.LongClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("membership_expire_time")
                        )
                    )
                )
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.LongClass)
                .paramCount(0)
                .addUsingField(
                    FieldMatcher.create()
                    .type(Types.LongClass)
                    .addAnnotation(
                        AnnotationMatcher.create()
                        .addElement(
                            AnnotationElementMatcher.create()
                            .stringValue("membership_expire_time")
                        )
                    )
                )
        ).hook(vipTime);
        find.key("splash_ad_show_limit").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .addEqString("splash_ad_show_limit")
        ).hook(100);
        find.key("splash_ad_show_count").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .addEqString("splash_ad_show_count")
        ).hook(100);
        find.key("hasAdCache").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .name("hasAdCache")
        ).hook(false);
        find.key("hasTopViewSplashAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("hasTopViewSplashAd")
        ).hook(false);
        find.key("hasFloatAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramTypes(Types.StringClass)
                .name("hasFloatAd")
        ).hook(false);
        find.key("hasAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramTypes(Types.ListClass)
                .name("hasAd")
        ).hook(false);
        find.key("isAdxCoinLiveAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isAdxCoinLiveAd")
        ).hook(false);
        find.key("isUseAdFromCache").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isUseAdFromCache")
        ).hook(false);
        find.key("isRequestNewStyleNovelAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isRequestNewStyleNovelAd")
        ).hook(false);
        find.key("isLiveAdType").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramTypes(Types.StringClass)
                .name("isLiveAdType")
        ).hook(false);
        find.key("isStageRewardAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isStageRewardAd")
        ).hook(false);
        find.key("isLocalClueAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isLocalClueAd")
        ).hook(false);
        find.key("isNovelNewNaAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isNovelNewNaAd")
        ).hook(false);
        find.key("isNovelNaAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isNovelNaAd")
        ).hook(false);
        find.key("isNovelLynxAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isNovelLynxAd")
        ).hook(false);
        find.key("isDynamicAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isDynamicAd")
        ).hook(false);
        find.key("isDynamicAdSuccess").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isDynamicAdSuccess")
        ).hook(false);
        find.key("isFallbackAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isFallbackAd")
        ).hook(false);
        find.key("isOnlyHasAdsActivity").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isOnlyHasAdsActivity")
        ).hook(false);
        find.key("isAdsOptEnable").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isAdsOptEnable")
        ).hook(false);
        find.key("isSplashAdShowing").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isSplashAdShowing")
        ).hook(false);
        find.key("isConfigAdBlockRules").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isConfigAdBlockRules")
        ).hook(true);
        find.key("isAdFreeEnabled").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isAdFreeEnabled")
        ).hook(true);
        find.key("isRefreshOptSkipAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isRefreshOptSkipAd")
        ).hook(true);
        find.key("isLocalLifeAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isLocalLifeAd")
        ).hook(false);
        find.key("isImSaasAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isImSaasAd")
        ).hook(false);
        find.key("isReadFlowAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isReadFlowAd")
        ).hook(false);
        find.key("getAdType").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramCount(0)
                .name("getAdType")
        ).hook(null);
        find.key("isVerticalAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVerticalAd")
        ).hook(false);
        find.key("isReaderGameAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isReaderGameAd")
        ).hook(false);
        find.key("isNoAdForFrontChapter").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isNoAdForFrontChapter")
        ).hook(false);
        find.key("isNewChapterAdStyle").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isNewChapterAdStyle")
        ).hook(false);
        find.key("isLocalClueAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isLocalClueAd")
        ).hook(false);
        find.key("isLiveStreamAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isLiveStreamAd")
        ).hook(false);
        find.key("isLiveAdvertise").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isLiveAdvertise")
        ).hook(false);
        find.key("isLiveAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isLiveAd")
        ).hook(false);
        find.key("isEcAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isEcAd")
        ).hook(false);
        find.key("isDynamicAdData").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isDynamicAdData")
        ).hook(false);
        find.key("isBrandAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isBrandAd")
        ).hook(false);
        find.key("isAuthorAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isAuthorAd")
        ).hook(false);
        find.key("isAdFromMiddlePage").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isAdFromMiddlePage")
        ).hook(false);
        find.key("isSubscribed").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isSubscribed")
        ).hook(true);
        find.key("isSoftAdVersion2").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isSoftAdVersion")
        ).hook(false);
        find.key("isSoftAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isSoftAd")
        ).hook(false);
        find.key("isSoftAd2").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("isSoftAd")
        ).hook(0);
        find.key("isAd").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isAd")
        ).hook(false);
    	find.key("isAd2").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("isAd")
        ).hook(0);
        find.key("getIsVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("getIsVip")
        ).hook(true);
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("isVipUser").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipUser")
        ).hook(true);
        find.key("isVipEmoticonTabShow").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipEmoticonTabShow")
        ).hook(true);
        find.key("isVipSource").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipSource")
        ).hook(true);
        find.key("isSvip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isSvip")
        ).hook(true);
    }
    
    private static void Hook_背书匠() {
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("getMemberExpireDay").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramCount(0)
                .name("getMemberExpireDay")
        ).hook("2099-12-31");
    }
    
    private static void Hook_轻图() {
        find.key("getVipStatus").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVipStatus")
        ).hook(3);
        find.key("getExpireDate").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getExpireDate")
        ).hook(VipTime);
    }
    
    private static void Hook_PassStore() {
    	String resName = "prime_title_purchase";
        int resId = mContext.getResources().getIdentifier(resName, "string", packageName);
        if (resId > 0) {
            find.key(resName).findMethod(
                MethodMatcher.create()
                    .addUsingNumber(resId)
            ).fi(method -> {
                method.getInvokes()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .hook(true);
            });
        }
    }
    
    private static void Hook_造画艺术滤镜() {
        find.key("getUserLevel").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getUserLevel")
        ).fi(v ->{
            v.hook(2l);
            find.key(v.ClassName + "getUid").findClass(
                ClassMatcher.create()
                    .className(v.ClassName)
            ).findMethod(
                MethodMatcher.create()
                    .returnType(Types.LongType)
                    .paramCount(0)
            ).hook(86l);
        });
    }
    
    private static void Hook_YoYo日常() {
        find.key("isSuperVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isSuperVip")
        ).hook(true);
        find.key("isIs_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isIs_vip")
        ).hook(true);
        find.key("getVip_end_time").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getVip_end_time")
        ).hook(VipTime);
        find.key("getSvipEndTime").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getSvipEndTime")
        ).hook(VipTime);
        find.key("getSvipStatus").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getSvipStatus")
        ).hook(2);
        find.key("getVip_status").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVip_status")
        ).hook(2);
    }
    
    private static void Hook_修改水印相机() {
        find.key("isInfinityVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isInfinityVip")
        ).hook(true);
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("ad_delay").findClass(
            ClassMatcher.create()
                .addEqString("delay")
                .addEqString("ad_configs")
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.VoidType)
                .paramTypes(Types.LongType)
        ).fi(v ->{
            v.hook(
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        param.args[0] = 0l;
                    }
                }
            );
            find.key(v.ClassName + "ad_delay").findClass(
                ClassMatcher.create()
                    .className(v.ClassName)
            ).findMethod(
                MethodMatcher.create()
                    .returnType(Types.VoidType)
                    .paramTypes(Types.JSONObjectClass)
            ).hook(
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        try {
                            XposedHelpers.callMethod(param.thisObject, v.MethodName, 0l);
                        } catch(Throwable err) {}
                    }
                }
            );
            find.key(v.ClassName + "ad_configs").findClass(
                ClassMatcher.create()
                    .className(v.ClassName)
            ).findMethod(
                MethodMatcher.create()
                    .returnType(Types.VoidType)
                    .addEqString("ad_configs")
                    .paramCount(0)
            ).hook(
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        try {
                            XposedHelpers.callMethod(param.thisObject, v.MethodName, 0l);
                        } catch(Throwable err) {}
                    }
                }
            );
        });
    }
    
    private static void Hook_喵钱记账() {
        find.key("isPermanent").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isPermanent")
        ).hook(true);
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("getVipExpireTime").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getVipExpireTime")
        ).hook(VipTime);
    }
    
    private static void Hook_简讯() {
        find.key("isIs_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isIs_vip")
        ).fi(v ->{
            v.hook(true);
            find.key(v.ClassName + "getStatus").findClass(
                ClassMatcher.create()
                    .className(v.ClassName)
            ).findMethod(
                MethodMatcher.create()
                    .returnType(Types.IntType)
                    .paramCount(0)
                    .name("getStatus")
            ).hook(1);
            find.key(v.ClassName + "getVip_expire_time").findClass(
                ClassMatcher.create()
                    .className(v.ClassName)
            ).findMethod(
                MethodMatcher.create()
                    .returnType(Types.StringClass)
                    .paramCount(0)
                    .name("getVip_expire_time")
            ).hook("2099-12-31");
        });
    }
    
    private static void Hook_可栗口语() {
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("getVipExpireTimestamp").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVipExpireTimestamp")
        ).hook(Integer.MAX_VALUE);
        find.key("getExpireAt").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getExpireAt")
        ).hook(Integer.MAX_VALUE);
    }
    
    private static void Hook_画世界() {
        find.key("getVipId").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVipId")
        ).hook(1);
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
    }
    
    private static void Hook_飞鸭AI记账() {
        find.key("getVipType").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVipType")
        ).hook(2);
    	find.key("getUserIsVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("getUserIsVip")
        ).hook(true);
        find.key("isVipUse").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipUse")
        ).hook(true);
        find.key("isVipUsed").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipUsed")
        ).hook(true);
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("getVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("getVip")
        ).hook(true);
        find.key("getVipExpireTime").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getVipExpireTime")
        ).hook(VipTime);
    }
    
    private static void Hook_照片去水印() {
    	find.key("isVipForever").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipForever")
        ).hook(true);
        find.key("getIsVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("getIsVip")
        ).hook(true);
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("getVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVip")
        ).hook(1);
        find.key("isLogin").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isLogin")
        ).hook(true);
    }
    
    private static void Hook_logo设计大师() {
        find.key("getVipType").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVipType")
        ).hook(4);
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("isUserLogin").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isUserLogin")
        ).hook(true);
    }
    
    private static void Hook_元元记账() {
        find.key("getNormalVipBoolean").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanClass)
                .paramCount(0)
                .name("getNormalVipBoolean")
        ).hook(true);
        find.key("getNormalVipForever").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanClass)
                .paramCount(0)
                .name("getNormalVipForever")
        ).hook(true);
        find.key("getVisitVipDt").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongClass)
                .paramCount(0)
                .name("getVisitVipDt")
        ).hook(Vip888);
    }
    
    private static void Hook_Mathfuns() {
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .addEqString("products")
                .addEqString("valid")
        ).hook(true);
    }
    
    private static void Hook_墨迹天气() {
        find.key("getIsVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("getIsVip")
        ).hook(true);
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("isIs_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isIs_vip")
        ).hook(true);
        find.key("isVipExpiration").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipExpiration")
        ).hook(false);
    }
    
    private static void Hook_中华万年历() {
        find.key("isShowFortuneCoin").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isShowFortuneCoin")
        ).hook(true);
        find.key("isShowPayDialog").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isShowPayDialog")
        ).hook(false);
        find.key("isForeverVipUser").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isForeverVipUser")
        ).hook(true);
        find.key("isVipUser").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipUser")
        ).hook(true);
        find.key("isVipTheme").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipTheme")
        ).hook(false);
        find.key("vip_status").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .addUsingString("vip_status")
        ).hook(1);
        find.key("vip_level").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramCount(0)
                .addUsingString("vip_level")
        ).hook("2");
        find.key("vip_expire_date").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .addUsingString("vip_expire_date")
        ).hook(Vip888);
        find.key("bd_ad").findMethod(
            MethodMatcher.create()
                .returnType(Types.VoidType)
                .paramCount(0)
                .addUsingString("AbstractProdTemplate,load-dex请求，回调成功")
        ).hook(null);
        find.key("isVip").findClass(
            ClassMatcher.create()
                .addEqString("pref_is_show_ai_video_guide")
                .addEqString("pref_is_ai_home_page")
                .interfaceCount(0)
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
        ).hook(true);
    }
    
    private static void Hook_万能小组件_Top_Widgets() {
        find.key("setVip").findClass(
            ClassMatcher.create()
                .addUsingString("KEY_SP_IS_VIP")
                .addUsingString("KEY_SP_IS_GOOGLE_VIP")
                .interfaceCount(0)
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
        ).hook(true);
    }
    
    private static void Hook_一木清单() {
        find.key("getMemberType").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramCount(0)
                .name("getMemberType")
        ).hook("PERMANENT");
        find.key("getMemberExpiredTime").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getMemberExpiredTime")
        ).hook(Vip888);
    }
    
    private static void Hook_一木记物() {
        find.key("getVipInfo").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVipInfo")
        ).hook(2);
    }
    
    private static void Hook_元气壁纸() {
        find.key("isVip").findClass(
            ClassMatcher.create()
                .className("com.wander.common.vip.VipServiceImpl")
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
        ).hook(true);
    }
    
    private static void Hook_全能翻译宝() {
        find.key("isVip").findClass(
            ClassMatcher.create()
                .addEqString("user_vip_level")
                .addEqString("vip_manual_time")
                .interfaceCount(0)
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .addAnnotation(
                    AnnotationMatcher.create()
                    .type(Types.JvmStaticClass)
                )
        ).hook(true);
    }
    
    private static void Hook_傲软抠图() {
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .name("isVip")
                .paramCount(0)
        ).hook(true);
        find.key("isExpire").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .name("isExpire")
                .paramCount(0)
        ).hook(false);
        find.key("hasVipValid").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .name("hasVipValid")
                .paramCount(0)
        ).hook(true);
        find.key("hasVipValidOrBalance").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .name("hasVipValidOrBalance")
                .paramCount(0)
        ).hook(true);
        find.key("getBalance").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .name("getBalance")
                .paramCount(0)
        ).hook(getRandomLong(100, 10000));
        find.key("getVip_deadline").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongClass)
                .name("getVip_deadline")
                .paramCount(0)
        ).hook(218330035200L);
        find.key("getIs_recharge_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanClass)
                .name("getIs_recharge_vip")
                .paramCount(0)
        ).hook(true);
        find.key("getIs_recharge_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanClass)
                .name("getIs_recharge_vip")
                .paramCount(0)
        ).hook(true);
        find.key("getIs_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanClass)
                .name("getIs_vip")
                .paramCount(0)
        ).hook(true);
    }
    
    private static void Hook_倒数日() {
        find.key("PREFERENCE_LAST_MILESTONE_ALERT_TIME").findClass(
            ClassMatcher.create()
                .addEqString("PREFERENCE_REQUESTED_HM_PERMISSION")
                .addEqString("PREFERENCE_LAST_MILESTONE_ALERT_TIME")
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramTypes(Types.ContextClass)
        ).hook(true);
        find.key("PREFERENCE_NAME_LOCAL_PURCHASE").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .addEqString("PREFERENCE_NAME_LOCAL_PURCHASE")
                .paramTypes(Types.ApplicationClass)
        ).hook(true);
    }
    
    private static void Hook_ZArchiver解压缩工具() {
        find.key("canUse").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(1)
                .name("canUse")
        ).hook(true);
        find.key("isNeedPay").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isNeedPay")
        ).hook(true);
        find.key("isLogin").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isLogin")
        ).hook(true);
    }
    
    private static void Hook_翻页时钟() {
        find.key("getVip_level").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVip_level")
        ).hook(1);
    }
    
    private static void Hook_LinkUp() {
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
    }
    
    private static void Hook_一叶日历() {
        find.key("getIsPro").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("getIsPro")
        ).hook(true);
    }
    
    private static void Hook_扫描全能王() {
        find.key("key_show_login_dialog_on_scan_done").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .addEqString("key_show_login_dialog_on_scan_done")
        ).hook(true);
        find.key("qp3sdjd79xhdas02sd").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .addEqString("qp3sdjd79xhdas02sd")
        ).hook(1l);
    }
    
    private static void Hook_今日水印相机() {
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("isAdFreeVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isAdFreeVip")
        ).hook(true);
        find.key("getVipExpireTime").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getVipExpireTime")
        ).hook(Vip888);
        find.key("getAdFreeVipExpireTime").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getAdFreeVipExpireTime")
        ).hook(Vip888);
        find.key("getRemoveWatermarkCounts").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getRemoveWatermarkCounts")
        ).hook(1);
    }
    
    private static void Hook_红果免费漫剧() {
        Fields.create()
            .add("canWorn", true)
            .add("isAdFreeVip", true)
            .add("isStoryVip", true)
            .add("isPubVip", true)
            .add("adVipAvailable", true)
            .add("autoRenew", true)
            .add("continueMonth", true)
            .add("continueMonthBuy", true)
            .add("isUnionVip", true)
            .add("isAutoCharge", true)
            .add("isAdVip", true)
            .add("isVip", true)
            .add("isVip", "1")
            .add("expireTime", "218330035200")
            .Build();
    }
    
    private static void Hook_AiPPT制作师() {
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("isCanUseVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isCanUseVip")
        ).hook(true);
        find.key("isPermanentVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isPermanentVip")
        ).hook(true);
    }
    
    private static void Hook_非凡表格() {
        find.key("getVip_expire_time").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getVip_expire_time")
        ).hook(VipTime);
        InjectActivity((activity, RootView, ActivityNane) -> {
            if (ActivityNane.endsWith("VipTestActivity") || ActivityNane.endsWith("VipTestActivity2") || ActivityNane.endsWith("VipActivity") || ActivityNane.endsWith("VipActivity2")) {
                activity.finish();
            } else if (ActivityNane.endsWith("SplashActivity")) {
                find.key("Splash_ad").findClass(
                    ClassMatcher.create()
                        .className(ActivityNane)
                ).findMethod(
                    MethodMatcher.create()
                        .returnType(Types.VoidType)
                        .paramCount(0)
                ).fi(method -> {
                    method.getInvokes()
                    .name("startActivity")
                    .fi(child -> {
                        try {
                        	boolean isOk = Arrays.stream(method.getDeclaredMethods()).anyMatch(met-> met.getName().equals("initPresenter"));
                            if (isOk) XposedHelpers.callMethod(activity, "initPresenter");
                            XposedHelpers.callMethod(activity, method.MethodName);
                        } catch(Throwable err) {}
                    });
                });
            }
        });
    }
    
    private static void Hook_非凡文档() {
        find.key("getVip_expire_time").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getVip_expire_time")
        ).hook(VipTime);
        find.key("getAi_category_free_use_times").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getFree_talk_free_use_times")
        ).hook(Integer.MAX_VALUE);
        find.key("getFree_talk_free_use_times").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getFree_talk_free_use_times")
        ).hook(Integer.MAX_VALUE);
        find.key("getFree_use_times").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getFree_use_times")
        ).hook(Integer.MAX_VALUE);
        InjectActivity((activity, RootView, ActivityNane) -> {
            if (ActivityNane.endsWith("VipTestActivity") || ActivityNane.endsWith("VipTestActivity2") || ActivityNane.endsWith("VipActivity") || ActivityNane.endsWith("VipActivity2")) {
                activity.finish();
            }
        });
    }

    private static void Hook_非凡PPT() {
	    find.key("getVip_expire_time").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getVip_expire_time")
        ).hook(VipTime);
        find.key("getVip_state").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVip_state")
        ).hook(1);
        find.key("isLifetime_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isLifetime_vip")
        ).hook(true);
        InjectActivity((activity, RootView, ActivityNane) -> {
            if (ActivityNane.endsWith("VipTestActivity") || ActivityNane.endsWith("VipTestActivity2") || ActivityNane.endsWith("VipActivity") || ActivityNane.endsWith("VipActivity2")) {
                activity.finish();
            } else if (ActivityNane.endsWith("SplashActivity")) {
                find.key("Splash_ad").findClass(
                    ClassMatcher.create()
                        .className(ActivityNane)
                ).findMethod(
                    MethodMatcher.create()
                        .returnType(Types.VoidType)
                        .paramCount(0)
                ).fi(method -> {
                    method.getInvokes()
                    .name("startActivity")
                    .fi(child -> {
                        try {
                        	boolean isOk = Arrays.stream(method.getDeclaredMethods()).anyMatch(met-> met.getName().equals("initPresenter"));
                            if (isOk) XposedHelpers.callMethod(activity, "initPresenter");
                            XposedHelpers.callMethod(activity, method.MethodName);
                        } catch(Throwable err) {}
                    });
                });
            }
        });
    }

    private static void Hook_小熊录屏() {
        String resName = "durec_vip_level_desc";
        int resId = mContext.getResources().getIdentifier(resName, "string", packageName);
        if (resId > 0) {
            find.key(resName).findMethod(
                MethodMatcher.create()
                    .addUsingNumber(resId)
            ).fi(method -> {
                method.getInvokes()
                .returnType(Types.BooleanType)
                .paramTypes(Types.ContextClass)
                .hook(true);
            });
        }
    }

    private static void Hook_CAD看图王() {
        findAndHookMethod("com.stone.app.sharedpreferences.AppSharedPreferences", mClassLoader, "checkFunctionPointUseable", String.class, XC_MethodReplacement.returnConstant(true));
        findAndHookMethod("com.stone.app.ui.base.BaseActivity", mClassLoader, "checkFunctionPointShowFree", String.class, XC_MethodReplacement.returnConstant(true));
        findAndHookMethod("com.stone.app.ui.base.BaseActivity", mClassLoader, "checkUserVIP_CanReceive", XC_MethodReplacement.returnConstant(true));
    }
    
    private static void Hook_红果免费短剧() {
        Fields.create()
            .add("canWorn", true)
            .add("isAdFreeVip", true)
            .add("isStoryVip", true)
            .add("isPubVip", true)
            .add("adVipAvailable", true)
            .add("autoRenew", true)
            .add("continueMonth", true)
            .add("continueMonthBuy", true)
            .add("isUnionVip", true)
            .add("isAutoCharge", true)
            .add("isAdVip", true)
            .add("isVip", true)
            .add("isVip", "1")
            .add("expireTime", "218330035200")
            .Build();
    }
    
    private static void Hook_4399在线玩() {
        InjectActivity((activity, RootView, ActivityNane) -> {
            if (ActivityNane.startsWith("com.bytedance.sdk.openadsdk.stub.activity")) {
                AdActivity = activity;
            }
        });
        find.key("OnVideoAdListener").findClass(
            ClassMatcher.create()
                .addInterface("com.h4399.mads.listener.OnVideoAdListener")
        ).findMethod(
            MethodMatcher.create()
                .returnType(Types.VoidType)
                .paramCount(0)
                .name("onAdShow")
        ).hook(
            new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedHelpers.callMethod(param.thisObject, "onVideoCompleted");
                    if (AdActivity != null) AdActivity.finish();
                    main.postDelayed(() -> {
                        if (AdActivity != null) AdActivity.finish();
                    }, 500);
                }
            }
        );
    }
    
    private static void Hook_七猫免费小说() {
        findAndHookMethod("com.qimao.qmuser.model.entity.MineHeaderEntity", mClassLoader, "getAssets", XC_MethodReplacement.returnConstant(new ArrayList<>()));
        findAndHookMethod("com.qimao.qmuser.model.entity.mine_v2.VipInfo$VipOpenInfo", mClassLoader, "getText", XC_MethodReplacement.returnConstant("续费"));
        find.key("getYear_vip_show").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramCount(0)
                .name("getYear_vip_show")
        ).hook("1");
        find.key("getBanners_show_type").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramCount(0)
                .name("getBanners_show_type")
        ).hook("1");
        find.key("USER_IS_VIP").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramTypes(Types.ContextClass)
                .paramCount(1)
                .usingStrings("USER_IS_VIP", "0")
        ).hook("1");
        find.key("getIs_year_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("getIs_year_vip")
        ).hook(true);
        find.key("isShowYearVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isShowYearVip")
        ).hook(true);
        find.key("isVipState").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVipState")
        ).hook(true);
        find.key("isBookVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isBookVip")
        ).hook(true);
        find.key("isVipUser").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(1)
                .name("isVipUser")
        ).hook(true);
        find.key("isNewUser").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isNewUser")
        ).hook(true);
        find.key("getIs_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.StringClass)
                .paramCount(0)
                .name("getIs_vip")
        ).hook("1");
    }
    
    private static void Hook_可可影视() {
        //飞溅广告 AdPlaceInfo(id=
        findAndHookMethod("com.c.marketing.base.AdPlaceInfo", mClassLoader, "getItems", XC_MethodReplacement.returnConstant(new ArrayList<>()));
        findAndHookMethod("com.c.data.AppRecommend", mClassLoader, "getItems", XC_MethodReplacement.returnConstant(new ArrayList<>()));
        //主页推荐菜单的横幅广告 VodBannerLink(data=
        findAndHookMethod("com.salmon.film.video.data.VodBannerLink", mClassLoader, "getData", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    List list = new ArrayList<>();
                    try {
                        list = (List) param.getResult();
                        Gson gson = new Gson();
                        //逆向遍历，防止删除元素导致索引错位
                        for (int i = list.size() - 1; i >= 0; i--) {
                            try {
                                String s = gson.toJson(list.get(i));
                                JSONObject json = new JSONObject(s);
                                if (json.has("title")) {
                                    if (TextUtils.isEmpty(json.get("title").toString())) {
                                        list.remove(i);
                                    }
                                }
                            } catch (Throwable e) {}
                        }
                    } catch (Throwable e) {}
                    param.setResult(list);
                }
            });
        //主页推荐菜单的网格菜单广告 VodSection3(cols=
        findAndHookMethod("com.salmon.film.video.data.VodSection3", mClassLoader, "getData", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    List list = new ArrayList<>();
                    try {
                        list = (List) param.getResult();
                        List<String> AdList = new ArrayList<>();
                        AdList.add("爱优腾芒");
                        AdList.add("Netflix");
                        AdList.add("豆瓣250");
                        AdList.add("排行榜");
                        AdList.add("上映表");
                        Gson gson = new Gson();
                        //逆向遍历，防止删除元素导致索引错位
                        for (int i = list.size() - 1; i >= 0; i--) {
                            try {
                                String s = gson.toJson(list.get(i));
                                JSONObject json = new JSONObject(s);
                                if (json.has("text")) {
                                    if (!AdList.contains(json.get("text").toString())) {
                                        list.remove(i);
                                    }
                                }
                            } catch (Throwable e) {}
                        }
                    } catch (Throwable e) {}
                    param.setResult(list);
                }
            });
    }
    
    private static void Hook_番茄畅听() {
        find.key("isVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isVip")
        ).hook(true);
        find.key("getIsEnableSDK").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .usingStrings("SplashAdManagerImpl", "开屏 SDK 未启用", "开屏数据未加载好，无法展示广告")
        ).fi(method -> {
            method.getInvokes()
            .returnType(Types.BooleanType)
            .paramCount(0)
            .hook(false);
        });
    }
    
    private static void Hook_番茄免费小说() {
        Fields.create()
            .add("canWorn", true)
            .add("isAdFreeVip", true)
            .add("isStoryVip", true)
            .add("isPubVip", true)
            .add("adVipAvailable", true)
            .add("autoRenew", true)
            .add("continueMonth", true)
            .add("continueMonthBuy", true)
            .add("isUnionVip", true)
            .add("isAutoCharge", true)
            .add("isAdVip", true)
            .add("isVip", true)
            .add("isVip", "1")
            .add("expireTime", "218330035200")
            .Build();
        findAndHookMethod("com.dragon.read.component.NsAdDependImpl", mClassLoader, "readerIsAdFree", XC_MethodReplacement.returnConstant(true)); //[激励视频广告-反转] 命中实验，260484自动阅读不出激励广告入口
        findAndHookMethod("com.dragon.read.component.NsCommunityDependImpl", mClassLoader, "isHideFunctionInspireAd", XC_MethodReplacement.returnConstant(true)); //[激励视频广告-反转] 命中实验，260485不展示催更激励视频广告入口
        findAndHookMethod("com.dragon.read.component.biz.impl.NsVipImpl", mClassLoader, "isAnyVip", XC_MethodReplacement.returnConstant(true)); //com.dragon.read.component.biz.impl.mine.FanqieMineFragment
        findAndHookMethod("com.dragon.read.component.biz.impl.NsVipImpl", mClassLoader, "willShowLynxBanner", XC_MethodReplacement.returnConstant(false)); //注释：true显示网络加载会员横幅
        findAndHookMethod("com.dragon.read.component.biz.impl.NsVipImpl", mClassLoader, "canShowMulVip", XC_MethodReplacement.returnConstant(true)); //注释：true显示新会员横幅
        findAndHookMethod("com.dragon.read.component.biz.impl.NsVipImpl", mClassLoader, "isBuyPaidBook", String.class, XC_MethodReplacement.returnConstant(true)); //书籍已购买，可以直接下载
        findAndHookMethod("com.dragon.read.component.audio.biz.protocol.core.data.AudioPageBookInfo", mClassLoader, "isBookUnsignedAdFree", XC_MethodReplacement.returnConstant(true)); //unauthorized book
        //checkTtsPrivilege hasNewUserProtectPrivilege  或者 [激励视频广告-反转] checkTtsPrivilege命中实验，隐藏TTS听书激励入口
        find.key("hasTtsNewUserPrivilege").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("hasTtsNewUserPrivilege")
        ).hook(true);
        // 隐藏金币功能
        find.key("isPolarisEnable").findMethod(
            FindMethod.create().searchPackages("com.dragon.read").matcher(MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isPolarisEnable")
            )
        ).hook(false);
        //[激励视频广告-反转] 命中实验，屏蔽书架/收藏入口赚金币弹窗上的激励入口
        find.key("isHideInspireAd").findMethod(
            FindMethod.create().searchPackages("com.dragon.read").matcher(MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(1)
                .name("isHideInspireAd")
            )
        ).hook(true);
        find.key("ILiveFeedCard").findMethod(
            MethodMatcher.create()
                .returnType("com.dragon.read.plugin.common.api.live.feed.ILiveFeedCard")
                .usingStrings("热门主播", "热门直播", "热门直播间")
        ).hook(null);
        find.key("getIsEnableSDK").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .usingStrings("SplashAdManagerImpl", "开屏 SDK 未启用", "开屏数据未加载好，无法展示广告")
        ).fi(method -> {
            method.getInvokes()
            .returnType(Types.BooleanType)
            .paramCount(0)
            .hook(false);
        });
    }
    
    private static void Hook_我的听书() {
        find.key("isShowAds").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isShowAds")
                .usingStrings("is_show_ads")
        ).hook(false);
        find.key("is_skip_splash").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .usingStrings("is_skip_splash")
        ).hook(true);
        find.key("getOpenAdTime").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .name("getOpenAdTime")
                .usingStrings("open_ad_time")
        ).hook(System.currentTimeMillis() + 7 * 60000 * 60 * 24);
        find.key("init").findMethod(
            MethodMatcher.create()
                .returnType(Types.VoidType)
                .paramCount(3)
                .name("init")
                .usingStrings("请注意正在使用测试的id进行广告调用，请在发版时换成正式id")
        ).hook(null);
    }
    
    private static void Hook_得间免费小说() {
        //去除广告
        findAndHookMethod("com.zhangyue.iReader.module.proxy.AdProxy", mClassLoader, "getModuleId", XC_MethodReplacement.returnConstant(null));
        findAndHookMethod("com.zhangyue.iReader.module.proxy.AdProxy", mClassLoader, "isShowAd", Bundle.class, XC_MethodReplacement.returnConstant(false));
        findAndHookMethod("com.zhangyue.iReader.module.idriver.ad.AdUtil", mClassLoader, "isPreventAd", XC_MethodReplacement.returnConstant(true));
        findAndHookMethod("com.zhangyue.iReader.module.idriver.ad.AdUtil", mClassLoader, "needForbiddenAdInSevenDays", XC_MethodReplacement.returnConstant(true));
        findAndHookMethod("com.zhangyue.iReader.app.APP", mClassLoader, "loadAdStrategy", XC_MethodReplacement.returnConstant(null));
        findAndHookMethod("com.zhangyue.iReader.module.proxy.AdProxy", mClassLoader, "loadAdStrategy", String.class, String.class, XC_MethodReplacement.returnConstant(null));
        //隐藏金币入口
        findAndHookMethod("com.zhangyue.iReader.task.read.ReadTaskProgressManager", mClassLoader, "startReadTask", XC_MethodReplacement.returnConstant(null));
        long day31 = 2678580000l;
        long vip_time = System.currentTimeMillis() + day31;
        findAndHookMethod("com.zhangyue.iReader.DB.SPHelperTemp", mClassLoader, "getLong", String.class, long.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                String key = (String) param.args[0];
                if (!TextUtils.isEmpty(key)) {
                    if (key.equals("video_vip_time")) {
                        param.setResult(vip_time);
                    }
                }
            }
        });
        find.key("vip_time").findMethod(
            MethodMatcher.create()
                .returnType(Types.LongType)
                .paramCount(0)
                .usingEqStrings("video_vip_time")
        ).hook(vip_time);
        find.key("getIs_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramTypes(Types.BooleanType, Types.IntType)
                .usingStrings("request_reyun_inlaybooks_scene")
        ).hook(false);
    }
    
    private static void Hook_大海视频系列() {
        findAndHookMethod("android.content.ClipboardManager", mClassLoader, "setPrimaryClip", ClipData.class, XC_MethodReplacement.returnConstant(null));//剪切板
        find.key("getIs_vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getIs_vip")
        ).hook(1);
        find.key("getLogin_type").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getLogin_type")
        ).hook(1);
        find.key("getAd_source_id").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getAd_source_id")
        ).hook(0);
        find.key("barrage").findMethod(
            MethodMatcher.create()
                .returnType(Types.VoidType)
                .paramTypes(Types.IntType, Types.IntType)
                .usingStrings("collection", "vod_id", "start_time", "end_time")
        ).hook(null);
        InjectActivity((activity, RootView, ActivityNane) -> {
            onGlobalLayout(RootView, (mView, views) -> {
                views.forEach(v -> {
                    if (v instanceof TextView) {
                        TextView textView = (TextView) v;
                        String text = textView.getText().toString();
                        switch(text) {
                            case "官方客服":
                            case "分享可得终身免广告特权>":
                                ((View) textView.getParent().getParent()).setVisibility(8);
                                break;
                            case "催更新":
                            case "推广免广告":
                            case "我来说几句":
                            case "全部影评":
                                ((View) textView.getParent()).setVisibility(8);
                                break;
                            case "弹幕走一波...":
                            case "评论":
                                textView.setVisibility(8);
                                break;
                        }
                        String[] ids = {"iv_barrage_write_horizontal", "iv_barrage_horizontal", "iv_barrage"};
                        for (String s : ids) {
                            View root = mView.findViewById(mView.getResources().getIdentifier(s, "id", mContext.getPackageName()));
                            if (root != null) root.setVisibility(8);
                        }
                    }
                });
                return false;
            });
        });
    }
    
    private static void Hook_哔哩哔哩() {
        findAndHookMethod("tv.danmaku.bili.ui.splash.ad.model.Splash", mClassLoader, "isValid", XC_MethodReplacement.returnConstant(false));
        find.key("isEffectiveVip").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isEffectiveVip")
        ).hook(true);
        find.key("isSeniorUser").findMethod(
            MethodMatcher.create()
                .returnType(Types.BooleanType)
                .paramCount(0)
                .name("isSeniorUser")
        ).hook(true);
        find.key("getVipType").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .name("getVipType")
        ).hook(1);
    }

    private static void Hook_开发助手() {
        find.key("vip").findMethod(
            MethodMatcher.create()
                .returnType(Types.IntType)
                .paramCount(0)
                .usingNumbers(1000, 1991, 0, 1101)
                .usingEqStrings("a", "a")
        ).hook(0);
    }
}