package com.example.wifi_mission;

import DB.HistoryDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteHistory", value = "/DeleteHistory")
public class DeleteHistory extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HistoryDB historyDB = new HistoryDB();
        int number = Integer.parseInt(req.getParameter("ID"));
        int successCheck = historyDB.DeleteHistory(number);
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        if(successCheck>0) {
            System.out.println("삭제에 성공하였습니다");
            writer.println("<script>alert('위치 정보가 삭제되었습니다'); location.href='history.jsp';</script>");
        } else {
            System.out.println("삭제에 실패하였습니다");
            writer.println("<script>alert('위치 정보 삭제에 실패하였습니다');location.href='history.jsp';</script>");
        }
        writer.flush();
    }
}
