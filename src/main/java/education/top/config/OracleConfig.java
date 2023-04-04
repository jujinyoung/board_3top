package education.top.config;

import education.top.repository.BoardMapper;
import education.top.repository.BoardRepository;
import education.top.repository.ComCodeMapper;
import education.top.repository.OracleBoardRepository;
import education.top.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OracleConfig {

    private final BoardMapper boardMapper;
    private final ComCodeMapper comCodeMapper;

    @Bean
    public BoardService boardService(){
        return new BoardService(boardRepository(), comCodeMapper);
    }

    @Bean
    public BoardRepository boardRepository(){
        return new OracleBoardRepository(boardMapper);
    }
}
