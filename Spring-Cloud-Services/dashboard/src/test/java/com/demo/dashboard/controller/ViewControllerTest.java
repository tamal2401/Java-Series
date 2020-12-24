package com.demo.dashboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import com.demo.dashboard.domain.AlertProductInfo;
import com.demo.dashboard.feign.CrudServiceFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ViewController.class)
public class ViewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CrudServiceFeignClient crudServiceFeignClient;

    @Test
    public void testGetAllProduct() throws Exception {
        List<AlertProductInfo> allProducts = Arrays.asList(
                new AlertProductInfo(1, "name1", "desc1", new Timestamp(System.currentTimeMillis())),
                new AlertProductInfo(2, "name2", "desc2", new Timestamp(System.currentTimeMillis())),
                new AlertProductInfo(3, "name3", "desc3", new Timestamp(System.currentTimeMillis()))
        );
        Mockito.when(crudServiceFeignClient.allProducts()).thenReturn(allProducts);
        this.mockMvc.perform(get("/api/product/all")).andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$", hasSize(3)));
                    //.andDo(print());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Mockito.doNothing().when(crudServiceFeignClient).updateProduct(Mockito.any());
        this.mockMvc.perform(post("/api/product/update").content(
                asJsonString(new AlertProductInfo(1, "name1", "desc1", new Timestamp(System.currentTimeMillis()))))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Mockito.doNothing().when(crudServiceFeignClient).removeProduct(Mockito.anyInt());
        this.mockMvc.perform(delete("/api/product/delete/2")).andExpect(status().isOk());
    }

    @Test
    public void testCreateProduct() throws Exception {
        AlertProductInfo allProducts = new AlertProductInfo(1, "name1", "desc1", new Timestamp(System.currentTimeMillis()));

        Mockito.when(crudServiceFeignClient.saveProduct(Mockito.any())).thenReturn(allProducts);
        this.mockMvc.perform(post("/api/product/create").content(
                asJsonString(new AlertProductInfo(1, "name1", "desc1", new Timestamp(System.currentTimeMillis()))))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Data saved successfully"));
        //.andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
