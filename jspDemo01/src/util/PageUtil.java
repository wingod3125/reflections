package util;

import java.util.List;

/**
 * ��ҳ�������������,��װ��ҳ����Ҫ������
 * 
 * @author Administrator
 * @param <T>��ʾ����
 * 
 */
public class PageUtil<T> {
    private int pageIndex;// ��ǰҳ
    private int pageSize;// ÿҳ��С
    private int pageCount;// ��ҳ��
    private int pageNumber;// �ܼ�¼��
    private List<T> list;// ҳ���е�����

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
