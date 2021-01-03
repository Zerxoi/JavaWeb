# Cookie

## 概念

[Using HTTP cookies](https://developer.mozilla.org/en-US/docs/Web/HTTP/Cookies)

Cookie是服务器发送到浏览器并由浏览器保存的**键值对**数据，浏览器会在下次向同一服务器再发起请求时携带Cookie并发送到服务器上。

每个Cookie的大小不超过4KB。

## Cookie创建

```java
protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/plain;charset=UTF-8");
    // 创建 Cookie 对象
    Cookie yummy_cookie = new Cookie("yummy_cookie", "choco");
    Cookie tasty_cookie = new Cookie("tasty_cookie", "strawberry");
    // 将 Cookie对象 添加到响应头
    resp.addCookie(yummy_cookie);
    resp.addCookie(tasty_cookie);
    // 响应体内容
    resp.getWriter().println("Cookie 创建成功");
}
```

Cookie 创建的本质是服务器使用 `Set-Cookie` 响应头部向浏览器发送 Cookie信息。

服务器通过该头部告知客户端保存或修改 Cookie 信息（不存在就创建，已存在则修改）。

```
HTTP/1.1 200
Set-Cookie: yummy_cookie=choco
Set-Cookie: tasty_cookie=strawberry
Content-Type: text/plain;charset=UTF-8
Content-Length: 21
Date: Fri, 04 Dec 2020 08:40:51 GMT
Keep-Alive: timeout=20
Connection: keep-alive

Cookie 创建成功
```

## Cookie获取

在Cookie创建之后，对该服务器发起的每一次新请求，浏览器都会将之前保存的Cookie信息通过 Cookie 请求头部再发送给服务器。

```
GET /cookie_session/cookie.html HTTP/1.1
Host: localhost:8080
<!-- ... -->
Cookie: yummy_cookie=choco; tasty_cookie=strawberry
```

Java Servlet服务器可以通过 `HttpServletRequest.getCookies` 方法获取 Cookie 数组。

```java
protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/plain;charset=UTF-8");
    
    PrintWriter out = resp.getWriter();
    Cookie[] cookies = req.getCookies();
    for (Cookie cookie : cookies) {
        out.println(cookie.getName() + ": " + cookie.getValue());
    }
}
```

## Cookie 修改

如果 Cookie 已经存在，再次在响应头中指定 Cookie，则会修改现有的 Cookie 值。

```java
protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/plain;charset=UTF-8");

    Cookie yummy_cookie = new Cookie("yummy_cookie", "巧克力");
    resp.addCookie(yummy_cookie);

    Cookie tasty_cookie = CookieUtils.findCookie("tasty_cookie", req.getCookies());
    if (tasty_cookie != null) {
        tasty_cookie.setValue("草莓");
    }
    resp.addCookie(tasty_cookie);

    PrintWriter out = resp.getWriter();
    out.println("Cookie 修改成功");
}
```

其中 `CookieUtils.findCookie` 是用于查找指定名称的Cookie对象。

```java
public class CookieUtils {
    public static Cookie findCookie(String name, Cookie[] cookies) {
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }
}
```

## Cookie 生命周期

Cookie 的生命周期可以通过两种方式定义：

- 会话期 Cookie：浏览器关闭之后它会被自动删除，也就是说它仅在会话期内有效。
- 持久性 Cookie：Cookie 的生命周期取决于过期时间（Expires）或有效期（Max-Age）指定的一段时间。

Java 中可以通过 `cookie.setMaxAge(int expiry)` 指定 `cookie` 的生命周期。

- `expiry > 0` 表示cookie将在经过许多秒后过期并被删除
- `expiry == 0` 表示cookie被立即删除
- `expiry < 0` 表示cookie不会持久存储，并且在Web浏览器退出时将被删除。

注：`expiry` 默认为 `-1`

```java
protected void DefaultLifetime(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/plain;charset=UTF-8");
    
    Cookie bad_cookie = new Cookie("bad_cookie", "banana");
    bad_cookie.setMaxAge(-1);
    resp.addCookie(bad_cookie);

    PrintWriter out = resp.getWriter();
    out.println("bad_cookie 会话关闭时删除");
}

protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/plain;charset=UTF-8");
    
    Cookie cookie = CookieUtils.findCookie("bad_cookie", req.getCookies());
    if (cookie == null) {
        return;
    }
    cookie.setMaxAge(0);
    resp.addCookie(cookie);

    PrintWriter out = resp.getWriter();
    out.println("立即删除 bad_cookie");
}

protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/plain;charset=UTF-8");

    Cookie cookie = new Cookie("perishable_cookie", "banana");
    cookie.setMaxAge(3600);
    resp.addCookie(cookie);

    PrintWriter out = resp.getWriter();
    out.println("perishable_cookie 1小时后过期");
}
```

## 限制访问 Cookie

- `Secure` 属性：标记为 Secure 的 Cookie 只应通过被 **HTTPS** 协议加密过的请求发送给服务端。
    - Java 中通过 `cookie.setHttpOnly(true)`设置 `Secure` 属性
- `HttpOnly` 属性：JavaScript `Document.cookie` API 无法访问带有 `HttpOnly` 属性的cookie；此类 Cookie 仅作用于服务器。
    - Java 中通过 `cookie.setSecure(true)` 设置 `HttpOnly` 属性

示例

```
Set-Cookie: id=a3fWa; Expires=Wed, 21 Oct 2015 07:28:00 GMT; Secure; HttpOnly
```

## Cookie 作用域

`Domain` 和 `Path` 标识定义了Cookie的作用域：即允许 Cookie 应该发送给哪些URL。

### Domain 属性

`Domain` 指定了哪些主机可以接受 Cookie。如果不指定，默认为 [`origin`](https://developer.mozilla.org/zh-CN/docs/Glossary/%E6%BA%90)，不包含子域名。(只有当协议，主机和端口都匹配时，两个对象具有相同的`origin`)。`Domain`要和请求服务器的域名一致，否则会被浏览器拦截。

Java 中通过 `cookie.setDomain(domain)` 设置 `Domain` 属性

### Path 属性

`Path` 标识指定了主机下的哪些路径可以接受 Cookie。（该 URL 路径必须存在于请求 URL 中）

Java 中通过 `cookie.setPath(path)` 设置 `Path` 属性

### SameSite 属性

[Site的定义](https://developer.mozilla.org/en-US/docs/Glossary/Site)

`SameSite` 定义了跨站点（`Site`）的请求中是否会携带浏览器中已存在站点的 Cookie 。

`SameSite` 属性有三种值。分别为`None`， `Restrict`， `Lax`。

使用格式如下

```
Set-Cookie: key=value; SameSite={Strict|Restrict|Lax}
```

[Cookie 的 SameSite 属性](https://www.ruanyifeng.com/blog/2019/09/cookie-samesite.html)

- `Strict` 最为严格，完全禁止第三方 Cookie，跨站点时，任何情况下都不会发送 Cookie。换言之，只有当前网页的 URL 与请求目标一致，才会带上 Cookie。

- `Lax` 规则稍稍放宽，大多数情况也是不发送第三方 Cookie，但是导航到目标网址的 Get 请求除外。

导航到目标网址的 GET 请求，只包括三种情况：链接，预加载请求，GET 表单。详见下表。

|请求类型|示例|正常情况|Lax|
|-|-|-|-|
|链接|`<a href="..."></a>`|发送 Cookie|发送 Cookie|
|预加载|`<link rel="prerender" href="..."/>`|发送 Cookie|发送 Cookie|
|GET 表单|`<form method="GET" action="...">`|发送 Cookie|发送 Cookie|
|POST 表单|`<form method="POST" action="...">`|发送 Cookie|不发送|
|iframe|`<iframe src="..."></iframe>`|发送 Cookie|不发送|
|AJAX|`$.get("...")`|发送 Cookie|不发送|
|Image|`<img src="...">`|发送 Cookie|不发送|

- `None` 跨站点请求发送可以第三方 Cookie。

Chrome 计划将`Lax`变为默认设置。这时，网站可以选择显式关闭`SameSite`属性，将其设为`None`。不过，前提是必须同时设置`Secure`属性（Cookie 只能通过 HTTPS 协议发送），否则无效。

下面的设置无效。

```
Set-Cookie: widget_session=abc123; SameSite=None
```

下面的设置有效。

```
Set-Cookie: widget_session=abc123; SameSite=None; Secure
```