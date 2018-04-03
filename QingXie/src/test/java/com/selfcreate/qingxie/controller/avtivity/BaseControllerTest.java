package com.selfcreate.qingxie.controller.avtivity;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author evans 2018/4/2 0:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class BaseControllerTest {
    @Autowired
    public WebApplicationContext wac;

    private MockMvc mockMvc;

    public MockHttpSession session;

    final Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    private static void output(MvcResult mvcResult) throws UnsupportedEncodingException {
        int status = mvcResult.getResponse().getStatus();
        System.out.println("请求状态码：" + status);
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("接口返回结果：" + result);
//        JSONObject resultObj = JSON.parseObject(result);
    }
    /**
     * 无参post
     *
     * @param api
     */
    void postAndOutput(String api) {
        try {
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post(api))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            output(mvcResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 含参post
     *
     * @param api
     * @param params
     */
    void postAndOutput(String api, Map<String, String> params) {
        try {
            MultiValueMap param = new HttpHeaders();
            param.setAll(params);
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post(api).params(param))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            output(mvcResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getAndOutput(String api, @NotNull Map<String, String> params) {
        try {
            MultiValueMap param = new HttpHeaders();
            param.setAll(params);
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.get(api).params(param))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            output(mvcResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void getAndOutput(String api) {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(api))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            output(mvcResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
