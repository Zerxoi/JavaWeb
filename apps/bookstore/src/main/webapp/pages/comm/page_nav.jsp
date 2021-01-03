<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<div id="page_nav">
    <c:if test="${ requestScope.page.page > 1 }">
        <a href="${ requestScope.page.url }&page=1">首页</a>
        <a href="${ requestScope.page.url }&page=${ requestScope.page.page - 1 }">上一页</a>
    </c:if>
    <c:choose>
        <c:when test="${ requestScope.page.page < 3 }">
            <c:set var="begin" value="1" />
            <c:set var="end" value="${ requestScope.page.pageTotal < 5 ? requestScope.page.pageTotal : 5 }" />
        </c:when>
        <c:when test="${ requestScope.page.page > requestScope.page.pageTotal - 2 }">
            <c:set var="begin" value="${ requestScope.page.pageTotal - 4 > 1 ? requestScope.page.pageTotal - 4 : 1 }" />
            <c:set var="end" value="${ requestScope.page.pageTotal }" />
        </c:when>
        <c:otherwise>
            <c:set var="begin" value="${ requestScope.page.page - 2 }" />
            <c:set var="end" value="${ requestScope.page.page + 2 }" />
        </c:otherwise>
    </c:choose>
    <c:forEach begin="${ pageScope.begin }" end="${ requestScope.page.page - 1 }" var="i">
        <a href="${ requestScope.page.url }&page=${ i }">${ i }</a>
    </c:forEach>
    [${ requestScope.page.page }]
    <c:forEach begin="${ requestScope.page.page + 1 }" end="${ pageScope.end }" var="i">
        <a href="${ requestScope.page.url }&page=${ i }">${ i }</a>
    </c:forEach>
    <c:if test="${ requestScope.page.page < requestScope.page.pageTotal }">
        <a href="${ requestScope.page.url }&page=${ requestScope.page.page + 1 }">下一页</a>
        <a href="${ requestScope.page.url }&page=${ requestScope.page.pageTotal }">末页</a>
    </c:if>
    共${ requestScope.page.pageTotal }页，${ requestScope.page.total }条记录
    到第<input value="${ requestScope.page.page }" id="pn_input" />页
    <input id="page_btn" type="button" value="确定">
</div>

<script>
    $(function () {
        $("#page_btn").click(function () {
            var page = $("#pn_input").val();
            if (page < 1 || page > ${ requestScope.page.pageTotal }) {
                alert("页码 [" + page + "] 不合法");
                return false;
            }
            location.href = "${ pageScope.basePath }${ requestScope.page.url }&page=" + page;
        });
    });
</script>