package education.top.service;

import education.top.com.paging.PageService;
import education.top.domain.Board;
import education.top.domain.ComCode;
import education.top.repository.BoardRepository;
import education.top.repository.ComCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    private final ComCodeRepository comCodeRepository;

    public List<Board> findAllBoard(int currentPage, int numberPerPage){
        int begin = PageService.begin(currentPage, numberPerPage);
        int end = PageService.end(currentPage, numberPerPage);
        return boardRepository.findAll(begin, end);
    }

    public Board findBoard(Long id){
        return boardRepository.findById(id);
    }

    public void write(Board board){
        boardRepository.write(board);
    }

    public void delete(Long id){
        boardRepository.delete(id);
    }

    public void deleteMulti(List<Long> ids){
        boardRepository.deleteMulti(ids);
    }

    public void update(Board board){
        boardRepository.update(board);
    }

    public void read(Long id){
        boardRepository.read(id);
    }

    public int getTotalRecords(){
        return boardRepository.getTotalRecords();
    }

    public int getTotalRecordsByWord(String searchCondition, String searchWord){
        return boardRepository.getTotalRecordsByWord(searchCondition, searchWord);
    }

    public List<ComCode> getComCodes(){
        return comCodeRepository.findALl();
    }

    public List<Board> getAllBoardByWord(int currentPage, int numberPerPage, String searchCondition, String searchWord){
        int begin = PageService.begin(currentPage, numberPerPage);
        int end = PageService.end(currentPage, numberPerPage);
        return boardRepository.findAllByWord(begin, end, searchCondition, searchWord);
    }
}
