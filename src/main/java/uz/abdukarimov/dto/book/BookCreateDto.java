package uz.abdukarimov.dto.book;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookCreateDto {
    private String name;
    private String author;
    private Integer pageCount;
}
