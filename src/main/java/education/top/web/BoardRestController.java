package education.top.web;

import education.top.domain.Board;
import education.top.service.BoardService;
import education.top.web.form.WriteForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/ajax")
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/del")
    public Map<String, Object> deleteMulti(@RequestParam(value="checkBoxArr[]") List<Long> checkBoxArr,
                                  @RequestParam(defaultValue = "1") int currentPage,
                                  @RequestParam(defaultValue = "5") int numberPerPage,
                                  @RequestParam(defaultValue = "01") String searchCondition,
                                  @RequestParam(defaultValue = "") String searchWord){

        boardService.deleteMulti(checkBoxArr);

        List<Board> boards;
        int totalRecords;
        if (searchWord.equals("")){
            boards = boardService.findAllBoard(currentPage, numberPerPage);
            totalRecords = boardService.getTotalRecords();
        }else {
            boards = boardService.getAllBoardByWord(currentPage, numberPerPage, searchCondition, searchWord);
            totalRecords = boardService.getTotalRecordsByWord(searchCondition, searchWord);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("boards", boards);
        map.put("totalRecords", totalRecords);
        return map;
    }

    @PostMapping("/write")
    public String write(@RequestBody @Validated WriteForm writeForm, HttpServletResponse response){
        log.debug(writeForm.toString());
        Board board = new Board(writeForm.getTitle(), writeForm.getWriter(), writeForm.getTitle());
        boardService.write(board);

        //작성자 저장
        if (writeForm.getWriteCheck()){
            log.debug("작성자 저장완료={}", writeForm.getWriter());
            createCookie(writeForm.getWriter(), response);
        }else{
            log.debug("작성자 쿠키만료={}", writeForm.getWriter());
            expiredCookie(response);
        }

        return "ok";
    }

    private void expiredCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("writer", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private void createCookie(String writer, HttpServletResponse response) {
        Cookie cookie = new Cookie("writer", writer);
        cookie.setMaxAge(7 * 24 * 60 * 60); //7일
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
