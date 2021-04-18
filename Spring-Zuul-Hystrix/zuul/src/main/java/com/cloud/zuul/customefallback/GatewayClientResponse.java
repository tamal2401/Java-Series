package com.cloud.zuul.customefallback;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GatewayClientResponse implements ClientHttpResponse {

    private HttpStatus status;
    private String msg;

    public GatewayClientResponse(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return status;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return status.value();
    }

    @Override
    public String getStatusText() throws IOException {
        return status.getReasonPhrase();
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(msg.getBytes());
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        return header;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
