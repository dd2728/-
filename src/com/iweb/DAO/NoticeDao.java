package com.iweb.DAO;

import com.iweb.pojo.Notice;

import java.util.List;

/**
 * @author zxy
 * @create 2023/6/12 19:24
 */
public interface NoticeDao {
    void insert(Notice notice);
    void update(Notice notice);
    void delete(Notice notice);
    List<Notice> selectAll();
    List<Notice> listByTitleLike(String key);
    List<Notice> listByContentLike(String key);
}
