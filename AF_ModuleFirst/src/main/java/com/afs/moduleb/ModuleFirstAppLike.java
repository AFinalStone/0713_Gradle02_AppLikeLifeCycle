package com.afs.moduleb;

import android.content.Context;

import com.afs.applike.annotation.AppLikeLifeCycle;
import com.afs.applike.lifecycle.IAppLike;

@AppLikeLifeCycle
public class ModuleFirstAppLike implements IAppLike {
    @Override
    public int getPriority() {
        return MAX_PRIORITY;
    }

    @Override
    public void onCreate(Context context) {
        System.out.println("ModuleFirstAppLike的onCreate方法被执行");
    }

    @Override
    public void onDestroy() {
        System.out.println("ModuleFirstAppLike的onDestroy方法被执行");
    }
}
