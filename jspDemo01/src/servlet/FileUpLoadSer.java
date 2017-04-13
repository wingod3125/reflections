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
        PrintWriter out = response.getWriter();// ��ͻ���д��
        // �ж��ǲ��� enctype="multipart/form-data"
        boolean bool = ServletFileUpload.isMultipartContent(request);
        String path = "D:/userFile";// ȡ����������·��
        if (bool) { // ��ʾ�ñ���Ҫ�ļ��ϴ�
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(factory);
            sfu.setSizeMax(1024 * 1024 * 4);// �����ļ��ϴ��Ĵ�С��ֻ�ܴ����4M���ļ�
            try {
                // ȡ�����е�FileItem����
                List<FileItem> list = sfu.parseRequest(request);
                int count = 0;
                // isFormField()������ѭ���ж�FileItem�����ǲ����ļ���
                for (FileItem fileItem : list) {
                    // �жϱ�Ԫ���ǲ����ļ���������ļ��򣬷��ص���false
                    if (!fileItem.isFormField()) {
                        // ȡ���ļ���
                        String fileName = fileItem.getName();
                        // �ѷ�����·��+�ļ���
                        String pathFileName = path + "/" + fileName;
                        // �ϴ�
                        File f = new File(pathFileName);
                        fileItem.write(f);
                        count++;
                    }
                }
                out.print("<script>alert('" + count
                        + "���ļ��ϴ��ɹ���');location.href='fileUpLoad.jsp';</script>");
            } catch (Exception e) {
                out.print("<script>alert('�ļ�ֻ�ܴ����4M��С��');location.href='fileUpLoad.jsp';</script>");
                e.printStackTrace();
            }

        }
    }

}
