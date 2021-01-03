# JSP(Java Server Pages)

## 意义

JSP 的主要作用是替代 Servlet 程序回传 html 页面的数据。因为 Servlet 程序回传 html 页面数据是一件非常繁琐的事情，其开发成本和维护成本都很高。

## 本质

JSP文件的本质是一个Servlet程序（继承`HttpServlet`）。第一次访问JSP页面的时候，Tomcat服务器会把jsp页面翻译成为一个 `.java` 源文件并编译为 `.class` 字节码，存放在`CATALINA_BASE/work/Catalina/localhost`中对应的目录下。

参见：[tomcat的work目录](https://blog.csdn.net/wugouzi/article/details/12713559)

## 指令（directive）

参考：

1. [The Java EE 5 Tutorial](https://docs.oracle.com/javaee/5/tutorial/doc/javaeetutorial5.pdf)
2. [JSP系列二：JSP指令元素：page指令，include指令，taglib指令](https://blog.csdn.net/qfs_v/article/details/2799118)

标准语法
```jsp
<%@ drective attribute="value" %>
```

XML语法
```xml
<jsp:directive.directiveType attribute1="value1" attribute2="value2" ... />
```

### page 指令

page指令用于设置JSP页面的属性，这些属性将用于和JSP容器通信，控制所生成的servlet结构。

- `language`：指定JSP页面中使用的脚本语言。默认值为`java` | `<%@ page language="scripting-language" %>`
- `import`：如果`language`属性是`java`，则使用`import`属性导入类或包 | `<%@ page import="fully-qualified-classname, packagename.*" %>`
- `contentType`：定义HTTP响应的MIME（多用途Internet邮件扩展）类型。默认值为 `text/html; charset=ISO-8859-1` | `<%@ page contentType="text/html; charset=UTF-8" %>`
- `pageEncoding`：设置JSP页面的编码 | `<%@ page pageEncoding="UTF-8" %>`
- `buffer`：`buffer`属性设置缓冲区大小（以千字节为单位）以处理JSP页面响应生成的输出。缓冲区的默认大小为 `8kb`。 | `<%@ page buffer="none|xxxkb" %>`
- `autoFlush`：控制当缓冲区满了后，是自动清空输出缓冲区(默认为 `true`)，还是在缓冲区溢出后抛出异常(`false`) | `<%@ page autoFlush="ture|false" %>`
- `errorPage`：指定在发生异常时Web容器应将控制权转发到错误页面；文件路径以`/`开始表示web工程的根目录，不以`/`开始表示相对文件所在目录 | `<%@ page errorPage="file-name" %>`
- `isErrorPage`：表示当前页是否可以作为其他jsp页面的错误页面| `<%@ page isErrorPage="ture|false" %>`
    - 错误页面应该放在WEB-INF目录下面，只让服务器访问，也不会生成转发的调用，客户端只能看到最初的请求页面URL,看不到错误页面的URL。
    - 如果为整个web应用程序指定错误页面，或为应用中不同类型的错误指定错误处理页面，使用`web.xml`中的`error-page`元素。
    - 如果一个页面通过该属性定义了专有的错误页面，那么在 `web.xml` 文件中定义的任何错误页面不会被使用。
    - 错误页面中存在`exception`异常对象实例来处理异常。
- `session`：控制页面是否参与会话 | `<%@ page session="ture|false" %>`
- `extends`：指定JSP页面生成的`Servlet`的超类。（避免使用）

### include 指令（静态包含）

include指令用于包含任何资源的内容，它可以是JSP文件，HTML文件或文本文件。 include指令在页面转换时会包含所包含资源的原始内容（jsp页面仅被转换一次，因此最好包含静态资源）。

标准语法
```jsp
<%@ include file="relative url" %>
```

XML语法
```xml
<jsp:directive.include file="relative url" />
```

### 例子

```jsp
<%-- index.jsp --%>
<html>
<body>
    <%!
        private String name = "zerxoi";
    %>
    <%@ include file="header.html" %>
    <h2>index.jsp name: <%= name %></h2>
    <%@ include file="footer.jsp" %>
</body>
</html>
```

```jsp
<!-- header.html -->
<html>
<body>
    <header>Header</header>
</body>
</html>
```

```jsp
<%-- footer.jsp --%>
<html>
<body>
    <%
        String footer = "I'm a footer";
    %>
    <footer><%= footer  %></footer>
</body>
</html>
```

输出结果如下：

```html
<html>
<body>
    <header>Header</header>
    <h2>index.jsp name: zerxoi</h2>
    <footer>I'm a footer</footer>
</body>
</html>
```

转换为Servlet的文件内容为

```java
public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {
    // ...
    out.write("<html>\n");
    out.write("<body>\n");
    out.write("<html>\r\n");
    out.write("<body>\r\n");
    out.write("    <header>Header</header>\r\n");
    out.write("</body>\r\n");
    out.write("</html>");
    out.write("    <h2>Hello World!</h2>\n");
    out.write("<html>\r\n");
    out.write("<body>\r\n");
    out.write("    <footer>");
    out.print( "Footer"  );
    out.write("</footer>\r\n");
    out.write("</body>\r\n");
    out.write("</html>");
    out.write("</body>\r\n");
    out.write("</html>");
    // ...
}
```

但是如果 footer.jsp 改为

```jsp
<%-- footer.jsp --%>
<html>
<body>
    <%!
        private String name = "kaguya";
    %>
    <footer>footer name:<%= name  %></footer>
</body>
</html>
```

出现以下错误会产生重复字段`Duplicate field`错误，说明 Servlet中有两个 name 字段，侧面说明 include 指令实际上就是将包含文件的内容放到标签所在的位置，等到内容包含完了之后，对整个文档进行翻译得到最终的结果。

### taglib 指令

声明用户使用自定义的标签，将标签库描述符文件导入到jsp页面。

标准语法
```jsp
<%@ taglib prefix="tt" [tagdir=/WEB-INF/tags/dir | uri=URI] %>
```

XML语法（`xmlns`属性）
```xml
xmlns:prefix="tag library URL"
<!-- e.g. -->
xmlns:c="http://java.sun.com/jsp/jstl/core"
xmlns:u="urn:jsptld:/WEB-INF/tlds/my.tld"
xmlns:u="urn:jsptagdir:/WEB-INF/tags/mytaglibs/"
```

## JSP声明标签（Declarations）

语法：
```jsp
<%! scripting-language-declaration %>
```

如果脚本语言是Java编程语言，则JSP声明中的变量和方法将成为JSP页面Servlet类中的声明。可以定制初始化过程，以允许JSP页面读取持久性配置数据，初始化资源以及执行任何其他一次性活动。

```jsp
<%-- <%@ page import="java.util.Map, java.util.HashMap" %> --%>

<%!
    private Integer id = 41917049;
    private String name = "zerxoi";
    private static Map<String, String> map;
%>
<%!
    static {
        map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
    }
%>
<%!
    public int add(int a, int b) {
        return a + b;
    }
%>
<%!
    public static class A {
        private String name = "A";
    }
%>
```

上述JSP文件内容会被转化为以下内容

```java
public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {
    private Integer id = 41917049;
    private String name = "zerxoi";
    private static Map<String, String> map;


    static {
        map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
    }


    public int add(int a, int b) {
        return a + b;
    }


    public static class A {
        private String name = "A";
    }
    //...
}
```

JSP声明标签会被翻译到Servlet类中，所以可以在声明标签中定义**方法**和**属性**。

## JSP表达式标签（Expressions）

JSP表达式用于将脚本语言表达式的值（转换为字符串）插入返回给客户端的数据流中。 当脚本语言为Java编程语言时，表达式将转换为语句，该语句将表达式的值转换为String对象，并将其插入隐式`out`对象。

语法：
```jsp
<%= scripting-language-expression %>
```

```jsp
    <p>My ID is <%= id %> and my name is <%= name %></p>
    <p>Map: <%= map %></p>
    <p>username from query: <%= request.getParameter("username") %> </p>
```

上述JSP文件中的内容会被转化为`_jspService`方法中的以下内容。因为在表达式脚本的语句在`_jspService`之中，所以该方法中的参数也可以被使用。

```java
public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {
    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.jsp.JspWriter out = null;
    out = pageContext.getOut();
    // ...
      out.write("    <p>My ID is ");
      out.print( id );
      out.write(" and my name is ");
      out.print( name );
      out.write("</p>\n");
      out.write("    <p>Map: ");
      out.print( map );
      out.write("</p>\n");
      out.write("    <p>username from query: ");
      out.print( request.getParameter("username") );
      out.write(" </p>\n");
    // ...
}
```

当访问 `http://localhost:8080/jsp/?username=admin` 链接时，获得以下内容。

```txt
My ID is 41917049 and my name is zerxoi

Map: {key1=value1, key2=value2, key3=value3}

username from query: admin
```

所以说，**JSP表达式元素**实际上就是将元素内容放入到`_jspService`的`out.print`方法中

## JSP脚本标签（Scriptlets）

JSP脚本集用于包含对页面中使用的脚本语言有效的任何代码片段。将脚本语言设置为Java时，脚本小程序将转换为Java编程语言语句片段，并将其插入JSP页面Servlet的服务方法中。 在JSP页面中的任何位置都可以访问在scriptlet中创建的编程语言变量。

```jsp
<%
    scripting-language-statements
%>
```

```jsp
    <table border="1">
    <%
        int i = 9;
        while (i > 0) {
    %>
        <tr>
            <td>第<%= i %>层</td>
        </tr>
    <%
            i--;
        }
    %>
    </table>
```

转化后的代码如下

```java
public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {
    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.jsp.JspWriter out = null;
    out = pageContext.getOut();
    // ...
    out.write("    <table border=\"1\">\n");
    out.write("    ");

    int i = 9;
    while (i > 0) {

    out.write("\n");
    out.write("        <tr>\n");
    out.write("            <td>第");
    out.print( i );
    out.write("层</td>\n");
    out.write("        </tr>\n");
    out.write("    ");

        i--;
    }
    
    out.write("\n");
    out.write("    </table>\n");
    // ...
}
```

和JSP表达式标签一样，JSP脚本的内容也是在`_jspService`方法中的，因此可以在JSP表达式标签使用该方法的参数（如`request`,`response`,`out`等）。下面的代码和上面的有相同的效果。

JSP脚本标签的内容会被翻译到`_jspService`中，显然我们可以在`_jspService`方法中定义局部变量或者调用其他方法，但是不能在其中再定义其他的方法。

```jsp
    <table border="1">
    <%
        int i = 9;
        while (i > 0) {
            out.print("<tr><td>第" + i + "层</td></tr>");
            i--;
        }
    %>
    </table>
```

表达式标签元素`<%= expression %>` 可以使用脚本标签元素 `<% out.print(expression) %>` 替代。

## 注释

```jsp
<!-- <h1>Hello World</h1> -->
<%
    // 单行注释
    /*
        多行注释
    */
%>
<%-- <% 12 / 0; %> --%>
```

注释转化为Servlet的结果如下

```java
out.write("<!-- <h1>Hello World</h1> -->\n");
// 单行注释
/*
    多行注释
*/
```

- HTML注释会被打印输出到响应流中
- Java注释会直接保存在Servlet中
- JSP注释会直接过滤掉

## JSP内置对象

```java
  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    java.lang.Throwable exception = org.apache.jasper.runtime.JspRuntimeLibrary.getThrowable(request);
    if (exception != null) {
      response.setStatus(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    // ...
}
```

JSP转化为Servlet的Java的代码中存在着9个内置的对象。

1. `request`：请求对象
2. `response`：响应对象
3. `pageContext`：JSP页面的上下文对象
4. `session`：会话对象；在`page`指令中`session`属性默认为`true`，设置为`false`则没有该对象。
5. `application`：ServletContext对象
6. `config`：ServletConfig对象
7. `out`：JSP响应输出流
8. `page`：指向当前JSP的对象（`this`）,即Servlet对象
9. `exception`：异常对象；在`page`指令中`isErrorPage`属性默认为`false`，将该属性设置为`true`即可获得该对象。

### 4个域对象

1. `PageContext | pageContext`：当前JSP页面范围内有效
2. `HttpServletRequest | request`：一次请求内有效
3. `HttpSession | session`：一个会话范围内有效（打开浏览器访问服务器，知道关闭浏览器）
4. `ServletContext | application`：整个Web工程范围内都有效

#### 有效域测试 1

如果访问该JSP页面
```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <body>
        <%
            pageContext.setAttribute("key", "pageContext");
            request.setAttribute("key", "request");
            session.setAttribute("key", "session");
            application.setAttribute("key", "application");
        %>

        pageContext.getAttribute("key"): <%= pageContext.getAttribute("key") %> <br/>
        request.getAttribute("key"): <%= request.getAttribute("key") %> <br/>
        session.getAttribute("key"): <%= session.getAttribute("key") %> <br/>
        application.getAttribute("key"): <%= application.getAttribute("key") %>
    </body>
</html>
```

四个域对象的`getAttribute`全部为`null`

#### 有效域测试 2

定义 `scope1.jsp` 如下：

```jsp
<%-- scope1.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <body>
        <%
            pageContext.setAttribute("key", "pageContext");
            request.setAttribute("key", "request");
            session.setAttribute("key", "session");
            application.setAttribute("key", "application");
        %>

        pageContext.getAttribute("key"): <%= pageContext.getAttribute("key") %> <br/>
        request.getAttribute("key"): <%= request.getAttribute("key") %> <br/>
        session.getAttribute("key"): <%= session.getAttribute("key") %> <br/>
        application.getAttribute("key"): <%= application.getAttribute("key") %>

        <%
            request.getRequestDispatcher("/scope2.jsp").forward(request, response);
        %>
    </body>
</html>
```

定义 `scope2.jsp` 如下：
```jsp
<%-- scope1.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <body>
        pageContext.getAttribute("key"): <%= pageContext.getAttribute("key") %> <br/>
        request.getAttribute("key"): <%= request.getAttribute("key") %> <br/>
        session.getAttribute("key"): <%= session.getAttribute("key") %> <br/>
        application.getAttribute("key"): <%= application.getAttribute("key") %>
    </body>
</html>
```

1. 如果访问 `scope1.jsp` ，`scope1.jsp` 会将请求调度到 `scope2.jsp`， 因为不是一个 JSP页面，所以只有 `pageContext.getAttribute("key"): null`
2. 如果继续访问 `scope2.jsp`的话，因为之前没有设置请求属性，所以`request.getAttribute("key"): null`
3. 如果关闭浏览器，接着访问 `scope2.jsp`，因为会话重新建立后并未在会话中创建相应属性，所以`session.getAttribute("key"): null`
4. 如果重启Web工程再访问 `scope2.jsp`，因为Web工程重新启动后并未在工程上下文中创建相应属性，所以 `pageContext.getAttribute("key"): null`

### 优先级

4个域对象的使用优先级是按其有效范围排序的，即 `pageContext`优先级 <  `request`优先级 < `session`优先级 < `pageContext`优先级

## out 与 response.getWriter()

JSP中的`out`是一个`javax.servlet.jsp.JspWriter`接口，其实现类是 `org.apache.jasper.runtime.JspWriterImpl`。

```java
public class JspWriterImpl extends JspWriter {
    private Writer out;
    private ServletResponse response;
    private char cb[];
    private int nextChar;
    // ...

    private void initOut() throws IOException {
        if (out == null) {
            out = response.getWriter();
        }
    }

    protected final void flushBuffer() throws IOException {
        if (bufferSize == 0)
            return;
        flushed = true;
        ensureOpen();
        if (nextChar == 0)
            return;
        initOut();
        out.write(cb, 0, nextChar);
        nextChar = 0;
    }

    @Override
    public void flush()  throws IOException {
        flushBuffer();
        if (out != null) {
            out.flush();
        }
    }
}
```

- 由`initOut()` 方法可知 `JspWriterImpl` 中的 `out` 属性实际上就是 `response.getWriter()`
- `JspWriterImpl`中的`cb`属性实际上就是`JspWriterImpl`的缓冲数组，`nextChar`属性是缓冲数组的字符数
- `JspWriterImpl`的`flush()`方法就是将`cb`缓冲的数据写入`response.getWriter()`,数据写入之后`response.getWriter()`在调用`flush()`方法将`response.getWriter()`缓冲的数据写入到响应的输出流中。

因为转换后的Servlet程序中使用的是`out.print`所以`out`的优先级要比`response.getWriter()`高。

### 测试

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <body>
        <%
            out.write("out 1st<br/>"); <%-- #1 --%>
            out.flush(); <%-- #2 --%>
            response.getWriter().write("writer 1st<br/>"); <%-- #3 --%>
            out.write("out 2st<br/>"); <%-- #4 --%>
            response.getWriter().write("writer 2st<br/>"); <%-- #5 --%>
            out.flush(); <%-- #6 --%>
        %>
    </body>
</html>
```

1. `out.write` 会将 `"out 1st<br/>"`写入到其缓冲区（也就是`JspWriterImpl`中的`cb`）
2. `out.flush` 会将 `out`缓冲区的数据写入到`response.getWriter()`的缓冲区中，并调用`response.getWriter()`的`flush()`方法，将`response.getWriter()`的缓冲区写入到响应的输出流中
3. `response.getWriter().write`方法会把`"writer 1st<br/>"`写入到`response.getWriter()`的缓冲区，但未写入响应流中
4. `out.write` 将`"out 2st<br/>"`写入到`out`的缓冲区中
5. `response.getWriter().write`将`"writer 2st<br/>"`写入到`response.getWriter()`的缓冲区，但未写入响应流中
6. `out.flush`方法先将`out`缓冲区中的`"out 2st<br/>"`写入到`response.getWriter()`的缓冲区，`"out 2st<br/>"`会追加到其缓冲区的末尾；再通过`response.getWriter().write`将其缓冲区的数据输出到响应的输出流中。

输出结果如下：
```html
out 1st<br/>
writer 1st<br/>
writer 2st<br/>
out 2st<br/>
```

## JSP 常用标签

### JSP 静态包含

参见：[include指令](#\ include\ 指令（静态包含）)

### JSP 动态包含

语法：
```jsp
<jsp:include page="includedPage" />
```

例子：
```jsp
<%-- index.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
    <jsp:include page="header.html" />
    <h2>Hello World!</h2>
    <jsp:include page="footer.jsp">
        <jsp:param name="username" value="alice"/>
    </jsp:include>
</body>
</html>
```

```html
<!-- header.html -->
<html>
<body>
    <header>Header</header>
</body>
</html>
```

```jsp
<%-- footer.jsp --%>
<html>
<body>
    <%
        String footer = "I'm a footer";
    %>
    <footer><%= footer  %></footer>
</body>
</html>
```

`index.jsp`转换后的结果如下

```java
public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException {
    // ...
    org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.html", out, false);
    out.write("    <h2>Hello World!</h2>\n");
    org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
    // ...
}
```

```java
public static void include(ServletRequest request, ServletResponse response, String relativePath, JspWriter out, boolean flush)
    throws IOException, ServletException {

    if (flush && !(out instanceof BodyContent))
        out.flush();

    String resourcePath = getContextRelativePath(request, relativePath);
    RequestDispatcher rd = request.getRequestDispatcher(resourcePath);

    rd.include(request,
                new ServletResponseWrapperInclude(response, out));

}
```

- 因为`footer.jsp`是动态文件，Tomcat服务器还会把`footer.jsp`翻译为一个Java的Servlet文件
- `org.apache.jasper.runtime.JspRuntimeLibrary.include` 方法本质上就是调用了 `RequestDispatcher.include`方法
- 通过 `<jsp:param name="username" value="alice"/>` 元素可以为请求传递参数

### JSP 标签转发

```jsp
<jsp:forward page="includedPage" />
```

该标签翻译后的结果如下
```java
request.getRequestDispatcher("includedPage").forward(request, response);
```

直接调用`RequestDispatcher.forward`方法

## Servlet 与 JSP

- Servlet 适合做业务处理
- JSP页面 适合做页面的展示
- Servlet 可以通过 `RequestDispatcher.forward` 将请求调度到JSP页面，将JSP页面的响应返回给客户端
- Servlet 业务处理的参数可以通过`ServletRequest.setAttribute`设置，在JSP页面中可以通过`request.getAttribute`获取到参数

## 动态 `<base>`  元素

``jsp
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<base href="<%= basePath %>">
```