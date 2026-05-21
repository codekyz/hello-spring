package hello.hello_spring.domain;

import hello.hello_spring.domain.BaseEntity;
import hello.hello_spring.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clubs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false, length = 100)
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    private int maxMembers;

    @Column(nullable = false)
    private boolean isPublic;

    @Builder
    public Club(User owner, String name, String description,
                int maxMembers, boolean isPublic) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.maxMembers = maxMembers;
        this.isPublic = isPublic;
    }

    public void update(String name, String description, int maxMembers, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.maxMembers = maxMembers;
        this.isPublic = isPublic;
    }
}
