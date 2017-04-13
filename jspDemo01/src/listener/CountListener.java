package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * ������������ͳ����������
 * 
 * @author Administrator
 * 
 */
public class CountListener implements HttpSessionListener {
    // �����������洢��������
    private static int count = 0;

    // ����Sessionʱ����
    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        count++;
    }

    // ����Sessionʱ����
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        count--;
    }

    // �������ߵ�����
    public static int getCount() {
        return count;
    }
}
