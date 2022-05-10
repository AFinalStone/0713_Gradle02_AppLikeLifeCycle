package com.afs.basebiz;

import android.content.Context;

public interface IAppLike {
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
