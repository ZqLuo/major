package com.example.util;

import java.util.List;

/**
 * Created by zqLuo
 */
public class Page<T> {
    /**
     * 当前页
     */
    private int curPage = 1;
    /**
     * 页面大小
     */
    private int pageSize = 10;
    /**
     * 总数
     */
    private Long total;
    /**
     * 结果
     */
    List<T> result;
    /**
     * 总页数
     */
    private int totalPage;

    public Page() {
    }

    public Page(int curPage, int pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
