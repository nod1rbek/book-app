package uz.abdukarimov.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.abdukarimov.dto.file.ResourceDto;

import java.util.UUID;

@Builder
@Getter
@Setter
public class Book {
    private String id;
    private String name;
    private Integer pageCount;
    private String author;
    private String path;
    private String imgPath;
    private ResourceDto resourceDto;

    public Book() {
        this.id = UUID.randomUUID().toString();
    }

    public Book(String id, String name, Integer pageCount, String author, String path, String imgPath) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.author = author;
        this.path = path;
        this.imgPath = imgPath;
    }

    public Book(String id, String name, Integer pageCount, String author, String path, String imgPath, ResourceDto resourceDto) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.author = author;
        this.path = path;
        this.imgPath = imgPath;
        this.resourceDto = resourceDto;
    }

    public String getIdAsString() {
        return this.id;
    }
}
