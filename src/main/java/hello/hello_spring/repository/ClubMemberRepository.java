package hello.hello_spring.repository;

import hello.hello_spring.domain.ClubMember;
import hello.hello_spring.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {

    Page<ClubMember> findByClubId(Long clubId, Pageable pageable);

    Optional<ClubMember> findByClubIdAndUser(Long clubId, User user);

    boolean existsByClubIdAndUser(Long clubId, User user);

    /** 사용자가 가입한 클럽 목록 */
    @Query("SELECT cm FROM ClubMember cm JOIN FETCH cm.club WHERE cm.user = :user")
    Page<ClubMember> findByUserWithClub(@Param("user") User user, Pageable pageable);
}
