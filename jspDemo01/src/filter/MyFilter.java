package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * ��������ʵ�������������Ӧ���ַ�����Ĺ���
 */
@WebFilter("/MyFilter")
public class MyFilter implements Filter {

  
    /**
     * ���������ٵķ������ڳ���ر�ʱ���Զ�����
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     *  ����������ǹ�����ʵ�ֹ��ܵĴ����д�ķ���
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // ����������ǳ��ؼ�,֪ͨ��һ�����������������͵���һ������������еĻ�
        chain.doFilter(request, response);
    }

    /**
     *  �������ڴ���ʵ��ʱ��ʼ�����ݵķ�����������һ������Ҳ��Servletһ����һֱפ���ڷ������ˣ�֪������ر�
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
