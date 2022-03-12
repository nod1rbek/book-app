package uz.abdukarimov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import uz.abdukarimov.dto.book.BookCreateDto;
import uz.abdukarimov.models.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("select id,name,author,pageCount,book_path, img_path from book.book", new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Book.builder()
                        .id(rs.getString("id"))
                        .name(rs.getString("name"))
                        .author(rs.getString("author"))
                        .pageCount(rs.getInt("pageCount"))
                        .path(rs.getString("book_path"))
                        .imgPath(rs.getString("img_path"))
                        .build();
            }
        });
    }

    public Optional<Book> get(String id) {
        return Optional.empty();
    }

    public String create(Book book) {
        jdbcTemplate.execute("insert into book.book(id,name,author,book_path,pageCount,img_path) values(?,?,?,?,?,?)",
                new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        ps.setObject(1, book.getId());
                        ps.setString(2, book.getName());
                        ps.setString(3, book.getAuthor());
                        ps.setString(4, book.getPath());
                        ps.setInt(5, book.getPageCount());
                        ps.setString(6, book.getImgPath());
                        return ps.execute();
                    }
                });
        return book.getIdAsString();
    }

    public boolean update(BookCreateDto updateDto, String id) {
        String str = "update book.book set name = ?, author=?, pageCount=? where id= ?";
        jdbcTemplate.update(str, updateDto.getName(), updateDto.getAuthor(), updateDto.getPageCount(), id);
        return true;
    }

    public void delete(String id) {
        String str = "delete from book.book where id=?";
        jdbcTemplate.update(str, id);
    }
}
