<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ page import="xyz.zerxoi.*, java.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
    <%
    request.setAttribute("key", "value");
    %>
    <h2>&lt;% request.setAttribute("key", "value"); %&gt;</h2>
    <br />
    <h2>JSP 脚本 &lt;%= request.getAttribute("key") %&gt;: <%= request.getAttribute("key") %></h2>
    <h2>EL表达式 &dollar;{ key }: ${ key } </h2>
    <br />
    <h2>JSP 脚本 &lt;%= request.getAttribute("key1") %&gt;: <%= request.getAttribute("key1") %></h2>
    <h2>EL表达式 &dollar;{ key1 }: ${ key1 } </h2>

    <%
        JavaBean bean = new JavaBean();
        request.setAttribute("bean", bean);
        bean.setName("Tohru");
        bean.setAge(3000);
        String[] interests = new String[] {"cpp", "java", "golang"};
        bean.setInterests(interests);
        List<String> list = new ArrayList<>();
        list.add("orz");
        list.add("mdzz");
        list.add("wdnmd");
        bean.setList(list);
        Map<String, String> map = new HashMap<>();
        map.put("u1s1", "有一说一");
        map.put("yyds", "永远的神");
        map.put("yygq", "阴阳怪气");
        bean.setMap(map);
    %>
    &dollar;{ bean }: ${ bean } <br />
    &dollar;{ bean.age }: ${ bean.age } <br />
    &dollar;{ bean.name }: ${ bean.name } <br />
    &dollar;{ bean.interests }: ${ bean.interests } <br />
    &dollar;{ bean.interests[1] }: ${ bean.interests[1] } <br />
    &dollar;{ bean.list }: ${ bean.list } <br />
    &dollar;{ bean.list[1] }: ${ bean.list[1] } <br />
    &dollar;{ bean.map }: ${ bean.map } <br />
    &dollar;{ bean.map.u1s1 }: ${ bean.map.u1s1 } <br />
    &dollar;{ bean.map["u1s1"] }: ${ bean.map["u1s1"] } <br />
    &dollar;{ bean.x }: ${ bean.x } <br />
    <hr />
    <%
    // 1. 值为 null
    pageContext.setAttribute("emptyNull", null);
    // 2. 空串
    pageContext.setAttribute("emptyStr", "");
    // 3. 空数组
    pageContext.setAttribute("emptyArray1", new Object[]{});
    pageContext.setAttribute("emptyArray2", new Object[3]);
    pageContext.setAttribute("emptyArray3", new Integer[]{});
    pageContext.setAttribute("emptyArray4", new int[]{});
    pageContext.setAttribute("emptyArray5", new int[3]);
    // 4. 空List
    pageContext.setAttribute("emptyList", new ArrayList<String>());
    // 5. 空Map
    pageContext.setAttribute("emptyMap", new HashMap<String, String>());
    %>

    ${empty emptyNull } <%-- true --%>
    ${empty emptyStr } <%-- true --%>
    ${empty emptyArray1 } <%-- true --%>
    ${empty emptyArray2 } <%-- false --%>
    ${empty emptyArray3 } <%-- true --%>
    ${empty emptyArray4 } <%-- false --%>
    ${empty emptyArray5 } <%-- false --%>
    ${empty emptyList } <%-- true --%>
    ${empty emptyMap } <%-- true --%>
    <hr />
    &dollar;{ requestScope.set }:

    <c:if test="${ empty requestScope.set }"> null </c:if> <br />
    <c:set scope="request" var="set" value="c:set" />
    执行 &lt;c:set scope="request" var="set" value="c:set" /&gt;<br />
    &dollar;{ requestScope.set }: ${ requestScope.set } <br />

    <hr />
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

    <hr />

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

    <hr />
    <%
        pageContext.setAttribute("arr", new String[] {"wdnmd", "yyds", "u1s1"});
    %>
    <c:forEach items="${ pageScope.arr }" var="item">
        ${ item } <br />
    </c:forEach>

    <hr />
    <%
        request.setAttribute("map", map);
    %>
    <c:forEach items="${ requestScope.map }" var="entry">
        ${ entry.key }=${ entry.value } <br />
    </c:forEach>

    <hr />
    <%
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "石原里美", 26, "shiyuanlimei", "10010"));
        userList.add(new User(2, "桥本环奈", 23, "qiaobenhuannai", "10086"));
        userList.add(new User(3, "新垣结衣", 28, "xinyuanjieyi", "10000"));
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

</body>

</html>