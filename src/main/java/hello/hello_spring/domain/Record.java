package hello.hello_spring.domain;

import hello.hello_spring.domain.BaseEntity;
import hello.hello_spring.domain.Book;
import hello.hello_spring.domain.Reading;
import hello.hello_spring.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "records",
       indexes = {
           @Index(name = "idx_records_user_id", columnList = "user_id"),
           @Index(name = "idx_records_book_id", columnList = "book_id")
       })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    /** 독서 현황과의 선택적 연관. 기록 삭제 시 null 처리 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reading_id")
    private Reading reading;

    /** 0.5 단위 별점 (0.5 ~ 5.0) */
    @Column(nullable = false, precision = 2, scale = 1)
    private BigDecimal rating;

    @Lob
    @Column(nullable = false)
    private String content;

    @Builder
    public Record(User user, Book book, Reading reading,
                  BigDecimal rating, String content) {
        validateRating(rating);
        this.user = user;
        this.book = book;
        this.reading = reading;
        this.rating = rating;
        this.content = content;
    }

    public void update(BigDecimal rating, String content) {
        validateRating(rating);
        this.rating = rating;
        this.content = content;
    }

    private void validateRating(BigDecimal rating) {
        if (rating == null
                || rating.compareTo(BigDecimal.valueOf(0.5)) < 0
                || rating.compareTo(BigDecimal.valueOf(5.0)) > 0) {
            throw new IllegalArgumentException("별점은 0.5 ~ 5.0 사이여야 합니다.");
        }
    }
}
