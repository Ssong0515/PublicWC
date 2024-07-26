package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 로그인 페이지 요청 처리
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String userId,
                        @RequestParam String userPw,
                        Model model,
                        HttpSession session) {
        Optional<Users> userOptional = userService.findById(userId);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(userPw)) {
            // 로그인 성공
            session.setAttribute("userId", userOptional.get().getId());
            return "redirect:/users/mypage"; // 로그인 후 마이페이지로 이동
        } else {
            // 로그인 실패
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login";
        }
    }

    // 회원 가입 페이지 요청 처리
    @GetMapping("/signup")
    public String showSignupPage() {
        return "login/signUp";
    }

    // 회원 가입 폼 제출 처리
    @PostMapping("/signup")
    public String signup(@RequestParam String password,
                         @RequestParam String confirmPassword,
                         @RequestParam String email,
                         @RequestParam String gender,
                         @RequestParam String handicap,
                         Model model) {

        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        // 새로운 사용자 객체 생성
        Users newUser = new Users();
        newUser.setPassword(password); // 비밀번호는 암호화됨
        newUser.setEmail(email);
        newUser.setGender(gender);
        newUser.setHandicap(handicap);

        // 사용자 정보를 데이터베이스에 저장
        userService.saveUser(newUser);
        model.addAttribute("message", "회원 가입이 완료되었습니다. 로그인을 해주세요.");
        return "login";
    }

    // 마이페이지 요청 처리
    @GetMapping("/mypage")
    public String showMyPage(@SessionAttribute("userId") String userId, Model model) {
        Optional<Users> userOptional = userService.findById(userId);

        userOptional.ifPresentOrElse(
                user -> model.addAttribute("user", user),
                () -> model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.")
        );

        return "mypage";
    }

    // 마이페이지 정보 수정 처리
    @PostMapping("/mypage/update")
    public String updateUserInfo(@SessionAttribute("userId") String userId,
                                 @RequestParam String email,
                                 @RequestParam String gender,
                                 @RequestParam String handicap,
                                 Model model) {
        Optional<Users> userOptional = userService.findById(userId);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setEmail(email);
            user.setGender(gender);
            user.setHandicap(handicap);

            userService.saveUser(user);
            model.addAttribute("message", "정보가 성공적으로 수정되었습니다.");
        } else {
            model.addAttribute("errorMessage", "사용자 정보를 수정할 수 없습니다.");
        }

        return "mypage";
    }

    // 회원 탈퇴 처리
    @PostMapping("/mypage/delete")
    public String deleteUser(@SessionAttribute("userId") String userId, Model model) {
        Optional<Users> userOptional = userService.findById(userId);

        if (userOptional.isPresent()) {
            userService.deleteUser(userId);
            model.addAttribute("message", "회원 탈퇴가 완료되었습니다.");
        } else {
            model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
        }

        return "redirect:/"; // 탈퇴 후 메인 페이지로 리다이렉트
    }
}
