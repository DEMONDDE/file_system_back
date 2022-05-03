package com.hjd.dao;

import com.hjd.data.FileChunk;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author hujiande
 */
@Repository
public interface FileMapper extends Mapper<FileChunk> {
}
