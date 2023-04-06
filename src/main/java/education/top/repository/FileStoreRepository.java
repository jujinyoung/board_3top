package education.top.repository;

import education.top.domain.FileStore;

import java.util.List;

public interface FileStoreRepository {

    Long save(FileStore fileStore);

    List<FileStore> findAll();

    FileStore findById(Long id);
}
