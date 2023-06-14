package com.iweb.service;

import com.iweb.pojo.EUser;
import com.iweb.util.DBUtil;

import java.sql.*;


public class MainService {
    public static boolean login(EUser inputUser) {
        boolean isLogin = false;
        String sql = "SELECT password, user_type FROM euser WHERE username = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, inputUser.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pw = rs.getString("password");
                String utype = rs.getString("user_type");
                if (pw.equals(inputUser.getPassword())) {
                    System.out.println("登录成功！用户名为：" + inputUser.getUsername());
                    isLogin = true;
                    inputUser.setUser_type(utype);
                } else {
                    System.out.println("用户名或密码错误，请重新输入");
                    isLogin = false;
                }
            } else {
                System.out.println("用户名不存在，请重新输入");
                isLogin = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isLogin;
    }


    public static boolean register(EUser registerUser) {
        boolean isRegister = false;
        String sql1 = "SELECT COUNT(*) FROM euser WHERE username = ?";
        String sql2 = "INSERT INTO euser (username, password, user_type) VALUES (?, ?, '员工')";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps1 = c.prepareStatement(sql1);
             PreparedStatement ps2 = c.prepareStatement(sql2)) {
            ps1.setString(1, registerUser.getUsername());
            ResultSet rs = ps1.executeQuery();
            rs.next();
            int num = rs.getInt(1);
            if (num == 0) {
                ps2.setString(1, registerUser.getUsername());
                ps2.setString(2, registerUser.getPassword());
                ps2.executeUpdate();
                System.out.println("注册成功！用户名为：" + registerUser.getUsername());
                isRegister = true;
            } else {
                System.out.println("该用户名已存在！");
                isRegister = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRegister;
    }

}
