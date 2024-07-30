package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.service.ToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ToiletService toiletService;

    @GetMapping("/write")
    public String boardWrite(Model model) {
        return "board/boardWrite"; // boardWrite.html을 렌더링
    }

    @PostMapping("/write")
    public String submitPost(@RequestParam Map<String, String> allParams) {
        // 파라미터를 WcInfo 객체로 변환
        WcInfo wcInfo = new WcInfo();
        wcInfo.setName(allParams.get("title"));
        wcInfo.setComment(allParams.get("content"));
        wcInfo.setAddr1(allParams.get("addr1"));
        wcInfo.setAddr2(allParams.get("addr2"));
        wcInfo.setTime(allParams.get("time"));
        wcInfo.setLatitude(allParams.get("latitude"));
        wcInfo.setLongitude(allParams.get("longitude"));
        wcInfo.setCreateUserId(allParams.get("createUserId"));
        wcInfo.setWcpass(allParams.get("wcpass"));

        // 데이터 저장
        toiletService.addWcInfo(wcInfo);

        // 저장 완료 후 목록 페이지로 리다이렉트
        return "redirect:/board/list?message=게시물이%20성공적으로%20등록되었습니다.";
    }

    @GetMapping("/list")
    public String boardList(@RequestParam(value = "message", required = false) String message, Model model) {
        model.addAttribute("wcList", toiletService.getAllToilets());
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "board/boardList"; // boardList.html을 렌더링
    }
}
