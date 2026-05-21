package hello.hello_spring.domain;

import hello.hello_spring.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "club_members",
       uniqueConstraints = @UniqueConstraint(
           name = "uk_club_members_club_user",
           columnNames = {"club_id", "user_id"}
       ))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ClubMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClubRole role;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    @Builder
    public ClubMember(Club club, User user, ClubRole role) {
        this.club = club;
        this.user = user;
        this.role = role;
    }
}
