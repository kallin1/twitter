package tweets.entity;

import tweets.dto.TweetsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// DB 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(schema = "twitter", name = "tweets_table")
public class TweetsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;  // 트윗 작성 시간
    private Long userId;             // 사용자 ID
    private String userName;         // 사용자 이름
    private String userProfile;      // 사용자 프로필
    private String text;             // 트윗 내용
    private int likeCount;           // 좋아요 수
    private int view;                // 조회 수
    private Long prevTweetId;        // 이전 트윗 ID (존재하는 경우)

    private int tweetsHits;          // 조회 수 (트윗 조회수 추가)

    // TweetsDTO를 받아서 TweetsEntity로 변환하는 메서드
    public static TweetsEntity toSaveEntity(TweetsDTO tweetsDTO) {
        TweetsEntity tweetsEntity = new TweetsEntity();

        // TweetsDTO에서 각 필드를 가져와 TweetsEntity에 세팅
        tweetsEntity.setDateTime(tweetsDTO.getDateTime());
        tweetsEntity.setUserId(tweetsDTO.getUserId());
        tweetsEntity.setUserName(tweetsDTO.getUserName());
        tweetsEntity.setUserProfile(tweetsDTO.getUserProfile());
        tweetsEntity.setText(tweetsDTO.getText());
        tweetsEntity.setLikeCount(tweetsDTO.getLikeCount());
        tweetsEntity.setView(tweetsDTO.getView());
        tweetsEntity.setPrevTweetId(tweetsDTO.getPrevTweetId());

        // 기본 값 설정
        tweetsEntity.setTweetsHits(0);  // 조회 수 초기값 설정

        return tweetsEntity;
    }
}
