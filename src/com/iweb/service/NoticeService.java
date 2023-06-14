package com.iweb.service;

import com.iweb.DAO.NoticeDao;
import com.iweb.pojo.Notice;
import com.iweb.util.DBUtil;
import com.iweb.view.EmployeeView;
import com.iweb.view.NoticeView;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxy
 * @create 2023/6/12 19:25
 */
public class NoticeService implements NoticeDao {
    @Override
    public void insert(Notice notice) {
        String sql = "INSERT INTO notice (title, content) VALUES (?, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, notice.getTitle());
            ps.setString(2, notice.getContent());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("插入失败，请重新尝试");
                NoticeView.insertView();
            }else{
                System.out.println("插入数据成功!即将返回上一页");
                NoticeView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Notice notice) {
        String sql = "UPDATE notice SET title = ?, content = ? WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, notice.getTitle());
            ps.setString(2, notice.getContent());
            ps.setInt(3,notice.getId());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("更新失败，请重新尝试");
                NoticeView.updateView();
            }else{
                System.out.println("更新数据成功!即将返回上一页");
                NoticeView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Notice notice) {
        String sql="delete from notice where id=?";
        try(Connection c=DBUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,notice.getId());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("删除失败，请重新尝试");
                NoticeView.deleteView();
            }else{
                System.out.println("删除数据成功!即将返回上一页");
                NoticeView.rootView();
            }
        }catch (SQLException e){
            e.printStackTrace();}
    }

    @Override
    public List<Notice> selectAll() {
        List<Notice> noticeList=new ArrayList<>();
        String sql="select * from notice";
        try(Connection c=DBUtil.getConnection();
            Statement s=c.createStatement();
            ResultSet r=s.executeQuery(sql)){
            while(r.next()){
                int id=r.getInt(1);
                String title=r.getString(2);
                String content=r.getString(3);
                Notice notice=new Notice(id,title,content);
                noticeList.add(notice);
            }
        }catch (SQLException e){
            e.printStackTrace();}
        return noticeList;
    }

    @Override
    public List<Notice> listByTitleLike(String key) {
        List<Notice> noticeList=new ArrayList<>();
        String sql="select * from notice WHERE title LIKE ?";
        try(Connection c=DBUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,"%" + key + "%");
            ResultSet r=ps.executeQuery();
            while(r.next()){
                int id=r.getInt(1);
                String title=r.getString(2);
                String content=r.getString(3);
                Notice notice=new Notice(id,title,content);
                noticeList.add(notice);
            }
        }catch (SQLException e){
            e.printStackTrace();}
        return noticeList;
    }

    @Override
    public List<Notice> listByContentLike(String key) {
        List<Notice> noticeList=new ArrayList<>();
        String sql="select * from notice WHERE content LIKE ?";
        try(Connection c=DBUtil.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,"%" + key + "%");
            ResultSet r=ps.executeQuery();
            while(r.next()){
                int id=r.getInt(1);
                String title=r.getString(2);
                String content=r.getString(3);
                Notice notice=new Notice(id,title,content);
                noticeList.add(notice);
            }
        }catch (SQLException e){
            e.printStackTrace();}
        return noticeList;
    }
}
