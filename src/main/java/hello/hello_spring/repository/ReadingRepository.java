package hello.hello_spring.repository;

import hello.hello_spring.domain.Reading;
import hello.hello_spring.domain.ReadingStatus;
import hello.hello_spring.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

    Page<Reading> findByUser(User user, Pageable pageable);

    Optional<Reading> findByIdAndUser(Long id, User user);

    Optional<Reading> findByUserIdAndBookId(Long userId, Long bookId);

    boolean existsByUserIdAndBookId(Long userId, Long bookId);

    /** 사용자의 상태별 독서 현황 조회 */
    @Query("SELECT r FROM Reading r JOIN FETCH r.book WHERE r.user = :user AND r.status = :status")
    Page<Reading> findByUserAndStatus(
            @Param("user") User user,
            @Param("status") ReadingStatus status,
            Pageable pageable);
}
