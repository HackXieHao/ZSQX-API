package com.selfcreate.qingxie.controller.avtivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> params = new HashMap<>();
        params.put("size", "5");
        params.put("page", "2");
        getAndOutput("/activity/home", params);
    }

    @Test
    public void getUserActivities() {
    }

    @Test
    public void getUserWorks() {
    }

    @Test
    public void getForks() {
        getAndOutput("/activity/3/forks");
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