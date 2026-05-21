package hello.hello_spring.domain;

import hello.hello_spring.domain.User;
import hello.hello_spring.domain.Book;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookmarks",
       indexes = @Index(name = "idx_bookmarks_user_id", columnList = "user_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    /** 해당 페이지 번호 (선택) */
    private Integer page;

    /** 밑줄 친 문장 또는 메모 */
    @Lob
    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Bookmark(User user, Book book, Integer page, String content) {
        this.user = user;
        this.book = book;
        this.page = page;
        this.content = content;
    }
}
