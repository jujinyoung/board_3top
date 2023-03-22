package education.top.web;

import education.top.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax")
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/del")
    public String deleteMulti(@RequestParam(value="checkBoxArr[]") List<Long> checkBoxArr){
        boardService.deleteMulti(checkBoxArr);
        return "ok";
    }
}
