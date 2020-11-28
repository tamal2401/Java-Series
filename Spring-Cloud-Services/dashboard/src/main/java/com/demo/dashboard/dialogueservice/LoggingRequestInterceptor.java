package com.demo.dashboard.dialogueservice;


import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class LoggingRequestInterceptor implements Interceptor, ClientHttpRequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request req = chain.request();
        HttpUrl url = req.url();
        String method = req.method();
        String headers = null;
        if (log.isDebugEnabled()) {
            headers = logHeaders(req.headers().toMultimap());
        }
        Response response = chain.proceed(req);
        log.info("URL = {}, Method= {}, headers={}, Requestbody={}, responseCode={}",
                url, method, headers, req.body(), response.code());
        return response;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);
        URI url = request.getURI();
        String method = request.getMethodValue();
        String headers = null;
        if (log.isDebugEnabled()) {
            headers = logHeaders(request.getHeaders());
        }
        log.info("URL = {}, Method= {}, headers={}, Requestbody={}, responseCode={}",
                url, method, headers, new String( body,StandardCharsets.UTF_8),response.getStatusCode());
        return response;
    }

    private String logHeaders(Map<String, List<String>> headers) {
        StringBuilder builder = new StringBuilder();
        headers.entrySet().stream().forEach(each -> {
            builder.append(each.getKey()).append("=").append(each.getValue());
        });
        return builder.toString();
    }
}
