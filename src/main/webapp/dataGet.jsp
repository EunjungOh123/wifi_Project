<%@ page import="Service.WifiOpenAPI" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<% WifiOpenAPI wifiOpenAPI = new WifiOpenAPI(); %>
<head>
    <title>Wifi 데이터 가져오기</title>
</head>
<body style="text-align: center;">
<h2><%=wifiOpenAPI.WifiCall()%>개의 와이파이 정보를 정상적으로 저장하였습니다</h2>
<a class="home" href="/"><p style="text-align:center;">홈으로 가기</p></a>
</body>
</html>
