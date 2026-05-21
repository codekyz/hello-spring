package hello.hello_spring.repository;

import hello.hello_spring.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {

    Page<Record> findByUser(User user, Pageable pageable);

    Optional<Record> findByIdAndUser(Long id, User user);

    /** 특정 도서의 평균 별점 */
    @Query("SELECT AVG(r.rating) FROM Record r WHERE r.book.id = :bookId")
    Optional<BigDecimal> findAverageRatingByBookId(@Param("bookId") Long bookId);

    /** N+1 방지: user + book 패치 조인 */
    @Query("SELECT r FROM Record r JOIN FETCH r.book WHERE r.user = :user")
    Page<Record> findByUserWithBook(@Param("user") User user, Pageable pageable);
}
