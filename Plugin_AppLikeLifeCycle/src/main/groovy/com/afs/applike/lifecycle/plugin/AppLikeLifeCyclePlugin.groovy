package com.afs.applike.lifecycle.plugin


import org.gradle.api.Plugin
import org.gradle.api.Project

class AppLikeLifeCyclePlugin implements Plugin<Project> {
    void apply(Project project) {
        println "------AppLikeLifeCyclePlugin plugin entrance-------"
    }
}