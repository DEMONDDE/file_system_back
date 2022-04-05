package com.hjd.service;

import com.hjd.dao.FileMapper;
import com.hjd.data.FileType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hujiande
 */
@Service
public class FileService {
   @Resource
   private FileMapper fileMapper;

   @Resource
   private MongoTemplate mongoTemplate;

   public List<FileType> listFileTypes() {
      return new ArrayList<>();
   }
}
