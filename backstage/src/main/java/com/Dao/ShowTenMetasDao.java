package com.Dao;

import com.Entity.filemetas;
import com.Entity.filemetas2;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-02 23:46
 */
public interface ShowTenMetasDao {
    public  List<filemetas2> GetlastTenMetas(int pagenum, int pagesize) throws SQLException;
}
