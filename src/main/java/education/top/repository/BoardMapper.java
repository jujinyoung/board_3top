package education.top.repository;

import education.top.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {

    ArrayList<Board> findAll();

    Board findById(Long id);

    Integer write(String title, String writer, String content);

    void delete(Long id);

    void deleteMulti(List<Long> ids);

    void update(Long id, String title, String content);

    void read(Long id);
}
