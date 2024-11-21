package tweets.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import tweets.service.TweetsService;
import tweets.dto.TweetsDTO;

import java.util.List;



@Controller
@RequiredArgsConstructor
public class HomeController {
    private final TweetsService tweetsService;

    // 1. 게시글 목록 조회
    @GetMapping("/tweets/home")
    @ResponseBody
    //객체를 반환하여 API로 JSON형태를 넘겨줌
    public List<TweetsDTO> getTweets() {
        List<TweetsDTO> tweetsList = tweetsService.findAll();

        // DTO 객체가 제대로 반환되는지 확인하기 위한 로그
        if (tweetsList != null && !tweetsList.isEmpty()) {
            // tweetsList의 각 DTO 객체를 출력
            tweetsList.forEach(tweet -> System.out.println(tweet.toString()));  // DTO의 내용을 출력
        } else {
            System.out.println("No tweets found");
        }

        return tweetsList;  // 이 객체는 JSON으로 변환되어 반환됨
    }

    //@GetMapping("/home")
    //public String home() { return "home"; }


}




