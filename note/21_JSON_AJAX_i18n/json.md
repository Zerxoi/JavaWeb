# JSON

## JavaScript

[JSON](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/JSON)

### 对象字面量和JSON

[Object literal notation vs JSON](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Object_initializer#Description)

1. JSON仅允许使用 `"property": value` 语法定义属性。属性名称必须用双引号引起来，并且定义不能为简写形式。

简写形式如下
```javascript
// Shorthand property names (ES2015)
let a = 'foo', b = 42, c = {};
let o = {a, b, c}

// Shorthand method names (ES2015)
let o = {
  property(parameters) {}
}

// Computed property names (ES2015)
let prop = 'foo';
let o = {
  [prop]: 'hey',
  ['b' + 'ar']: 'there'
}
```

2. 在JSON中，值只能是字符串，数字，数组，`true`，`false`，`null`或另一个（JSON）对象。

3. 无法将函数值分配给JSON中的值。

注：以上内容可以通过 `JSON.parse` 函数来测试

### JSON 静态方法

- `JSON.parse(text[, reviver])`：解析JSON字符串并返回对应的JSON对象。可以额外传入一个`reviver`转换函数，在JSON对象返回之前修改属性的值。

```javascript
JSON.parse('{"1": 1, "2": 2, "3": {"4": 4, "5": {"6": 6}}}', (key, value) => {
    console.log(key, typeof key, value, typeof value);
    if (typeof value === "number") {
        return 2 * value;
    } else {
        return value;
    }
});
```

```
1 string 1 number
2 string 2 number
4 string 4 number
6 string 6 number
5 string {6: 12} object
3 string {4: 8, 5: {…}} object
 string {1: 2, 2: 4, 3: {…}}1: 22: 43: {4: 8, 5: {…}} object

{1: 2, 2: 4, 3: {…}}
```

根据 `console.log(key, typeof key, value, typeof value);` 语句可以很清楚的看到 `JSON.parse` 函数的解析规律：即先解析JSON对象中的属性的值，所有属性的值都解析完后再解析该JSON对象；最外层对象的JSON属性时空字符串。


- `JSON.stringify(value[, replacer[, space]])`：返回与指定值对应的JSON字符串。可以通过额外的参数, 控制仅包含某些属性, 或者以自定义方法来替换某些key对应的属性值。

```javascript
JSON.stringify({ "1": 2, "2": 4, "3": { "4": 8, "5": { "6": 12 } } }, (key, value) => {
    console.log(key, typeof key, value, typeof value);
    if (typeof value === "number") {
        return value / 2;
    }
    return value;
});
```

根据 `console.log(key, typeof key, value, typeof value)` 语句可以看出 `JSON.stringify` 函数的解析规律与 `JSON.parse` 正好相反：即先解析JSON对象，在解析其中中的属性的值；最外层对象的属性时空字符串。


```
 string {1: 2, 2: 4, 3: {…}} object
1 string 2 number
2 string 4 number
3 string {4: 8, 5: {…}} object
4 string 8 number
5 string {6: 12} object
6 string 12 number
"{"1":1,"2":2,"3":{"4":4,"5":{"6":6}}}"
```

如果 `replacer` 是一个数组，数组的值代表将被序列化成 JSON 字符串的属性名。

```javascript
JSON.stringify({ foundation: "Mozilla", model: "box", week: 45, transport: "car", month: 7 }, ['week', 'month']);  
```

```
"{"week":45,"month":7}"
```

## Java

[Gson User Guide](https://github.com/google/gson/blob/master/UserGuide.md)

基本类型，JavaBean对象，数组的序列化和反序列化参考上面的链接即可。

### 容器的序列化和反序列化

Gson可以序列化任意对象的容器`Collection`，但不能对其进行反序列化，因为用户无法指示生成的对象的类型。 相反，在反序列化时，`Collection`必须是特定的通用类型。 

```java
public class Test {
    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(0);

        // Serialization
        String json = gson.toJson(list); // ==> json is [1,2,3,4,5]
        System.out.println(json);
        // Deserialization
        Type listType = new TypeToken<List<Integer>>() {
        }.getType();
        List<Integer> obj = gson.fromJson(json, listType);
        for (int i = 0; i < obj.size(); i++) {
            System.out.println(obj.get(i));
        }
    }
}
```

### 序列化和反序列化泛型

当调用`toJson(obj)`时，Gson调用`obj.getClass()`以获取有关要序列化的字段的信息。 同样，通常可以在`fromJson(json，MyClass.class)`方法中传递`MyClass.class`对象。 如果对象是非泛型类型，则可以正常工作。 但是，如果对象属于泛型类型，由于**Java Type Erasure（Java类型擦除）**将导致泛型类型信息丢失。

```java
public class Test {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Foo<Bar> foo = new Foo<Bar>();
        Bar bar = new Bar();
        bar.i = 1;
        foo.value = bar;

        String json = gson.toJson(foo); // May not serialize foo.value correctly
        System.out.println(json);
        Foo<Bar> obj = gson.fromJson(json, foo.getClass()); // Fails to deserialize foo.value as Bar
        System.out.println(obj.value.i);
    }
}

class Foo<T> {
    public T value;
}

class Bar {
    public int i;
}
```

上面的代码无法将值解释为 `Bar`类，因为`Gson`调用`foo.getClass()`来获取其类信息，但是此方法返回的是原始类`Foo.class`。 这意味着`Gson`无法知道这是`Foo<Bar>`类型的对象，而不仅仅是纯`Foo`。

得到的 `obj.value` 的类型是 `com.google.gson.internal.LinkedTreeMap` 类型，而不是`Bar`类型。

可以通过`TypeToken`类为泛型类型指定正确的参数化类型来解决此问题。

```java
public class Test {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Foo<Bar> foo = new Foo<Bar>();
        Bar bar = new Bar();
        bar.i = 1;
        foo.value = bar;

        Type fooType = new TypeToken<Foo<Bar>>() {
        }.getType();

        String json = gson.toJson(foo, fooType); // May not serialize foo.value correctly
        System.out.println(json);
        Foo<Bar> obj = gson.fromJson(json, fooType); // Fails to deserialize foo.value as Bar
        System.out.println(obj.value.i);
    }
}

class Foo<T> {
    public T value;
}

class Bar {
    public int i;
}
```

注：`new TypeToken<Foo<Bar>>() {}` 是匿名子类，创建一个继承`TypeToken<Foo<Bar>>`的子类。

## 任意类型容器的序列化与反序列化

```java
public class Test {
    static class Event {
        private String name;
        private String source;

        private Event(String name, String source) {
            this.name = name;
            this.source = source;
        }

        @Override
        public String toString() {
            return "Event [name=" + name + ", source=" + source + "]";
        }
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        Collection<Object> collection = new ArrayList<>();
        collection.add("hello");
        collection.add(5);
        collection.add(new Event("GREETINGS", "guest"));
        String json = gson.toJson(collection);
        System.out.println("Using Gson.toJson() on a raw collection: " + json);

        JsonArray array = JsonParser.parseString(json).getAsJsonArray();
        String message = gson.fromJson(array.get(0), String.class);
        int number = gson.fromJson(array.get(1), int.class);
        Event event = gson.fromJson(array.get(2), Event.class);
        System.out.printf("Using Gson.fromJson() to get: %s, %d, %s", message, number, event);
    }
}
```