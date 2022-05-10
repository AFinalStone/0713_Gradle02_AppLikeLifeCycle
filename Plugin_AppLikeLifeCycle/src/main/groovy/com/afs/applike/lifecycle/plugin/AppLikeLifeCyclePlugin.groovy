package com.afs.applike.lifecycle.plugin


import org.gradle.api.Plugin
import org.gradle.api.Project

class AppLikeLifeCyclePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        println "------组件化AppLike生命周期管理插件被执行-------"
    }
}