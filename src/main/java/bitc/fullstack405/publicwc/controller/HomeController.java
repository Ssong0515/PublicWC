package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private ToiletService toiletService;

    /**
     * 도로명 주소를 받아 해당 주소의 화장실 목록을 반환합니다.
     * 세션에서 사용자 정보를 가져오지만, 현재는 사용되지 않습니다.
     */
    @GetMapping("/wcList")
    public List<WcInfo> getWcList(@RequestParam String address, HttpSession session) {
        Users user = (Users) session.getAttribute("users");
        return toiletService.searchToiletsByAddress(address);
    }
}
