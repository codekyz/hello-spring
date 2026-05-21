package hello.hello_spring.controller;

import hello.hello_spring.domain.user.UserService;
import hello.hello_spring.domain.user.UserResponse;
import hello.hello_spring.domain.user.SignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** POST /api/v1/auth/signup — 회원가입 */
    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody @Valid SignupRequest req) {
        return ResponseEntity.ok(userService.signup(req));
    }

    /** GET /api/v1/users — 회원 목록 조회 */
    @GetMapping("/api/v1/users")
    public ResponseEntity<List<UserResponse>> list() {
        return ResponseEntity.ok(userService.findAll());
    }
}
