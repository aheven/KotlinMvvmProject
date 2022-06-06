本项目旨在收藏一些实用的 Kotlin 项目，充分结合 Koltin 语法实现 Android 完整项目。功能如下：

1. Gradle 使用 Kotlin DSL 语法构建 Android 项目，详情请参见[Gradle 官方文档](https://docs.gradle.org/current/userguide/userguide.html)。
2. [Gradle插件检测](https://github.com/ben-manes/gradle-versions-plugin)，提供了一个任务 `dependencyUpdate` 来检测依赖项的更新。使用命令 `.\gradlew dependencyUpdate`。
3. [DylanCaiCoding/LoadingStateView (github.com)](https://github.com/DylanCaiCoding/LoadingStateView/blob/master/README_ZH.md) 是一个深度解耦缺省页和标题栏的工具，核心功能的实现代码只有一个 200 行左右（不算注释）的 Kotlin 文件。不仅能在请求网络数据时显示加载中、加载成功、加载失败、无数据的视图或自定义视图，还可以对标题栏进行解耦。