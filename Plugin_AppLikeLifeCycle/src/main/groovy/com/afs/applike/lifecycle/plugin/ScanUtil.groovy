//package com.afs.applike.lifecycle.plugin
//
//import com.android.apksig.ApkSignerEngine
//import org.gradle.jvm.internal.JarFile
//
//class ScanUtil {
//
//    static final PROXY_CLASS_PREFIX = "Heima\$\$"
//    static final PROXY_CLASS_SUFFIX = "\$\$Proxy.class"
//    //注意class文件名中的包名是以“/”分隔开，而不是“.”分隔的，这个包名是我们通过APT生成的所有 IAppLike 代理类的包名
////    static final PROXY_CLASS_PACKAGE_NAME = "com/hm/iou/lifecycle/apt/proxy"
//    static final PROXY_CLASS_PACKAGE_NAME = "com/afs/applike/init"
//    //AppLifeCycleManager是应用生命周期框架初始化方法调用类
////    static final REGISTER_CLASS_FILE_NAME = "com/hm/lifecycle/api/AppLifeCycleManager.class"
//    static final REGISTER_CLASS_FILE_NAME = "com/afs/applike/lifecycle/AppLifeCycleManager.class"
//
//    //包含生命周期管理初始化类的文件，即包含 com.hm.lifecycle.api.AppLifeCycleManager 类的class文件或者jar文件
//    static File FILE_CONTAINS_INIT_CLASS
//
//    /**
//     * 判断该class是否是我们的目标类
//     *
//     * @param file
//     * @return
//     */
//    static boolean isTargetProxyClass(File file) {
//        if (file.name.endsWith(PROXY_CLASS_SUFFIX) && file.name.startsWith(PROXY_CLASS_PREFIX)) {
//            return true
//        }
//        return false
//    }
//
//    /**
//     * 扫描jar包里的所有class文件：
//     * 1.通过包名识别所有需要注入的类名
//     * 2.找到AppLifeCycleManager类所在的jar包，后面我们会在该jar包里进行代码注入
//     *
//     * @param jarFile
//     * @param destFile
//     * @return
//     */
//    static List<String> scanJar(File jarFile, File destFile) {
////        def file = new JarFile(jarFile)
//        Enumeration<ApkSignerEngine.OutputJarSignatureRequest.JarEntry> enumeration = jarFile.entries()
//        List<String> list = null
//        while (enumeration.hasMoreElements()) {
//            //遍历这个jar包里的所有class文件项
//            ApkSignerEngine.OutputJarSignatureRequest.JarEntry jarEntry = enumeration.nextElement()
//            //class文件的名称，这里是全路径类名，包名之间以"/"分隔
//            String entryName = jarEntry.getName()
//            if (entryName == REGISTER_CLASS_FILE_NAME) {
//                //标记这个jar包包含 AppLifeCycleManager.class
//                //扫描结束后，我们会生成注册代码到这个文件里
//                FILE_CONTAINS_INIT_CLASS = destFile
//            } else {
//                //通过包名来判断，严谨点还可以加上类名前缀、后缀判断
//                //通过APT生成的类，都有统一的前缀、后缀
//                if (entryName.startsWith(PROXY_CLASS_PACKAGE_NAME)) {
//                    if (list == null) {
//                        list = new ArrayList<>()
//                    }
//                    list.addAll(entryName.substring(entryName.lastIndexOf("/") + 1))
//                }
//            }
//        }
//        return list
//    }
//
//    static boolean shouldProcessPreDexJar(String path) {
//        return !path.contains("com.android.support") && !path.contains("/android/m2repository")
//    }
//
//}