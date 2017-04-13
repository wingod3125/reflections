package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUpLoadSer
 */
@WebServlet("/FileUpLoadSer")
public class FileUpLoadSer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();// 向客户端写出
        // 判断是不是 enctype="multipart/form-data"
        boolean bool = ServletFileUpload.isMultipartContent(request);
        String path = "D:/userFile";// 取到服务器的路径
        if (bool) { // 表示该表单需要文件上传
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(factory);
            sfu.setSizeMax(1024 * 1024 * 4);// 设置文件上传的大小，只能传最多4M的文件
            try {
                // 取到所有的FileItem对象
                List<FileItem> list = sfu.parseRequest(request);
                int count = 0;
                // isFormField()方法，循环判断FileItem对象是不是文件域
                for (FileItem fileItem : list) {
                    // 判断表单元素是不是文件域，如果是文件域，返回的是false
                    if (!fileItem.isFormField()) {
                        // 取到文件名
                        String fileName = fileItem.getName();
                        // 把服务器路径+文件名
                        String pathFileName = path + "/" + fileName;
                        // 上传
                        File f = new File(pathFileName);
                        fileItem.write(f);
                        count++;
                    }
                }
                out.print("<script>alert('" + count
                        + "个文件上传成功！');location.href='fileUpLoad.jsp';</script>");
            } catch (Exception e) {
                out.print("<script>alert('文件只能传最多4M大小！');location.href='fileUpLoad.jsp';</script>");
                e.printStackTrace();
            }

        }
    }

}
