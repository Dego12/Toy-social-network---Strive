package repository.database;

import domain.Message;
import domain.User;
import repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MessageDbRepository implements Repository<Long, Message> {
    private String url;
    private String username;
    private String password;

    public MessageDbRepository(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Message findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Message> findAll() {
        Set<Message> messages = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages");
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String first_name = resultSet.getString("from_first");
                String last_name = resultSet.getString("from_last");
                String to_users = resultSet.getString("to_user");
                String[] usrs = to_users.split(",");
                List<User> toUsers = new LinkedList<User>();
                for (String u: usrs)
                {
                    String[] us = u.split(" ");
                    User ur = new User(us[0], us[1]);
                    toUsers.add(ur);
                }
                String message = resultSet.getString("msg");
                String string_date = resultSet.getString("date");
                LocalDateTime localDateTime = LocalDateTime.parse(string_date);
                User from = new User(first_name, last_name);
                Message msg = new Message(from, toUsers, message, localDateTime);
                msg.setId(id);
                messages.add(msg);
            }
            return messages;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }


    @Override
    public Message save(Message entity) {

        String sql = "insert into messages (from_first, from_last, to_user, msg, date) values (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getFrom().getFirstName());
            ps.setString(2, entity.getFrom().getLastName());
            ps.setString(3, entity.toStringToUsers());
            ps.setString(4, entity.getMessage());
            ps.setString(5, entity.getData().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Message entity) {
        String sql = "delete from messages where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, entity.getId().intValue());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Message update(Message entity) { return null; }
}
