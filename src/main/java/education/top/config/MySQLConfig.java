package education.top.config;

import education.top.repository.*;
import education.top.repository.mysql.MySQLBoardMapper;
import education.top.repository.mysql.MySQLBoardRepository;
import education.top.repository.mysql.MySQLComCodeMapper;
import education.top.repository.mysql.MySQLComCodeRepository;
import education.top.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MySQLConfig {

    private final MySQLBoardMapper boardMapper;
    private final MySQLComCodeMapper comCodeMapper;

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
}
