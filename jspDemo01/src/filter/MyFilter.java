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
 * 过滤器，实现设置请求和响应的字符编码的功能
 */
@WebFilter("/MyFilter")
public class MyFilter implements Filter {

  
    /**
     * 过滤器销毁的方法，在程序关闭时会自动调用
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     *  这个方法就是过滤器实现功能的代码编写的方法
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // 下面这句代码非常关键,通知下一个过滤器，把请求发送到下一个过滤器如果有的话
        chain.doFilter(request, response);
    }

    /**
     *  过滤器在创建实例时初始化数据的方法，过滤器一创建，也像Servlet一样，一直驻留在服务器端，知道程序关闭
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
