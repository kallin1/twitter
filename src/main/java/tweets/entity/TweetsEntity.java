package tweets.entity;

import tweets.dto.TweetsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//@EntityListeners(AuditingEntityListener.class)




// DB 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter

@Table(schema = "twitter", name = "tweets")
public class TweetsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @UpdateTimestamp
    @Column(name = "dateTime", insertable = false)
    private LocalDateTime dateTime;  // 트윗 작성 시간

    @Column(name = "userID")
    private Long userId;             // 사용자 ID

    @Column(name = "userName")
    private String userName;         // 사용자 이름

    @Column(name = "userProfile")
    private String userProfile;      // 사용자 프로필

    @Column(name = "text")
    private String text;             // 트윗 내용

    @Column(name = "likeCount")
    private int likeCount;           // 좋아요 수

    @Column(name = "view")
    private int view;                // 조회 수

    @Column(name = "prevTweetID")
    private Long prevTweetId;        // 이전 트윗 ID (존재하는 경우)



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
        tweetsEntity.setView(0);  // 조회 수 초기값 설정

        return tweetsEntity;
    }
}
