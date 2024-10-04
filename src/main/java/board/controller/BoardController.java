package board.controller;

import board.dto.BoardDTO;
import board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor

//컨트롤러 : 클라이언트에서 보내는 http요청을 수신, Get/Post/Put/Delete등  사용해 처리
//request mapping 옆 주소에 api주소 넣으면 됨
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //게시글 작성
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }

    //게시글 목록
    @GetMapping("/")
    public String findAll(Model model){
        //DB에서 전체 게시글 데이터를 가져와서 save.html에 보여준다
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        
        return "list";
    }

}
