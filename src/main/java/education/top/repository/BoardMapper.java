package education.top.repository;

import education.top.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {

    ArrayList<Board> findAll(int begin, int end);

    Board findById(Long id);

    void write(Board board);

    void delete(Long id);

    void deleteMulti(List<Long> ids);

    void update(Board board);

    void read(Long id);

    Integer getTotalRecords();
}
