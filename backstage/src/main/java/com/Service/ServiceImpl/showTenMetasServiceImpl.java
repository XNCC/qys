package com.Service.ServiceImpl;

import com.Dao.ShowTenMetasDao;
import com.Dao.uploadDaoImpl.ShowTenMetasDaoImpl;
import com.Entity.filemetas;
import com.Entity.filemetas2;
import com.Service.showTenMetasService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-03 19:20
 */
public class showTenMetasServiceImpl implements showTenMetasService {
    @Override
    public  List<filemetas2> GetlastTenMetas(int pagenum, int pagesize) throws SQLException {
        ShowTenMetasDao showTenMetasDao=new ShowTenMetasDaoImpl();
        return showTenMetasDao.GetlastTenMetas(pagenum,pagesize);
    }
}
