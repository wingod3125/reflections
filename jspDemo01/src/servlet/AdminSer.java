package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.AdminBiz;
import biz.impl.AdminBizImpl;

/**
 * Servlet implementation class AdminSer
 */
@WebServlet("/AdminSer")
public class AdminSer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        AdminBiz biz = new AdminBizImpl();
        if (biz.isUsedUserName(name)) {
            // 用户名被占用
            response.getWriter().write("n");
        } else {
            // 添加新用户
            response.getWriter().write("y");
        }
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
