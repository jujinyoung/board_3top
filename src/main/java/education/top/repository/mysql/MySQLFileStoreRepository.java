package education.top.repository.mysql;

import education.top.domain.FileStore;
import education.top.repository.FileStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MySQLFileStoreRepository implements FileStoreRepository {

    private final MySQLFileStoreMapper fileStoreMapper;

    @Override
    public Long save(FileStore fileStore) {
        return fileStoreMapper.save(fileStore);
    }

    @Override
    public List<FileStore> findAll() {
        return fileStoreMapper.findAll();
    }

    @Override
    public FileStore findById(Long id) {
        return fileStoreMapper.findById(id);
    }
}
