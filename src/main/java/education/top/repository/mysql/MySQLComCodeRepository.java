package education.top.repository.mysql;

import education.top.domain.ComCode;
import education.top.repository.ComCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MySQLComCodeRepository implements ComCodeRepository {

    private final MySQLComCodeMapper comCodeMapper;
    @Override
    public List<ComCode> findALl() {
        return comCodeMapper.findALl();
    }
}
