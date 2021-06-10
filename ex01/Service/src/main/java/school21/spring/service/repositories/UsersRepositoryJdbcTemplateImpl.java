package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository<User> {

    private DataSource dataSource;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        return new User(rs.getLong("id"), rs.getString("email"));
    };

    @Override
    public Optional<User> findByEmail(String email) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        List<User> userList = jdbcTemplate.query("SELECT * FROM userss WHERE email = ?", userRowMapper, email);
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }

    @Override
    public User findById(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        List<User> userList = jdbcTemplate.query("SELECT * FROM userss WHERE id = ?", userRowMapper, id);
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        List<User> userList = jdbcTemplate.query("SELECT * FROM userss", userRowMapper);
        return userList;
    }

    @Override
    public void save(User entry) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.update("INSERT INTO userss (email) VALUES (?)", entry.getEmail());
    }

    @Override
    public void update(User entry) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.update("UPDATE userss SET email = ? WHERE id = ?", entry.getEmail(), entry.getId());
    }

    @Override
    public void delete(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.update("DELETE FROM userss WHERE id = ?", id);
    }
}
