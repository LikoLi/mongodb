package org.liko.study.mongodb.thumbnailator;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author liko
 * @Date 2019/8/2
 * @Version 1.0
 * @Description ThumbnailatorTest
 */
public class ThumbnailatorTest {

    /**
     * Scaling an image by a given factor
     */
    @Test
    public void sacle() throws IOException {
        String fileName = "test.bmp";
        double factor = 0.1;
        while (factor <= 1) {
            Thumbnails.of(fileName).scale(factor).toFile(factor + fileName);
            factor += 0.1;
        }
    }
}
