package com.dev.repositories;

import com.dev.models.Spells;
import com.dev.utilis.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpellTrapRepo implements CrudRepository<Spells> {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Create
    public Spells add(Spells s) {

        try (Connection conn = cu.getConnection()){

            String sql = "insert into mt_card values (default, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getSpellName());
            ps.setString(2, s.getSpellType());
            ps.setString(3, s.getSpellRace());

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                s.setId(rs.getInt("id"));
                return s;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    // Read
    public Spells getById(Integer id){

        try (Connection conn = cu.getConnection()){

            String sql = "select * from mt_card where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Spells s = new Spells();
                s.setId(rs.getInt("id"));
                s.setSpellName(rs.getString("mt_name"));
                s.setSpellType(rs.getString("mt_type"));
                s.setSpellRace(rs.getString("mt_race"));
                return s;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Spells> getAll() {

        List<Spells> spell = new ArrayList<>();

        try (Connection conn = cu.getConnection()){
            String sql = "select * from mt_card";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Spells s = new Spells(
                        rs.getInt("id"),
                        rs.getString("mt_name"),
                        rs.getString("mt_type"),
                        rs.getString("mt_race")
                );
                spell.add(s);
            }

            return spell;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    // Update
    public void update(Spells s){

        try (Connection conn = cu.getConnection()) {

            String sql = "update monster_card set mt_name = ?, mt_type = ?, mt_name = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getSpellName());
            ps.setString(2, s.getSpellType());
            ps.setString(3, s.getSpellRace());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(Integer id) {

        try (Connection conn = cu.getConnection()) {

            String sql = "delete from mt_card where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
