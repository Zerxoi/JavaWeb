<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">结算</span>
    <div>
        <span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临尚硅谷书城</span>
        <a href="pages/order/order.jsp">我的订单</a>
        <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </div>
</div>