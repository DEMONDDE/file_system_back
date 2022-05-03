package com.hjd.controller;

import com.hjd.data.FileChunk;
import com.hjd.data.FileChunkResult;
import com.hjd.data.FileType;
import com.hjd.data.Result;
import com.hjd.service.FileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
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

    @GetMapping("/chunks")
    public Result.Data existChunks(FileChunk file){
        FileChunkResult fileChunkResult = fileService.existChunks(file);
        return Result.successResultData(fileChunkResult);
    }

    @PostMapping("/chunks")
    public Result.Data uploadChunks(@RequestBody FileChunk fileChunk){
        try {
            fileService.uploadFile(fileChunk);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.errorResultData();
        }
        return Result.successResultData("");
    }

    @PostMapping("/merge")
    public Result.Data mergeChunk(@RequestBody FileChunk fileChunk){
        fileService.mergeFile(fileChunk.getIdentifier(), fileChunk.getFileName(), fileChunk.getTotalChunks());
    }
}
