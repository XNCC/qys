package com.Service.ServiceImpl;

import com.Dao.selectAllDao;
import com.Dao.uploadDaoImpl.selectAllDaoImpl;
import com.Entity.filemetas2;
import com.Service.selectAllService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-07 16:25
 */
public class selectAllServiceimpl implements selectAllService {
    @Override
    public List<filemetas2> selectAlls() throws SQLException {
        selectAllDao selectAllDao=new selectAllDaoImpl();
        List<filemetas2> filemetas2s = selectAllDao.selectAlls();
        return filemetas2s;
    }
}
