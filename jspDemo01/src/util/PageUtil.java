package util;

import java.util.List;

/**
 * 分页所需的数据属性,封装分页所需要的数据
 * 
 * @author Administrator
 * @param <T>表示类型
 * 
 */
public class PageUtil<T> {
    private int pageIndex;// 当前页
    private int pageSize;// 每页大小
    private int pageCount;// 总页数
    private int pageNumber;// 总记录数
    private List<T> list;// 页面中的数据

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
