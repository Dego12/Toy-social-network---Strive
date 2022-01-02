package repository.database;

import domain.Message;
import domain.Password;
import domain.User;
import repository.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PasswordDbRepository implements Repository<Long, Password> {
    private String url;
    private String username;
    private String password;

    public PasswordDbRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Password findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Password> findAll() {
        Set<Password> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from passwords");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("usr");
                String password = resultSet.getString("pass");
                String[] name = firstName.split(" ");
                User u = new User(name[0], name[1]);
                Password ur = new Password(u, password);
                ur.setId(id);
                users.add(ur);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Password save(Password entity) {

        String sql = "insert into passwords (usr, pass) values (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getUser().toString());
            ps.setString(2, entity.getPassword());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Password pass) {
        String sql = "delete from passwords where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pass.getId().intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Password update(Password user) {
        String sql = "update passwords set usr = ?, pass = ? where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setInt(3, user.getId().intValue());
            String usr = user.getUser().getFirstName() + " " + user.getUser().getLastName();
            ps.setString(1, usr);
            ps.setString(2, user.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
