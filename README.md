本项目旨在收藏一些实用的 Kotlin 项目，充分结合 Koltin 语法实现 Android 完整项目。功能如下：

1. Gradle 使用 Kotlin DSL 语法构建 Android 项目，详情请参见[Gradle 官方文档](https://docs.gradle.org/current/userguide/userguide.html)。
2. [Gradle插件检测](https://github.com/ben-manes/gradle-versions-plugin)，提供了一个任务 `dependencyUpdate` 来检测依赖项的更新。使用命令 `.\gradlew dependencyUpdate`。
3. [ViewBindingKTX](https://github.com/DylanCaiCoding/ViewBindingKTX)，相对于 Kotlin synthetics、ButterKnife、findViewById，能减少 id 写错或类型写错导致的异常，官方和 JakeWharton 都推荐使用。但是 ViewBinding 直接使用会有点繁琐，所以本库能**帮助你在各种使用场景用尽可能少的代码来使用 ViewBinding**。
4. [idisfkj/android-startup](https://github.com/idisfkj/android-startup)，提供一种在应用启动时能够更加简单、高效的方式来初始化组件。开发人员可以使用`android-startup`来简化启动序列，并显式地设置初始化顺序与组件之间的依赖关系。 与此同时`android-startup`支持**同步与异步等待**，并通过有向无环图[拓扑排序](https://github.com/idisfkj/android-startup/blob/master/android-startup/src/main/java/com/rousetime/android_startup/sort/TopologySort.kt)的方式来保证内部依赖组件的初始化顺序。
5. [DylanCaiCoding/Longan](https://github.com/DylanCaiCoding/Longan)，Longan 是一个简化 Android 开发的 Kotlin 工具类集合，可以使代码更加简洁易读。**目前有超过 500 个常用方法或属性，能有效提高开发效率**。每个用法都会思考很多，并且参考官方 KTX 库的命名规则和用法，用起来会更加的舒服。
6. [DylanCaiCoding/LoadingStateView](https://github.com/DylanCaiCoding/LoadingStateView/blob/master/README_ZH.md) 是一个深度解耦缺省页和标题栏的工具，核心功能的实现代码只有一个 200 行左右（不算注释）的 Kotlin 文件。不仅能在请求网络数据时显示加载中、加载成功、加载失败、无数据的视图或自定义视图，还可以对标题栏进行解耦。