# CSS

**层叠样式表（Cascading Style Sheet，简称：CSS）**是用来指定文档如何展示给用户的一门语言——如网页的样式、布局、等等。

一份**文档**是由标记语言组织起来的文本文件 —— HTML 是最常见的标记语言, 但你可能也听说过其他可标记语言，如 SVG 或 XML。

**展示**一份文档给用户实际上是将文档变成用户可用的文件。Browsers：如 Firefox，Chrome, 或 Edge，都可以将文档在电脑屏幕、投影仪或打印机等设备上进行可视化。

CCS 可以用于给文档添加样式 —— 比如改变标题和链接的颜色及大小。它也可用于创建布局 —— 比如将一个单列文本变成包含主要内容区域和存放相关信息的侧边栏区域的布局。它甚至还可以用来做一些特效，比如动画。查看本段内容中所给出的特定案例。

[**CSS References**](https://developer.mozilla.org/en-US/docs/Web/CSS/Reference)

##  语法

参考: 

1. [“CSS 规则集”详解](https://developer.mozilla.org/zh-CN/docs/Learn/Getting_started_with_the_web/CSS_basics#CSS_%E7%A9%B6%E7%AB%9F%E4%BB%80%E4%B9%88%E6%9D%A5%E5%A4%B4%EF%BC%9F)
2. [语法](https://developer.mozilla.org/en-US/docs/Web/CSS/Syntax)

![规则集](2020-10-20-15-52-05.png)

整个结构称为 **规则集（通常简称“规则”）**，各部分释义如下：

- [**选择器（Selector）**](https://developer.mozilla.org/en-US/docs/Learn/CSS/Building_blocks/Selectors)

HTML 元素的名称位于规则集开始。它选择了一个或多个需要添加样式的元素（在这个例子中就是 `p` 元素）。要给不同元素添加样式只需要更改选择器就行了。
- **声明（Declaration）**

一个单独的规则，如 `color: red;` 用来指定添加样式元素的属性。
- **属性（Properties）**

改变 HTML 元素样式的途径。（本例中 `color` 就是 `<p>` 元素的属性。）CSS 中，由编写人员决定修改哪个属性以改变规则。
- **属性的值（Property value）**

在属性的右边，冒号后面即属性的值，它从指定属性的众多外观中选择一个值（我们除了 `red` 之外还有很多属性值可以用于 `color` ）。

注意其他重要的语法：

- 每个规则集（除了选择器的部分）都应该包含在成对的大括号里（`{}`）。
- 在每个声明里要用冒号（`:`）将属性与属性值分隔开。
- 在每个规则集里要用分号（`;`）将各个声明分隔开。

注释

```css
/* 注释 */
```

## 在HTML里应用CSS样式

[在你的HTML里面应用CSS](https://developer.mozilla.org/zh-CN/docs/Learn/CSS/First_steps/How_CSS_is_structured#%E5%9C%A8%E4%BD%A0%E7%9A%84HTML%E9%87%8C%E9%9D%A2%E5%BA%94%E7%94%A8CSS)

- **内联样式**(不推荐)
内联样式表存在于HTML元素的style属性之中。其特点是每个CSS表只影响一个元素

- **外部样式表**(推荐)

外部样式表是指将CSS编写在扩展名为`.css` 的单独文件中，并从HTML`<link>` 元素引用它的情况:

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>My CSS experiment</title>
    <link rel="stylesheet" href="styles.css">
  </head>
  <body>
    <h1>Hello World!</h1>
    <p>This is my first CSS example</p>
  </body>
</html>
```
`<link>` 元素的 `href` 属性需要引用你的文件系统中的一个文件。

- **内部样式表**

内部样式表是指不使用外部CSS文件，而是将CSS放在HTML文件`<head>`标签里的`<style>`标签之中。

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>My CSS experiment</title>
    <!--  style 元素要求是MIME类型, 默认值为 text/css -->
    <style>
      h1 {
        color: blue;
        background-color: yellow;
        border: 1px solid black;
      }

      p {
        color: red;
      }
    </style>
  </head>
  <body>
    <h1>Hello World!</h1>
    <p>This is my first CSS example</p>
  </body>
</html>
```

有的时候，这种方法会比较有用（比如你使用的内容管理系统不能直接编辑CSS文件)，但该方法和外部样式表比起来更加低效 。在一个站点里，你不得不在每个页面里重复添加相同的CSS，并且在需要更改CSS时修改每个页面文件。

## 选择器

参考: [CSS selectors](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Selectors)

## 优先级

[Specificity](https://developer.mozilla.org/en-US/docs/Web/CSS/Specificity)

[Specificity_2](https://developer.mozilla.org/en-US/docs/Learn/CSS/Building_blocks/Cascade_and_inheritance#Specificity_2)

## 继承

[继承](https://developer.mozilla.org/zh-CN/docs/Web/CSS/inheritance)

[Understanding inheritance](https://developer.mozilla.org/en-US/docs/Learn/CSS/Building_blocks/Cascade_and_inheritance#Understanding_inheritance)
