package com.hjd.controller;

import com.hjd.data.FileType;
import com.hjd.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hujiande
 */
@RestController
public class FileController {

    @Resource
    private FileService fileService;


    @GetMapping("/fileTypes")
    public List<FileType> listFileTypes(){
        return fileService.listFileTypes();
    }
}
