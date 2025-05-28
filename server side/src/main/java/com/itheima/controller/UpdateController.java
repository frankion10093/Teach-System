package com.itheima.controller;


import com.itheima.anno.Log;
import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UpdateController {

    @Autowired
    private AliOSSUtils aliyunosss;

    //文件存储本地存储方案，由于本地部署的会出现很多问题，现在企业开发基本被淘汰，所以不推荐使用本地存储方案
//    @PostMapping("/upload")
//    public Result update(String username, Integer age, MultipartFile image) throws Exception {
//        log.info("文件上传:{},{},{}",username,age,image);
//
//        //获取上传文件的名称
//        String filename = image.getOriginalFilename();
//        //获取上传文件的后缀名
//        int index = filename.lastIndexOf(".");
//        //获得位置之后截取字符串
//        String extname = filename.substring(index);
//        //为了解决存在上传文件名称相同的问题，使用uuid作为文件名
//        String newFileName = UUID.randomUUID().toString()+extname;
//
//        log.info("新文件名:{}",newFileName);
//
//        //将文件存储在服务器的磁盘文件目录中
//        image.transferTo(new File("D:\\vscode\\"+newFileName));
//
//        return Result.success();
//    }

    /**
     * 文件上传到阿里云OSS
     * @param image
     * @return
     * @throws IOException
     */
    @Log
    @PostMapping
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传：{}",image.getOriginalFilename());

      String url = aliyunosss.upload(image);

       log.info("文件上传完成，文件访问url为：{}",url);



        return Result.success(url);
    }

}
