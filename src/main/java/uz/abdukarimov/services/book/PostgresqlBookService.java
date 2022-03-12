package uz.abdukarimov.services.book;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.abdukarimov.dao.BookDao;
import uz.abdukarimov.dto.book.BookCreateDto;
import uz.abdukarimov.dto.file.ResourceDto;
import uz.abdukarimov.mappers.BookMapper;
import uz.abdukarimov.models.Book;
import uz.abdukarimov.services.file.FileStorageService;

import java.util.List;
import java.util.Optional;


@Service
public class PostgresqlBookService implements BookService {

    private final BookMapper mapper;

    private final BookDao bookDao;

    private final FileStorageService fileStorageService;

    @Autowired
    public PostgresqlBookService(BookMapper mapper, BookDao bookDao, FileStorageService fileStorageService) {
        this.mapper = mapper;
        this.bookDao = bookDao;
        this.fileStorageService = fileStorageService;
    }


    @Override
    public Book get(String id) {
        Optional<Book> bookOptional = bookDao.getAll().stream().filter(book -> book.getId().equals(id)).findFirst();
        return bookOptional.isEmpty() ? null : bookOptional.get();
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @SneakyThrows
    @Override
    public String create(BookCreateDto dto, MultipartFile file, MultipartFile img) {
        ResourceDto resourceDto = fileStorageService.store(file);
        String storeImg = fileStorageService.storeImg(img);
        Book book = mapper.toEntity(dto);
        book.setPath(resourceDto.getPath());
        book.setImgPath(storeImg);
        book.setResourceDto(resourceDto);
        return bookDao.create(book);
    }

    @Override
    public void delete(String id) {
        bookDao.delete(id);
    }

    @Override
    public boolean update(BookCreateDto updateDto, String id) {
        return bookDao.update(updateDto, id);
    }
}
