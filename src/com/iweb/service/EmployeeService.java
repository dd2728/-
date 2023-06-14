package com.iweb.service;

import com.iweb.DAO.EmployeeDao;
import com.iweb.pojo.Employee;
import com.iweb.util.DBUtil;
import com.iweb.view.EmployeeView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxy
 * @create 2023/6/12 19:25
 */
public class EmployeeService implements EmployeeDao {
    @Override
    public void insert(Employee employee) {
        String sql = "INSERT INTO employee (name, id_number, phone_number, sex) VALUES (?, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getId_number());
            ps.setString(3, employee.getPhone_number());
            ps.setString(4, employee.getSex());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("插入失败，请重新尝试");
                EmployeeView.insertView();
            }else{
                System.out.println("插入数据成功!即将返回上一页");
                EmployeeView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employee SET name = ?, id_number = ?, phone_number = ?, sex = ? WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getId_number());
            ps.setString(3, employee.getPhone_number());
            ps.setString(4, employee.getSex());
            ps.setInt(5, employee.getId());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("更新失败，请重新尝试");
                EmployeeView.updateView();
            }else{
                System.out.println("更新数据成功!即将返回上一页");
                EmployeeView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employee employee) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, employee.getId());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("删除失败，请重新尝试");
                EmployeeView.deleteView();
            }else{
                System.out.println("删除数据成功!即将返回上一页");
                EmployeeView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> selectAll() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String idNumber = r.getString("id_number");
                String phoneNumber = r.getString("phone_number");
                String sex = r.getString("sex");
                Employee employee = new Employee(id, name, idNumber, phoneNumber, sex);
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    @Override
    public List<Employee> listByNameLike(String key) {
        List<Employee> matchingEmployees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE name LIKE ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%");
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String idNumber = r.getString("id_number");
                String phoneNumber = r.getString("phone_number");
                String sex = r.getString("sex");
                Employee employee = new Employee(id, name, idNumber, phoneNumber, sex);
                matchingEmployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchingEmployees;
    }

    @Override
    public List<Employee> listByIdNumberLike(String key) {
        List<Employee> matchingEmployees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE id_number LIKE ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%");
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String idNumber = r.getString("id_number");
                String phoneNumber = r.getString("phone_number");
                String sex = r.getString("sex");
                Employee employee = new Employee(id, name, idNumber, phoneNumber, sex);
                matchingEmployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingEmployees;
    }

    @Override
    public List<Employee> listByPhoneNumberLike(String key) {
        List<Employee> matchingEmployees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE phone_number LIKE ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%");
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String idNumber = r.getString("id_number");
                String phoneNumber = r.getString("phone_number");
                String sex = r.getString("sex");
                Employee employee = new Employee(id, name, idNumber, phoneNumber, sex);
                matchingEmployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingEmployees;
    }

    @Override
    public List<Employee> listBySex(String sex) {
        List<Employee> matchingEmployees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE sex = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, sex);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String idNumber = r.getString("id_number");
                String phoneNumber = r.getString("phone_number");
                String employeeSex = r.getString("sex");
                Employee employee = new Employee(id, name, idNumber, phoneNumber, employeeSex);
                matchingEmployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchingEmployees;
    }

    @Override
    public List<Employee> listByPosition(Integer positionId) {
        List<Employee> matchingEmployees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE eposition_id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, positionId);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String idNumber = r.getString("id_number");
                String phoneNumber = r.getString("phone_number");
                String sex = r.getString("sex");
                Employee employee = new Employee(id, name, idNumber, phoneNumber, sex);
                matchingEmployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingEmployees;
    }

    @Override
    public List<Employee> listByDepartment(Integer departmentId) {
        List<Employee> matchingEmployees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE department_id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String name = r.getString("name");
                String idNumber = r.getString("id_number");
                String phoneNumber = r.getString("phone_number");
                String sex = r.getString("sex");
                Employee employee = new Employee(id, name, idNumber, phoneNumber, sex);
                matchingEmployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingEmployees;
    }
}
