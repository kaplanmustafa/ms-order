package com.example.msorder.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/test")
public class MyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String parameterLoc = req.getParameter("name");
        System.out.println("Servlet çalıştı " + parameterLoc);

        PrintWriter writerLoc = resp.getWriter();
        writerLoc.println("Hello test");
        resp.addHeader("Content-Type", "text/plain");
    }
}
