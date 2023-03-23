package education.top.service;

import education.top.com.paging.PageService;
import education.top.domain.Board;
import education.top.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public ArrayList<Board> findAllBoard(int currentPage, int numberPerPage){
        int begin = PageService.begin(currentPage, numberPerPage);
        int end = PageService.end(currentPage, numberPerPage);
        return boardMapper.findAll(begin, end);
    }

    public Board findBoard(Long id){
        return boardMapper.findById(id);
    }

    public void write(String title, String writer, String content){
        boardMapper.write(title, writer, content);
    }

    public void delete(Long id){
        boardMapper.delete(id);
    }

    public void deleteMulti(List<Long> ids){
        boardMapper.deleteMulti(ids);
    }

    public void update(Board board){
        boardMapper.update(board.getId(), board.getTitle(), board.getContent());
    }

    public void read(Long id){
        boardMapper.read(id);
    }

    public int getTotalRecords(){
        return boardMapper.getTotalRecords();
    }
}
