package com.iweb.DAO;

import com.iweb.pojo.EPosition;

import java.util.List;

/**
 * @author zxy
 * @create 2023/6/12 16:53
 */
public interface EPositionDao {
    void insert(EPosition position);
    void update(EPosition position);
    void delete(EPosition position);
    List<EPosition> selectAll();
    List<EPosition> listByNameLike(String key);
}
