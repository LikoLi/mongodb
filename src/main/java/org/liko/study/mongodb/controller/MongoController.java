package org.liko.study.mongodb.controller;

import org.liko.study.mongodb.dao.MongoDao;
import org.liko.study.mongodb.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liko
 * @Date 2019/7/24
 * @Version 1.0
 * @Description MongoController
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private MongoDao dao;

    @GetMapping("/add")
    public String add() {
        Blog blog = new Blog();
        blog.setName("sfd").setName("Java").setDesc("learn java").setContent("xxxxxxxxxxxxxx");
        dao.save(blog);
        return "success";
    }

}
