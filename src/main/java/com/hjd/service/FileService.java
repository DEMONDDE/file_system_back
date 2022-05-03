package com.hjd.service;

import com.hjd.dao.FileMapper;
import com.hjd.data.FileChunk;
import com.hjd.data.FileChunkResult;
import com.hjd.data.FileType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * @author hujiande
 */
@Service
public class FileService {
   @Resource
   private FileMapper fileMapper;

   @Resource
   private MongoTemplate mongoTemplate;
   @Resource
   private RedisTemplate redisTemplate;

   public String FILE_PATH = "/Users/hujiande/Downloads";

   public List<FileType> listFileTypes() {
      return new ArrayList<>();
   }


   /**
    * 分片是否上传
    */
   public FileChunkResult existChunks(FileChunk file) {
      java.io.File fileDir = new java.io.File(FILE_PATH + java.io.File.separator + file.getIdentifier());
      boolean exists = fileDir.exists();
      Set<Integer> uploads = (Set<Integer>) redisTemplate.opsForHash().get(file.getIdentifier(), "uploads");
      //判断是否上传
      if(uploads == null && uploads.size() == file.getTotalChunks() && exists){
         return new FileChunkResult(true, null);
      }
      //没上传创建相应路径
      fileDir.mkdir();
      return new FileChunkResult(false, uploads);
   }

   /**
    * 上传分片
    */
   public void uploadFile(FileChunk file) throws IOException {
      File dir = new File(FILE_PATH + File.separator + file.getIdentifier());
      if(!dir.exists()){
         dir.mkdir();
      }
      File chunk = new File(dir, String.valueOf(file.getChunkNumber()));
      file.getFile().transferTo(chunk);
      Set<Integer> uploads = (Set<Integer>) redisTemplate.opsForHash().get(file.getIdentifier(), "upload");
      if(uploads == null){
         Set<Integer> upload = new HashSet<>(Arrays.asList(file.getChunkNumber()));
         Map<String, Object> fileInfo = new HashMap<>();
         fileInfo.put("upload", upload);
         fileInfo.put("totalSize", file.getTotalSize());
         fileInfo.put("totalChunks", file.getTotalChunks());
         fileInfo.put("path", dir.getAbsolutePath());
         redisTemplate.opsForHash().putAll(file.getIdentifier(), fileInfo);
      }else {
         uploads.add(file.getChunkNumber());
      }
   }

   /**
    * 请求合并分片
    */
   public void mergeFile(String identifier, String fileName, long totalChunks){
      //校验文件是否存在
      //校验块是否都存在
      File dir = new File(FILE_PATH + File.separator + identifier);
      File merger = new File(FILE_PATH, fileName);
      File[] files = dir.listFiles();
      Arrays.sort(files, (Comparator.comparingInt(o -> Integer.valueOf(o.getName()))));
      try {
         RandomAccessFile randomAccessFile = new RandomAccessFile(merger, "rw");
         byte[] data = new byte[1024];
         for(int i = 0; i < files.length; i++){
            RandomAccessFile randomAccessChunk = new RandomAccessFile(files[i], "r");
            int len = 0;
            while((len = randomAccessChunk.read(data)) != -1){
               randomAccessFile.write(data, 0, len);
            }
            randomAccessChunk.close();
         }
         randomAccessFile.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
