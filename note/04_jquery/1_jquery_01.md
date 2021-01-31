# jQuery

[jQuery API](https://api.jquery.com/)

## jQuery 核心

[jQuery Core](https://api.jquery.com/category/core/)

从一开始就在想`$(documnet).ready(function() {})`是什么意思.

```javascript
window.jQuery = window.$ = jQuery;
```

所以 `$` 就是 `jQuery` 函数.

但是我在[jQuery()](https://api.jquery.com/jQuery/)的官方文档中并未找到关于`$(document)`的定义. 因为`document`既不是[Selector](https://api.jquery.com/Types/#Selector), 也不是[Element](https://api.jquery.com/Types/#Element)(这一点可以通过`document instanceof Element`来验证), 更不是[PlainObject](https://api.jquery.com/Types/#PlainObject). 那么 `$(document)` 到底是什么意思.

在jQuery源码中我找到了,
```javascript
var jQuery = function( selector, context ) {
    return new jQuery.fn.init( selector, context );
};

jQuery.fn = jQuery.prototype = {
    // ...
}

var init = jQuery.fn.init = function( selector, context, root ) {
    var match, elem;

    // HANDLE: $(""), $(null), $(undefined), $(false)
    if ( !selector ) {
        return this;
    }

    // Method init() accepts an alternate rootjQuery
    // so migrate can support jQuery.sub (gh-2101)
    root = root || rootjQuery;

    // Handle HTML strings
    if ( typeof selector === "string" ) {
        // ....
    // HANDLE: $(DOMElement)
    } else if ( selector.nodeType ) {
        this[ 0 ] = selector;
        this.length = 1;
        return this;

    // HANDLE: $(function)
    // Shortcut for document ready
    } else if ( isFunction( selector ) ) {
        // rootjQuery 等价为 jQuery( document )
        // 如果未指定root, root变量即为 jQuery( document )
        // $(function() {}) 等价于 $(document).ready(function() {})
        return root.ready !== undefined ?
            root.ready( selector ) :

            // Execute immediately if ready is not present
            selector( jQuery );
    }

    return jQuery.makeArray( selector, this );
};

// Give the init function the jQuery prototype for later instantiation
init.prototype = jQuery.fn;

rootjQuery = jQuery( document );
```

在 `jQuery.fn.init` 函数中可以很清楚的知道, 实际上文档中 `Element` 是存在问题的, 而实际上应该是 `Node`, 源码中通过 `selector.nodeType` 来判断其类型. `document.nodeType` 为 9(更多可以参见 [MDN Node.nodeType](https://developer.mozilla.org/en-US/docs/Web/API/Node/nodeType)).

`jQuery` 函数使用 `new` 关键字创建 `jQuery.fn.init` 的实例对象, 该对象就是 **jQuery对象**. 该实例对象的构造方法的 `prototype` 属性是 `jQuery.fn.init.prototype`, 它和`jQuery.fn` 还有 `jQuery.prototype` 均指向同一对象, 因此jQuery对象会通过原型链 `__proto__` 继承 `jQuery.fn` 中的属性.

```javascript
jQuery.extend = jQuery.fn.extend = function() {
    // ...
}
```

使用 `jQuery.extend()` 为 `jQuery` 增加静态属性, 该属性可以通过 `jQuery.attribute` 或者 `$.attribute` 直接访问; 使用 `jQuery.fn.extend()` 为 `jQuery.fn` 增加实例属性, 该属性可以通过 `jQuery` 所创加的实例来访问, 如 `$(document).attribe`.

jQuery对象底层实际上就是`jQuery.fn.init`函数构造的对象数组, `length`属性记录的是数组的长度, 数组的内容基本都是DOM对象.

## 选择器

[jQuery Selectors](https://api.jquery.com/category/selectors/)

[MDN Selectors](https://developer.mozilla.org/en-US/docs/Web/CSS/Reference#Selectors)

### [基本选择器](https://api.jquery.com/category/selectors/basic-css-selectors/)

1. [通配选择器 (“*”)](https://api.jquery.com/all-selector/)
2. [元素选择器 (“element”)](https://api.jquery.com/element-selector/)
3. [类选择器 (“.class”)](https://api.jquery.com/class-selector/)
4. [ID选择器 (“#id”)](https://api.jquery.com/id-selector/)
5. [多重选择器 (“selector1, selector2, selectorN”)](https://api.jquery.com/multiple-selector/)

### [属性选择器](https://api.jquery.com/category/selectors/attribute-selectors/)

1. [Has Attribute Selector [name]](https://api.jquery.com/has-attribute-selector/)
2. [Attribute Contains Prefix Selector [name|=”value”]](https://api.jquery.com/attribute-contains-prefix-selector/)
3. [Attribute Contains Selector [name*=”value”]](https://api.jquery.com/attribute-contains-selector/)
4. [Attribute Contains Word Selector [name~=”value”]](https://api.jquery.com/attribute-contains-word-selector/)
5. [Attribute Ends With Selector [name$=”value”]](https://api.jquery.com/attribute-ends-with-selector/)
6. [Attribute Equals Selector [name=”value”]](https://api.jquery.com/attribute-equals-selector/)
7. [Attribute Not Equal Selector [name!=”value”]](https://api.jquery.com/attribute-not-equal-selector/)
8. [Attribute Starts With Selector [name^=”value”]](https://api.jquery.com/attribute-starts-with-selector/)
9. [Multiple Attribute Selector [name=”value”][name2=”value2″]](https://api.jquery.com/multiple-attribute-selector/)

### [层级选择器](https://api.jquery.com/category/selectors/hierarchy-selectors/)

- [子选择器 (“parent > child”)](https://api.jquery.com/child-selector/)
- [后代选择器 (“ancestor descendant”)](https://api.jquery.com/descendant-selector/)
- [相邻兄弟选择器 (“prev + next”)](https://api.jquery.com/next-adjacent-selector/)
- [普通兄弟选择器 (“prev ~ siblings”)](https://api.jquery.com/next-siblings-selector/)

### 过滤选择器

注意: 过滤选择器和CSS中的伪类, 伪元素选择器并不一样

1. [基本过滤选择器](https://api.jquery.com/category/selectors/basic-filter-selectors/)
2. [内容过滤选择器](https://api.jquery.com/category/selectors/content-filter-selector/)

### 表单选择器

参见: [表单选择器](https://api.jquery.com/category/selectors/form-selectors/)

- `:button`
- `:checkbox`
- `:checked`
- `:disabled`
- `:enabled`
- `:file`
- `:focus`
- `:image`
- `:input`
- `:password`
- `:radio`
- `:reset`
- `:submit`
- `:text`

## jQuery遍历

[jQuery Traversing](https://api.jquery.com/category/traversing/) 

## jQuery操作

[jQuery Manipulation](https://api.jquery.com/category/manipulation/)

- `html()`: 获取匹配元素集中第一个元素的HTML内容, 或设置每个匹配元素的HTML内容.
- `text()`: 获取匹配元素集中每个元素的组合文本内容(包括它们的后代), 或设置匹配元素的文本内容.
- `val()`: 获取匹配元素集中第一个元素的`value`, 或设置每个匹配元素的`value`. 一般都是表单元素.
- `attr()`: 获取匹配元素集中第一个元素的属性(attribute), 或为每个匹配元素设置一个或多个属性(attribute).
- `prop()`: 获取匹配元素集中第一个元素的属性(property), 或为每个匹配元素设置一个或多个属性(property).

注：`val()` 能够取到针对`text`，`hidden`可输入的文本框的`value`值，而 `attr('value')` 可以取到HTML元素中所设置的属性`value`的值，不能获取动态的如`<input type="text" />` 的文本框手动输入的值。

[jQuery 的 attr 与 prop 的区别](https://github.com/JChehe/blog/blob/master/posts/jQuery%20%E7%9A%84%20attr%20%E4%B8%8E%20prop%20%E7%9A%84%E5%8C%BA%E5%88%AB.md)

attribute是HTML元素属性; property是DOM对象的属性.

- `append()`/`appendTo()`
- `prepend()`/`prependTo()`
- `insertAfter()` 
- `insertBefore()`
- `replaceWith()`/`replaceAll()`
- `remove()`
- `empty()`

## jQuery CSS

[jQuery CSS](https://api.jquery.com/category/css/)

**样式**

- `.addClass()`
- `.removeClass()`
- `.toggleClass()`

**布局**

[Offset](https://api.jquery.com/category/offset/)

- `.offset()`
- `.position()`

**动画**

[Effects](https://api.jquery.com/category/effects/)

- `.show()`
- `.hide()`
- `.toggle()`

- `.fadeIn()`
- `.fadeOut()`
- `.fadeToggle()`
- `.fadeTo()`

## 事件

[Events](https://api.jquery.com/category/events/)

- `.ready()`
- `.click()`
- `.on()`
- `.off()`
- `.one()`

### `.on()` 函数

参考: [jQuery .on()](https://api.jquery.com/on/)

```javascript
.on( events [, selector ] [, data ], handler(eventObject) )
```

1. 回调函数中调用 `event.stopPropagation()`和`event.stopImmediatePropagation()`会阻止事件冒泡; 回调函数 `return false` 回调函数会自动调用`event.stopPropagation()` 和 `event.preventDefault()`; 也可以直接将`false`作为`handler`参数, 作为 `function() {return false}`的简写.
2. 回调函数的的第一个参数是事件对象`event`; 回调函数的`this` 本质上是 `event.currentTarget`, 他总是指向事件绑定的元素; 而 `Event.target` 则是事件触发的元素。参考:[Event.currentTarget](https://developer.mozilla.org/en-US/docs/Web/API/Event/currentTarget)和[Event.target](https://developer.mozilla.org/zh-CN/docs/Web/API/Event/target)
3. 如果`data`参数给`.on()`并且不是`null` 或者 `undefined`，那么每次触发事件时，`event.data`都传递给处理程序。

```javascript
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>jQuery</title>
</head>

<body>
    <ul class="level-1">
        <li class="item-i">I</li>
        <li class="item-ii">II
            <ul class="level-2">
                <li class="item-a">A</li>
                <li class="item-b">B
                    <ul class="level-3">
                        <li class="item-1">1</li>
                        <li class="item-2">2</li>
                        <li class="item-3">3</li>
                    </ul>
                </li>
                <li class="item-c">C</li>
            </ul>
        </li>
        <li class="item-iii">III</li>
    </ul>
    <script src="jquery-3.5.1.js"></script>
    <script>
        $(document).on("click", ".level-1", function (event) {
            // 因为click事件绑定在 ul.level-1 元素上, 因此 this总是 ul.level-1 元素
            console.log(this);
            // event.target 是 click 事件发生的元素
            console.log(event.target);
            // event.stopPropagation();
            // event.preventDefault();
            return false;
        });
    </script>
</body>

</html>
```

注:

1. DOM `onevent` 事件处理函数的处理过程可以参见[这里](https://html.spec.whatwg.org/multipage/webappapis.html#the-event-handler-processing-algorithm). 大部分事件处理回调函数中 `return false` 只相当于 `event.preventDefault()`
2. jQuery事件回调函数中 `return false` 相当于 `event.stopPropagation()` 和 `event.preventDefault()`, 参见[jQuery .on()](https://api.jquery.com/on/)
3. `EventTarget.addEventListener()` 事件监听的回调函数没有返回值, 因此 `return false` 并不起作用, 需要显示调用 `event.stopPropagation()` 和 `event.preventDefault()`. 参见[The event listener callback](https://developer.mozilla.org/en-US/docs/Web/API/EventTarget/addEventListener#The_event_listener_callback)