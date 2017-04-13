package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.AdminBiz;
import biz.GradeBiz;
import biz.StudentBiz;
import biz.impl.AdminBizImpl;
import biz.impl.GradeBizImpl;
import biz.impl.StudentBizImpl;
import entity.Admin;
import entity.Grade;
import entity.Student;
/**
 * 用于执行一些账户操作
 * @author Administrator
 *
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Account() {
    }

    // GET请求时执行的方法
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // GET请求时把请求转交给POST方法来处理
        doPost(request, response);
    }

    // POST请求时执行的方法
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        /*
         * 通过过滤器实现 request.setCharacterEncoding("utf-8");
         * response.setCharacterEncoding("utf-8");
         */
        // 通过response对象获取out对象
        // PrintWriter out = response.getWriter();
        String tag = request.getParameter("tag");
        if (tag.equals("log")) {
            String userName = request.getParameter("userName");
            String pwd = request.getParameter("pwd");
            AdminBiz biz = new AdminBizImpl();
            if (biz.login(userName, pwd) > 0) {
                String saveAge = request.getParameter("saveAge");
                if (saveAge != null) {
                    int i = Integer.parseInt(saveAge);
                    Cookie ckname = new Cookie("name", userName);
                    // 设置过期时间，单位是秒
                    ckname.setMaxAge(60 * i);
                    Cookie ckpwd = new Cookie("pwd", pwd);
                    ckpwd.setMaxAge(60 * i);
                    // 写入到客户端
                    response.addCookie(ckname);
                    response.addCookie(ckpwd);
                }
                // 获取Session
                request.getSession().setAttribute("name", userName);
                response.sendRedirect("index.jsp");
            } else {
                // 登录不成功
                request.setAttribute("name", userName);
                request.setAttribute("message", "用户名或密码错误，请重新输入");
                request.getRequestDispatcher("login.jsp").forward(request,
                        response);
            }
        } else if (tag.equals("exit")) {
            request.getSession().invalidate();// 销毁session
            response.sendRedirect("index.jsp");
        } else if (tag.equals("register")) {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            AdminBiz biz = new AdminBizImpl();
            if (biz.isUsedUserName(userName)) {
                request.setAttribute("name", userName);
                request.setAttribute("message", "此用户名以被占用，请换个用户名");
                request.getRequestDispatcher("register.jsp").forward(request,
                        response);
            } else {
                Admin admin = new Admin();
                admin.setUserName(userName);
                admin.setPassword(password);
                int result = biz.save(admin);
                if (result > 0) {
                    response.sendRedirect("login.jsp");
                }
            }
        } else if (tag.equals("getGrade")) {
            GradeBiz biz = new GradeBizImpl();
            List<Grade> list = biz.procFind();
            request.getSession().setAttribute("gradeList", list);
            response.sendRedirect("student/addStudent.jsp");
        } else if (tag.equals("addStudent")) {
            Student student = new Student();
            student.setStudentName(request.getParameter("name"));
            student.setSex(request.getParameter("sex"));
            student.setTelephone(request.getParameter("phone"));
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sdf.parse(request.getParameter("birthday"));
                student.setBirthday(d);
            } catch (ParseException e) {
                student.setBirthday(null);
                e.printStackTrace();
            }
            student.setAddress(request.getParameter("address"));
            student.setEmail(request.getParameter("email"));
            Grade grade = new Grade();
            grade.setGid(Integer.parseInt(request.getParameter("grade")));
            student.setGrade(grade);
            StudentBiz biz = new StudentBizImpl();
            if (biz.add(student) > 0) {
                response.sendRedirect("student/studentList.jsp");
            }
        }
    }

}
