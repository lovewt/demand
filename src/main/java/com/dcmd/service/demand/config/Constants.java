package com.dcmd.service.demand.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/**
 * 配置
 * Created by 1 on 2018/9/5.
 */
@Configuration
public class Constants {
    /**图片在服务器上面的存放路径*/
    @Value("${file.path}")
    private  String filePath;

    /**
     * 文件上傳路徑
     */
    @Value("${file.upload}")
    private  String fileUpload;

    @Value("${file.uploadImagePath}")
    private String uploadImagePath;


    /**图片回显路径*/
    @Value("${file.downloadPath}")
    private String downloadPath;

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(String fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getUploadImagePath() {
        return uploadImagePath;
    }

    public void setUploadImagePath(String uploadImagePath) {
        this.uploadImagePath = uploadImagePath;
    }
}
