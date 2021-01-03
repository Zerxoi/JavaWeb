<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>

<body>
	<%
		Locale locale = null;
		String localeParam = request.getParameter("locale");
		if ("zh_CN".equals(localeParam)) {
			locale = Locale.CHINA;
		} else if ("en_US".equals(localeParam)) {
			locale = Locale.US;
		} else {
			locale = request.getLocale();
		}
		ResourceBundle i18n = ResourceBundle.getBundle("i18n", locale);
	%>

	<a href="i18n.jsp?locale=zh_CN">中文</a>|
	<a href="i18n.jsp?locale=en_US">English</a>
	<center>
		<h1><%=i18n.getString("regist")%></h1>
		<table>
			<form>
				<tr>
					<td><%=i18n.getString("username")%></td>
					<td><input name="username" type="text" /></td>
				</tr>
				<tr>
					<td><%=i18n.getString("password")%></td>
					<td><input type="password" /></td>
				</tr>
				<tr>
					<td><%=i18n.getString("sex")%></td>
					<td>
						<input type="radio" /><%=i18n.getString("male")%>
						<input type="radio" /><%=i18n.getString("female")%>
					</td>
				</tr>
				<tr>
					<td><%=i18n.getString("email")%></td>
					<td><input type="text" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="reset" value="<%=i18n.getString("reset")%>" />&nbsp;&nbsp;
						<input type="submit" value="<%=i18n.getString("submit")%>" /></td>
				</tr>
			</form>
		</table>
		<br /> <br /> <br /> <br />
	</center>
	国际化测试：
	<br /> 1、访问页面，通过浏览器设置，请求头信息确定国际化语言。
	<br /> 2、通过左上角，手动切换语言
</body>

</html>