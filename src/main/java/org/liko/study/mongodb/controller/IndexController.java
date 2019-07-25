package org.liko.study.mongodb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author liko
 * @Date 2019/7/25
 * @Version 1.0
 * @Description IndexController
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
