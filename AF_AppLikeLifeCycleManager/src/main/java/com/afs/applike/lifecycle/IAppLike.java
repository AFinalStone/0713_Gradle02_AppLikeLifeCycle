package com.afs.applike.lifecycle;

import android.content.Context;

public interface IAppLike {
    int MAX_PRIORITY = 10;
    int MIN_PRIORITY = 1;
    int NORM_PRIORITY = 5;

    int getPriority();

    /**
     * 模块初始化
     *
     * @param context
     */
    void onCreate(Context context);

    /**
     * 模块生命周期完结
     */
    void onDestroy();
}
