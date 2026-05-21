package hello.hello_spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * 외부 API(카카오 책 검색 등)에서 조회한 도서 정보를 로컬 캐싱하는 엔티티.
 * 직접 등록 없이 ISBN을 키로 중복 저장을 방지합니다.
 */
@Entity
@Table(name = "books",
       indexes = @Index(name = "idx_books_isbn", columnList = "isbn"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "isbn", "title"})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 200)
    private String author;

    @Column(length = 100)
    private String publisher;

    @Column(length = 500)
    private String coverImage;

    @Lob
    private String description;

    private LocalDate publishedDate;

    @Builder
    public Book(String isbn, String title, String author,
                String publisher, String coverImage,
                String description, LocalDate publishedDate) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.coverImage = coverImage;
        this.description = description;
        this.publishedDate = publishedDate;
    }
}
