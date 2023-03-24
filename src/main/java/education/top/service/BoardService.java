package education.top.service;

import education.top.com.paging.PageService;
import education.top.domain.Board;
import education.top.domain.ComCode;
import education.top.repository.BoardMapper;
import education.top.repository.ComCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private ComCodeMapper comCodeMapper;

    public List<Board> findAllBoard(int currentPage, int numberPerPage){
        int begin = PageService.begin(currentPage, numberPerPage);
        int end = PageService.end(currentPage, numberPerPage);
        return boardMapper.findAll(begin, end);
    }

    public Board findBoard(Long id){
        return boardMapper.findById(id);
    }

    public void write(Board board){
        boardMapper.write(board);
    }

    public void delete(Long id){
        boardMapper.delete(id);
    }

    public void deleteMulti(List<Long> ids){
        boardMapper.deleteMulti(ids);
    }

    public void update(Board board){
        boardMapper.update(board);
    }

    public void read(Long id){
        boardMapper.read(id);
    }

    public int getTotalRecords(){
        return boardMapper.getTotalRecords();
    }

    public int getTotalRecordsByWord(String searchCondition, String searchWord){
        return boardMapper.getTotalRecordsByWord(searchCondition, searchWord);
    }

    public List<ComCode> getComCodes(){
        return comCodeMapper.findALl();
    }

    public List<Board> getAllBoardByWord(int currentPage, int numberPerPage, String searchCondition, String searchWord){
        int begin = PageService.begin(currentPage, numberPerPage);
        int end = PageService.end(currentPage, numberPerPage);
        return boardMapper.findAllByWord(begin, end, searchCondition, searchWord);
    }
}
