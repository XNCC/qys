package com.Dao.uploadDaoImpl;

import com.Dao.getOneMetaDao;
import com.Entity.filemetas;
import com.Entity.filemetas2;
import com.Utils.derbyConectUtil;
import com.Utils.sqlConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-04 1:18
 */
public class getOneMetaDaoImpl implements getOneMetaDao {
    @Override
    public filemetas2 selectByUUid(String uuid) throws SQLException {
//        Connection connection = sqlConnectUtil.GetConnection();
        Connection connection = derbyConectUtil.GetConnection();
        String sql = "select * from filemetas where filesavepath=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, uuid);
        ResultSet rs = preparedStatement.executeQuery();
        filemetas2 filemetas = new filemetas2();
        while (rs.next()) {
            filemetas.setId(rs.getInt(1));
            filemetas.setFilesize(rs.getString(2));
            filemetas.setFiletype(rs.getString(3));
            filemetas.setFileoldname(rs.getString(4));
            filemetas.setFilecreatetime(rs.getTime(5).toString());
            filemetas.setFilesavepath(rs.getString(6));
        }
        if(rs!=null){
            rs.close();
        }
        if(preparedStatement!=null){
            preparedStatement.close();
        }
        if (filemetas != null) {
            System.out.println(filemetas + "<><><><><>");
            return filemetas;
        } else {
            return null;
        }

    }
}
