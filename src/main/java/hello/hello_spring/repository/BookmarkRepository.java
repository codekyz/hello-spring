package hello.hello_spring.repository;

import hello.hello_spring.domain.Bookmark;
import hello.hello_spring.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Page<Bookmark> findByUser(User user, Pageable pageable);

    Page<Bookmark> findByUserAndBookId(User user, Long bookId, Pageable pageable);

    Optional<Bookmark> findByIdAndUser(Long id, User user);
}
