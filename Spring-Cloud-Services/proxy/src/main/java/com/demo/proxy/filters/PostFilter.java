package com.demo.proxy.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

public class PostFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public String filterType() {
        return "psot";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("In post filter");
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
        response.addHeader("custom-header", "test");
        return null;
    }
}
