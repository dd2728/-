package com.iweb.DAO;

import com.iweb.pojo.EUser;

import java.util.List;

/**
 * @author zxy
 * @create 2023/6/12 13:38
 */
public interface EUserDao {

    void insert(EUser eUser);

    void update(EUser eUser);

    void delete(EUser eUser);

    List<EUser> selectAll();

    List<EUser> listByNameLike(String key);

    List<EUser> listByType(String selectStatus);
}
