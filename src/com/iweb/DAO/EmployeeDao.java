package com.iweb.DAO;

/**
 * @author zxy
 * @create 2023/6/12 19:23
 */
import com.iweb.pojo.Employee;

import java.util.List;

public interface EmployeeDao {
    void insert(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);
    List<Employee> selectAll();
    List<Employee> listByNameLike(String key);
    List<Employee> listByIdNumberLike(String key);
    List<Employee> listByPhoneNumberLike(String key);
    List<Employee> listBySex(String sex);
    List<Employee> listByPosition(Integer positionId);
    List<Employee> listByDepartment(Integer departmentId);
}

