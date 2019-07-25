package org.liko.study.mongodb.dao;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> findAll() {
        List<String> fileNames = new ArrayList<>();

        Query query = new Query();
        GridFSFindIterable gridFSFiles = gridFsTemplate.find(query);
        MongoCursor<GridFSFile> iterator = gridFSFiles.iterator();
        while (iterator.hasNext()) {
            GridFSFile next = iterator.next();
            fileNames.add(next.getFilename());
        }
        return fileNames;
    }

    public void deleteByName(String fileName) {
        Query query = new Query(Criteria.where("filename").is(fileName));
        gridFsTemplate.delete(query);
    }
}

