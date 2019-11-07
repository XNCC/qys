package com.Service;

import com.Entity.filemetas;

import java.sql.SQLException;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-02 9:18
 */
public interface UploadAndDownloadService {
    public Integer upload(filemetas filemetas)throws SQLException;
}
