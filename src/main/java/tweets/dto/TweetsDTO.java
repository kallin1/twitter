package tweets.dto;

import tweets.entity.TweetsEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

//데이터 전송 객체
public class TweetsDTO {

    // 필드 선언
    private Long id;               // 트윗 ID
    private LocalDateTime dateTime; // 트윗 작성 시간
    private Long userId;           // 사용자 ID
    private String userName;       // 사용자 이름
    private String userProfile;    // 사용자 프로필
    private String text;           // 트윗 내용
    private int likeCount;         // 좋아요 수
    private int view;              // 조회 수
    private Long prevTweetId;      // 이전 트윗 ID (존재하는 경우)
    
    //게시글 목록
    // Entity -> DTO 변환 메서드 예시
    public static TweetsDTO toTweetsDTO(TweetsEntity tweetsEntity) {
        TweetsDTO tweetsDTO = new TweetsDTO();
        tweetsDTO.setId(tweetsEntity.getId());
        tweetsDTO.setDateTime(tweetsEntity.getDateTime());
        tweetsDTO.setUserId(tweetsEntity.getUserId());
        tweetsDTO.setUserName(tweetsEntity.getUserName());
        tweetsDTO.setUserProfile(tweetsEntity.getUserProfile());
        tweetsDTO.setText(tweetsEntity.getText());
        tweetsDTO.setLikeCount(tweetsEntity.getLikeCount());
        tweetsDTO.setView(tweetsEntity.getView());
        tweetsDTO.setPrevTweetId(tweetsEntity.getPrevTweetId());
        return tweetsDTO;
    }
}
