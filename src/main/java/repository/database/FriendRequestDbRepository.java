package repository.database;

import domain.FriendRequest;
import domain.Friendship;
import domain.User;
import repository.Repository;
import validators.Validator;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class FriendRequestDbRepository implements Repository<Long, FriendRequest> {
    private String url;
    private String username;
    private String password;

    public FriendRequestDbRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    @Override
    public FriendRequest findOne(Long along)
    {
        return null;
    }

    @Override
    public Iterable<FriendRequest> findAll() {
        Set<FriendRequest> friendrequests = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendrequests");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String u1_first_name = resultSet.getString("first_user_first_name");
                String u1_last_name = resultSet.getString("first_user_last_name");
                String u2_first_name = resultSet.getString("second_user_first_name");
                String u2_last_name = resultSet.getString("second_user_last_name");
                String status = resultSet.getString("status");
                User u1 = new User(u1_first_name, u1_last_name);
                User u2 = new User(u2_first_name, u2_last_name);
                FriendRequest fr = new FriendRequest(u1, u2,status);
                fr.setId(id);
                friendrequests.add(fr);
            }
            return friendrequests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendrequests;
    }

    @Override
    public FriendRequest save(FriendRequest entity) {

        String sql = "insert into friendrequests (first_user_first_name, first_user_last_name, second_user_first_name, second_user_last_name, status) values (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getUser1().getFirstName());
            ps.setString(2, entity.getUser1().getLastName());
            ps.setString(3, entity.getUser2().getFirstName());
            ps.setString(4, entity.getUser2().getLastName());
            ps.setString(5, entity.getStatus());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(FriendRequest entity) {
        String sql = "delete from friendrequests where id = ?";
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, entity.getId().intValue());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public FriendRequest update(FriendRequest friendrequest) {
        String sql = "update friendrequests set status=? where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setInt(2, friendrequest.getId().intValue());
            ps.setString(1, friendrequest.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
