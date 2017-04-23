package com.example.filter;

import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.HeaderWriterFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zqLuo
 */
public class SystemHeaderWriterFilter extends HeaderWriterFilter{

    public SystemHeaderWriterFilter(List<HeaderWriter> headerWriters) {
        super(headerWriters);
        this.headerWriter();
    }

    /**
     * 自定义HeaderWriter，用以覆盖security默认的Header,
     * 使默认的"X-Frame-Options：DENY"禁止一切iframe调用
     * 转化为"X-Frame-Options：SAMEORIGIN"允许同域下的iframe调用
     */
    public HeaderWriter headerWriter(){
        HeaderWriter headerWriter = new HeaderWriter() {
            @Override
            public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
                for (String name : response.getHeaderNames()) {
                    System.out.print(response.getHeader(name));
                    System.out.println();
                }
                response.setHeader("X-Frame-Options", "SAMEORIGIN");
            }
        };
        return headerWriter;
    }
}
