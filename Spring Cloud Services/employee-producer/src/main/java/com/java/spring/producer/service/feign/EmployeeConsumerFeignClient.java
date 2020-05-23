package com.java.spring.producer.service.feign;

import com.java.spring.producer.model.DomainEmployee;
import com.java.spring.producer.service.feign.config.CosumerFeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "employee-consumer", configuration = CosumerFeignConfig.class)
public interface EmployeeConsumerFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/consumer/newemployee")
    String persistEmployee(@RequestBody DomainEmployee dEmp);
}
