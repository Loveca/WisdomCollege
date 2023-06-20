package com.xuecheng.media.model.dto;

import lombok.Data;

@Data
public class UploadFileParamsDto {

    //文件名称
    private String filename;

    //文件类型（图片，视频，文档）
    private String fileType;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 标签
     */
    private String tags;

    /**
     * 上传人
     */
    private String username;

    /**
     * 备注
     */
    private String remark;
}
