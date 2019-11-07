package com.Dao.uploadDaoImpl;

import com.Dao.selectAllDao;
import com.Entity.filemetas;
import com.Entity.filemetas2;
import com.Utils.derbyConectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-07 16:20
 */
public class selectAllDaoImpl implements selectAllDao {
    @Override
    public List<filemetas2> selectAlls() throws SQLException {
        String sql="select * from filemetas";
        Connection connection = derbyConectUtil.GetConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        List<filemetas2> list=new ArrayList<>();
        while (rs.next()) {
            filemetas2 filemetas = new filemetas2();
            filemetas.setId(rs.getInt(1));
            filemetas.setFilesize(rs.getString(2));
            filemetas.setFiletype(rs.getString(3));
            filemetas.setFileoldname(rs.getString(4));
            filemetas.setFilecreatetime(rs.getTime(5).toString());
            filemetas.setFilesavepath(rs.getString(6));
            list.add(filemetas);
        }
        if(rs!=null){
            rs.close();
        }
        if(preparedStatement!=null){
            preparedStatement.close();
        }

        return list;
    }
}
