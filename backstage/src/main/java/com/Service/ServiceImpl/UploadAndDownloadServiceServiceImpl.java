package com.Service.ServiceImpl;

import com.Dao.uploadDaoImpl.uploadDaoImpl;
import com.Entity.filemetas;
import com.Service.UploadAndDownloadService;

import java.sql.SQLException;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-02 9:18
 */
public class UploadAndDownloadServiceServiceImpl implements UploadAndDownloadService {

    public Integer upload(filemetas filemetas) throws SQLException {
        uploadDaoImpl uploadDaoImpl=new uploadDaoImpl();
        Integer insert = uploadDaoImpl.insert(filemetas);
        return insert;
    }


}
