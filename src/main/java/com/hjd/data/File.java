package com.hjd.data;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class File implements Serializable {
    private int id;
    private String name;
    private BigDecimal size;
    private String path;
    private String type;
    private String describe;
    private String originName;
    private String FileTpe;
}
