package com.selfcreate.qingxie.controller.avtivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

/**
 * @author evans 2018/4/2 0:38
 */

public class ActivityControllerTest extends BaseControllerTest {
//    private MockMvc mockMvc;
//    @Before
//    public void setUp() {
////        import com.xfs.web.controller.APIController;
////        APIController apiController = new APIController();
//        mockMvc = MockMvcBuilders.standaloneSetup().build();
//    }
    @Test
    public void getHome() {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/getSequence"))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            int status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            String result = mvcResult.getResponse().getContentAsString();
            System.out.println("接口返回结果：" + result);
            JSONObject resultObj = JSON.parseObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserActivities() {
    }

    @Test
    public void getUserWorks() {
    }

    @Test
    public void getForks() {
    }

    @Test
    public void getDetails() {
    }

    @Test
    public void pushActivity() {
    }

    @Test
    public void joinActivity() {
    }
}