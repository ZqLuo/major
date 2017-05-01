package com.example.util;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * sql查询分页结果
 * Created by zqLuo
 */
public class PageReturn {
    /**
     * 当前页
     */
    private int page = 0;
    /**
     * 页面大小
     */
    private int pageSize = 10;

    private List result;

    private int totalPage;

    private int total = 0;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public int getTotalPage() {
        int pageCount = this.total/this.getPageSize();
        if(this.total%this.getPageSize() > 0){
            pageCount += 1;
        }
        return pageCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
