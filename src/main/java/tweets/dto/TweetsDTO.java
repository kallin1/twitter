package tweets.dto;

import tweets.entity.TweetsEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//DTO(Data Transfer Object), VO, Bean
public class TweetsDTO {
    
    //게시글 저장
    private Long id;
    private String contents;
    private int view;
    private LocalDateTime tweetsCreatedTime;
    private LocalDateTime tweetsUpdatedTime;
    
    //게시글 목록
    public static TweetsDTO toTweetsDTO(TweetsEntity tweetsEntity){
        TweetsDTO tweetsDTO = new TweetsDTO();
        tweetsDTO.setId(tweetsEntity.getId());
        tweetsDTO.setContents(tweetsEntity.getContents());
        tweetsDTO.setView(tweetsEntity.getTweetsHits());

        tweetsDTO.setTweetsCreatedTime(tweetsEntity.getCreatedTime());
        tweetsDTO.setTweetsUpdatedTime(tweetsEntity.getUpdatedTime());
        return tweetsDTO;
    }
}
