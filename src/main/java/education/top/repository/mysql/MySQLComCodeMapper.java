package education.top.repository.mysql;

import education.top.domain.ComCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MySQLComCodeMapper {

    List<ComCode> findALl();
}
