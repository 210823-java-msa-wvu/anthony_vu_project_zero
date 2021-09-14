package com.dev.repositories;

import com.dev.models.CreateDeck;
import com.dev.models.Deck;
import com.dev.utilis.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterDeckRepo implements CrudRepository<Deck> {
    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    public Deck add(Deck deck) {

        try (Connection conn = cu.getConnection()){

            String sql = "insert into user_deck select * from ygo_deck where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, deck.getId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                deck.setId(rs.getInt("id"));
                return deck;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void addCard(Integer id){
        try (Connection conn = cu.getConnection()){

            String sql = "insert into user_deck select * from ygo_card where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Deck getById(Integer id) {
        return null;
    }

    @Override
    public List<Deck> getAll() {

        List<Deck> deck = new ArrayList<>();

        try (Connection conn = cu.getConnection()){

            String sql = "select * from user_deck";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Deck d = new Deck(
                        rs.getInt("id"),
                        rs.getString("card_name"),
                        rs.getString("card_race"),
                        rs.getString("card_type")
                );
                deck.add(d);
            }

            return deck;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Deck deck) {

    }

    // Delete
    public void removeCard(Integer id){

        try (Connection conn = cu.getConnection()){

            String sql = "delete from user_deck where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void dropDeck(String deckName){

        try (Connection conn = cu.getConnection()){

            String sql = "drop table " + deckName;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeAll(){
        try (Connection conn = cu.getConnection()){

            String sql = "truncate user_deck";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

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

    public void deleteDeck(String username, String deckName) {

        try (Connection conn = cu.getConnection()){

            String sql = "delete from " + username + " where deck_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, deckName);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
