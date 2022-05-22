package com.afs.applike.lifecycle.plugin


import org.gradle.api.Plugin
import org.gradle.api.Project

class AppLikeLifeCyclePlugin implements Plugin<Project> {
    void apply(Project project) {
        println "------AppLikeLifeCyclePlugin plugin entrance-------"
        def android = project.extensions.getByType(AppExtension)
        android.registerTransform(new LifeCycleTransform(project))
    }

}