package org.liko.study.mongodb.dao;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author liko
 * @Date 2019/7/24
 * @Version 1.0
 * @Description PicDao
 */
@Repository
public class PicDao {
    @Autowired
    private MongoTemplate template;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public void save(InputStream inputStream, String fileName) throws FileNotFoundException {
        gridFsTemplate.store(inputStream, fileName);
    }

}

