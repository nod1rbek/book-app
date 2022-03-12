package uz.abdukarimov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.abdukarimov.models.AuthLogin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LoginDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AuthLogin> getAll() {
        return jdbcTemplate.query("select id,username,password,role from book.user", new UserRowMapper());
    }

    public static class UserRowMapper implements RowMapper<AuthLogin> {
        public AuthLogin mapRow(ResultSet rs, int rowNum) throws SQLException {
            AuthLogin login=AuthLogin.builder()
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .role(rs.getString("role"))
                    .build();
            return login;
        }
    }
}
