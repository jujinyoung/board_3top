package education.top.repository.oracle;

import education.top.domain.Board;
import education.top.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OracleBoardRepository implements BoardRepository {

    private final BoardMapper boardMapper;

    @Override
    public List<Board> findAll(int begin, int end) {
        return boardMapper.findAll(begin, end);
    }

    @Override
    public Board findById(Long id) {
        return boardMapper.findById(id);
    }

    @Override
    public Board write(Board board) {
        boardMapper.write(board);
        return board;
    }

    @Override
    public void delete(Long id) {
        boardMapper.delete(id);
    }

    @Override
    public void deleteMulti(List<Long> ids) {
        boardMapper.deleteMulti(ids);
    }

    @Override
    public void update(Board board) {
        boardMapper.update(board);
    }

    @Override
    public void read(Long id) {
        boardMapper.read(id);
    }

    @Override
    public Integer getTotalRecords() {
        return boardMapper.getTotalRecords();
    }

    @Override
    public Integer getTotalRecordsByWord(String searchCondition, String searchWord) {
        return boardMapper.getTotalRecordsByWord(searchCondition, searchWord);
    }

    @Override
    public List<Board> findAllByWord(int begin, int end, String searchCondition, String searchWord) {
        return boardMapper.findAllByWord(begin, end, searchCondition, searchWord);
    }
}
