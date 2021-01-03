# web API

[Web API reference](https://developer.mozilla.org/en-US/docs/Web/Reference/API)

[Web APIs](https://developer.mozilla.org/zh-CN/docs/Web/API)


## DOM

[Document Object Model (DOM)](https://developer.mozilla.org/en-US/docs/Web/API/Document_Object_Model)

[Node](https://developer.mozilla.org/en-US/docs/Web/API/Node): `Node` 是一个接口，各种类型的 DOM API 对象会从这个接口继承。它允许我们使用相似的方式对待这些不同类型的对象；比如, 继承同一组方法，或者用同样的方式测试。

[Document](https://developer.mozilla.org/en_US/docs/Web/API/Document): `Document` 接口表示任何在浏览器中载入的网页，并作为网页内容的入口，也就是**DOM 树**。DOM 树包含了像 `<body>` 、`<table>` 这样的元素，以及大量其他元素。它向网页文档本身提供了全局操作功能，能解决如何获取页面的 URL ，如何在文档中创建一个新的元素这样的问题。

[Element](https://developer.mozilla.org/en-US/docs/Web/API/Element): `Element` 是一个通用性非常强的基类，所有 `Document` 对象下的对象都继承自它。这个接口描述了所有相同种类的元素所普遍具有的方法和属性。

## 事件

[Event](https://developer.mozilla.org/en-US/docs/Web/API/Event)

[Event reference](https://developer.mozilla.org/en-US/docs/Web/Events)

### 事件注册

**事件处理器(Event Handler)**

[DOM onevent handlers](https://developer.mozilla.org/en-US/docs/Web/Guide/Events/Event_handlers)

[GlobalEventHandlers](https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers)

两种方式

- 设定HTML中的 `on<eventtype>` 属性:

```html
<button onclick="handleClick()">
```

HTML中设置`on<event>`的值会作为其事件处理的**函数体**.

- 通过JavaScript设置元素的on `<eventtype>` 属性:

```javascript
document.querySelector("button").onclick = function(event) { … }

document.querySelector("button").setAttribute("onclick", 'function(event) { … }');
```

[处理程序的返回值确定事件是否被取消](https://html.spec.whatwg.org/multipage/webappapis.html#the-event-handler-processing-algorithm)

每个对象对于给定的事件**只能有一个事件**处理器（尽管该处理程序可以调用多个子处理器）。这就是为什么`addEventListener()` 通常是获得事件通知的更好方法，特别是当你希望彼此独立地应用各种事件处理程序时，即使对于相同的事件和/或相同的元素也是如此。

**事件侦听器(Event Listener)**

[EventTarget.addEventListener()](https://developer.mozilla.org/en-US/docs/Web/API/EventTarget/addEventListener)

[EventTarget.removeEventListener()](https://developer.mozilla.org/en-US/docs/Web/API/EventTarget/removeEventListener)

## 正则表达式

[Regular expressions](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions)

正则表达式的创建

1. 正则表达式字面量

```javascript
var re = /ab+c/;
```

在脚本加载时会提供正则表达式的编译. 如果正则表达式是常量, 是由字面量来提高正则表达式的效率.

2. 正则表达式对象构造器`RegExp`

```javascript
var re = new RegExp('ab+c');
```

正则表达式构造函数提供正则表达式的运行时编译. 当正则表达式可能会变动或者从某处输入的话则应该使用正则表达式对象构造器.

