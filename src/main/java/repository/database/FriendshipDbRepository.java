package repository.database;

import domain.Friendship;
import domain.User;
import repository.Repository;
import validators.Validator;

import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class FriendshipDbRepository implements Repository<Long, Friendship> {
    private String url;
    private String username;
    private String password;
    private Validator<Friendship> validator;

    public FriendshipDbRepository(String url, String username, String password, Validator<Friendship> validator)
    {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public Friendship findOne(Long along)
    {
        return null;
    }

    @Override
    public Iterable<Friendship> findAll() {
        Set<Friendship> friendships = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String u1_first_name = resultSet.getString("first_user_first_name");
                String u1_last_name = resultSet.getString("first_user_last_name");
                String u2_first_name = resultSet.getString("second_user_first_name");
                String u2_last_name = resultSet.getString("second_user_last_name");
                String string_date = resultSet.getString("date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
                LocalDate localDate = LocalDate.parse(string_date);
                User u1 = new User(u1_first_name, u1_last_name);
                User u2 = new User(u2_first_name, u2_last_name);
                Friendship fr = new Friendship(u1, u2);
                fr.setDate(localDate);
                fr.setId(id);
                friendships.add(fr);
            }
            return friendships;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public Friendship save(Friendship entity) {

        String sql = "insert into friendships (first_user_first_name, first_user_last_name, second_user_first_name, second_user_last_name, date) values (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getUser1().getFirstName());
            ps.setString(2, entity.getUser1().getLastName());
            ps.setString(3, entity.getUser2().getFirstName());
            ps.setString(4, entity.getUser2().getLastName());
            ps.setString(5, entity.getDate().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Friendship entity) {
        String sql = "delete from friendships where id = ?";
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
    public Friendship update(Friendship entity) {
        return null;
    }

}