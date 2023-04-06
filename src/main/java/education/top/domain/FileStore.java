package education.top.domain;

import lombok.Data;

@Data
public class FileStore {

    private Long id;
    private String uploadFileName;
    private String storeFileName;

    public FileStore(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
