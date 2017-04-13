package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.GradeBiz;
import biz.StudentBiz;
import biz.impl.GradeBizImpl;
import biz.impl.StudentBizImpl;
import entity.Grade;
import entity.Student;

/**
 * Servlet implementation class Grade
 */
@WebServlet("/Grade")
public class RegisterSer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        GradeBiz biz = new GradeBizImpl();
        // 取得所有的年级信息
        List<Grade> list = biz.find();
        request.setAttribute("gradeList", list);
        // 转发到addStudent.jsp页面
        request.getRequestDispatcher("student/addStudent.jsp").forward(request,
                response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
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
        // 封装年级对象
        Grade grade = new Grade();
        grade.setGid(Integer.parseInt(request.getParameter("grade")));
        student.setGrade(grade);
        StudentBiz biz = new StudentBizImpl();
        if (biz.add(student) > 0) {
            response.sendRedirect("StudentSer");
        } else {
            request.getRequestDispatcher("student/addStudent.jsp").forward(
                    request, response);
        }
    }

}
