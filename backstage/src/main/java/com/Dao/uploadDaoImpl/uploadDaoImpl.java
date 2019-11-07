package com.Dao.uploadDaoImpl;

import com.Dao.uploadDao;
import com.Entity.filemetas;
import com.Utils.derbyConectUtil;
import com.Utils.sqlConnectUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-02 16:42
 */
public class uploadDaoImpl  implements uploadDao {
    //上传元数据到数据库
    public Integer insert(filemetas filemetas) throws SQLException {
    SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
//        Connection connection = sqlConnectUtil.GetConnection();
        Connection connection = derbyConectUtil.GetConnection();
        String sql="insert into filemetas  (filesize ,filetype ,fileoldname,filecreatetime,filesavepath   )values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,filemetas.getFilesize());
        preparedStatement.setString(2,filemetas.getFiletype());
        preparedStatement.setString(3,filemetas.getFileoldname());
        preparedStatement.setString(4,sdf.format(new Date(filemetas.getFilecreatetime().getTime())));
        preparedStatement.setString(5,filemetas.getFilesavepath());
        int i = preparedStatement.executeUpdate();
        if(preparedStatement!=null){
            preparedStatement.close();
        }

        return i;
    }


}
