package education.top.repository.mysql;

import education.top.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MySQLBoardMapper {

    List<Board> findAll(int begin, int end);

    Board findById(Long id);

    void write(Board board);

    void delete(Long id);

    void deleteMulti(List<Long> ids);

    void update(Board board);

    void read(Long id);

    Integer getTotalRecords();

    Integer getTotalRecordsByWord(String searchCondition, String searchWord);

    List<Board> findAllByWord(int begin, int end, String searchCondition, String searchWord);
}
