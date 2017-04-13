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

import util.PageUtil;
import biz.GradeBiz;
import biz.StudentBiz;
import biz.impl.GradeBizImpl;
import biz.impl.StudentBizImpl;
import entity.Grade;
import entity.Student;

/**
 * Servlet implementation class StudentSer
 */
@WebServlet("/StudentSer")
public class StudentSer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // 跳转执行的是get请求
        String tag = request.getParameter("tag");
        StudentBiz biz = new StudentBizImpl();
        if (tag != null && tag.equals("del")) {
            int id = Integer.parseInt(request.getParameter("id"));
            new StudentBizImpl().delete(id);
        } else if (tag != null && tag.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = biz.findById(id);
            request.setAttribute("student", student);

            GradeBiz gbiz = new GradeBizImpl();
            List<Grade> list = gbiz.find();
            request.setAttribute("gradeList", list);

            request.getRequestDispatcher("student/editStudent.jsp").forward(
                    request, response);
            return;
        }
        // 执行显示所有学生的信息
        int pageIndex = 1;
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            pageIndex = pageIndex <= 0 ? 1 : pageIndex;
        }
        int pageSize = 5;
        PageUtil<Student> pu = biz.getStudentPage(pageIndex, pageSize);
        request.setAttribute("pu", pu);

        request.getRequestDispatcher("student/studentList.jsp").forward(
                request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("tag") != null) {
            String tag = request.getParameter("tag");
            if (tag.equals("edit")) {
                Student student = new Student();
                int studentNo = Integer.parseInt(request
                        .getParameter("studentNo"));
                student.setStudentNo(studentNo);
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

                new StudentBizImpl().update(student);
                response.sendRedirect("StudentSer");
            }
        } else {
            StudentBiz biz = new StudentBizImpl();
            // 表单提交指定post请求
            String sex = request.getParameter("sex");// 获取性别信息
            System.out.println(sex);
            // 调用后台方法取数据
            List<Student> list = new StudentBizImpl().procFindBySex(sex);
            request.setAttribute("list", list);
            int count = biz.getStudentCount();
            request.setAttribute("count", count);
            request.getRequestDispatcher("student/studentList.jsp").forward(
                    request, response);
        }
    }

}
