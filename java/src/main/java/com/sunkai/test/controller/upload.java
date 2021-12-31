package com.sunkai.test.controller;

import com.sunkai.test.bean.Result;
import com.sunkai.test.utils.UploadFileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
public class upload {
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result uploadFilemm(@RequestParam("file") MultipartFile multipartFile){
        Result result = new Result();
        if(multipartFile.isEmpty()){
            result.setCode(201);
            result.setData("");
            result.setMsg("文件为空!");
            return  result;
        }
        UploadFileUtils uploadFileUtils = new UploadFileUtils();
        String url = uploadFileUtils.uploadFile(multipartFile);
        result.setData(url);
        return result;
    }
}
