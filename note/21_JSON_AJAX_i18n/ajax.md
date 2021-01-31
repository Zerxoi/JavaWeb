# AJAX

[Ajax](https://developer.mozilla.org/zh-CN/docs/Web/Guide/AJAX)

[AJAX 术语表](https://developer.mozilla.org/zh-CN/docs/Glossary/AJAX)


**AJAX(Asynchronous JavaScript And XML)**是一种使用 `XMLHttpRequest` 对象与服务器通信的技术。它可以使用JSON，XML，HTML和text文本等格式发送和接收数据。

尽管X在Ajax中代表XML, 但由于JSON的许多优势，比如更加轻量以及作为Javascript的一部分，目前JSON的使用比XML更加普遍。JSON和XML都被用于在Ajax模型中打包信息。

AJAX允许只更新一个 HTML 页面的部分 DOM，而无须重新加载整个页面。浏览器地址栏不会发生变化，局部更新不会舍弃原来的内容。

AJAX还允许异步工作，这意味着在请求发出去之后，代码可以继续运行，等到收到服务器数据响应后再进行数据处理（相比之下，同步会在收到响应数据之前阻止代码继续运行，直到收到响应时代码才继续执行）。

通过交互式网站和现代 Web 标准，AJAX正在逐渐被 JavaScript 框架中的函数和官方的 [`Fetch API`](https://developer.mozilla.org/zh-CN/docs/Web/API/Fetch_API) 标准取代。

## XMLHttpRequest

[Getting Started](https://developer.mozilla.org/zh-CN/docs/Web/Guide/AJAX/Getting_Started)

[XMLHttpRequest API](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest)

- [`XMLHttpRequest.open(method, url[, async[, user[, password]]])`](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/open)
    - **初始化一个新创建的请求，或重新初始化一个现有的请求。**
    - `method`：使用的HTTP请求方法，如 `"GET"`、`"POST"`、`"PUT"`、`"DELETE"`等
    - `url`：将请求发送到的URL
    - `async`：请求请求是否是异步的，异步和同步的区别见下面的`XMLHttpRequest.send()`方法
    - `user`：用于身份验证的可选用户名；默认情况下为`null`
    - `password`：用于身份验证的可选密码；默认情况下为`null`
- [`XMLHttpRequest.send([body])`](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/send)
    - **将请求发送到服务器**
    -  如果请求是异步的（这是默认的），那么一旦请求被发送，这个方法就会返回，并使用事件传递结果。如果请求是同步的，则在响应到达之前该方法不会返回。
    - `send()`接受一个可选参数，该参数可让您指定请求的正文；这主要用于诸如PUT之类的请求。如果请求方法是GET或HEAD，则将忽略`body`参数，并将请求主体设置为`null`。
    - 如果没有使用 `setRequestHeader()`设置Accept请求头，则发送类型为`"*/*"`（任何类型）的Accept请求头。
- [`XMLHttpRequest.onreadystatechange`](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/onreadystatechange)
    - **每当readyState属性发生变化时，就会调用一次事件处理函数**
    - 这不应该用于同步请求，因为同步请求不适用事件传递结果。
- [`XMLHttpRequest.readyState`](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState)
    - 返回XMLHttpRequest客户端所处的状态
    - XMLHttpRequest 状态如下：

|Value|State|Description|
|-----|-----|-----------|
|0|`UNSENT`|Client has been created. `open()` not called yet.|
|1|`OPENED`|`open()` has been called.|
|2|`HEADERS_RECEIVED`|`send()` has been called, and headers and status are available.|
|3|`LOADING`|Downloading; `responseText` holds partial data.|
|4|`DONE`|The operation is complete.|
- [`XMLHttpRequest.status`]()
    - **返回`XMLHttpRequest`响应的数字HTTP状态代码**
    - 在请求完成之前，状态值为0，在XMLHttpRequest出错时，浏览器也会报告状态为0。

### XMLHttpRequest 示例

```javascript
document.querySelector(".ajax_btn").addEventListener('click', ajaxRequest);

function ajaxRequest() {
    var httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", "http://localhost:8080/json_ajax_i18n/ajaxServlet?action=ajax", true);
    httpRequest.send();
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                alert(httpRequest.responseText);
            } else {
                alert("There was a problem with the request");
            }
        }
    }
}
```

## jQuery

[**`jQuery.ajax()`**](https://api.jquery.com/jQuery.ajax/)

[dataType vs accepts - Ajax Request](https://stackoverflow.com/questions/33060712/datatype-vs-accepts-ajax-request)

执行异步HTTP（Ajax）请求。

### `jQuery.ajax( url [, settings ] )`

- `url`：请求发送到的URL的字符串。
- `settings`：一组配置Ajax请求的键/值对。所有的设置都是可选的。可以使用 `$.ajaxSetup()` 为任何选项设置一个默认值。
    - `method`：请求方法。默认是`GET`。1.9.0之前的jQuery版本，则应使用`type`
    - `url`：请求的URL地址
    - `async`：是否是异步请求（默认为`true`）
    - `contentType`：告诉服务器请求数据的数据类型，默认是`application/x-www-form-urlencoded; charset=UTF-8`
    - `data`：发送到服务器的数据。如果HTTP方法是不能具有实体主体（例如GET）的方法，则数据将附加到URL。当数据是对象时，除非将`processData`选项设置为`false`，否则jQuery会根据对象的键/值对生成数据字符串。
    - `accepts`：一组键/值对对象，该对象为`dataType`提供映射，`dataType`中指定的数据类型会在`Accept`请求头中发送。`Accept`请求头告诉服务器可以发送回的数据类型
    - `dataFilter`：用于处理`XMLHttpRequest`的原始响应数据的函数。 这是一个预过滤函数，返回清理后的响应数据。 该函数接受两个参数：服务器返回的原始数据和`dataType`参数。
    - `dataType`：期望从服务器返回的数据类型。如果未指定任何内容，则jQuery将尝试根据响应的MIME类型进行推断。`dataType`会对请求的响应将由jQuery预处理，处理后的结果会传递到`success`回调函数。可处理的数据类型有 `xml`、 `json`、`script`、`html` 等
    - `success`：请求成功时要调用的函数。该函数将传递3个参数：**从服务器返回的由`dataFilter`回调函数过滤和`dataType`参数预处理的数据**，**描述状态的字符串** 和 **`jqXHR`**

`jQuery.get( url [, data ] [, success ] [, dataType ] )` 、 `jQuery.post( url [, data ] [, success ] [, dataType ] )` 、`jQuery.getJSON( url [, data ] [, success ] )` 等都是对 `jQuery.ajax` 的封装，使用方法类似。

### `.serialize()`

`.serialize()` 方法以标准的URL编码表示法创建文本字符串。它可以作用于已选择单个表单控件的jQuery对象（例如`<input>`，`<textarea>`和`<select>`）。

