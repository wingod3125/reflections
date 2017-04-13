package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �������������̨ǰ�ж��û��Ƿ��¼��û������ת����¼ҳ��
 */
@WebFilter("/PassFilter")
public class PassFilter implements Filter {

    /**
     * �ж��û��Ƿ��¼�����û�е���
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // ǿ��ת��requestΪ����
        HttpServletRequest hsRequest = (HttpServletRequest) request;
        HttpServletResponse hsResponse = (HttpServletResponse) response;
        // ���Session��û�б��浽�û��ĵ�¼��Ϣ�����ص�¼����
        if (hsRequest.getSession().getAttribute("name") == null) {
            hsResponse.sendRedirect("../login.jsp");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
