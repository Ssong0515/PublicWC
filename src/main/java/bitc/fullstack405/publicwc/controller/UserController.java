package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@SessionAttributes("users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 회원 가입 페이지를 보여줍니다.
     */
    @GetMapping("/signup")
    public String showSignupPage() {
        return "login/signUp";
    }

    /**
     * 회원 가입 폼 제출을 처리합니다.
     * - 아이디 중복 체크
     * - 비밀번호 확인
     * - 사용자 정보를 데이터베이스에 저장
     */
    @PostMapping("/signup")
    public String signup(@RequestParam String userId,
                         @RequestParam String password,
                         @RequestParam String confirmPassword,
                         @RequestParam String email,
                         @RequestParam String gender,
                         @RequestParam String handicap,
                         Model model) {

        if (userService.findById(userId).isPresent()) {
            model.addAttribute("error", "이미 존재하는 아이디입니다.");
            return "login/signUp";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "login/signUp";
        }

        Users newUser = new Users();
        newUser.setId(userId);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setGender(gender);
        newUser.setHandicap(handicap);

        userService.saveUser(newUser);
        model.addAttribute("message", "회원 가입이 완료되었습니다. 로그인을 해주세요.");
        return "login/login";
    }

    /**
     * 마이페이지를 요청하고, 세션에 저장된 사용자 정보를 모델에 추가합니다.
     */
    @GetMapping("/mypage")
    public String showMyPage(@SessionAttribute("users") Users users, Model model) {
        Optional<Users> userOptional = userService.findById(users.getId());

        userOptional.ifPresentOrElse(
                user -> model.addAttribute("user", user),
                () -> model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.")
        );

        return "mypage";
    }

    /**
     * 마이페이지에서 사용자 정보를 수정합니다.
     */
    @PostMapping("/mypage/update")
    public String updateUserInfo(@SessionAttribute("users") Users users,
                                 @RequestParam String email,
                                 @RequestParam String gender,
                                 @RequestParam String handicap,
                                 Model model) {

        Optional<Users> userOptional = userService.findById(users.getId());

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

    /**
     * 회원 탈퇴 요청을 처리합니다.
     * - 사용자 정보를 찾은 후 삭제
     */
    @PostMapping("/mypage/delete")
    public String deleteUser(@SessionAttribute("users") Users users, Model model) {
        Optional<Users> userOptional = userService.findById(users.getId());

        if (userOptional.isPresent()) {
            userService.deleteUser(users.getId());
            model.addAttribute("message", "회원 탈퇴가 완료되었습니다.");
        } else {
            model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
        }

        return "redirect:/";
    }
}
