package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository<User> {

    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = new User();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet res = statement.executeQuery("SELECT * FROM userss WHERE email=" + email + ";");

            if  (res.next()) {
                user = new User((long) res.getInt("id"), res.getString("email"));
            }

            connection.close();
            return Optional.of(user);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {

        ArrayList<User> users = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM userss;");

            while (res.next()) {
                users.add(new User(res.getLong("id"), res.getString("email")));
            }
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            user = new User();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet res = statement.executeQuery("SELECT * FROM userss WHERE id=" + id + ";");

            if  (res.next()) {
                user = new User(res.getLong("id"), res.getString("email"));
            }

            connection.close();
            return user;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            statement.execute("DELETE FROM userss WHERE id="+ id + ";");
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(String.format("UPDATE userss SET email=%s WHERE id=%d;", user.getEmail(), user.getId()));
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(String.format("INSERT INTO userss VALUES (%d, '%s');", user.getId(), user.getEmail()));
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}