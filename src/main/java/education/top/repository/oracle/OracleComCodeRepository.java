package education.top.repository.oracle;

import education.top.domain.ComCode;
import education.top.repository.ComCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OracleComCodeRepository implements ComCodeRepository {

    private final ComCodeMapper comCodeMapper;

    @Override
    public List<ComCode> findALl() {
        return comCodeMapper.findALl();
    }
}
