package education.top.config;

import education.top.file.FileStorage;
import education.top.repository.*;
import education.top.repository.mysql.*;
import education.top.service.BoardService;
import education.top.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MySQLConfig {

    private final MySQLBoardMapper boardMapper;
    private final MySQLComCodeMapper comCodeMapper;
    private final MySQLFileStoreMapper fileStoreMapper;
    private final FileStorage fileStorage;

    @Bean
    public FileService fileService(){
        return new FileService(fileStoreRepository(), fileStorage);
    }

    @Bean
    public BoardService boardService(){
        return new BoardService(boardRepository(), comCodeRepository());
    }

    @Bean
    public BoardRepository boardRepository(){
        return new MySQLBoardRepository(boardMapper);
    }

    @Bean
    public ComCodeRepository comCodeRepository(){
        return new MySQLComCodeRepository(comCodeMapper);
    }

    @Bean
    public FileStoreRepository fileStoreRepository(){
        return new MySQLFileStoreRepository(fileStoreMapper);
    }
}
