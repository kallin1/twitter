package tweets.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tweets.dto.TweetsDTO;
import tweets.service.TweetsService;
import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor

//컨트롤러 : 클라이언트에서 보내는 http요청을 수신, Get/Post/Put/Delete등  사용해 처리
//@RequestMapping("/tweets")
public class TweetsController {

    private final TweetsService tweetsService;

    //작성 페이지로 이동
    @GetMapping
    public String saveTweets(){
        return "/tweets";
    }



    //게시글 조회 1 올리고 상세 트윗 가져오기
    @GetMapping("/tweets/{tweetid}")
    public String findById(@PathVariable Long id, Model model){

        tweetsService.view(id);
        TweetsDTO tweetsDTO = tweetsService.findById(id);
        model.addAttribute("tweetsDto", tweetsDTO);

        return "/tweets/{tweetid}";
    }



    //트윗 삭제
    @GetMapping("/delete/{tweetid}")
    public String delete(@PathVariable Long id){
        tweetsService.delete(id);
        return "redirect:/home";
    }
    


    /*
    //게시글 작성
    @PostMapping
    public String PostTweets(TweetsDTO tweetsDTO) {
        TweetsService.create(tweetsDTO);
        return "/tweets";
    }
    */


}
