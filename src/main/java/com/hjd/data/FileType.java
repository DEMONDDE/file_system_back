package com.hjd.data;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author hujiande
 */
@Data
public class FileType implements Serializable {
    private int id;
    private String name;
    private int parentId;
    private List<FileType> children;
}
