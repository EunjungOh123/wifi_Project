<%@ page import="DB.HistoryDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Service.History" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%
    HistoryDB historyDB = new HistoryDB();
    ArrayList<History> list = historyDB.bringList();
%>
<head>
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>위치 히스토리 목록</title>
</head>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/"><b>공공 와이파이 서비스</b></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link" href="/">홈</a>
                    <a class="nav-link" href="history.jsp">위치 히스토리 목록</a>
                    <a class="nav-link" href="dataGet.jsp">Open Api 와이파이 정보 가져오기</a>
                </div>
            </div>
        </div>
    </nav>
</header>
<body>
<table class="table table-hover">
    <thead class="table-light" style="text-align:center;">
    <tr>
        <th scope="col"><span style="font-size: 11pt;">ID</span></th>
        <th scope="col"><span style="font-size: 11pt;">X좌표</span></th>
        <th scope="col"><span style="font-size: 11pt;">Y좌표</span></th>
        <th scope="col"><span style="font-size: 11pt;">조회일자</span></th>
        <th scope="col"><span style="font-size: 11pt;">비고</span></th>
    </tr>
    </thead>
    <tbody class="table-group-divider" style="text-align:center; vertical-align: middle">
    <tr id="history_list">
        <% for (int i = 0; i < list.size(); i++) {
            History history = list.get(i);
        %>
        <td><span style="font-size: 12pt;"><%=history.getID()%></span></td>
        <td><span style="font-size: 12pt;"><%=history.getX좌표()%></span></td>
        <td><span style="font-size: 12pt;"><%=history.getY좌표()%></span></td>
        <td><span style="font-size: 12pt;"><%=history.get조회일자()%></span></td>
        <td>
            <button type="button" class="btn btn-outline-dark btn-sm"
                    onclick="location.href='DeleteHistory?ID=<%=history.getID()%>'">삭제
            </button>
        </td>
    </tr>
    <% }
    %>
    </tbody>
</table>
<br>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>
