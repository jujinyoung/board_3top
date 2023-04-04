package education.top.repository;

import education.top.domain.Board;

import java.util.List;

public interface BoardRepository {

    List<Board> findAll(int begin, int end);

    Board findById(Long id);

    Board write(Board board);

    void delete(Long id);

    void deleteMulti(List<Long> ids);

    void update(Board board);

    void read(Long id);

    Integer getTotalRecords();

    Integer getTotalRecordsByWord(String searchCondition, String searchWord);

    List<Board> findAllByWord(int begin, int end, String searchCondition, String searchWord);
}
