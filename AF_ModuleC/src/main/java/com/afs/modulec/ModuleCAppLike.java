package com.afs.modulec;

import android.content.Context;

import com.afs.applike.annotation.AppLikeLifeCycle;
import com.afs.applike.lifecycle.IAppLike;

@AppLikeLifeCycle
public class ModuleCAppLike implements IAppLike {
    @Override
    public int getPriority() {
        return NORM_PRIORITY;
    }

    @Override
    public void onCreate(Context context) {
        System.out.println("ModuleCAppLike的onCreate方法被执行");
    }

    @Override
    public void onDestroy() {
        System.out.println("ModuleCAppLike的onDestroy方法被执行");
    }
}
