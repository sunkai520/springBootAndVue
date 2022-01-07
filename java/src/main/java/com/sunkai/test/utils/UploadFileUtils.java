package com.sunkai.test.utils;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class UploadFileUtils {

    private final static String FILE_PATH=System.getProperty("user.dir")+"/images/";
    public String uploadFile(MultipartFile multipartFile){
        //获取文件后缀
        String fileName = UUID.randomUUID().toString().trim().replaceAll("-","")+"."+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1);
        String filePath = FILE_PATH+fileName;
        Long size = multipartFile.getSize();
        System.out.println(size+"size");
        File file = new File(filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try{
            multipartFile.transferTo(file);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "images/"+fileName;
    }
}
