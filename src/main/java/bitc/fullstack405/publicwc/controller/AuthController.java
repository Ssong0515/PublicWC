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
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        Optional<Users> userOptional = userService.findById(username);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            session.setAttribute("userId", userOptional.get().getId());
            return "redirect:/users/mypage";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login/login";
        }
    }

    @GetMapping("/signup")
    public String showSignupPage() {
        return "login/signUp";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String password2,
                         @RequestParam String userEmail,
                         @RequestParam String gender,
                         @RequestParam String handicap,
                         Model model) {

        if (!password.equals(password2)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        if (userService.findById(username).isPresent()) {
            model.addAttribute("error", "이미 사용 중인 ID입니다.");
            return "signup";
        }

        Users newUser = new Users();
        newUser.setId(username);
        newUser.setPassword(password);
        newUser.setEmail(userEmail);
        newUser.setGender(gender);
        newUser.setHandicap(handicap);

        userService.saveUser(newUser);
        return "redirect:/auth/login"; // 로그인 페이지로 리다이렉트
    }
}