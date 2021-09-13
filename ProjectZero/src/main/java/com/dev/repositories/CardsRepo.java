package com.dev.repositories;

import com.dev.models.Cards;
import com.dev.utilis.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class CardsRepo implements CrudRepository<Cards> {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Create
    @Override
    public Cards add(Cards cards) {

        try (Connection conn = cu.getConnection()){

            String sql = "insert into ygo_card values (default, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cards.getCardName());
            ps.setString(2, cards.getCardRace());
            ps.setString(3, cards.getCardType());

            ps.execute();


        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    // Read
    @Override
    public Cards getById(Integer id) {

        try (Connection conn = cu.getConnection()){

            String sql = "select * from ygo_card where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Cards c = new Cards();
                c.setId(rs.getInt("id"));
                c.setCardName(rs.getString("card_name"));
                c.setCardRace(rs.getString("card_race"));
                c.setCardType(rs.getString("card_type"));
                return c;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Cards> getAll() {

        List<Cards> card = new ArrayList<>();

        try (Connection conn = cu.getConnection()){

            String sql = "select * from ygo_card";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Cards c = new Cards(
                        rs.getInt("id"),
                        rs.getString("card_name"),
                        rs.getString("card_race"),
                        rs.getString("card_type")
                );
                card.add(c);
            }

            return card;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    // Update
    @Override
    public void update(Cards cards) {

        try (Connection conn = cu.getConnection()){

            String sql = "update ygo_card set card_name = ?, card_race = ?, card_type = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cards.getCardName());
            ps.setString(2, cards.getCardRace());
            ps.setString(3, cards.getCardType());

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    // Delete
    @Override
    public void delete(Integer id) {

        try (Connection conn = cu.getConnection()){

            String sql = "delete from ygo_card where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
