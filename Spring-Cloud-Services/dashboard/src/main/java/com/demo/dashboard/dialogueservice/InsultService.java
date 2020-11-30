package com.demo.dashboard.dialogueservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Component
public class InsultService {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ServiceProperties prop;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    @Autowired
    public InsultService(OkHttpClient client, ServiceProperties prop, ObjectMapper mapper) {
        Assert.notNull(client, "OkHttpClient bean must not be null");
        this.client = client;

        Assert.notNull(prop, "Service properties can must not be null");
        this.prop = prop;

        Assert.notNull(mapper, "Mapper instance can not be null");
        this.mapper = mapper;
    }

    public InsultModel call() throws IOException {
        log.info("Calling Insult Service to get new response");

        HttpUrl.Builder urlbBuilder = HttpUrl.parse(this.prop.getInsult().getApi()).newBuilder();
        String url = urlbBuilder.addQueryParameter("lang", "en")
                                .addQueryParameter("type", "json")
                                .build()
                                .toString();

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        InsultModel insultModel = new InsultModel();
        try (Response response = this.client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody body = Optional.ofNullable(response.body())
                        .orElseThrow(() -> new IllegalStateException(
                                String.format("Call successful to %s , but with empty response body", url)));
                insultModel = this.mapper.reader().forType(InsultModel.class).readValue(body.bytes());
            }
        } catch (IOException ex) {
            log.error("Error occured while calling url {} ", url);
            throw ex;
        }
        return insultModel;
    }
}
