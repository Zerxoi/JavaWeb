<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>购物车</title>
	<%@ include file="/pages/comm/head.jsp" %>
	<script>
		$(function () {
			$(".cart_delete").click(function () {
				return confirm("是否删除 " + $(this).parent().parent().find("td").first().text() + " ？");
			})

			$("cart_clear").click(function () {
				return confirm("是否清空购物车？")
			})

			$(".updateCount").change(function () {
				// 因为 <intput> 是 HTMLInputElement，可以通过 value 属性访问表单值
				// https://developer.mozilla.org/en-US/docs/Web/API/HTMLInputElement
				var count =  this.value;
				// Element 可以通过 getAttribute 获取自定义属性
				var id = this.getAttribute("bookId");
				if (confirm("是否修改 "+ $(this).parent().parent().find("td").first().text() + "数量为 " + count + " ?")) {
					location.href = "${ pageScope.contextPath }cartServlet?action=updateCount&id=" + id + "&count=" + count;
				} else {
					// defaultValue 也是 HTMLInputElement 的属性之一，该默认值最初是在创建该对象的HTML中指定的
					this.value = this.defaultValue;
				}
			})
		})
	</script>
</head>

<body>

	<%@ include file="/pages/comm/login_success_menu.jsp" %>


	<div id="main">

		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${ empty sessionScope.cart.items }">
				<td colspan="5"> 当前购物车为空 </td>
			</c:if>
			<c:forEach items="${ sessionScope.cart.items }" var="entry">
				<tr>
					<td>${ entry.value.name }</td>
					<td>
						<input type="text" class="updateCount" value="${ entry.value.count }" bookId="${ entry.value.id }">
					</td>
					<td>${ entry.value.price }</td>
					<td>${ entry.value.totalPrice }</td>
					<td><a  class="cart_delete" href="cartServlet?action=delete&id=${ entry.value.id }">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${ not empty sessionScope.cart.items }">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${ sessionScope.cart.totalCount }</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${ sessionScope.cart.totalPrice }</span>元</span>
				<span class="cart_span"><a  class="cart_clear" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=create">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%@ include file="/pages/comm/footer.jsp" %>

</body>

</html>