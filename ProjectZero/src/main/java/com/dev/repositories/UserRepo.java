package com.dev.repositories;

import com.dev.models.User;
import com.dev.utilis.ConnectionUtil;
import com.dev.utilis.YourFoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepo implements CrudRepository<User>{

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    public User add(User user) {

        try (Connection conn = cu.getConnection()){

            String sql = "insert into users values (default, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.getAdmin());

            ps.execute();


        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public User createDeck(User user){

        try (Connection conn = cu.getConnection()){

            String sql = "insert into ? values (default, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.getAdmin());

            ps.execute();


        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    public User getByUsername(String username) throws YourFoolException {

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from users where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("admin")
                );
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // try-with-resources - automatically closes resources after execution
//        finally {
//            conn.close();
//        }

        throw new YourFoolException();

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Integer id) {

    }
}