package com.Service.ServiceImpl;

import com.Dao.uploadDaoImpl.getOneMetaDaoImpl;
import com.Entity.filemetas;
import com.Entity.filemetas2;
import com.Service.getOneMetaService;

import java.sql.SQLException;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-04 1:24
 */
public class getOneMetaServiceImpl implements getOneMetaService {
    @Override
    public filemetas2 selectByUUid(String uuid) throws SQLException {
        getOneMetaDaoImpl getOneMetaDaoImpl=new getOneMetaDaoImpl();
        return getOneMetaDaoImpl.selectByUUid(uuid);
    }
}
