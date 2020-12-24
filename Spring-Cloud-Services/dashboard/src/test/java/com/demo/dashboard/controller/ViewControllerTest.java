package com.demo.dashboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.demo.dashboard.domain.AlertProductInfo;
import com.demo.dashboard.feign.CrudServiceFeignClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(ViewController.class)
public class ViewControllerTest {

    private MockMvc mockMvc;

    @MockBean
    CrudServiceFeignClient crudServiceFeignClient;

    @Test
    public void testcreateProduct() throws Exception {
        List<AlertProductInfo> allProducts = Arrays.asList(
                new AlertProductInfo(1, "name1", "desc1", new Timestamp(System.currentTimeMillis())),
                new AlertProductInfo(2, "name2", "desc2", new Timestamp(System.currentTimeMillis())),
                new AlertProductInfo(3, "name3", "desc3", new Timestamp(System.currentTimeMillis()))
        );
        Mockito.when(crudServiceFeignClient.allProducts()).thenReturn(allProducts);
        this.mockMvc.perform(get("/api/product/all")).andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andDo(print());
    }
}
