package hello.hello_spring.domain.user;

import hello.hello_spring.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse {

    private final Long id;
    private final String email;
    private final String nickname;
    private final String profileImage;
    private final LocalDateTime createdAt;

    public UserResponse(User user) {
        this.id           = user.getId();
        this.email        = user.getEmail();
        this.nickname     = user.getNickname();
        this.profileImage = user.getProfileImage();
        this.createdAt    = user.getCreatedAt();
    }
}
