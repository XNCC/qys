package com.Dao.uploadDaoImpl;

import com.Dao.ShowTenMetasDao;
import com.Entity.filemetas;
import com.Entity.filemetas2;
import com.Utils.derbyConectUtil;
import com.Utils.sqlConnectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ncc
 * @site ncc网站
 * @company 呵呵
 * @create 2019-11-02 23:48
 */
public class ShowTenMetasDaoImpl implements ShowTenMetasDao {
    @Override
    public List<filemetas2> GetlastTenMetas(int pagenum, int pagesize) throws SQLException {
        //Connection connection = sqlConnectUtil.GetConnection();
        Connection connection = derbyConectUtil.GetConnection();
        String sql = "SELECT max(id) id FROM filemetas";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int id = resultSet.getInt("id");
        resultSet.close();
        System.out.println(id+"id>>>>>>>>>>>>>>>>>>>");
        if (id > 10) {
            String sql2 = "SELECT * from filemetas order by id desc OFFSET "+0 +" ROWS FETCH NEXT "+ pagesize+ " ROWS ONLY";
//            mysql分页语句
//            String sql2 = "SELECT * from filemetas  limit  " + (id-10) + "," + pagesize;
            return getlist(sql2);
        }else if(id>0&&id<10){
            String sql3 = "SELECT * from filemetas order by id desc OFFSET 0 ROWS FETCH NEXT "+ pagesize+ " ROWS ONLY";
            return getlist(sql3);
        }
        return null;
    }


    //获取列表
    public List<filemetas2> getlist(String sql) throws SQLException {
        Connection connection = derbyConectUtil.GetConnection();
        List<filemetas2> list=new ArrayList<>();
        System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            filemetas2 filemetas=new filemetas2();
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
        if(statement!=null){
            statement.close();
        }
        return  list;
    }
}
