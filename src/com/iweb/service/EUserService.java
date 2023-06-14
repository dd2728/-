package com.iweb.service;

import com.iweb.DAO.EUserDao;
import com.iweb.pojo.EUser;
import com.iweb.util.DBUtil;
import com.iweb.view.EUserView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxy
 * @create 2023/6/12 11:29
 */
public class EUserService implements EUserDao {
    //添加用户
    @Override
    public void insert(EUser eUser) {
        String sql="INSERT INTO eUser (username, PASSWORD, user_type) VALUES (?, ?, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, eUser.getUsername());
            ps.setString(2, eUser.getPassword());
            ps.setString(3, eUser.getUser_type());
            int num=ps.executeUpdate();//返回更新数据库的行数
            if(num==0){
                System.out.println("插入失败，请重新尝试");
                EUserView.insertView();
            }else{
                System.out.println("插入数据成功!即将返回上一页");
                EUserView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查询所有用户
    @Override
    public List<EUser> selectAll() {
        List<EUser> eUserList=new ArrayList<>();
        String sql="select * from eUser";
        try (
                Connection c= DBUtil.getConnection();
                Statement s=c.createStatement();
        ){
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                String password="******";
                String type=rs.getString(4);
                EUser eUser=new EUser(id,name,password,type);
                eUserList.add(eUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eUserList.size()==0?null:eUserList;
    }

    //根据用户名查询用户
    @Override
    public List<EUser> listByNameLike(String key) {
        List<EUser> eUserList = new ArrayList<>();
        String sql="SELECT * FROM eUser WHERE username LIKE ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String userType = rs.getString("user_type");
                EUser eUser = new EUser(id, username, password, userType);
                eUserList.add(eUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eUserList;
    }

    @Override
    public List<EUser> listByType(String selectStatus) {
        List<EUser> eUserList = new ArrayList<>();
        String sql = "SELECT * FROM eUser WHERE user_type = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, selectStatus);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String userType = rs.getString("user_type");
                EUser eUser = new EUser(id, username, password, userType);
                eUserList.add(eUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eUserList;
    }

    @Override
    public void update(EUser updateUser) {
        String sql = "UPDATE eUser SET username = '" + updateUser.getUsername() + "', " +
                "password = '" + updateUser.getPassword() + "', " +
                "user_type = '" + updateUser.getUser_type() + "' " +
                "WHERE id = " + updateUser.getId();

        try (
                Connection c = DBUtil.getConnection();
                Statement s = c.createStatement();
        ) {
            int num=s.executeUpdate(sql);
            if(num==0){
                System.out.println("更新失败，请重新尝试");
                EUserView.updateView();
            }else{
                System.out.println("用户修改成功,即将返回上一页");
                EUserView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(EUser eUser) {
        String sql="DELETE FROM eUser WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, eUser.getId());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("删除失败，请重新尝试");
                EUserView.deleteView();
            }else {
                System.out.println("删除成功!即将返回上一页");
                EUserView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
