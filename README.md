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

## 二、Gradle Transform

上面我们只是通过插件打印了一行日志，在实际开发中并没有什么意义，那么怎么通过插件在打包前去扫描所有的class文件呢，幸运的是官方给我们提供了 Gradle
Transform技术，简单来说就是能够让开发者在项目构建阶段即由class到dex转换期间修改class文件，Transform阶段会扫描所有的class文件和资源文件，具体技术我这里不详细展开，下面通过伪代码部分说下我的思路。
