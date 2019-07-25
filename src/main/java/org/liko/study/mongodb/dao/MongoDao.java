package org.liko.study.mongodb.dao;

import org.liko.study.mongodb.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author liko
 * @Date 2019/7/24
 * @Version 1.0
 * @Description MongoDao
 */
@Repository
public class MongoDao {

    @Autowired
    private MongoTemplate template;

    public void save(Blog blog) {
        System.out.println(blog);
        blog = template.save(blog);
        System.out.println(blog);
    }

    public Blog findByName(String name) {
        Query query = new Query(Criteria.where(Blog.Fields.name).is(name));
        return template.findOne(query, Blog.class);
    }

    public void updateBlog(Blog blog) {
        Query query = new Query(Criteria.where(Blog.Fields.id)
                .is(blog.getId()));
        Update update = new Update()
                .set(Blog.Fields.name, blog.getName())
                .set(Blog.Fields.desc, blog.getDesc())
                .set(Blog.Fields.content, blog.getContent());
        template.updateFirst(query, update, Blog.class);
    }

    public void deleteById(String id) {
        Query query = new Query(Criteria.where(Blog.Fields.id)
                .is(id));
        template.remove(query, Blog.class);
    }
}
