package com.lm.bookstore.model;

import java.util.List;

/**
 * @author LM_Code
 * @create 2019-03-03-10:04
 */
public class PageResult<T> {
    List<T> list;
    long totalCount;//总条数
    int pageSize = 4;//每页显示数
    int totalPage;//总页数
    int currentPage;//当前页

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                '}';
    }
}
