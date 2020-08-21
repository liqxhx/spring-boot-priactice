package com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.controller;

import com.lqh.priactice.springboot.redis.priactice.springboot.mvc.thymeleaf.vo.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p> 类描述: FileUploadController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/20 16:53
 * @since 2020/07/20 16:53
 */
@ResponseBody
@RequestMapping("/upload")
@Slf4j
public class FileUploadController {
    private static final String FOLDER = "static/images";

    /**
     * /upload/icon
     * @param src
     * @return
     */
    @PostMapping("/icon")
    @ResponseBody
    public ResponseDTO<String> uploadFaceIcon(@RequestParam(name = "face") MultipartFile src) {
        if (src.isEmpty()) {
            return ResponseDTO.fail("无上传文件");
        }

        String fileName = src.getOriginalFilename();
        ClassPathResource classPathResource = new ClassPathResource(FOLDER);
        File dest = null;
        try {
            dest = new File(classPathResource.getFile(), fileName);
            src.transferTo(dest);
            return ResponseDTO.success("上传成功");
        } catch (IOException e) {
            log.error("#file#upload ex:{}", e.getMessage(), e);
            return ResponseDTO.fail(e.getMessage());
        }
    }
}
