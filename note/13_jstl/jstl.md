# JSTL(JSP Standard Tag Library)

[JavaServer Pages Standard Tag Library 1.1 Tag Reference](https://docs.oracle.com/javaee/5/jstl/1.1/docs/tlddocs/index.html)

[Why there is el-api.jar in Tomcat7 but no jstl libs?](https://stackoverflow.com/questions/9026873/why-there-is-el-api-jar-in-tomcat7-but-no-jstl-libs)

Tomcat 服务器有 EL 表达式的 Jar 包，所以在JSP中不需要引入额外依赖就可以使用 EL 表达式；没有 JSTL 相关的 Jar 包，所以如果要使用 JSTL 需要添加相关依赖。

JSTL的依赖可以使用 [taglibs-standard-spec](https://mvnrepository.com/artifact/org.apache.taglibs/taglibs-standard-spec) 和 [taglibs-standard-impl](https://mvnrepository.com/artifact/org.apache.taglibs/taglibs-standard-impl)。Apache Standard Taglib 是Java Standard Tag Library（JSTL）规范的开源实现。

JSTL标签库全称是指 JSP Standard Tag Library。JSP标准标签库是一个不断完善的开放源代码的JSP标签库。

EL表达式主要是为了替换JSP中的表达式脚本，而标签库而是为了替换代码脚本。这样使得整个JSP页面变得更加简洁。

JSTL由五种不同功能的标签库组成：

|功能范围|URI|前缀|
|------|---|----|
|核心标签库|`http://java.sun.com/jsp/jstl/core`|`c`|
|格式化|`http://java.sun.com/jsp/jstl/fmt`|`fmt`|
|函数|`http://java.sun.com/jsp/jstl/function`|`fn`|
|数据库（不使用）|`http://java.sun.com/jsp/jstl/sql`|`sql`|
|XML（不使用）|`http://java.sun.com/jsp/jstl/xml`|`x`|

使用 taglib 指令引入标签库

```jsp
<%-- CORE 标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- FMT 标签库 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- FUNCTION 标签库 --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/function" %>
<%-- SQL 标签库 --%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%-- XML 标签库 --%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
```

## 准备

1. 导入[`taglibs-standard-impl.jar`](https://mvnrepository.com/artifact/org.apache.taglibs/taglibs-standard-impl)和[`taglibs-standard-spec.jar`](https://mvnrepository.com/artifact/org.apache.taglibs/taglibs-standard-spec)
2. 使用 `taglib` 指令导入标签库，例如`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`


## CORE 核心库使用

1. `c:set` 和 `c:if`

```jsp
<c:if test="${ empty requestScope.set }"> null </c:if> <br />
<c:set scope="request" var="set" value="c:set" />
执行 &lt;c:set scope="request" var="set" value="c:set" /&gt;<br />
&dollar;{ requestScope.set }: ${ requestScope.set } <br />
```

`<c:set var="foo" scope="session" value="..."/>`

- `scope`：设置键值对存储的域对象
    - page：PageContext域（默认值）
    - request：Request域
    - session：Session域
    - application：ServletContext域
- `var`：键
- `value`：值

`<c:if test="${ empty requestScope.set }"> null </c:if>`

- `test`：输入EL表达式

2. 多路判断

```jsp
<%
    request.setAttribute("score", 60);
%>
${ requestScope.score } 分数等级：
<c:choose>
    <c:when test="${ requestScope.score >= 90 }">
        A
    </c:when>
    <c:when test="${ requestScope.score >= 80 }">
        B
    </c:when>
    <c:when test="${ requestScope.score >= 70 }">
        C
    </c:when>
    <c:when test="${ requestScope.score >= 60 }">
        D
    </c:when>
    <c:otherwise>
        E
    </c:otherwise>
</c:choose>
```

注：`<c:when>`和`<c:otherwise>`只能在`<c:choose>`标签内使用

3. `c:forEach`

```jsp
    <table>
        <c:forEach begin="1" end="9" var="i">
            <tr>
                <c:forEach begin="1" end="${ i }" var="j">
                    <td>
                        ${ i }*${ j }=${ i * j }
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
```

4. 遍历数组

```jsp
<%
    pageContext.setAttribute("arr", new String[] {"wdnmd", "yyds", "u1s1"});
%>
<c:forEach items="${ pageScope.arr }" var="item">
    ${ item } <br />
</c:forEach>
```

5. 遍历Map
```jsp
<%
    Map<String, String> map = new HashMap<>();
    map.put("u1s1", "有一说一");
    map.put("yyds", "永远的神");
    map.put("yygq", "阴阳怪气");
    request.setAttribute("map", map);
%>
<c:forEach items="${ requestScope.map }" var="entry">
        ${ entry.key }=${ entry.value } <br />
</c:forEach>
```

6. 遍历JavaBean

```java
package xyz.zerxoi;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String password;
    private String phone;

    public User() {
    }

    public User(Integer id, String name, Integer age, String password, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User [age=" + age + ", id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone
                + "]";
    }
}
```

```jsp
<%@ page import="xyz.zerxoi.*, java.util.*" %>
<%
    List<User> userList = new ArrayList<>();
    userList.add(new User(1, "石原里美", 26, "shiyuanlimei", "10010"));
    userList.add(new User(2, "桥本环奈", 23, "qiaobenhuannai", "10086"));
    userList.add(new User(3, "新垣结衣", 28, "xinyuanjieyi", "10000"));
    userList.add(new User(4, "四宫辉夜", 16, "sigonghuiye", "12345"));
    userList.add(new User(5, "樱岛麻衣", 17, "yingdaomayi", "67890"));
    userList.add(new User(6, "远坂凛", 17, "yuanbanlin", "54321"));
    userList.add(new User(7, "栗山未来", 15, "lishanweilai", "09876"));
    request.setAttribute("users", userList);
%>

<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>age</th>
        <th>password</th>
        <th>phone</th>
        <th>current</th>
    </tr>
    <c:forEach items="${ requestScope.users }" var="user" varStatus="status" >
        <tr>
            <td>${ user.id }</td>
            <td>${ user.name }</td>
            <td>${ user.age }</td>
            <td>${ user.password }</td>
            <td>${ user.phone }</td>
            <td>${ status.current }</td>
        </tr>
    </c:forEach>
</table>
```

- `begin`：起始索引
- `end`：终止索引
- `items`：数组，List或Map数据集
- `var`：子对象
- `step`：步长
- `varStatus`：当前变量的状态，`javax.servlet.jsp.jstl.core.LoopTagSupport$Status`，该类的实现如下

```java
public LoopTagStatus getLoopStatus() {

    // local implementation with reasonable default behavior
    class Status implements LoopTagStatus {

        // 获取当前遍历到的数据，即 var
        public Object getCurrent() {
            return (LoopTagSupport.this.getCurrent());
        }

        // 获取当前数据的索引
        public int getIndex() {
            return (index + begin);       // our 'index' isn't getIndex()
        }

        // 表示当前对象是遍历的第几个元素
        public int getCount() {
            return (count);
        }

        // 当前数据是否是第一个数据
        public boolean isFirst() {
            return (index == 0);          // our 'index' isn't getIndex()
        }

        // 当前数据是否是最后一个数据
        public boolean isLast() {
            return (last);                // use cached value
        }

        // 获取 begin
        public Integer getBegin() {
            if (beginSpecified) {
                return (new Integer(begin));
            } else {
                return null;
            }
        }

        // 获取 end
        public Integer getEnd() {
            if (endSpecified) {
                return (new Integer(end));
            } else {
                return null;
            }
        }

        // 获取 step
        public Integer getStep() {
            if (stepSpecified) {
                return (new Integer(step));
            } else {
                return null;
            }
        }
    }

    /*
        * We just need one per invocation...  Actually, for the current
        * implementation, we just need one per instance, but I'd rather
        * not keep the reference around once release() has been called.
        */
    if (status == null) {
        status = new Status();
    }

    return status;
}
```