package hello.hello_spring.domain;

import hello.hello_spring.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "email", "nickname"})
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(length = 500)
    private String profileImage;

    @Builder
    public User(String email, String password, String nickname, String profileImage) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public void updateProfile(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
