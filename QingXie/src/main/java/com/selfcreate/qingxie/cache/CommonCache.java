package com.selfcreate.qingxie.cache;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author evans 2018/3/8 20:56
 */

public class CommonCache {
    public static Map<String,HttpSession> sessionMap=new HashMap<>();
}
