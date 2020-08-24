package com.lqh.priactice.spring.security.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * <p> 类描述: UserControllerTests
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/08 07:31
 * @since 2020/07/08 07:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testUpload() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .fileUpload("/file/upload")
                // 第一个参数要与FileController中接收参数名一样
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void testQuery() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 判断返回json集合数量为3
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));


    }

    @Test
    public void testAdd() throws Exception {
        String content = "{\"name\":\"lqh\", \"password\":\"1\", \"cityId\":1}";
        mockMvc.perform(MockMvcRequestBuilders
            .post("/user/add")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(content))
            .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

    }
}
