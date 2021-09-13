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
import java.util.Locale;

public class CreateDeckRepo implements CrudRepository<CreateDeck>{

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    public CreateDeck add(CreateDeck createDeck) {
        return null;
    }

    public void addCardToDeck(String name_deck, Integer id){
        try (Connection conn = cu.getConnection()){

            String sql = "insert into " + name_deck + " select * from ygo_card where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addDeck(String username, String deckname){

        try (Connection conn = cu.getConnection()){

            String sql = "insert into " + username + " values (default, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, deckname);

            ps.execute();


        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void createDeck(String deckname){

        try (Connection conn = cu.getConnection()){

            String sql = "create table " + deckname + "(id serial, card_name VARCHAR not null, card_race VARCHAR not null, card_type VARCHAR not null);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public CreateDeck getById(Integer id) {
        return null;
    }

    @Override
    public List<CreateDeck> getAll() {
        return null;
    }

    public List<CreateDeck> getAll(String username) {

        List<CreateDeck> createDecks = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from " + username;

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                CreateDeck d = new CreateDeck(
                        rs.getInt("id"),
                        rs.getString("deck_name")
                );
                createDecks.add(d);
            }

            return createDecks;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(CreateDeck createDeck) {

    }

    @Override
    public void delete(Integer id) {

    }

    public void removeAllCards(String deckName){
        try (Connection conn = cu.getConnection()){

            String sql = "truncate " + deckName;
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void removeCardFromDeck(String deck, Integer id){
        try (Connection conn = cu.getConnection()){

            String sql = "delete from " + deck + " where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
