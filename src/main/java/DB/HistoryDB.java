package DB;

import Service.History;

import java.sql.*;
import java.util.ArrayList;

public class HistoryDB {

    static final String url = "jdbc:mariadb://localhost:3306/wifidb?useUnicode=true&characterEncoding=UTF8";
    static final String dbUserID = "testuser1";
    static final String dbPassWord = "dhdmswjd";


    public HistoryDB() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<History> bringList () {
        ArrayList<History> historyList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassWord);
            String sql = " select * " + " from history " + " order by `ID` desc ";
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                History history = new History();
                history.setID(rs.getInt("ID"));
                history.setX좌표(rs.getDouble("X좌표"));
                history.setY좌표(rs.getDouble("Y좌표"));
                history.set조회일자(rs.getString("조회일자"));
                historyList.add(history);
            }
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
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return historyList;
    }

    public int DeleteHistory (int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int successCheck = 0;

        try{
            connection = DriverManager.getConnection(url, dbUserID, dbPassWord);
            String sql = " delete from history where `ID`=? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            successCheck = preparedStatement.executeUpdate();
            if(successCheck>0) {
                System.out.println("위치 정보 삭제에 성공하였습니다.");
            } else {
                System.out.println("위치 정보 삭제에 실패하였습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return successCheck;
    }

    public void HistoryInfoInsert(double latitude_Y, double longitude_X) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassWord);
            String sql = " insert into history(`ID`,`X좌표`,`Y좌표`, `조회일자`) " +
                    " values(?,?,?,now()) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, getIdNum());
            preparedStatement.setDouble(2, longitude_X);
            preparedStatement.setDouble(3, latitude_Y);

            int successCheck = preparedStatement.executeUpdate();
            if(successCheck>0) {
                System.out.println("위치 정보 저장에 성공하였습니다.");
            } else {
                System.out.println("위치 정보 저장에 실패하였습니다.");
            }

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
    public int getIdNum () { // ID = db에 올라가는 순서대로 번호 가짐
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(url, dbUserID, dbPassWord);
            String sql = " select `ID` " + " from history " + " order by `ID` desc "; //  내림차순 정렬  > 게시된 목록의 가장 최근 ID 가져옴
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return rs.getInt("ID")+1; // getInt : 지정한 column 값을 int 타입으로 반환
            }
            return 1; // db에 처음 올라가는 경우 (현재 table 비어있는 경우)
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
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1; // 오류가 발생한 경우
    }
}
