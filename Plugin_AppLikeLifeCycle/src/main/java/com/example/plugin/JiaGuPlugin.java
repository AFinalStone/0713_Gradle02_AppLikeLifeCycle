package com.example.plugin;


import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class JiaGuPlugin implements Plugin<Project> {

    public void apply(Project project) {
        JiaGuExt jiaGuExt = project.getExtensions().create("jiagu", JiaGuExt.class);
        project.afterEvaluate(new Action<Project>() {
            public void execute(Project project) {
            }
        });
    }
}