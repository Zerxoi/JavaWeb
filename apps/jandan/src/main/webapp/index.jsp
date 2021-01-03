<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ page import="xyz.zerxoi.service.BaseService" %>

<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<base href="<%= basePath %>">
<link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="static/style.css">
<script type="text/javascript" src="static/jquery-3.5.1.min.js"></script>
<script>
    function getPage() {
        $.ajax({
            type: "get",
            url: "<%= basePath %>" + "page.jsp",
            success: function (data) {
                $("body").html(data);
            }
        });
    }

    $(document).ready(function () {
        getPage();

        setInterval(() => {
            getPage();
        }, <%= BaseService.NEXT_INTERVAL %>);
    })
</script>
<html>

<body>

    <div class="page"></div>
</body>

</html>