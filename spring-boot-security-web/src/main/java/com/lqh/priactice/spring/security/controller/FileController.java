package com.lqh.priactice.spring.security.controller;

import com.lqh.priactice.spring.security.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p> 类描述: FileController
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/10 07:29
 * @since 2020/07/10 07:29
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public FileInfo upload(MultipartFile file) throws Exception {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String dir = ".";
        String fileName = System.currentTimeMillis() +".txt";
        File localFile = new File(dir, fileName);

        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/download/{filename}")
    public void upload(@PathVariable("filename") String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
       try (
               InputStream in = new FileInputStream(new File(".", filename+".txt"));
               OutputStream out = response.getOutputStream();
       ) {

           response.setContentType("application/x-download");
           response.addHeader("Content-Disposition", "attachment;filename=test.txt");
           IOUtils.copy(in, out);
       } catch (Exception e) {

       }
    }
}
