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
        // 从数据库取与省份对应的城市集合
        List<City> list = new ArrayList<>();
        if (gid.equals("1")) {
            list.add(new City(10, "广州"));
            list.add(new City(11, "深圳"));
            list.add(new City(12, "珠海"));
            list.add(new City(13, "茂名"));
        } else if (gid.equals("2")) {
            list.add(new City(20, "南宁"));
            list.add(new City(21, "柳州"));
            list.add(new City(22, "桂林"));
        } else if (gid.equals("3")) {
            list.add(new City(30, "长沙"));
            list.add(new City(31, "湘潭"));
            list.add(new City(32, "株洲"));
            list.add(new City(33, "张家界"));
        } else if (gid.equals("4")) {
            list.add(new City(40, "武汉"));
            list.add(new City(41, "黄石"));
            list.add(new City(42, "恩施"));
            list.add(new City(43, "仙桃"));
        } else if (gid.equals("5")) {
            list.add(new City(50, "朝阳区"));
            list.add(new City(51, "海淀区"));
            list.add(new City(52, "丰台区"));
            list.add(new City(53, "昌平区"));
        }

        response.setContentType("text/html;charset=utf-8");
        // 把集合对象封装JSON对象，发送到jsp页面上
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
