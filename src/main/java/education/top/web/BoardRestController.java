package education.top.web;

import education.top.com.paging.PageBlock;
import education.top.com.paging.PageService;
import education.top.domain.Board;
import education.top.service.BoardService;
import education.top.web.form.WriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
@RequiredArgsConstructor
@RequestMapping("/ajax")
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("/del")
    public Map<String, Object> deleteMulti(@RequestParam(value="checkBoxArr[]") List<Long> checkBoxArr,
                                  @RequestParam(defaultValue = "1") int currentPage,
                                  @RequestParam(defaultValue = "5") int numberPerPage,
                                  @RequestParam(defaultValue = "01") String searchCondition,
                                  @RequestParam(defaultValue = "") String searchWord){
        log.debug("currentPage={}", currentPage);
        log.debug("numberPerPage={}", numberPerPage);
        log.debug("searchWord={}", searchWord);
        log.debug("searchCondition={}", searchCondition);

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

        //페이징 처리
        int numberOfPageBlock = 10;
        int totalPage = (int) Math.ceil((double) totalRecords/numberPerPage) == 0 ? 1 : (int) Math.ceil((double) totalRecords/numberPerPage);
        PageBlock pageBlock = PageService.pagingService(currentPage, numberPerPage, numberOfPageBlock, totalPage);

        Map<String, Object> map = new HashMap<>();
        map.put("boards", boards);
        map.put("totalRecords", totalRecords);
        map.put("pageBlock", pageBlock);
        map.put("searchCondition", searchCondition);
        map.put("searchWord", searchWord);
        return map;
    }

    @PostMapping("/write")
    public ResponseEntity write(@RequestBody @Validated WriteForm writeForm, HttpServletResponse response){
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

        return ResponseEntity.ok().body(writeForm);
    }

    @PostMapping("/view/{id}")
    public ResponseEntity edit(@PathVariable Long id, @Validated @RequestBody Board board){
        boardService.update(board);
        log.debug("수정 완료");
        return ResponseEntity.ok().body(board);
    }

    private void expiredCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("writer", null);
        cookie.setMaxAge(0);
        cookie.setPath("/board/v2");
        response.addCookie(cookie);
    }

    private void createCookie(String writer, HttpServletResponse response) {
        Cookie cookie = new Cookie("writer", writer);
        cookie.setMaxAge(7 * 24 * 60 * 60); //7일
        cookie.setPath("/board/v2");
        response.addCookie(cookie);
    }
}
