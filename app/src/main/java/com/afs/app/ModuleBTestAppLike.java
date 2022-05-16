package com.afs.app;

import android.content.Context;

import com.afs.applike.annotation.AppLikeLifeCycle;
import com.afs.applike.lifecycle.IAppLike;

@AppLikeLifeCycle
public class ModuleBTestAppLike implements IAppLike {
    @Override
    public int getPriority() {
        return NORM_PRIORITY;
    }

    @Override
    public void onCreate(Context context) {
        System.out.println("ModuleBTestAppLike的onCreate方法被执行");
    }

    @Override
    public void onDestroy() {
        System.out.println("ModuleBTestAppLike的onDestroy方法被执行");
    }
}
