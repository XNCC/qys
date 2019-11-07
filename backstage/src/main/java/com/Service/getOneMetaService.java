package com.Service;

import com.Entity.filemetas;
import com.Entity.filemetas2;

import java.sql.SQLException;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-04 1:24
 */
public interface getOneMetaService {
    public filemetas2 selectByUUid(String uuid) throws SQLException;
}
