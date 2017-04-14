package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import entity.City;

/**
 * Servlet implementation class ProvinceSer
 */
@WebServlet("/ProvinceSer")
public class ProvinceSer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String gid = request.getParameter("gid");
        // �����ݿ�ȡ��ʡ�ݶ�Ӧ�ĳ��м���
        List<City> list = new ArrayList<>();
        if (gid.equals("1")) {
            list.add(new City(10, "����"));
            list.add(new City(11, "����"));
            list.add(new City(12, "�麣"));
            list.add(new City(13, "ï��"));
        } else if (gid.equals("2")) {
            list.add(new City(20, "����"));
            list.add(new City(21, "����"));
            list.add(new City(22, "����"));
        } else if (gid.equals("3")) {
            list.add(new City(30, "��ɳ"));
            list.add(new City(31, "��̶"));
            list.add(new City(32, "����"));
            list.add(new City(33, "�żҽ�"));
        } else if (gid.equals("4")) {
            list.add(new City(40, "�人"));
            list.add(new City(41, "��ʯ"));
            list.add(new City(42, "��ʩ"));
            list.add(new City(43, "����"));
        } else if (gid.equals("5")) {
            list.add(new City(50, "������"));
            list.add(new City(51, "������"));
            list.add(new City(52, "��̨��"));
            list.add(new City(53, "��ƽ��"));
        }

        response.setContentType("text/html;charset=utf-8");
        // �Ѽ��϶����װJSON���󣬷��͵�jspҳ����
        try {
            JSONArray ja = JSONArray.fromObject(list);
            JSONObject jo = new JSONObject();
            jo.put("city", ja);
            response.getWriter().write(jo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
