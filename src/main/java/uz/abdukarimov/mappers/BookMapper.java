package uz.abdukarimov.mappers;

import org.springframework.stereotype.Component;
import uz.abdukarimov.dto.book.BookCreateDto;
import uz.abdukarimov.models.Book;

import java.util.UUID;

@Component
public class BookMapper {

    public Book toEntity(BookCreateDto dto) {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setPageCount(dto.getPageCount());
        return book;
    }
}
