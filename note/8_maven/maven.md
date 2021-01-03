# Maven

[Maven 安装](https://maven.apache.org/install.html)

[Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

**注**：Windows 中执行如下命令可能存在问题

```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

将带有 `.` 的命令参数两侧加上引号 `"` 即可正常运行。

## Maven 换源

[Using Mirrors for Repositories](https://maven.apache.org/guides/mini/guide-mirror-settings.html)

[Maven 镜像](https://maven.aliyun.com/mvn/guide)

[VS Code Maven 换源设置](https://www.dhao.xyz/2020/06/29/VSCode-Java-Environment-Setup/)

```json
{
  "java.configuration.maven.globalSettings": "C:/Program Files/apache-maven-3.6.3/conf/settings.xml"
}
```

## VS Code 配置

[Troubleshooting](https://github.com/microsoft/vscode-maven/blob/master/Troubleshooting.md)

```json
{
    "maven.executable.path": "C:/Program Files/apache-maven-3.6.3/bin/mvn.cmd",
    "java.configuration.maven.globalSettings": "C:/Program Files/apache-maven-3.6.3/conf/settings.xml"
}
```

注: Maven创建的项目中存在警告，原因是Maven配置中的默认JDK版本是JDK1.7，要手动`pom.xml`为对应JDK版本即可。

```xml
<properties>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <maven.compiler.source>11</maven.compiler.source>
  <maven.compiler.target>11</maven.compiler.target>
</properties>
```

[Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/index.html)

## VS Code Maven 添加本地 Jar 包及其源码包

参考：[VSCode Java手动导入jar和源码包](https://blog.ixk.me/vscode-java-manually-imports-jar-and-source-package.html)

在项目的 `.classpath` 文件中添加如下内容

```xml
<classpathentry kind="lib" path="src/lib/spring-context-5.3.2.jar" sourcepath="src/lib/spring-context-5.3.2-sources.jar" />
```

## 打包不带 `.properties` 文件

参考：[Maven 打包时丢失properties文件](https://blog.csdn.net/blue_rem/article/details/40396483)

其他知识：[Two asterisks in file path](https://stackoverflow.com/questions/8532929/two-asterisks-in-file-path)

`**/` 表示目录下的递归子目录

注：

修改 `<resources>` 元素会覆盖默认的设置。

例如，在使用 `jar` 打包，默认会包含 `src/main/resources` 路径下的内容，但是如果修改后就将默认配置给覆盖了。

## Maven 原型

[Maven 原型描述及其生成目录结构](https://maven.apache.org/archetypes/index.html)

[Maven 插件管理](https://maven.apache.org/archetype/plugin-management.html)

[Maven 生命周期](https://maven.apache.org/ref/3.6.3/maven-core/lifecycles.html)

[各种打包生命周期的插件绑定](https://maven.apache.org/ref/3.6.3/maven-core/default-bindings.html)

[可用插件](https://maven.apache.org/plugins/index.html)

### `jar` 插件打包目录结构

[jar 插件的使用](https://maven.apache.org/plugins/maven-jar-plugin/usage.html)

`jar`项目目录结构

```
jarproject
└───src
    ├───main
    │   ├───java            源码目录
    │   │   └───xyz
    │   │       └───zerxoi
    │   │               App.java
    │   │
    │   └───resources       资源目录
    │       └───images
    │               jandan.jpg
    │
    └───test                测试目录
        └───java
            └───xyz
                └───zerxoi
                        AppTest.java
```

默认的打包方式就是`jar`

如果在 `web.xml` 中未配置 `<packaging>` 元素或者已经添加 `<packaging>jar</packaging>` 元素的话，可以直接调用 `mvn package` 对项目进行打包。

也可以直接使用 `mvn compile jar:jar` 对项目进行打包。

打包后的`jar`文件如下。

```
jarproject-1.0-SNAPSHOT.jar
├───images
│       jandan.jpg
│
├───META-INF
│   │   MANIFEST.MF
│   │
│   └───maven
│       └───xyz.zerxoi
│           └───test
│                   pom.properties
│                   pom.xml
│
└───xyz
    └───zerxoi
            App.class
```

### `war` 插件打包目录结构

[war 插件的使用](https://maven.apache.org/plugins/maven-war-plugin/usage.html)

`war` 项目目录结构

```
warproject
└───src
    ├───main
    │   ├───java            源码目录
    │   │   └───xyz
    │   │       └───zerxoi
    │   │               App.java
    │   │
    │   ├───resources       资源目录
    │   │   └───images
    │   │           jandan.jpg
    │   │
    │   └───webapp          Web目录
    │       │   index.jsp
    │       │
    │       ├───jsp
    │       │       login.jsp
    │       │
    │       └───WEB-INF
    │               web.xml
    │
    └───test
        └───java
            └───xyz
                └───zerxoi
                        AppTest.java
```

可以通过在`web.xml` 中添加 `<packaging>war</packaging>` 元素并调用 `mvn package` 对项目进行打包或者直接调用 `mvn compile war:war` 来指定使用`war`的打包方式。

打包后的`war`文件目录结构如下：

```
warproject-1.0-SNAPSHOT.jar
│   index.jsp
│
├───jsp
│       login.jsp
│
├───META-INF
│   │   MANIFEST.MF
│   │
│   └───maven
│       └───xyz.zerxoi
│           └───test
│                   pom.properties
│                   pom.xml
│
└───WEB-INF
    │   web.xml
    │
    └───classes
        ├───images
        │       jandan.jpg
        │
        └───xyz
            └───zerxoi
                    App.class
```

## Maven 建立多个项目

[How do I build more than one project at once?](https://maven.apache.org/guides/getting-started/index.html#how-do-i-build-more-than-one-project-at-once)