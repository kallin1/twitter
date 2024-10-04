package board.entity;

import board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//DB 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(schema ="twitter", name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id //pk컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increament
    private Long id;
    @Column(length = 20, nullable = false) //컬럼의 크기지정, not null
    //private String boardWriter;
    //@Column() //디폴트 크기 255, null가능
    private String boardContents;
    @Column
    private int boardHits;


    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
