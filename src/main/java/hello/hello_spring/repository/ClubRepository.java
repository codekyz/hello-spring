package hello.hello_spring.repository;

import hello.hello_spring.domain.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClubRepository extends JpaRepository<Club, Long> {

    /** 공개 클럽 목록 */
    Page<Club> findByIsPublicTrue(Pageable pageable);

    /** 이름으로 검색 (공개 클럽만) */
    Page<Club> findByNameContainingAndIsPublicTrue(String name, Pageable pageable);

    /** 현재 멤버 수 조회 */
    @Query("SELECT COUNT(cm) FROM ClubMember cm WHERE cm.club.id = :clubId")
    long countMembersByClubId(@Param("clubId") Long clubId);
}
