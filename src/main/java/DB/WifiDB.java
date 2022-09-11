package DB;

import Service.Wifi;

import java.sql.*;
import java.util.ArrayList;

public class WifiDB {

    static final String url = "jdbc:mariadb://localhost:3306/wifidb?useUnicode=true&characterEncoding=UTF8";
    static final String dbUserID = "testuser1";
    static final String dbPassWord = "dhdmswjd";

    public WifiDB() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Wifi> nearDistanceWifi (double lat, double lnt) { // lnt(X좌표) : 경도, lat(Y좌표) : 위도
        ArrayList<Wifi> nearList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassWord);
            String sql = " SELECT (6371*acos(cos(radians(?))*cos(radians(`Y좌표`))*cos(radians(`X좌표`) " +
                    " -radians(?))+sin(radians(?))*sin(radians(`Y좌표`)))) AS 거리 " +
                    " ,wf.* " +
                    " FROM wifi_info wf " +
                    " ORDER BY 거리 " +
                    " limit 20 ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, lat);
            preparedStatement.setDouble(2, lnt);
            preparedStatement.setDouble(3, lat);
            rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Wifi wifi = new Wifi();
                wifi.setDistance(rs.getDouble("거리"));
                wifi.set관리번호(rs.getString("관리번호"));
                wifi.set자치구(rs.getString("자치구"));
                wifi.set와이파이명(rs.getString("와이파이명"));
                wifi.set도로명주소(rs.getString("도로명주소"));
                wifi.set상세주소(rs.getString("상세주소"));
                wifi.set설치위치 (rs.getString("설치위치"));
                wifi.set설치유형(rs.getString("설치유형"));
                wifi.set설치기관(rs.getString("설치기관"));
                wifi.set서비스구분(rs.getString("서비스구분"));
                wifi.set망종류(rs.getString("망종류"));
                wifi.set설치년도(rs.getString("설치년도"));
                wifi.set실내외구분(rs.getString("실내외구분"));
                wifi.set접속환경(rs.getString("접속환경"));
                wifi.setX좌표(rs.getDouble("X좌표"));
                wifi.setY좌표(rs.getDouble("Y좌표"));
                wifi.set작업일자(rs.getString("작업일자"));
                nearList.add(wifi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null &&!preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nearList;
    }

    public void wifiInfoInsert (ArrayList<Wifi> list) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassWord);
            connection.setAutoCommit(false);

            String sql = " insert into wifi_info( `관리번호`,`자치구`, `와이파이명`, `도로명주소`,`상세주소` " +
                    " ,`설치위치`,`설치유형`,`설치기관`,`서비스구분`,`망종류`,`설치년도`,`실내외구분`,`접속환경`,`X좌표`,`Y좌표`,`작업일자`) " +
                    " values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,?) ";

            preparedStatement = connection.prepareStatement(sql);

            // 대용량 데이터를 빠르게 처리할 수 있는 addBatch 사용 (한 번에 송신해서 실행함)
            int count = 0;
            for(Wifi wifi : list) {
                preparedStatement.setString(1, wifi.get관리번호());
                preparedStatement.setString(2, wifi.get자치구());
                preparedStatement.setString(3, wifi.get와이파이명());
                preparedStatement.setString(4, wifi.get도로명주소());
                preparedStatement.setString(5, wifi.get상세주소());
                preparedStatement.setString(6, wifi.get설치위치());
                preparedStatement.setString(7, wifi.get설치유형());
                preparedStatement.setString(8, wifi.get설치기관());
                preparedStatement.setString(9, wifi.get서비스구분());
                preparedStatement.setString(10, wifi.get망종류());
                preparedStatement.setString(11, wifi.get설치년도());
                preparedStatement.setString(12, wifi.get실내외구분());
                preparedStatement.setString(13, wifi.get접속환경());
                preparedStatement.setDouble(14, wifi.getX좌표());
                preparedStatement.setDouble(15, wifi.getY좌표());
                preparedStatement.setString(16, wifi.get작업일자());

                count++;

                preparedStatement.addBatch();
                preparedStatement.clearParameters();

                if(count % 1000 == 0) { // 1000건 단위로 실시
                    preparedStatement.executeBatch(); // 배치 실행
                    preparedStatement.clearBatch(); // 배치 초기화
                    connection.commit();
                }
            }
            // 남은 쿼리 처리
            preparedStatement.executeBatch(); // 배치 실행
            preparedStatement.clearBatch(); // 배치 초기화
            connection.commit();

            System.out.println("데이터 저장을 성공했습니다");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
