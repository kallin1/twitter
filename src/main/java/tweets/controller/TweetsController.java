package tweets.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tweets.dto.TweetsDTO;
import tweets.service.TweetsService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor

//컨트롤러 : 클라이언트에서 보내는 http요청을 수신, Get/Post/Put/Delete등  사용해 처리
//request mapping 옆 주소에 api주소 넣으면 됨
@RequestMapping("/tweets")
public class TweetsController {

    private final TweetsService tweetsService;

    //게시글 작성
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping
    public String save(@ModelAttribute TweetsDTO tweetsDTO) throws IOException {
        System.out.println("boardDTO = " + tweetsDTO);
        tweetsService.save(tweetsDTO);
        return "redirect:/tweets/"; // 글 작성 후 목록 페이지로 리다이렉트
    }

    //게시글 목록
    @GetMapping("/")
    public String findAll(Model model){
        //DB에서 전체 게시글 데이터를 가져와서 save.html에 보여준다
        List<TweetsDTO> tweetsDTOList = tweetsService.findAll();
        model.addAttribute("tweetsList", tweetsDTOList);
        
        return "home";
    }

}
