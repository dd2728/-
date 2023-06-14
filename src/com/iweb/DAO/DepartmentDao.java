package com.iweb.DAO;

/**
 * @author zxy
 * @create 2023/6/12 15:47
 */
import com.iweb.pojo.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department department);
    void update(Department department);
    void delete(Department department);
    List<Department> selectAll();
    List<Department> listByNameLike(String key);
}

