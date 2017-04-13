package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodayInHistory {
    /**
     * 发起http get请求获取网页源代码
     */
    private static String httpRequest(String requestUrl) {
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection httpUrlConn = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            // 建立连接
            URL url = new URL(requestUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");

            // 获取输入流
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            // 读取返回结果
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                httpUrlConn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    /**
     * 从html中抽取历史上今天的信息
     */
    private static List<String> extract(String html) {
        List<String> list = new ArrayList<>();
        Pattern p = Pattern
                .compile("(.*)(<ul class=\"list clearfix\">)(.*?)(</ul>)(.*)");
        Matcher m = p.matcher(html);
        if (m.matches()) {
            for (String s : m.group(3).replaceAll("</?[^>]+>", "").trim()
                    .split("  ")) {
                if (!s.equals("")) {
                    list.add(s);
                }
            }
        }
        return list;
    }

    /**
     * 返回查询到的历史上的今天的信息
     */
    public static List<String> getTodayInHistory() {
        String html = httpRequest("http://www.lssdjt.com");
        List<String> list = extract(html);
        return list;
    }
}
