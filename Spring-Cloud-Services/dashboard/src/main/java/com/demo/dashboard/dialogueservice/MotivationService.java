package com.demo.dashboard.dialogueservice;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MotivationService {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ServiceProperties prop;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    @Autowired
    public MotivationService(OkHttpClient client, ServiceProperties prop, ObjectMapper mapper) {
        Assert.notNull(client, "OkHttpClient bean must not be null");
        this.client = client;

        Assert.notNull(prop, "Service properties can must not be null");
        this.prop = prop;

        Assert.notNull(mapper, "Mapper instance can not be null");
        this.mapper = mapper;
    }

    public MotivationModel call() throws IOException {
        log.info("Calling Motivational Service to get new response");

        HttpUrl.Builder urlbBuilder = HttpUrl.parse(this.prop.getMotivation().getApi()).newBuilder();
        String url = urlbBuilder.build().toString();

        Request request = new Request.Builder()
                                    .get()
                                    .url(url)
                                    .build();

         MotivationModel model = new MotivationModel();
        try(Response response = this.client.newCall(request).execute()){
            if(response.isSuccessful()){
                ResponseBody body = Optional.ofNullable(response.body())
                        .orElseThrow(() -> new IllegalStateException(
                                String.format("Call successful to %s , but with empty response body", url)));
               model =  this.mapper.reader().forType(MotivationModel.class).readValue(body.bytes());
            }
        }catch(IOException ex){
            log.error("Error occured while calling url {} ", url);
            throw ex;
        }
        return model;
    }
}
