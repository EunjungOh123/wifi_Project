<%@ page import="DB.WifiDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Service.Wifi" %>
<%@ page import="DB.HistoryDB" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<%
    WifiDB wifiDB = new WifiDB();
    String latitude_Y = request.getParameter("latitude_Y");
    String longitude_X = request.getParameter("longitude_X");
%>
<head>
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>공공 와이파이 서비스</title>
</head>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp"><b>공공 와이파이 서비스</b></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link" href="index.jsp">홈</a>
                    <a class="nav-link" href="history.jsp">위치 히스토리 목록</a>
                    <a class="nav-link" href="dataGet.jsp">Open Api 와이파이 정보 가져오기</a>
                </div>
            </div>
        </div>
    </nav>
</header>
<body>
<br>
<form class = "myPlace">
    &nbsp;<label>LAT: </label>
    &nbsp;<input type="text" id="latitude_Y" name="latitude_Y" class="input_form" style="vertical-align: middle"<%if(latitude_Y !=null){%>
                 value = <%=latitude_Y%>
                     <%}else{%> value = 0.0 <%}%>/>

    <label>LNT: </label>

    &nbsp;<input type="text" id="longitude_X" name="longitude_X" class="input_form" style="vertical-align: middle" <%if(longitude_X !=null){%>
                 value = <%=longitude_X%>
                     <%}else{%> value = 0.0 <%}%>/>&nbsp;

    <button type = "button" id = "locationButton" class="btn btn-outline-dark btn-sm" style="vertical-align: middle">내 위치 가져오기</button>
    <button type = "summit" class="btn btn-outline-dark btn-sm" style="vertical-align: middle">근처 WIFI 정보 보기</button>
</form>


<table class="table table-hover">
    <thead class="table-light" style="text-align:center;">
    <tr>
        <th scope="col"><span style="font-size: 11pt;">거리(km)</span></th>
        <th scope="col"><span style="font-size: 11pt;">관리번호</span></th>
        <th scope="col"><span style="font-size: 11pt;">자치구</span></th>
        <th scope="col"><span style="font-size: 11pt;">와이파이명</span></th>
        <th scope="col"><span style="font-size: 11pt;">도로명주소</span></th>
        <th scope="col"><span style="font-size: 11pt;">상세주소</span></th>
        <th scope="col"><span style="font-size: 11pt;">설치위치(층)</span></th>
        <th scope="col"><span style="font-size: 11pt;">설치유형</span></th>
        <th scope="col"><span style="font-size: 11pt;">설치기관</span></th>
        <th scope="col"><span style="font-size: 11pt;">서비스구분</span></th>
        <th scope="col"><span style="font-size: 11pt;">망종류</span></th>
        <th scope="col"><span style="font-size: 11pt;">설치년도</span></th>
        <th scope="col"><span style="font-size: 11pt;">실내외구분</span></th>
        <th scope="col"><span style="font-size: 11pt;">WIFI접속환경</span></th>
        <th scope="col"><span style="font-size: 11pt;">X좌표</span></th>
        <th scope="col"><span style="font-size: 11pt;">Y좌표</span></th>
        <th scope="col"><span style="font-size: 11pt;">작업일자</span></th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    <%
        if(latitude_Y == null && longitude_X == null) {
    %>
    <tr>
        <p>
        <td colspan="17" style="text-align:center;">위치 정보를 입력한 후에 조회해 주세요</td>
        </p>
    </tr>
    <%
    } else {
        ArrayList<Wifi> wifiList = wifiDB.nearDistanceWifi(Double.valueOf(latitude_Y), Double.valueOf(longitude_X));
        HistoryDB historyDB = new HistoryDB();
        historyDB.HistoryInfoInsert(Double.valueOf(latitude_Y), Double.valueOf(longitude_X));
        for(int i = 0; i<wifiList.size(); i++) {
            Wifi wifi = wifiList.get(i);
    %>
    <tr id = "wifi_list">
        <td><span style="font-size: 10pt;"><%=String.format("%.4f", wifi.getDistance())%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get관리번호()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get자치구()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get와이파이명()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get도로명주소()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get상세주소()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get설치위치()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get설치유형()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get설치기관()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get서비스구분()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get망종류()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get설치년도()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get실내외구분()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get접속환경()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.getX좌표()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.getY좌표()%></span></td>
        <td><span style="font-size: 10pt;"><%=wifi.get작업일자()%></span></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<script type="text/javascript">

    class WifiServiceController {
        constructor() {
            this.location = document.querySelector(".myPlace"); // get the first element with class [location_form]
            this.locationButton = document.querySelector("#locationButton"); // get the element with id [locationButton]
        }

        initWifiServiceFormController() {
            this.initGetLocationButton();
        }

        initGetLocationButton() {
            const locationButton = this.locationButton;
            locationButton.addEventListener("click", () => {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(position => {
                        alert("내 위치 정보를 불러오는데 성공했습니다.");
                        this.location["latitude_Y"].value = position.coords.latitude;
                        this.location["longitude_X"].value = position.coords.longitude;
                    })
                } else {
                    alert("내 위치 정보를 불러오는데 실패했습니다.");
                }
            });
        }
    }
    document.addEventListener("DOMContentLoaded", ()=> {
        const wifiServiceController = new WifiServiceController();
        wifiServiceController.initWifiServiceFormController();
    });

</script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>