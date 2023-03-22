package education.top.web;

import education.top.domain.Board;
import education.top.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public String board(Model model){
        ArrayList<Board> boards = boardService.findAllBoard();
        log.debug("게시글 개수 = {}", boards.size());
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String writeForm(Model model){
        model.addAttribute("board", new Board());
        return "board/form";
    }

    @PostMapping("/form")
    public String write(@Validated @ModelAttribute Board board, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.debug("write validation errors= {}", bindingResult);
            return "board/form";
        }
        log.debug(board.getTitle());
        boardService.write(board.getTitle(), board.getWriter(), board.getContent());
        return "redirect:/board";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model){
        boardService.read(id);
        Board board = boardService.findBoard(id);
        model.addAttribute("board", board);
        return "board/view";
    }

    @PostMapping("/view/{id}")
    public String delete(@PathVariable Long id){
        log.debug("삭제 완료");
        boardService.delete(id);
        return "redirect:/board";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        log.debug("수정Form 이동");
        Board board = boardService.findBoard(id);
        model.addAttribute("board", board);
        return "board/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @Validated @ModelAttribute Board board, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.debug("edit validation errors= {}", bindingResult);
            return "board/edit";
        }
        log.debug("수정 완료");
        boardService.update(board);
        return "redirect:/board/view/{id}";
    }
}
