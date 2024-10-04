package tweets.entity;

import tweets.dto.TweetsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//DB 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(schema ="twitter", name = "tweets_table")
public class TweetsEntity extends BaseEntity{
    @Id //pk컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increament
    private Long id;
    @Column(length = 20, nullable = false) //컬럼의 크기지정, not null
    //private String boardWriter;
    //@Column() //디폴트 크기 255, null가능
    private String contents;
    @Column
    private int tweetsHits;


    public static TweetsEntity toSaveEntity(TweetsDTO tweetsDTO){
        TweetsEntity tweetsEntity = new TweetsEntity();
        tweetsEntity.setContents(tweetsDTO.getContents());
        tweetsEntity.setTweetsHits(0);
        return tweetsEntity;
    }
}
