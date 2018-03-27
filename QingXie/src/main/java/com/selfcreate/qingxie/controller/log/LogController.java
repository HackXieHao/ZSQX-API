package com.selfcreate.qingxie.controller.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author evans 2018/3/27 19:52
 */

@Controller
@RequestMapping("/log")
public class LogController {
    @GetMapping("/show")
    public String showLog(){
        return "log";
    }
}
