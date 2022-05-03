package com.hjd.data;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author hujiande
 */
@Data
public class FileChunk implements Serializable {
    private String identifier;
    private MultipartFile file;
    private Integer chunkNumber;
    private Long chunkSize;
    private Long currentChunkSize;
    private Long totalSize;
    private Long totalChunks;
    private String fileName;
}
