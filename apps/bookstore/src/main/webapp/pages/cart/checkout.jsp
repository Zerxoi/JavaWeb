<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
<%@ include file="/pages/comm/head.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<%@ include file="/pages/comm/login_success_menu.jsp" %>

	
	<div id="main">
		
		<h1>你的订单已结算，订单号为${ sessionScope.orderId }</h1>
		
	
	</div>
	
	<%@ include file="/pages/comm/footer.jsp" %>

</body>
</html>