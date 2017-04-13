package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;

import util.PageUtil;

/**
 * ʵ�ַ�ҳ��ѯ���ܵ���
 * 
 * @author Administrator
 * 
 */
public class PageDaoImpl {
    /**
     * 
     * @param sql
     *            ���ڻ�ȡ�ܼ�¼��
     * @param sql1
     *            ����ȡ��ҳ�������
     * @param pageIndex
     *            ��ǰҳ
     * @param pageSize
     *            ÿҳ��С
     * @return ��װ������Ϣ�ķ�ҳ��
     */

    public PageUtil<Object> getPage(String sql, String sql1, int pageIndex,
            int pageSize) {
        PageUtil<Object> pu = new PageUtil<Object>();
        List<Object> list = new ArrayList<>();
        Connection conn = BaseDao.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                pu.setPageNumber(rs.getInt(1));
            }
            st = conn.prepareStatement(sql1);
            st.setInt(1, (pageIndex - 1) * pageSize);
            st.setInt(2, pageSize);
            rs = st.executeQuery();
            while (rs.next()) {
                List<Object> line = new ArrayList<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    line.add(rs.getString(i));
                }
                list.add(line);
            }
            pu.setList(list);
            pu.setPageIndex(pageIndex);
            pu.setPageSize(pageSize);
            // ������ҳ��
            int pageCount = (int) Math.ceil(pu.getPageNumber()
                    / (double) pageSize);
            pu.setPageCount(pageCount);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }

        return pu;

    }
}
