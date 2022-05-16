package com.afs.applike.lifecycle;

import android.content.Context;

import com.afs.applike.annotation.AppLikeLifeCycleConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class AppLikeLifeCycleManager {

    private static List<IAppLike> APP_LIKE_LIST = new ArrayList<>();

    /**
     * 注册IAppLike类
     */
    public static void registerAppLike(IAppLike appLike) {
        APP_LIKE_LIST.add(appLike);
    }

    /**
     * 初始化，需要在Application.onCreate()里调用
     *
     * @param context
     */

    public static void init(Context context) {
        scanClassFile(context);
        Collections.sort(APP_LIKE_LIST, new AppLikeComparator());
        for (IAppLike appLike : APP_LIKE_LIST) {
            appLike.onCreate(context);
        }
    }

    public static void terminate() {
        for (IAppLike appLike : APP_LIKE_LIST) {
            appLike.onDestroy();
        }
    }

    private static void scanClassFile(Context context) {
        try {
            //扫描到所有的目标类
            Set<String> set = ClassUtils.getFileNameByPackageName(context, AppLikeLifeCycleConfig.PROXY_CLASS_PACKAGE_NAME);
            if (set != null) {
                for (String className : set) {
                    try {
                        //通过反射来加载实例化所有的代理类
                        Object obj = Class.forName(className).newInstance();
                        if (obj instanceof IAppLike) {
                            APP_LIKE_LIST.add((IAppLike) obj);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 优先级比较器，优先级大的排在前面
     */
    static class AppLikeComparator implements Comparator<IAppLike> {

        @Override
        public int compare(IAppLike o1, IAppLike o2) {
            int p1 = o1.getPriority();
            int p2 = o2.getPriority();
            return p2 - p1;
        }
    }

}