package hello.hello_spring.domain;

import hello.hello_spring.domain.Book;
import hello.hello_spring.domain.BaseEntity;
import hello.hello_spring.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reading",
       uniqueConstraints = @UniqueConstraint(
           name = "uk_reading_user_book",
           columnNames = {"user_id", "book_id"}
       ),
       indexes = @Index(name = "idx_reading_user_id", columnList = "user_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reading extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReadingStatus status;

    private LocalDate startedAt;

    private LocalDate finishedAt;

    @Builder
    public Reading(User user, Book book, ReadingStatus status, LocalDate startedAt) {
        this.user = user;
        this.book = book;
        this.status = status;
        this.startedAt = startedAt;
    }

    public void updateStatus(ReadingStatus status) {
        this.status = status;
        if (status == ReadingStatus.COMPLETED && this.finishedAt == null) {
            this.finishedAt = LocalDate.now();
        }
    }
}
