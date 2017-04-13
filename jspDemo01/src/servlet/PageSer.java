package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.PageUtil;

import dao.impl.PageDaoImpl;

/**
 * Servlet implementation class PageSer
 */
@WebServlet("/PageSer")
public class PageSer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        int pageIndex = 1;
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            pageIndex = pageIndex <= 0 ? 1 : pageIndex;
        }
        int pageSize = 5;
        String sql = "select count(*) from student";
        String sql1 = "select * from student limit ?,?";
        PageDaoImpl impl = new PageDaoImpl();
        PageUtil<Object> pu = impl.getPage(sql, sql1, pageIndex, pageSize);
        request.setAttribute("pu", pu);
        request.getRequestDispatcher("pageTest.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }

}
