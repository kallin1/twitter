package tweets.service;

import jakarta.transaction.Transactional;
import tweets.dto.TweetsDTO;
import tweets.entity.TweetsEntity;
import tweets.repository.TweetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//DTO -> Entity, Entity -> DTO 변환작업이 일어남
//조회할땐 레파지토리로부터 Entity로 컨트롤러로 호출받을땐 DTO로 넘겨받음

@Service
@RequiredArgsConstructor
public class TweetsService {
    private final TweetsRepository tweetsRepository;

    //트윗 작성
    //DTO객체를 엔티티 객체로 옮겨담는다
    public void create(TweetsDTO tweetsDTO){
       TweetsEntity tweetsEntity = TweetsEntity.toSaveEntity(tweetsDTO);
        tweetsRepository.save(tweetsEntity);
    }

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

    @Transactional
    public void view(Long id) {
        TweetsRepository.view(id);
    }


    @Transactional
    public TweetsDTO findById(Long id) {
        Optional<TweetsEntity> optionalBoardEntity = tweetsRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            TweetsEntity tweetsEntity = optionalBoardEntity.get();
            TweetsDTO tweetsDTO = TweetsDTO.toTweetsDTO(tweetsEntity);
            return tweetsDTO;
        } else {
            return null;
        }
    }

    //게시글 삭제
    public void delete(Long id){
        tweetsRepository.deleteById(id);
    }
}
