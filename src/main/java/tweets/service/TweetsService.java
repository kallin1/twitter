package tweets.service;

import tweets.dto.TweetsDTO;
import tweets.entity.TweetsEntity;
import tweets.repository.TweetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//DTO -> Entity
//Entity -> DTO
//같은 변환작업이 일어남, 조회할땐 레파지토리로부터 Entity로 컨트롤러로 호출받을땐 DTO로 넘겨받음

@Service
@RequiredArgsConstructor
public class TweetsService {
    private final TweetsRepository tweetsRepository;

    //게시글 작성(저장)
    //DTO객체를 엔티티 객체로 옮겨담는다
    /*

    public void create(TweetsDTO tweetsDTO){
       TweetsEntity tweetsEntity = TweetsEntity.toSaveEntity(tweetsDTO);
        tweetsRepository.save(tweetsEntity);
    }
     */


    //게시글 목록
    public List<TweetsDTO> findAll() {
        List<TweetsEntity> tweetsEntityList = tweetsRepository.findAll();
        //이 엔티티로 넘어온 객체를 DTO 객체로 옮겨담아서 다시 컨트롤러로 리턴해야함
        //Array 객체 선언
        List<TweetsDTO> tweetsDTOList = new ArrayList<>();

        //엔티티 객체
        for (TweetsEntity tweetsEntity : tweetsEntityList) {
            tweetsDTOList.add(TweetsDTO.toTweetsDTO(tweetsEntity));
        }
        return tweetsDTOList;
    }
}
