package com.hjd.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author hujiande
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileChunkResult {
    private boolean skip;
    private Set<Integer> chunks;
}
