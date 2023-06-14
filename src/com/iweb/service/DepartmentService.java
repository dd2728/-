package com.iweb.service;

import com.iweb.DAO.DepartmentDao;
import com.iweb.pojo.Department;
import com.iweb.pojo.EUser;
import com.iweb.util.DBUtil;
import com.iweb.view.DepartmentView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxy
 * @create 2023/6/12 15:54
 */
public class DepartmentService implements DepartmentDao {
    @Override
    public void insert(Department department) {
        String sql = "INSERT INTO department (department_name) VALUES (?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, department.getDepartment_name());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("插入失败，请重新尝试");
                DepartmentView.insertView();
            }else{
                System.out.println("插入数据成功!即将返回上一页");
                DepartmentView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department department) {
        String sql = "UPDATE department SET department_name = ? WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, department.getDepartment_name());
            ps.setInt(2, department.getId());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("更新失败，请重新尝试");
                DepartmentView.updateView();
            }else{
                System.out.println("更新数据成功!即将返回上一页");
                DepartmentView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Department department) {
        String sql = "DELETE FROM department WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, department.getId());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("删除失败，请重新尝试");
                DepartmentView.deleteView();
            }else{
                System.out.println("删除数据成功!即将返回上一页");
                DepartmentView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Department> selectAll() {
        List<Department> departmentList = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                int id = r.getInt("id");
                String departmentName = r.getString("department_name");
                Department department = new Department(id, departmentName);
                departmentList.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    @Override
    public List<Department> listByNameLike(String key) {
        List<Department> matchingDepartments = new ArrayList<>();
        String sql = "SELECT * FROM department WHERE department_name LIKE ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%");
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String departmentName = r.getString("department_name");
                Department department = new Department(id, departmentName);
                matchingDepartments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingDepartments;
    }
}
