package com.cloud.zuul.customefallback;

import com.cloud.zuul.client.FallbackServiceImpl;
import com.cloud.zuul.model.FallbackModel;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Configuration
public class GlobalFallbackProvider implements FallbackProvider {

    @Autowired
    FallbackServiceImpl fallbackService;

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        HttpServletRequest currentReq = RequestContext.getCurrentContext().getRequest();

        FallbackModel model = new FallbackModel();
        model.setServiceName(route);
        model.setFailureMessage(cause.getMessage());
        model.setRequestType(currentReq.getMethod());

        String reqbody;
        try {
            reqbody = extractRequestBody(currentReq);
            model.setRequestBody(reqbody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fallBackRes = callFallbackService(model);

        if(cause instanceof HystrixTimeoutException){
            return new GatewayClientResponse(HttpStatus.GATEWAY_TIMEOUT, fallBackRes);
        }else{
            return new GatewayClientResponse(HttpStatus.SERVICE_UNAVAILABLE, fallBackRes);
        }
    }

    private String callFallbackService(FallbackModel model) {
        String fallbackRes = "";
        try {
            fallbackRes = fallbackService.call(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fallbackRes;
    }

    private String extractRequestBody(HttpServletRequest currentReq) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = currentReq.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return stringBuilder.toString();
    }
}
