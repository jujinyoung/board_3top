package education.top.config;

import education.top.repository.ComCodeRepository;
import education.top.repository.oracle.BoardMapper;
import education.top.repository.BoardRepository;
import education.top.repository.oracle.ComCodeMapper;
import education.top.repository.oracle.OracleBoardRepository;
import education.top.repository.oracle.OracleComCodeRepository;
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
        return new BoardService(boardRepository(), comCodeRepository());
    }

    @Bean
    public BoardRepository boardRepository(){
        return new OracleBoardRepository(boardMapper);
    }

    @Bean
    public ComCodeRepository comCodeRepository(){
        return new OracleComCodeRepository(comCodeMapper);
    }
}
