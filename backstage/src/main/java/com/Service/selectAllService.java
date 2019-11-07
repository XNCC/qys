package com.Service;

import com.Entity.filemetas2;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-07 16:25
 */
public interface selectAllService {
    List<filemetas2> selectAlls() throws SQLException;
}
