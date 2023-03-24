package education.top.web;

import education.top.domain.Board;
import education.top.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/del")
    public List<Board> deleteMulti(@RequestParam(value="checkBoxArr[]") List<Long> checkBoxArr,
                                   @RequestParam(defaultValue = "1") int currentPage,
                                   @RequestParam(defaultValue = "5") int numberPerPage,
                                   @RequestParam(defaultValue = "01") String searchCondition,
                                   @RequestParam(defaultValue = "") String searchWord){

        boardService.deleteMulti(checkBoxArr);

        List<Board> boards = null;
        if (searchWord.equals("")){
            boards = boardService.findAllBoard(currentPage, numberPerPage);
        }else {
            boards = boardService.getAllBoardByWord(currentPage, numberPerPage, searchCondition, searchWord);
        }
        return boards;
    }
}
