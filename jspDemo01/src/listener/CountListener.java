package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器，用来统计在线人数
 * 
 * @author Administrator
 * 
 */
public class CountListener implements HttpSessionListener {
    // 创建变量来存储在线人数
    private static int count = 0;

    // 创建Session时触发
    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        count++;
    }

    // 销毁Session时触发
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        count--;
    }

    // 返回在线的人数
    public static int getCount() {
        return count;
    }
}
