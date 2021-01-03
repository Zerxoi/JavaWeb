# tomcat

参考:[Apache Tomcat 9 Introduction](http://tomcat.apache.org/tomcat-9.0-doc/introduction.html)

## 环境变量

- **CATALINA_HOME**: Represents the root of your Tomcat installation, for example `/home/tomcat/apache-tomcat-9.0.10` or `C:\Program Files\apache-tomcat-9.0.10`.
- **CATALINA_BASE**: Represents the root of a runtime configuration of a specific Tomcat instance. If you want to have multiple Tomcat instances on one machine, use the `CATALINA_BASE` property.


## 目录结构

- **/bin** - Startup, shutdown, and other scripts. The `*.sh` files (for Unix systems) are functional duplicates of the `*.bat` files (for Windows systems). Since the Win32 command-line lacks certain functionality, there are some additional files in here.
- **/conf** - Configuration files and related DTDs. The most important file in here is server.xml. It is the main configuration file for the container.
- **/logs** - Log files are here by default.
- **/webapps** - This is where your webapps go.

## 目录映射

[Tomcat Web Application Deployment](https://tomcat.apache.org/tomcat-9.0-doc/deployer-howto.html#A_word_on_Contexts)

[Manager App How-To](https://tomcat.apache.org/tomcat-9.0-doc/manager-howto.html#Deploy_using_a_Context_configuration_%22.xml%22_file)

```xml
<Context path="/foobar" docBase="/path/to/application/foobar" reloadable="true"></Context>
```

[Tomcat 浏览器打不开](https://segmentfault.com/q/1010000016098865)