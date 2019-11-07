package com.Dao;

import com.Entity.filemetas;

import java.sql.SQLException;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-02 9:23
 */
public interface uploadDao {
        //上传元数据到数据库
        public Integer insert(filemetas filemetas) throws SQLException;
}
