## 前言

前一篇创建了一个最基本的gradle插件，本期我们来创建一个管理组件化模块类AppLike生命周期管理的插件

## 一、构建自定义脚本插件框架

### 1.1 构建插件

按照之前的方案，新建模块Plugin_AppLikeLifeCycle并创建com/afs/applike/lifecycle/plugin/AppLikeLifeCyclePlugin.groovy文件
和com.afs.applike.lefcycle.properties文件

```groovy
package com.afs.applike.lifecycle.plugin


import org.gradle.api.Plugin
import org.gradle.api.Project

class AppLikeLifeCyclePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        println "------AppLikeLifeCyclePlugin plugin entrance-------"
    }
}
```

```properties
implementation-class=com.afs.applike.lifecycle.plugin.AppLikeLifeCyclePlugin
```

### 1.2 上传插件代码到本地仓库

```groovy
apply plugin: 'java-library'
apply plugin: 'groovy'
apply plugin: 'maven'

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation gradleApi()
    implementation localGroovy()
    implementation 'com.android.tools.build:transform-api:1.5.0'
    implementation 'com.android.tools.build:gradle:3.5.3'
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

//通过maven将插件发布到本地的脚本配置 ， 根据自己的要求来修改
uploadArchives {
    repositories.mavenDeployer {
        // 配置 pom 信息
        pom.version = '1.0.0'
        pom.artifactId = 'applikelifecyclepluginlocal'
        pom.groupId = 'com.afs.stone'
        // 配置仓库地址
        repository(url: uri('../repo'))
    }
}
```

### 1.3 在项目根目录的build.gradle中添加插件依赖

```groovy
buildscript {
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.3'
        //引入本地仓库中的插件依赖
        classpath 'com.example.plugin:exapmplepluginlocal:1.0.0'
        //组件化模块生命周期管理插件依赖
        classpath 'com.afs.stone:applikelifecyclepluginlocal:1.0.0'
    }
}
```

### 1.4 在app模块中使用我们的自定义插件

```groovy
apply plugin: 'com.afs.applike.lefcycle'
```

运行app模块的gradle，成功看到日志信息如下：

```cmd
> Configure project :app
------组件化AppLike生命周期管理插件被执行-------
```
