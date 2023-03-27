package education.top.web;

import education.top.com.paging.PageBlock;
import education.top.com.paging.PageService;
import education.top.domain.Board;
import education.top.service.BoardService;
import education.top.web.form.WriteForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board/v2")
public class BoardControllerV2 {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public String board(Model model, @RequestParam(defaultValue = "1") int currentPage,
                        @RequestParam(defaultValue = "5") int numberPerPage,
                        @RequestParam(defaultValue = "01") String searchCondition,
                        @RequestParam(defaultValue = "") String searchWord){

        //검색 여부
        List<Board> boards;
        int totalRecords;
        if (searchWord.equals("")){
            boards = boardService.findAllBoard(currentPage, numberPerPage);
            totalRecords = boardService.getTotalRecords();
        }else {
            boards = boardService.getAllBoardByWord(currentPage, numberPerPage, searchCondition, searchWord);
            totalRecords = boardService.getTotalRecordsByWord(searchCondition, searchWord);
        }

        //페이징 처리
        int numberOfPageBlock = 10;
        int totalPage = (int) Math.ceil((double) totalRecords/numberPerPage) == 0 ? 1 : (int) Math.ceil((double) totalRecords/numberPerPage);
        PageBlock pageBlock = PageService.pagingService(currentPage, numberPerPage, numberOfPageBlock, totalPage);
        int boardNo = totalRecords - (currentPage-1) * numberPerPage;

        model.addAttribute("boardNo", boardNo);
        model.addAttribute("searchCondition", searchCondition);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("comCodes", boardService.getComCodes());
        model.addAttribute("pageBlock", pageBlock);
        model.addAttribute("boards", boards);
        return "board/v2/list";
    }

    @GetMapping("/form")
    public String writeForm(Model model, @CookieValue(value = "writer", required = false) String writer){
        log.debug("쿠키에 저장된 작성자명={}", writer);
        WriteForm board = new WriteForm();
        if (writer != null){
            board.setWriteCheck(true);
            board.setWriter(writer);
            log.debug("쿠키에 저장된 작성자명 = {}", writer);
        }
        model.addAttribute("board", board);
        return "board/v2/form";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model, @RequestParam(defaultValue = "1") int currentPage){
        Board board = boardService.findBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("currentPage", currentPage);
        return "board/v2/view";
    }

    @PostMapping("/view/{id}")
    public String edit(@PathVariable Long id, @Validated @ModelAttribute Board board, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.debug("edit validation errors= {}", bindingResult);
            return "board/v2/view";
        }
        boardService.update(board);
        log.debug("수정 완료");
        return "board/v2/success";
    }
}
