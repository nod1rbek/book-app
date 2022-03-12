package uz.abdukarimov.dto.book;

import lombok.*;
import uz.abdukarimov.dto.file.ResourceDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    private String id;
    private String name;
    private String author;
    private Integer pageCount;
    private ResourceDto resourceDto;
}
