package com.dev.repositories;

import com.dev.models.Monster;
import com.dev.utilis.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonsterRepo implements CrudRepository<Monster> {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Create
    public Monster add(Monster m) {

        try (Connection conn = cu.getConnection()){

            String sql = "insert into monster_card values (default, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getMonsterName());
            ps.setString(2,m.getMonsterRace());
            ps.setString(3,m.getMonsterType());

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                m.setId(rs.getInt("id"));
                return m;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    // Read
    public Monster getById(Integer id){

        try (Connection conn = cu.getConnection()){

            String sql = "select * from monster_card where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Monster m = new Monster();
                m.setId(rs.getInt("id"));
                m.setMonsterName(rs.getString("mon_name"));
                m.setMonsterRace(rs.getString("mon_race"));
                m.setMonsterType(rs.getString("mon_type"));
                return m;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Monster> getAll() {

        List<Monster> monsters = new ArrayList<>();

        try (Connection conn = cu.getConnection()){
            String sql = "select * from monster_card";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Monster m = new Monster(
                        rs.getInt("id"),
                        rs.getString("mon_name"),
                        rs.getString("mon_race"),
                        rs.getString("mon_type")
                );
                monsters.add(m);
            }

            return monsters;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    // Update
    public void update(Monster m){

        try (Connection conn = cu.getConnection()) {

            String sql = "update monster_card set mon_name = ?, mon_race = ?, mon_type = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getMonsterName());
            ps.setString(2, m.getMonsterRace());
            ps.setString(3, m.getMonsterType());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(Integer id) {

        try (Connection conn = cu.getConnection()) {

            String sql = "delete from monster_card where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
