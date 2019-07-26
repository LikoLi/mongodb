package org.liko.study.mongodb.controller;

import org.liko.study.mongodb.dao.PicDao;
import org.liko.study.mongodb.util.MD5Utitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author liko
 * @Date 2019/7/25
 * @Version 1.0
 * @Description FileController
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${liko.imgs.url}")
    private String baseUrl;

    @Autowired
    private PicDao picDao;

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {

        try {
            if (file.isEmpty()) {
                model.addAttribute("desc","文件为空");
            }

            String fileName = file.getOriginalFilename();
            String prefixName = fileName.substring(0, fileName.lastIndexOf("."));
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            String newFileName = prefixName + "_" + MD5Utitl.getFileMD5String(file.getInputStream()) + suffixName;

            InputStream inputStream = file.getInputStream();

            picDao.save(inputStream, newFileName);

            model.addAttribute("desc", "上传成功");
            model.addAttribute("link", baseUrl + "/" + newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("desc", "上传失败");
        }
        return "result";
    }

    @RequestMapping("/all")
    public String findAll(Model model) {
        List<String> fileNames = picDao.findAll();

        model.addAttribute("fileNames", fileNames);
        model.addAttribute("baseUrl", baseUrl);

        return "query";
    }

    @RequestMapping("/del/{fileName}")
    @ResponseBody
    public String deleteByFileName(@PathVariable("fileName") String fileName) {
        picDao.deleteByName(fileName);
        return "success";
    }
}
