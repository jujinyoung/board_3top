package education.top.repository.mysql;

import education.top.domain.FileStore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MySQLFileStoreMapper {

    Long save(FileStore fileStore);

    List<FileStore> findAll();

    FileStore findById(Long id);
}
