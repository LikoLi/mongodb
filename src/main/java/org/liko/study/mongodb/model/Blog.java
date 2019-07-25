package org.liko.study.mongodb.model;

import lombok.Data;

/**
 * @Author liko
 * @Date 2019/7/24
 * @Version 1.0
 * @Description Blog
 */
@Data
public class Blog {
    private String id;
    private String name;
    private String desc;
    private String content;

    public static final class Fields {
        public static final String id = "id";
        public static final String name = "name";
        public static final String desc = "desc";
        public static final String content = "content";
    }
}
