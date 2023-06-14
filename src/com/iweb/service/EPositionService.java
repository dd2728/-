package com.iweb.service;

import com.iweb.DAO.EPositionDao;
import com.iweb.pojo.EPosition;
import com.iweb.util.DBUtil;
import com.iweb.view.EPositionView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxy
 * @create 2023/6/12 16:52
 */
public class EPositionService implements EPositionDao {
    @Override
    public void insert(EPosition position) {
        String sql = "INSERT INTO ePosition (position_name) VALUES (?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, position.getPosition_name());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("插入失败，请重新尝试");
                EPositionView.insertView();
            }else{
                System.out.println("插入数据成功!即将返回上一页");
                EPositionView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(EPosition position) {
        String sql = "UPDATE ePosition SET position_name = ? WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, position.getPosition_name());
            ps.setInt(2, position.getId());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("更新失败，请重新尝试");
                EPositionView.updateView();
            }else{
                System.out.println("更新数据成功!即将返回上一页");
                EPositionView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(EPosition position) {
        String sql = "DELETE FROM ePosition WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, position.getId());
            int num=ps.executeUpdate();
            if(num==0){
                System.out.println("删除失败，请重新尝试");
                EPositionView.deleteView();
            }else{
                System.out.println("删除数据成功!即将返回上一页");
                EPositionView.rootView();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EPosition> selectAll() {
        List<EPosition> positionList = new ArrayList<>();
        String sql = "SELECT * FROM ePosition";

        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {
            while (r.next()) {
                int id = r.getInt("id");
                String positionName = r.getString("position_name");
                EPosition position = new EPosition(id, positionName);
                positionList.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return positionList;
    }

    @Override
    public List<EPosition> listByNameLike(String key) {
        List<EPosition> matchingPositions = new ArrayList<>();
        String sql = "SELECT * FROM ePosition WHERE position_name LIKE ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%");
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                int id = r.getInt("id");
                String positionName = r.getString("position_name");
                EPosition position = new EPosition(id, positionName);
                matchingPositions.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingPositions;
    }
}
