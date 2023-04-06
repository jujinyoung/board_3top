package education.top.service;

import education.top.domain.FileStore;
import education.top.file.FileStorage;
import education.top.repository.FileStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileStoreRepository fileStoreRepository;

    private final FileStorage fileStorage;

    public void save(MultipartFile file){

        try {
            FileStore fileStore = new FileStore(file.getOriginalFilename(), fileStorage.storeFile(file));
            fileStoreRepository.save(fileStore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FileStore> findAll(){
        return fileStoreRepository.findAll();
    }

    public FileStore findById(Long id){
        return fileStoreRepository.findById(id);
    }

    public String getFullPath(String storeFilename){
        return fileStorage.getFullPath(storeFilename);
    }
}
