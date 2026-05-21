package hello.hello_spring.repository;

import hello.hello_spring.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    /** 제목 또는 저자명으로 검색 */
    Page<Book> findByTitleContainingOrAuthorContaining(
            String title, String author, Pageable pageable);
}
